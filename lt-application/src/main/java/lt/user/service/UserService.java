package lt.user.service;


import javax.servlet.http.HttpServletRequest;

import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.Md5FileUtil;
import gzlazypack.common.util.PropertiesUtil;
import gzlazypack.common.util.PwdUtil;
import gzlazypack.common.util.ResultJSON;
import lt.admin.service.AuthAccountService;
import lt.base.command.ValidateSMSCommand;
import lt.base.entity.SMSValidateSaga;
import lt.base.entity.Seqs;
import lt.base.exception.SagaException;
import lt.base.qo.SeqsQO;
import lt.base.service.SMSValidateSagaService;
import lt.base.service.SeqsService;
import lt.marketing.command.CreateMarketingEntryCommand;
import lt.marketing.command.CreateMarketingSubjectCommand;
import lt.marketing.entity.MarketingEntry;
import lt.marketing.qo.MarketingEntryQO;
import lt.marketing.service.MarketingEntryService;
import lt.marketing.service.MarketingSubjectService;
import lt.user.command.ChangeUserCommand;
import lt.user.command.CreateUserCommand;
import lt.user.command.ModifyUserCommand;
import lt.user.command.UpdatePasswordCommand;
import lt.user.command.UserRegisterCommand;
import lt.user.converter.UserConverter;
import lt.user.dto.UserDTO;
import lt.user.entity.User;
import lt.user.qo.UserQO;
import lt.utils.QRCodeUtil;
import lt.utils.SessionUtil;
import lt.virtualAccount.command.CreateVirtualAccountCommand;
import lt.virtualAccount.entity.VirtualAccount;
import lt.virtualAccount.exception.VirtualAccountException;
import lt.virtualAccount.service.VirtualAccountService;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService extends BaseDao<User, UserQO>{

	@Autowired
	private SMSValidateSagaService smsValidateSagaServiceImpl;
	
	@Autowired
	private SeqsService seqsService;
	
	@Autowired
	private MarketingEntryService  marketingEntryService;
	
	@Autowired
	private MarketingSubjectService marketingSubjectService;
	
	@Autowired
	private AuthAccountService authAccountService;
	@Autowired
	private VirtualAccountService virtualAccountService;
	
	@Transactional(rollbackFor = SagaException.class)
	public String registerUser(UserRegisterCommand command,HttpServletRequest request) throws UserException,
	SagaException{
		UserDTO userDTO = null;
		// 检查验证码 
		//   byte 0 : 验证通过   1 ：  流程不存在， 2 ：验证不通过 ， 3 ： 验证码错误， 4 ：手机号与验证码不符
			byte b = checkSMSSaga(command.getSagaId(), command.getSmsValidCode(),command.getMobile());
			switch (b) {
			case 0:
			//	验证通过
			//	 跳出循环
				break;
			case 1:
				return ResultJSON.resultToJSONStr(false, "验证码与当前手机不符");
			//	break;
			case 2:
				return ResultJSON.resultToJSONStr(false, "验证不通过");
			//	break;
			case 3:
				return ResultJSON.resultToJSONStr(false, "验证码错误");
			//	break;
			case 4:
				return ResultJSON.resultToJSONStr(false, "手机号与验证码不符");
			//	break;
			}
			
			UserQO userQO=new UserQO();
			userQO.setMobile(command.getMobile());
			Integer count=queryCount(userQO);
			if (count > 0) {
				return ResultJSON.resultToJSONStr(false, "手机号重复");
			}
			UserQO userQO1=new UserQO();
			userQO1.setLoginName(command.getLoginName());
			Integer nameCount=queryCount(userQO1);
			if (nameCount > 0) {
				return ResultJSON.resultToJSONStr(false, "登录名已经被占用了哦");
			}
			
			try {
				// 查用户id序列
				SeqsQO seqsQO = new SeqsQO();
				seqsQO.setId("user");
				seqsQO.setWriteLock(true);
				Seqs seqs = seqsService.queryUnique(seqsQO);
				command.setNickName("会员"+(seqs.getNum() + 1));
				User user=new User();
				
				//根据推广令牌找到推广主体
				MarketingEntryQO marketingEntryQO=new MarketingEntryQO();
				marketingEntryQO.setId(command.getMarketingTokenId());
				marketingEntryQO.setFetchSubject(true);
				MarketingEntry marketingEntry=marketingEntryService.queryUnique(marketingEntryQO);
				User parent=null;
				if(null!=marketingEntry){
					parent=get(marketingEntry.getSubject().getDomainLink().getDomainId());
					user.setParent(parent);
				}else{
					user.setParent(user);
				}
				user.createFromRegister(command, parent);
				
		        save(user);
		        userDTO = UserConverter.donmainToDTO(user);
		        SessionUtil.putLoginUser(request, userDTO);
		      //初始化用户对应实体
				createDomainLink(user);
				
				//创建虚拟帐户用于结算
				VirtualAccount virtualAccount =createVirtualAccount(user);
				
				user.setCodeImage(createMarketingEntry(user, request));
				user.setVirtualAccountId(virtualAccount.getId());
				update(user);
			} catch (Exception e) {
				return ResultJSON.resultToJSONStr(false, "注册失败");
			}
	        return ResultJSON.resultToJSONStr(true, "恭喜注册成功", userDTO);
	        
	}
	
	public String modifUser(ModifyUserCommand command){
		
		try {
			User user = get(command.getUserId());
			user.modify(command);
			update(user);
			
		} catch (Exception e) {
			ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	/**
	 * 根据ID 改变用户的使用状态
	 * @param user_disable 使用户不可用
	 * 
	 */
	public String change(ChangeUserCommand command){
		User user = get(command.getUserId());
		try {
			user.changeStatus(command);
			update(user);
		} catch (Exception e) {
			ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	/**
	 * 	根据ID 改变用户的领域状态 
	 * @param domainId = userId
	 * 
	 */
	public String modifyDomainLink(String domainId,String sign){
		User user = get(domainId);
		try {
			user.getStatus().setValidateShop(sign);
			update(user);
		} catch (Exception e) {
			ResultJSON.resultToJSONStr(false, "修改失败");
		}
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	
	/**
	 * 后台增加用户
	 * CreateUserCommand
	 * @throws Exception 
	 */
	public String createUser(CreateUserCommand command,HttpServletRequest request) throws Exception{
		
		UserQO userQO=new UserQO();
		userQO.setMobile(command.getMobile());
		Integer count=queryCount(userQO);
		if (count > 0) {
			return ResultJSON.resultToJSONStr(false, "手机号重复");
		}
		UserQO userQO1=new UserQO();
		userQO1.setLoginName(command.getLoginName());
		Integer nameCount=queryCount(userQO1);
		if (nameCount > 0) {
			return ResultJSON.resultToJSONStr(false, "登录名重复");
		}
		
		UserQO userQO2=new UserQO();
		userQO2.setIdCardNo(command.getIdCardNo());
		Integer carNoCount=queryCount(userQO1);
		if (carNoCount > 0) {
			return ResultJSON.resultToJSONStr(false, "身份证重复");
		}
		
		SeqsQO seqsQO = new SeqsQO();
		seqsQO.setId("user");
		seqsQO.setWriteLock(true);
		Seqs seqs = seqsService.queryUnique(seqsQO);
		if(!StringUtils.isNotBlank(command.getNickName())){
			command.setNickName("会员"+seqs.getNum() + 1);
		}
		User user=new User();
		User parent=null;
		try {
			if(StringUtils.isNotBlank(command.getParentId())){
				parent=get(command.getParentId());
				user.setParent(parent);
			}else{
				user.setParent(user);
			}
			user.createUser(command, parent);
			save(user);
			//初始化用户对应实体
			createDomainLink(user);
			
			//创建虚拟帐户用于结算
			VirtualAccount virtualAccount =createVirtualAccount(user);
			
			
			user.setCodeImage(createMarketingEntry(user, request));
			user.setVirtualAccountId(virtualAccount.getId());
			update(user);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(false, "保存失败");
		}
		 return ResultJSON.resultToJSONStr(true, "保存成功");
	}
	
	
	public VirtualAccount createVirtualAccount(User user) throws VirtualAccountException{
		
		CreateVirtualAccountCommand createVirtualAccountCommand = new CreateVirtualAccountCommand();
		createVirtualAccountCommand.setName(user.getContactInfo().getMobile()
				+ "结算帐户");
		createVirtualAccountCommand.setVACurrencyId("balance-yuan");
		createVirtualAccountCommand.setOwnerId(user.getId());
		createVirtualAccountCommand.setOwnerName(user.getContactInfo().getMobile());
		createVirtualAccountCommand.setOwnerType("user");
		createVirtualAccountCommand.setBusinessType("account");
		VirtualAccount virtualAccount = virtualAccountService
				.createVirtualAccount(createVirtualAccountCommand);
		return virtualAccount;
	}
	
	
	public String createMarketingEntry(User user,HttpServletRequest request) throws Exception{
		
		CreateMarketingEntryCommand commandMarketing=new CreateMarketingEntryCommand();
		commandMarketing.setSubjectId(user.getId());
		commandMarketing.setChannelType(CreateMarketingEntryCommand.CHANNEL_TYPE_WB);
		commandMarketing.setTargetType(CreateMarketingEntryCommand.TARGET_TYPE_WEBSITE);
		commandMarketing.setTargetName("商城首页");
		String webSiteUrl =  PropertiesUtil.getProperiesValue("webSiteUrl", "system.properties");
		commandMarketing.setTargetUrl(webSiteUrl);
		commandMarketing.setIntro("整站，入口进来是商城首页");
		MarketingEntry marketingEntry=marketingEntryService.createMarketingEntry(commandMarketing);
		String file=user.getId()+".gif";
		//生成推广二维码
		QRCodeUtil.encode(webSiteUrl+"?entryId="+marketingEntry.getId(), request.getSession().getServletContext().getRealPath("/")+ "resources/img/lt.jpg",  PropertiesUtil.getProperiesValue("imagePath", "/system.properties"),true,file);
	    return file;
	}
	
	/**
	 * 
	 * @param request
	 * @param 
	 * @param password
	 */
	public String login(HttpServletRequest request, String userNamne, String pwd) {
		
		if (StringUtils.isNotBlank(userNamne)&&StringUtils.isNotBlank(pwd)) {
			User user = loginValidata(userNamne, pwd);
			UserDTO userDTO = UserConverter.donmainToDTO(user);
			if (userDTO != null) {
				SessionUtil.putLoginUser(request, userDTO);
				
			}
			if (user != null) {
				return ResultJSON.resultToJSONStr(true, "登录成功");
			}
		} 
		return ResultJSON.resultToJSONStr(false, "登录失败");
	}
	
	/**
	 * 
	 * @param request
	 * @param 
	 * @param password
	 */
	public UserDTO androidLogin(HttpServletRequest request, String userNamne, String pwd) {
		
		if (StringUtils.isNotBlank(userNamne)&&StringUtils.isNotBlank(pwd)) {
			User user = loginValidata(userNamne, pwd);
			UserDTO userDTO = UserConverter.donmainToDTO(user);
			if (userDTO != null) {
				return userDTO;
			} 
		} 
		return null;
	}
	
	/**
	 *检查用户名或者手机是否注册过
	 *根据type 判断传过来的 是 手机号码，还是 用户名
	 *type : loginName   mobile
	 */
	public String checkParam(String param,String type){
		
		UserQO userQO=new UserQO();
		if (type == "loginName") {
			userQO.setLoginName(param);
		}else if(type == "mobile") {
			userQO.setMobile(param);
		}
		Integer count=queryCount(userQO);
		if (count > 0) {
			return ResultJSON.resultToJSONStr(false, "已经注册过了");
		}
		return ResultJSON.resultToJSONStr(true, "可以注册");
	}
	
	/**
	 * 登陆校验
	 * @param loginName
	 * @param password
	 * @return
	 */
	private User loginValidata(String loginName, String password) {
		UserQO userQO = new UserQO();
		userQO.setLoginName(loginName);
		User user = queryUnique(userQO);
		if (null == user) {
			return null;
		}
		if (!PwdUtil.getPwd(password).equals(user.getUserLoginInfo().getEncryptPassword())) {
			return null;
		}
		return user;
		
	}
	
	
	
	public String UserUpdatePassword(UpdatePasswordCommand command){
		
		User user=get(command.getUserId());
		if(null==user)
		return ResultJSON.resultToJSONStr(false, "修改失败");
		user.updatePassword(command);
		update(user);
		return ResultJSON.resultToJSONStr(true, "修改成功");
	}
	
	
	
	/**
	 * 创建其他模块要初始化的用户对应实体
	 * 
	 * @param user
	 */
	private void createDomainLink(User user) {
		// 创建推广主体
		CreateMarketingSubjectCommand createMarketingSubjectCommand = new CreateMarketingSubjectCommand();
		createMarketingSubjectCommand.setSubjectId(user.getId());
		createMarketingSubjectCommand.setSubjectType("user");
		createMarketingSubjectCommand.setSubjectName(user.getBaseInfo()
				.getName());

		marketingSubjectService
				.createMarketingSubject(createMarketingSubjectCommand);
		

	}
	
	
	/**
	 * 检查短信验证码流程
	 * 
	 * @param command
	 * @throws UserException
	 * @throws SagaException
	 * 
	 *  byte 0 : 验证通过   1 ：  流程不存在， 2 ：验证不通过 ， 3 ： 验证码错误， 4 ：手机号与验证码不符
	 * 
	 */
	public byte checkSMSSaga(String sagaId, String validCode,String mobile)
			throws SagaException {
		SMSValidateSaga saga = null;
		if (StringUtils.isNotBlank(sagaId)) {
			saga = smsValidateSagaServiceImpl.get(sagaId);
		}else{
			return 1;
		}
//		SmsVlidateMobile(mobile, saga);
		
		if (saga == null) {
		//	throw new SagaException(SagaException.SAGA_NOT_EXIST, "流程不存在，非法请求");
			return 2;
		}
		//在此处判断手机号码 和当前接收的验证码是否相等
		if (!saga.getMobile().equals(mobile)) {
			return 4;
		}
		
		// 校验手机验证码，校验不通过会抛出SagaException
		ValidateSMSCommand validateSMSCommand = new ValidateSMSCommand();
		validateSMSCommand.setSagaId(sagaId);
		validateSMSCommand.setValidCode(validCode);
	//	validateSMSCommand.setMoblie(mobile);
		try {
			smsValidateSagaServiceImpl.validateSMS(validateSMSCommand,saga);
		//	return ResultJSON.resultToJSONStr(true, "验证通过");
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
		//	return ResultJSON.resultToJSONStr(false, "短信验证错误");
			return 3;
		}
		
	}
	
	
	
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, UserQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<User> getEntityClass() {
		// TODO Auto-generated method stub
		return User.class;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Md5FileUtil.MD5("123456"));
	}

	

}
