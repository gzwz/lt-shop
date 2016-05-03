package lt.oneBuy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import lt.base.command.CreateImageCommand;
import lt.base.qo.ImageQO;
import lt.base.service.ImageService;
import lt.oneBuy.command.CreateOneBuyCommand;
import lt.oneBuy.command.DisableOneBuyCommand;
import lt.oneBuy.command.EnableOneBuyCommand;
import lt.oneBuy.command.ModifyOneBuyCommand;
import lt.oneBuy.command.SscCommand;
import lt.oneBuy.dto.ResultSSCDTO;
import lt.oneBuy.entity.CloudBrand;
import lt.oneBuy.entity.CloudCategory;
import lt.oneBuy.entity.LotteryResult;
import lt.oneBuy.entity.LuckNum;
import lt.oneBuy.entity.OneBuy;
import lt.oneBuy.qo.LotteryResultQO;
import lt.oneBuy.qo.LuckNumQO;
import lt.oneBuy.qo.OneBuyQO;
import lt.order.entity.Order;
import lt.order.entity.OrderBuyerInfo;
import lt.order.entity.OrderMarketingInfo;
import lt.order.qo.OrderBuyerInfoQO;
import lt.order.qo.OrderQO;
import lt.order.service.OrderBuyerInfoService;
import lt.order.service.OrderService;
import lt.product.entity.ProductSnapshot;
import lt.product.service.ProductSnapshotService;
import lt.utils.RESULT;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.DateUtil;
import gzlazypack.common.util.ResultJSON;
import gzlazypack.common.util.UUIDGenerator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.common.base.Preconditions;

@Service
@Transactional
public class OneBuyService extends BaseDao<OneBuy, OneBuyQO> {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ProductSnapshotService productSnapshotService;

	@Autowired
	private CloudBrandService cloudBrandService;

	@Autowired
	private CloudCategoryService cloudCategoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private LuckNumService luckNumService;
	
	@Autowired
	private OrderBuyerInfoService buyerInfoService;
	
	@Autowired
	private LotteryResultService lotteryResultService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public LotteryResult getLotteryResult(String oneBuyId) {
		// TODO Auto-generated method stub
		LotteryResultQO resultQO = new LotteryResultQO();
		resultQO.setFetchBuyerInfo(true);
		resultQO.setFetchOrder(true);
		resultQO.setOneBuy(get(oneBuyId));
		return lotteryResultService.queryUnique(resultQO);
	}
	 
