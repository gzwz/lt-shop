package lt.admin.service;

import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.PwdUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import lt.admin.command.CreateAdminCommand;
import lt.admin.command.CreateAuthAccountCommand;
import lt.admin.command.ModifyAdminCommand;
import lt.admin.command.ModifyAuthAccountCommand;
import lt.admin.converter.AdminConverter;
import lt.admin.dto.AdminDTO;
import lt.admin.entity.Admin;
import lt.admin.entity.AuthAccount;
import lt.admin.entity.Role;
import lt.admin.qo.AdminQO;
import lt.admin.qo.AuthAccountQO;
import lt.admin.qo.RoleQO;
import lt.utils.SessionUtil;

@Service
@Transactional
public class AdminService extends BaseDao<Admin, AdminQO> {

	@Autowired
	private AuthAccountService accountServiceImpl;
	@Autowired
	private RoleService roleServiceImpl;

	public Admin createAdmin(CreateAdminCommand command) {

		Set<Role> roles = queryRoles(command.getRoleIds());

		Admin admin = new Admin();
		admin.create(command, roles);

		CreateAuthAccountCommand command2 = new CreateAuthAccountCommand();
		command2.setClientType(AuthAccount.CLIENT_TYPE_ADMIN);
		command2.setDomainId(admin.getId());
		command2.setLoginName(command.getLoginName());
		command2.setPassword(command.getPassword());
		AuthAccount account = accountServiceImpl.createAuthAccount(command2);

		admin.setAuthAccountId(account.getId());
		save(admin);

		return admin;
	}

	public void modifyAdmin(ModifyAdminCommand command) {

		Set<Role> roles = queryRoles(command.getRoleIds());

		Admin admin = get(command.getAdminId());
		admin.modify(command, roles);

		if (StringUtils.isNotBlank(command.getPassword())) {
			AuthAccount account = accountServiceImpl.get(admin.getAuthAccountId());

			ModifyAuthAccountCommand command2 = new ModifyAuthAccountCommand();
			command2.setAuthAccountId(account.getId());
			command2.setLoginName(account.getLoginName());
			command2.setPassword(command.getPassword());
			account.modify(command2);
			update(account);
		}

		update(admin);
	}

	private Set<Role> queryRoles(List<String> roleIds) {
		RoleQO qo = new RoleQO();
		qo.setIds(roleIds);
		List<Role> roleList = roleServiceImpl.queryList(qo);

		Set<Role> roles = new HashSet<>();

		if (roleList == null) {
			return roles;
		}

		for (Role role : roleList) {
			roles.add(role);
		}
		return roles;
	}
	
	/**
	 * 后台管理员登陆
	 * 
	 * @param request
	 * @param loginName 用户名
	 * @param password 密码
	 */
	public void login(HttpServletRequest request, String loginName, String password) {
		AuthAccount account = loginValidata(loginName, password);
		AdminQO qo = new AdminQO();
		qo.setAuthAccountId(account.getId());
		AdminDTO admin = AdminConverter.domainToDTO(queryUnique(qo), account);
		
		SessionUtil.putLoginAdmin(request, admin);
		
	}
	
	/**
	 * 登陆校验
	 * @param loginName
	 * @param password
	 * @return
	 */
	private AuthAccount loginValidata(String loginName, String password) {
		Preconditions.checkArgument(StringUtils.isNotBlank(loginName), "用户名不能为空");
		Preconditions.checkArgument(StringUtils.isNotBlank(password), "密码不能为空");
		AuthAccountQO accountQO = new AuthAccountQO();
		accountQO.setLoginName(loginName);
		AuthAccount account = accountServiceImpl.queryUnique(accountQO);
		Preconditions.checkArgument(null != account, "用户名不存在");
		Preconditions.checkArgument(StringUtils.equals(PwdUtil.getPwd(password), account.getEncryptPassword()),
				"用户名密码不匹配");
		return account;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, AdminQO qo) {
		return criteria;
	}

	@Override
	protected Class<Admin> getEntityClass() {
		return Admin.class;
	}

}