	//计算开奖号码
	public ResultSSCDTO getLuckNum(OneBuy oneBuy, SscCommand sscCommand) {
		LotteryResultQO resultQO = new LotteryResultQO();
		resultQO.setOneBuy(oneBuy);
		LotteryResult lotteryResult = lotteryResultService.queryUnique(resultQO);
		if(lotteryResult == null){
			lotteryResult = new LotteryResult();
			lotteryResult.setId(UUIDGenerator.getUUID());
		}else {
			//此商品已经开过奖
			return null;
		}  
		//
		lotteryResult.setOneBuy(oneBuy);
		lotteryResult.setExpect(sscCommand.getExpect());
		lotteryResult.setOpencode(sscCommand.getOpencode());
		lotteryResult.setOpentime(DateUtil.parseDate5(sscCommand.getOpentime()));
		lotteryResult.setOpentimestamp(sscCommand.getOpentimestamp());
		lotteryResult.setOpenLotteryTime(new Date());
		
		// 1）取该商品最后购买时间前网站所有商品100条购买时间记录	
		ResultSSCDTO resultSSCDTO = new ResultSSCDTO();
		OrderQO orderQO = new OrderQO();
		OrderMarketingInfo marketingInfo = new OrderMarketingInfo();
		marketingInfo.setOperationMerchantId("123456");
		marketingInfo.setOperationMerchantName("e-kcw.com");
		orderQO.setMarketingInfo(marketingInfo);
		List<Order> orderList = orderService.queryList(orderQO, 100);
		
		Integer num = 0;
		for (Order order : orderList) {
			Date buyTime = order.getBaseInfo().getCreateDate();
			num +=Integer.parseInt( buyTime.getHours()+""+ buyTime.getMinutes()+""+buyTime.getSeconds()+"");
		}
		String num1= "";
		if (num < 100000000) {
			num1 = num +""+new Random().nextInt(100);
		}
		Integer b = Integer.parseInt(sscCommand.getOpencode());
		Integer a = Integer.parseInt(num1);
		//商品份额输
		Integer luck = (a+b)%oneBuy.getCountInfo().getTotalCount()+10000001;
		lotteryResult.setLotteryNum(luck);
		
		LuckNumQO luckNumQO = new LuckNumQO();
		luckNumQO.setLuckNumber(luck);
		luckNumQO.setOneBuyQO(new OneBuyQO());
		luckNumQO.getOneBuyQO().setId(oneBuy.getId());
		LuckNum luckNum =luckNumService.queryUnique(luckNumQO);
		if (luckNum != null) {
			lotteryResult.setOrder(luckNum.getOrder());
			
			resultSSCDTO.setProductId(luckNum.getOneBuy().getId());
			resultSSCDTO.setLottery(luckNum.getLuckNumber().toString());
			
			OrderBuyerInfoQO buyerInfoQO = new OrderBuyerInfoQO();
			OrderQO orderQO2 = new OrderQO();
			orderQO2.setId(luckNum.getOrder().getId());
			buyerInfoQO.setOrderqo(orderQO2);
			OrderBuyerInfo buyerInfo = buyerInfoService.queryUnique(buyerInfoQO);
			
			if (buyerInfo!=null) {
				lotteryResult.setBuyerInfo(buyerInfo);
				
				resultSSCDTO.setMobile(buyerInfo.getMobile());
				//此处姓名是收货人姓名！
				resultSSCDTO.setUserName(buyerInfo.getConsigneeName());
				resultSSCDTO.setUserId(buyerInfo.getBuyerUserId());
			}
		}
		try {
			save(lotteryResult);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return resultSSCDTO;
	}
	
	public RESULT publish(OneBuy oneBuy) {
		// TODO Auto-generated method stub
		oneBuy.changeNewPublish();
		try {
			update(oneBuy);
			return RESULT.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
		
	}
	
	//根据支付订单减库存
	public void cutRepertory(OneBuy oneBuy, Integer partakeCount) {
		// TODO Auto-generated method stub
		Integer partakeCounted = oneBuy.getCountInfo().getPartakeCount();
		oneBuy.getCountInfo().setPartakeCount(partakeCount+ partakeCounted);
		Integer reside = oneBuy.getCountInfo().getTotalCount() - oneBuy.getCountInfo().getPartakeCount();
		oneBuy.getCountInfo().setResidueCount(reside);
	}
	/**
	 * 下架
	 * 
	 * @param command
	 */
	public void disable(DisableOneBuyCommand command) {
		OneBuy cloundPurchase = get(command.getOneBuyId());
		cloundPurchase.disable();
	}

	/**
	 * 上架
	 * 
	 * @param command
	 */
	public void enable(EnableOneBuyCommand command) {
		OneBuy cloundPurchase = get(command.getOneBuyId());
		cloundPurchase.enable();
		update(cloundPurchase);
		ProductSnapshot snapshot = new ProductSnapshot();
		snapshot.oneBuyCreate(cloundPurchase);
		productSnapshotService.save(snapshot);
	}

	/**
	 * 添加创建数据
	 * 
	 * @param command
	 * @return
	 */

	public OneBuy modify(ModifyOneBuyCommand command) {
		OneBuy cloundPurchase = get(command.getId());
		cloundPurchase.modify(command);

		// 图片处理
		Map<String, String> specImageMap = new HashMap<String, String>();
		if (StringUtils.isNoneBlank(command.getImages())) {
			for (int i = 0; i < command.getImages().split(",").length; i++) {
				specImageMap.put("product_image" + i, command.getImages()
						.split(",")[i]);
			}
			ImageQO imgeQO = new ImageQO();
			imgeQO.setDomainId(cloundPurchase.getId());
			imageService.delete(imageService.queryUnique(imgeQO));

			CreateImageCommand imgCommand = new CreateImageCommand();
			imgCommand.setSpecImageMap(specImageMap);
			List<CreateImageCommand> createImageCommandList = new ArrayList<CreateImageCommand>();
			createImageCommandList.add(imgCommand);
			command.setCreateImageCommandList(createImageCommandList);
			// 创建商品图片
			if (CollectionUtils.isNotEmpty(command.getCreateImageCommandList())) {
				int index = 0;
				for (CreateImageCommand createImageCommand : command
						.getCreateImageCommandList()) {
					createImageCommand.setDomainLinkId(cloundPurchase.getId());
					createImageCommand.setDomainLinkName(cloundPurchase
							.getBaseInfo().getName());
					createImageCommand.setDomainLinkType("product_image");
					createImageCommand.setSort(index);
					imageService.createImage(createImageCommand);
					index++;
				}
			}
		}
		update(cloundPurchase);
		return cloundPurchase;

	}
	
	

	public String createProduct(CreateOneBuyCommand command) {
		// TODO Auto-generated method stub
		CloudCategory cloudCategory = cloudCategoryService.get(command
				.getCloudCategoryId());
		Preconditions.checkArgument(cloudCategory != null, "【产品类目数为空】");

		CloudBrand cloudBrand = cloudBrandService
				.get(command.getCloudBrandId());

		Preconditions.checkArgument(cloudBrand != null, "【品牌数为空】");

		OneBuy cloundPurchase = new OneBuy();

		cloundPurchase.create(command, cloudCategory, cloudBrand);

		// 图片处理
		Map<String, String> specImageMap = new HashMap<String, String>();

		for (int i = 0; i < command.getImages().split(",").length; i++) {
			specImageMap.put("product_image" + i, command.getImages()
					.split(",")[i]);
		}

		CreateImageCommand imgCommand = new CreateImageCommand();
		imgCommand.setSpecImageMap(specImageMap);
		List<CreateImageCommand> createImageCommandList = new ArrayList<CreateImageCommand>();
		createImageCommandList.add(imgCommand);
		command.setCreateImageCommandList(createImageCommandList);
		// 创建商品图片
		if (CollectionUtils.isNotEmpty(command.getCreateImageCommandList())) {
			int index = 0;
			for (CreateImageCommand createImageCommand : command
					.getCreateImageCommandList()) {
				createImageCommand.setDomainLinkId(cloundPurchase.getId());
				createImageCommand.setDomainLinkName(cloundPurchase
						.getBaseInfo().getName());
				createImageCommand.setDomainLinkType("product_image");
				createImageCommand.setSort(index);
				imageService.createImage(createImageCommand);
				index++;
			}
		}

		try {
			save(cloundPurchase);
			return ResultJSON.resultToJSONStr(true, "操作成功");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "保存失败");
		}
	}

	public void hotT(EnableOneBuyCommand command) {
		OneBuy oneBuy = get(command.getOneBuyId());
		oneBuy.t();
		update(oneBuy);
	}

	public void hotF(DisableOneBuyCommand command) {
		OneBuy oneBuy = get(command.getOneBuyId());
		oneBuy.f();
		update(oneBuy);
	}

	public void getOneBuy(HttpServletRequest request, Model model) {

		OneBuyQO oneBuyQO = new OneBuyQO();
		oneBuyQO.setHot(true);
		oneBuyQO.setStatus(OneBuy.STATUS_ENABLE);
		oneBuyQO.setOrderBy(-1);
		model.addAttribute("oneBuyList", queryList(oneBuyQO, 4));
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, OneBuyQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<OneBuy> getEntityClass() {
		// TODO Auto-generated method stub
		return OneBuy.class;
	}


}
