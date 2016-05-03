/*import gzlazypack.common.component.BaseQO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lt.admin.command.CreateAdminCommand;
import lt.admin.entity.Admin;
import lt.admin.service.AdminService;
import lt.base.command.CreateImageCommand;
import lt.base.command.SendSMSValidCodeCommand;
import lt.base.entity.SMSValidateSaga;
import lt.base.entity.Seqs;
import lt.base.exception.SagaException;
import lt.base.service.SMSValidateSagaService;
import lt.base.service.SeqsService;
import lt.marketing.command.CreateMarketingEntryCommand;
import lt.marketing.command.EnterMarketingEntryCommand;
import lt.marketing.entity.MarketingEntry;
import lt.marketing.entity.MarketingToken;
import lt.marketing.entity.RebatesSetting;
import lt.marketing.exception.MarketingException;
import lt.marketing.qo.MarketingEntryQO;
import lt.marketing.qo.MarketingSubjectQO;
import lt.marketing.qo.MarketingTargetQO;
import lt.marketing.service.MarketingEntryService;
import lt.order.command.AddOrderDeliveryInfoCommand;
import lt.order.command.CreateOrderFromShoppingCarCommand;
import lt.order.command.CreateOrderImmediateCommand;
import lt.order.command.CreateShoppingCarItemCommand;
import lt.order.command.FinishOrderCommand;
import lt.order.command.OrderPaySuccessCommand;
import lt.order.entity.Order;
import lt.order.entity.OrderSKUItem;
import lt.order.entity.ShoppingCarItem;
import lt.order.exception.OrderException;
import lt.order.service.OrderService;
import lt.order.service.ShoppingCarItemService;
import lt.order.service.ShoppingCarService;
import lt.pay.command.ConfirmPaymentOrderSuccessCommand;
import lt.pay.service.PaymentOrderService;
import lt.product.command.CreateProductCategoryCommand;
import lt.product.command.CreateProductCommand;
import lt.product.command.CreateProductParameterCommand;
import lt.product.command.CreateProductParameterValueCommand;
import lt.product.command.CreateProductSKUItemCommand;
import lt.product.command.CreateProductSpecCommand;
import lt.product.command.CreateSKUProductCommand;
import lt.product.command.EnableProductCommand;
import lt.product.entity.Product;
import lt.product.entity.ProductCategory;
import lt.product.entity.ProductParameter;
import lt.product.entity.ProductSpec;
import lt.product.entity.SKUProduct;
import lt.product.qo.ProductQO;
import lt.product.qo.ProductSKUItemQO;
import lt.product.qo.ProductSpecQO;
import lt.product.qo.SKUProductQO;
import lt.product.service.ProductCategoryService;
import lt.product.service.ProductParameterService;
import lt.product.service.ProductParameterValueService;
import lt.product.service.ProductService;
import lt.product.service.ProductSpecService;
import lt.product.service.SKUProductService;
import lt.stock.command.CreateDeliveryOrderCommand;
import lt.stock.command.CreateStorageCommand;
import lt.stock.entity.DeliveryOrder;
import lt.stock.entity.Storage;
import lt.stock.service.DeliveryOrderService;
import lt.stock.service.StorageService;
import lt.user.command.AuthCloudMerchantCommand;
import lt.user.command.CreateUserAddressCommand;
import lt.user.command.CreateUserByAdminCommand;
import lt.user.command.ModifyUserRealInfoCommand;
import lt.user.command.UserRegisterCommand;
import lt.user.entity.User;
import lt.user.entity.UserAddress;
import lt.user.exception.UserException;
import lt.user.service.UserAddressService;
import lt.user.service.UserService;
import lt.virtualaccount.command.rebate.AgreeRebateWithdrawOrderCommand;
import lt.virtualaccount.command.rebate.ApplyRebateWithdrawCashCommand;
import lt.virtualaccount.command.rebate.UnFreezeAllOKRebateCommand;
import lt.virtualaccount.entity.VACurrency;
import lt.virtualaccount.entity.WithdrawOrder;
import lt.virtualaccount.exception.VirtualAccountException;
import lt.virtualaccount.qo.VirtualAccountChangeDetailQO;
import lt.virtualaccount.qo.VirtualAccountQO;
import lt.virtualaccount.service.VACurrencyService;
import lt.virtualaccount.service.VirtualAccountChangeDetailService;
import lt.virtualaccount.service.rebate.OrderRebateAccountService;
import lt.virtualaccount.service.rebate.RebateWithdrawOrderService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class InitUnits {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductParameterService productParameterService;
	@Autowired
	private ProductParameterValueService productParameterValueService;
	@Autowired
	private ProductSpecService productSpecService;
	@Autowired
	private VACurrencyService vACurrencyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SMSValidateSagaService smsValidateSagaService;
	@Autowired
	private MarketingEntryService marketingEntryService;
	@Autowired
	private ShoppingCarService shoppingCarService;
	@Autowired
	private ShoppingCarItemService shoppingCarItemService;
	@Autowired
	private SKUProductService skuProductService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	@Autowired
	private SeqsService seqsService;
	@Autowired
	private VirtualAccountChangeDetailService virtualAccountChangeDetailService;
	@Autowired
	private RebateWithdrawOrderService rebateWithdrawOrderService;
	@Autowired
	private PaymentOrderService paymentOrderService;
	@Autowired
	private OrderRebateAccountService orderRebateAccountService;
	
	@Test
	public void init() {
		// 创建序列
		Seqs seqs1 = new Seqs();
		seqs1.setId("user");
		seqs1.setNum(99999L);
		seqsService.save(seqs1);

		// 创建虚拟货币
		VACurrency currency = new VACurrency();
		currency.setId("balance-yuan");
		currency.setName("返佣余额");
		currency.setExchangeOneYuan(1D);
		currency.setProxyCurrency(false);
		currency.setUnit("元");
		vACurrencyService.save(currency);

		// 创建平台仓库
		CreateStorageCommand createStorageCommand = new CreateStorageCommand();
		createStorageCommand.setDomainId("platform");
		createStorageCommand.setDomainType("platform");
		createStorageCommand.setDomainName("平台仓库");
		createStorageCommand.setName("平台仓库");
		storageService.createStorage(createStorageCommand);

		// 创建超级管理员
		CreateAdminCommand createAdminCommand = new CreateAdminCommand();
		createAdminCommand.setTelephone("15100000000");
		createAdminCommand.setLoginName("admin");
		createAdminCommand.setPassword("123456");
		createAdminCommand.setName("超级管理员");
		createAdminCommand.setType(Admin.TYPE_PLATFORM_ADMIN);
		adminService.createAdmin(createAdminCommand);

		// 创建杭州西湖区代理商
		CreateUserByAdminCommand createUserByAdminCommand = new CreateUserByAdminCommand();
		createUserByAdminCommand.setName("张代理");
		createUserByAdminCommand.setProvinceId("330000");
		createUserByAdminCommand.setCityId("330100");
		createUserByAdminCommand.setAreaId("330106");
		createUserByAdminCommand.setMobile("13100000000");
		createUserByAdminCommand.setPassword("123456");
		createUserByAdminCommand.setIdCardNo("111111222222223333");
		createUserByAdminCommand.setUserType(User.TYPE_OPERATION_MERCHANT);
		User areaMerchantUser1 = null;
		try {
			areaMerchantUser1 = userService
					.createUserByAdmin(createUserByAdminCommand);
		} catch (VirtualAccountException | UserException e) {
			e.printStackTrace();
		}

		// 创建杭州滨江区代理商
		createUserByAdminCommand.setName("李代理");
		createUserByAdminCommand.setProvinceId("330000");
		createUserByAdminCommand.setCityId("330100");
		createUserByAdminCommand.setAreaId("330108");
		createUserByAdminCommand.setMobile("13200000000");
		createUserByAdminCommand.setPassword("123456");
		createUserByAdminCommand.setIdCardNo("111111222222223333");
		createUserByAdminCommand.setUserType(User.TYPE_OPERATION_MERCHANT);
		try {
			userService.createUserByAdmin(createUserByAdminCommand);
		} catch (VirtualAccountException | UserException e) {
			e.printStackTrace();
		}

		// 创建北京东城区代理商
		createUserByAdminCommand.setName("王代理");
		createUserByAdminCommand.setProvinceId("110000");
		createUserByAdminCommand.setCityId("110100");
		createUserByAdminCommand.setAreaId("110101");
		createUserByAdminCommand.setMobile("13400000000");
		createUserByAdminCommand.setPassword("123456");
		createUserByAdminCommand.setIdCardNo("111111222222223333");
		createUserByAdminCommand.setUserType(User.TYPE_OPERATION_MERCHANT);
		try {
			userService.createUserByAdmin(createUserByAdminCommand);
		} catch (VirtualAccountException | UserException e) {
			e.printStackTrace();
		}

		// 创建一个云商
		createUserByAdminCommand.setName("陈云商");
		createUserByAdminCommand.setProvinceId("110000");
		createUserByAdminCommand.setCityId("110100");
		createUserByAdminCommand.setAreaId("110101");
		createUserByAdminCommand.setMobile("13500000000");
		createUserByAdminCommand.setPassword("123456");
		createUserByAdminCommand.setIdCardNo("111111222222223333");
		createUserByAdminCommand.setImei("abcdefg123456");
		createUserByAdminCommand.setUserType(User.TYPE_CLOUD_MERCHANT);
		createUserByAdminCommand.setSuperiorUserId(areaMerchantUser1.getId());
		User cloudUser1 = null;
		try {
			cloudUser1 = userService
					.createUserByAdmin(createUserByAdminCommand);
		} catch (VirtualAccountException | UserException e) {
			e.printStackTrace();
		}

		// 创建一个下级云商
		createUserByAdminCommand.setName("张云商");
		createUserByAdminCommand.setProvinceId("110000");
		createUserByAdminCommand.setCityId("110100");
		createUserByAdminCommand.setAreaId("110101");
		createUserByAdminCommand.setMobile("13500000001");
		createUserByAdminCommand.setPassword("123456");
		createUserByAdminCommand.setIdCardNo("111111222222224444");
		createUserByAdminCommand.setImei("abcdefg123456");
		createUserByAdminCommand.setUserType(User.TYPE_CLOUD_MERCHANT);
		createUserByAdminCommand.setSuperiorUserId(cloudUser1.getId());
		User cloudUser2 = null;
		try {
			cloudUser2 = userService
					.createUserByAdmin(createUserByAdminCommand);
		} catch (VirtualAccountException | UserException e) {
			e.printStackTrace();
		}

		// 创建品类1
		CreateProductCategoryCommand createProductCategoryCommand = new CreateProductCategoryCommand();
		createProductCategoryCommand.setProductCategoryId("phone");
		createProductCategoryCommand.setName("手机");
		ProductCategory category1 = productCategoryService
				.createProductCategory(createProductCategoryCommand);
		// 创建品类2
		createProductCategoryCommand.setProductCategoryId("fitting");
		createProductCategoryCommand.setName("配件");
		productCategoryService
				.createProductCategory(createProductCategoryCommand);
		// 创建品类3
		createProductCategoryCommand.setProductCategoryId("near");
		createProductCategoryCommand.setName("周边");
		productCategoryService
				.createProductCategory(createProductCategoryCommand);

		// 创建参数1
		CreateProductParameterCommand createProductParameterCommand = new CreateProductParameterCommand();
		createProductParameterCommand.setName("材质");
		ProductParameter productParameter1 = productParameterService
				.createProductpParameter(createProductParameterCommand);
		// 创建参数2
		createProductParameterCommand.setName("拍照象素");
		ProductParameter productParameter2 = productParameterService
				.createProductpParameter(createProductParameterCommand);
		// 创建参数3
		createProductParameterCommand.setName("屏幕分辨率");
		ProductParameter productParameter3 = productParameterService
				.createProductpParameter(createProductParameterCommand);

		// 创建规格1
		CreateProductSpecCommand createProductSpecCommand = new CreateProductSpecCommand();
		createProductSpecCommand.setProductSpecId("color");
		createProductSpecCommand.setName("颜色");
		ProductSpec productSpecColor = productSpecService
				.createProductSpec(createProductSpecCommand);

		// 创建规格2
		createProductSpecCommand.setProductSpecId("mem");
		createProductSpecCommand.setName("内存");
		ProductSpec productSpecMem = productSpecService
				.createProductSpec(createProductSpecCommand);

		// 创建一个商品
		CreateProductCommand createProductCommand = new CreateProductCommand();
		createProductCommand.setName("苹果iphone6");
		createProductCommand.setIntro("快来买呀");
		createProductCommand.setMobileDetail("这是个很好的手机，移动版");
		createProductCommand.setPcDetail("这是个很好的手机，PC版");
		createProductCommand.setCategoryId(category1.getId());
		// 加入SKU
		createProductCommand
				.setCreateSKUCmds(new ArrayList<CreateSKUProductCommand>());

		// SKU1
		CreateSKUProductCommand createSKUProductCommand1 = new CreateSKUProductCommand();
		createSKUProductCommand1.setStockId("100001");
		createSKUProductCommand1.setStockNum(5);
		createSKUProductCommand1.setPrice(4998D);
		createSKUProductCommand1.setRebateSettingType(RebatesSetting.TYPE_FIX);
		createSKUProductCommand1.setRebateSettingValue1(30D);
		createSKUProductCommand1.setRebateSettingValue2(20D);
		createSKUProductCommand1.setRebateSettingValue3(10D);
		// 创建SKU1的sku颜色规格项
		createSKUProductCommand1
				.setCreateItemCmds(new ArrayList<CreateProductSKUItemCommand>());
		CreateProductSKUItemCommand createProductSKUItemCommand1_1 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand1_1.setSpecId(productSpecColor.getId());
		createProductSKUItemCommand1_1.setSpecValue("银色");
		createSKUProductCommand1.getCreateItemCmds().add(
				createProductSKUItemCommand1_1);

		// 创建SKU1的sku内存规格项
		CreateProductSKUItemCommand createProductSKUItemCommand1_2 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand1_2.setSpecId(productSpecMem.getId());
		createProductSKUItemCommand1_2.setSpecValue("16G");
		createSKUProductCommand1.getCreateItemCmds().add(
				createProductSKUItemCommand1_2);

		createProductCommand.getCreateSKUCmds().add(createSKUProductCommand1);

		// SKU2
		CreateSKUProductCommand createSKUProductCommand2 = new CreateSKUProductCommand();
		createSKUProductCommand2.setStockId("100002");
		createSKUProductCommand2.setStockNum(5);
		createSKUProductCommand2.setPrice(5998D);
		createSKUProductCommand2.setRebateSettingType(RebatesSetting.TYPE_FIX);
		createSKUProductCommand2.setRebateSettingValue1(40D);
		createSKUProductCommand2.setRebateSettingValue2(30D);
		createSKUProductCommand2.setRebateSettingValue3(20D);
		// 创建SKU2的sku颜色规格项
		createSKUProductCommand2
				.setCreateItemCmds(new ArrayList<CreateProductSKUItemCommand>());
		CreateProductSKUItemCommand createProductSKUItemCommand2_1 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand2_1.setSpecId(productSpecColor.getId());
		createProductSKUItemCommand2_1.setSpecValue("银色");
		createSKUProductCommand2.getCreateItemCmds().add(
				createProductSKUItemCommand2_1);

		// 创建SKU2的sku内存规格项
		CreateProductSKUItemCommand createProductSKUItemCommand2_2 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand2_2.setSpecId(productSpecMem.getId());
		createProductSKUItemCommand2_2.setSpecValue("32G");
		createSKUProductCommand2.getCreateItemCmds().add(
				createProductSKUItemCommand2_2);

		createProductCommand.getCreateSKUCmds().add(createSKUProductCommand2);
		// SKU3
		CreateSKUProductCommand createSKUProductCommand3 = new CreateSKUProductCommand();
		createSKUProductCommand3.setStockId("100003");
		createSKUProductCommand3.setStockNum(5);
		createSKUProductCommand3.setPrice(5998D);
		createSKUProductCommand3.setRebateSettingType(RebatesSetting.TYPE_RATE);
		createSKUProductCommand3.setRebateSettingValue1(0.03D);
		createSKUProductCommand3.setRebateSettingValue2(0.02D);
		createSKUProductCommand3.setRebateSettingValue3(0.01D);

		// 创建SKU3的sku颜色规格项
		createSKUProductCommand3
				.setCreateItemCmds(new ArrayList<CreateProductSKUItemCommand>());
		CreateProductSKUItemCommand createProductSKUItemCommand3_1 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand3_1.setSpecId(productSpecColor.getId());
		createProductSKUItemCommand3_1.setSpecValue("土豪金");
		createSKUProductCommand3.getCreateItemCmds().add(
				createProductSKUItemCommand3_1);

		// 创建SKU3的sku内存规格项
		CreateProductSKUItemCommand createProductSKUItemCommand3_2 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand3_2.setSpecId(productSpecMem.getId());
		createProductSKUItemCommand3_2.setSpecValue("16G");
		createSKUProductCommand3.getCreateItemCmds().add(
				createProductSKUItemCommand3_2);

		createProductCommand.getCreateSKUCmds().add(createSKUProductCommand3);
		// SKU4
		CreateSKUProductCommand createSKUProductCommand4 = new CreateSKUProductCommand();
		createSKUProductCommand4.setStockId("100004");
		createSKUProductCommand4.setStockNum(5);
		createSKUProductCommand4.setPrice(5998D);
		createSKUProductCommand4.setRebateSettingType(RebatesSetting.TYPE_RATE);
		createSKUProductCommand4.setRebateSettingValue1(0.04D);
		createSKUProductCommand4.setRebateSettingValue2(0.03D);
		createSKUProductCommand4.setRebateSettingValue3(0.02D);

		// 创建SKU4的sku颜色规格项
		createSKUProductCommand4
				.setCreateItemCmds(new ArrayList<CreateProductSKUItemCommand>());
		CreateProductSKUItemCommand createProductSKUItemCommand4_1 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand4_1.setSpecId(productSpecColor.getId());
		createProductSKUItemCommand4_1.setSpecValue("土豪金");
		createSKUProductCommand4.getCreateItemCmds().add(
				createProductSKUItemCommand4_1);

		// 创建SKU4的sku内存规格项
		CreateProductSKUItemCommand createProductSKUItemCommand4_2 = new CreateProductSKUItemCommand();
		createProductSKUItemCommand4_2.setSpecId(productSpecMem.getId());
		createProductSKUItemCommand4_2.setSpecValue("32G");
		createSKUProductCommand4.getCreateItemCmds().add(
				createProductSKUItemCommand4_2);

		createProductCommand.getCreateSKUCmds().add(createSKUProductCommand4);

		// 加入参数
		createProductCommand
				.setCreateParamValCmds(new ArrayList<CreateProductParameterValueCommand>());
		// 参数1
		CreateProductParameterValueCommand createProductParameterValueCommand1 = new CreateProductParameterValueCommand();
		createProductParameterValueCommand1
				.setProductParameterId(productParameter1.getId());
		createProductParameterValueCommand1.setValue("铝合金");
		createProductCommand.getCreateParamValCmds().add(
				createProductParameterValueCommand1);
		// 参数2
		CreateProductParameterValueCommand createProductParameterValueCommand2 = new CreateProductParameterValueCommand();
		createProductParameterValueCommand2
				.setProductParameterId(productParameter2.getId());
		createProductParameterValueCommand2.setValue("500万");
		createProductCommand.getCreateParamValCmds().add(
				createProductParameterValueCommand2);
		// 参数3
		CreateProductParameterValueCommand createProductParameterValueCommand3 = new CreateProductParameterValueCommand();
		createProductParameterValueCommand3
				.setProductParameterId(productParameter3.getId());
		createProductParameterValueCommand3.setValue("1920*1080");
		createProductCommand.getCreateParamValCmds().add(
				createProductParameterValueCommand3);

		// 加入图片
		createProductCommand
				.setCreateImageCommandList(new ArrayList<CreateImageCommand>());
		// 图片1
		CreateImageCommand createImageCommand1 = new CreateImageCommand();
		createImageCommand1.setTitle("图1");
		createImageCommand1.setSpecImageMap(new HashMap<String, String>());
		createImageCommand1
				.getSpecImageMap()
				.put("default",
						"http://img1.imgtn.bdimg.com/it/u=1076762079,3301763060&fm=11&gp=0.jpg");
		createImageCommand1
				.getSpecImageMap()
				.put("default",
						"http://img5.imgtn.bdimg.com/it/u=1569838843,2776431734&fm=11&gp=0.jpg");
		createProductCommand.getCreateImageCommandList().add(
				createImageCommand1);
		// 图片2
		CreateImageCommand createImageCommand2 = new CreateImageCommand();
		createImageCommand2.setTitle("图2");
		createImageCommand2.setSpecImageMap(new HashMap<String, String>());
		createImageCommand2
				.getSpecImageMap()
				.put("default",
						"http://img1.imgtn.bdimg.com/it/u=1076762079,3301763060&fm=11&gp=0.jpg");
		createImageCommand2
				.getSpecImageMap()
				.put("default",
						"http://img5.imgtn.bdimg.com/it/u=1569838843,2776431734&fm=11&gp=0.jpg");
		createProductCommand.getCreateImageCommandList().add(
				createImageCommand2);
		Product product = productService.createProduct(createProductCommand);

		// 商品上架
		EnableProductCommand enableProductCommand = new EnableProductCommand();
		enableProductCommand.setProductId(product.getId());
		productService.enable(enableProductCommand);

		// 创建一个有上级区域会员
		SendSMSValidCodeCommand sendSMSValidCodeCommand = new SendSMSValidCodeCommand();
		sendSMSValidCodeCommand.setScene("register");
		sendSMSValidCodeCommand.setMobile("13600000000");
		SMSValidateSaga smsValidateSaga1 = null;
		try {
			smsValidateSaga1 = smsValidateSagaService
					.sendSMS(sendSMSValidCodeCommand);
		} catch (SagaException e) {
			e.printStackTrace();
		}

		UserRegisterCommand userRegisterCommand = new UserRegisterCommand();
		userRegisterCommand.setMobile("13600000000");
		userRegisterCommand.setSagaId(smsValidateSaga1.getId());
		userRegisterCommand.setSmsValidCode(smsValidateSaga1.getValidCode());
		userRegisterCommand.setPassword("123456");
		userRegisterCommand.setProvinceId("330000");
		userRegisterCommand.setCityId("330100");
		userRegisterCommand.setAreaId("330108");
		User user1 = null;
		try {
			user1 = userService.registerUser(userRegisterCommand);
		} catch (SagaException | UserException e) {
			e.printStackTrace();
		}

		CreateUserAddressCommand createUserAddressCommand = new CreateUserAddressCommand();
		createUserAddressCommand.setUserId(user1.getId());
		createUserAddressCommand.setDefaultAddress(true);
		createUserAddressCommand.setName("用户1");
		createUserAddressCommand.setProvinceId("330000");
		createUserAddressCommand.setCityId("330100");
		createUserAddressCommand.setAreaId("330108");
		createUserAddressCommand.setDetail("什么路几号");
		createUserAddressCommand.setMobile(user1.getContactInfo().getMobile());
		UserAddress address1 = userAddressService
				.createUserAddress(createUserAddressCommand);

		// 创建一个无上级区域会员
		sendSMSValidCodeCommand.setScene("register");
		sendSMSValidCodeCommand.setMobile("13511111111");
		SMSValidateSaga smsValidateSaga2 = null;
		try {
			smsValidateSaga2 = smsValidateSagaService
					.sendSMS(sendSMSValidCodeCommand);
		} catch (SagaException e) {
			e.printStackTrace();
		}

		userRegisterCommand.setMobile("13511111111");
		userRegisterCommand.setSagaId(smsValidateSaga2.getId());
		userRegisterCommand.setSmsValidCode(smsValidateSaga2.getValidCode());
		userRegisterCommand.setPassword("123456");
		userRegisterCommand.setProvinceId("330000");
		userRegisterCommand.setCityId("330100");
		userRegisterCommand.setAreaId("330102");
		User user2 = null;
		try {
			user2 = userService.registerUser(userRegisterCommand);
		} catch (SagaException | UserException e) {
			e.printStackTrace();
		}
		createUserAddressCommand.setUserId(user2.getId());
		createUserAddressCommand.setDefaultAddress(true);
		createUserAddressCommand.setName("用户2");
		createUserAddressCommand.setProvinceId("330000");
		createUserAddressCommand.setCityId("330100");
		createUserAddressCommand.setAreaId("330108");
		createUserAddressCommand.setDetail("什么路几号");
		createUserAddressCommand.setMobile(user2.getContactInfo().getMobile());
		UserAddress address2 = userAddressService
				.createUserAddress(createUserAddressCommand);

		// 云商推广一个链接
		CreateMarketingEntryCommand createMarketingEntryCommand = new CreateMarketingEntryCommand();
		createMarketingEntryCommand.setSubjectId(cloudUser1.getId());
		createMarketingEntryCommand.setSubjectType("user");
		createMarketingEntryCommand.setTargetId(product.getId());
		createMarketingEntryCommand.setTargetName(product.getShowInfo()
				.getName());
		createMarketingEntryCommand.setTargetType("product");
		MarketingEntry entry = new MarketingEntry();
		try {
			entry = marketingEntryService
					.createMarketingEntry(createMarketingEntryCommand);
		} catch (MarketingException e) {
			e.printStackTrace();
		}
		// 游客点击链接
		EnterMarketingEntryCommand enterMarketingEntryCommand = new EnterMarketingEntryCommand();
		enterMarketingEntryCommand.setEntryId(entry.getId());
		MarketingToken marketingToken = marketingEntryService
				.enterMarketingEntry(enterMarketingEntryCommand);
		// 游客注册为普通会员，上级为推广的云商
		sendSMSValidCodeCommand.setScene("register");
		sendSMSValidCodeCommand.setMobile("13522222222");
		SMSValidateSaga smsValidateSaga3 = null;
		try {
			smsValidateSaga3 = smsValidateSagaService
					.sendSMS(sendSMSValidCodeCommand);
		} catch (SagaException e) {
			e.printStackTrace();
		}

		userRegisterCommand.setMobile("13522222222");
		userRegisterCommand.setSagaId(smsValidateSaga3.getId());
		userRegisterCommand.setSmsValidCode(smsValidateSaga3.getValidCode());
		userRegisterCommand.setPassword("123456");
		userRegisterCommand.setProvinceId("330000");
		userRegisterCommand.setCityId("330100");
		userRegisterCommand.setAreaId("330108");
		userRegisterCommand.setMarketingTokenId(marketingToken.getId());
		User user3 = null;
		try {
			user3 = userService.registerUser(userRegisterCommand);
		} catch (SagaException | UserException e) {
			e.printStackTrace();
		}

		createUserAddressCommand.setUserId(user3.getId());
		createUserAddressCommand.setDefaultAddress(true);
		createUserAddressCommand.setName("用户3");
		createUserAddressCommand.setProvinceId("330000");
		createUserAddressCommand.setCityId("330100");
		createUserAddressCommand.setAreaId("330108");
		createUserAddressCommand.setDetail("什么路几号");
		createUserAddressCommand.setMobile(user3.getContactInfo().getMobile());
		UserAddress address3 = userAddressService
				.createUserAddress(createUserAddressCommand);

		// 查看商品
		SKUProductQO skuProductQO = new SKUProductQO();
		skuProductQO.setProductQO(new ProductQO());
		skuProductQO.getProductQO().setId(product.getId());
		skuProductQO.setSortBySpecInfo(BaseQO.ORDER_DESC);
		List<SKUProduct> skuProducts = skuProductService
				.queryList(skuProductQO);

		// 有上级区域会员购买商品
		CreateOrderImmediateCommand createOrderImmediateCommand = new CreateOrderImmediateCommand();
		createOrderImmediateCommand.setUserId(user1.getId());
		createOrderImmediateCommand.setSkuProductId(skuProducts.get(0).getId());
		createOrderImmediateCommand.setNum(1);
		createOrderImmediateCommand.setAddessId(address1.getId());
		Order order1 = null;
		try {
			order1 = orderService
					.createOrderImmediate(createOrderImmediateCommand);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		// 无上级区域会员购买商品
		createOrderImmediateCommand.setUserId(user2.getId());
		createOrderImmediateCommand.setSkuProductId(skuProducts.get(1).getId());
		createOrderImmediateCommand.setNum(2);
		createOrderImmediateCommand.setAddessId(address2.getId());
		Order order2 = null;
		try {
			order2 = orderService
					.createOrderImmediate(createOrderImmediateCommand);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		// 有上级云商购买商品
		CreateShoppingCarItemCommand createShoppingCarItemCommand = new CreateShoppingCarItemCommand();
		createShoppingCarItemCommand
				.setSkuProductId(skuProducts.get(2).getId());
		createShoppingCarItemCommand.setNum(1);
		createShoppingCarItemCommand.setUserId(user3.getId());
		ShoppingCarItem shoppingCarItem1 = shoppingCarItemService
				.createShoppingCarItem(createShoppingCarItemCommand);

		createShoppingCarItemCommand
				.setSkuProductId(skuProducts.get(3).getId());
		createShoppingCarItemCommand.setNum(2);
		createShoppingCarItemCommand.setUserId(user3.getId());
		ShoppingCarItem shoppingCarItem2 = shoppingCarItemService
				.createShoppingCarItem(createShoppingCarItemCommand);

		CreateOrderFromShoppingCarCommand createOrderFromShoppingCarCommand = new CreateOrderFromShoppingCarCommand();
		createOrderFromShoppingCarCommand
				.setShoppingCarItemIds(new ArrayList<String>());
		createOrderFromShoppingCarCommand.getShoppingCarItemIds().add(
				shoppingCarItem1.getId());
		createOrderFromShoppingCarCommand.getShoppingCarItemIds().add(
				shoppingCarItem2.getId());
		createOrderFromShoppingCarCommand.setAddessId(address3.getId());
		createOrderFromShoppingCarCommand.setUserId(user3.getId());

		Order order3 = null;
		try {
			order3 = orderService
					.createOrderFromShoppingCar(createOrderFromShoppingCarCommand);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		// 支付
		OrderPaySuccessCommand orderPaySuccessCommand = new OrderPaySuccessCommand();
		orderPaySuccessCommand.setCashPayChannel("alipay");
		orderPaySuccessCommand.setOrderId(order1.getId());
		orderPaySuccessCommand.setPayDate(new Date());
		try {
			orderService.paySuccess(orderPaySuccessCommand);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		orderPaySuccessCommand.setCashPayChannel("wx");
		orderPaySuccessCommand.setOrderId(order2.getId());
		orderPaySuccessCommand.setPayDate(new Date());
		try {
			orderService.paySuccess(orderPaySuccessCommand);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		orderPaySuccessCommand.setCashPayChannel("unipay");
		orderPaySuccessCommand.setOrderId(order3.getId());
		orderPaySuccessCommand.setPayDate(new Date());
		try {
			orderService.paySuccess(orderPaySuccessCommand);
		} catch (OrderException e) {
			e.printStackTrace();
		}

		// 发货
		CreateDeliveryOrderCommand createDeliveryOrderCommand = new CreateDeliveryOrderCommand();
		createDeliveryOrderCommand.setOrderId(order1.getId());
		createDeliveryOrderCommand.setTrackingCompany("st");
		createDeliveryOrderCommand.setTrackingId("123456");
		Map<String, String> imeiMap1 = new HashMap<String, String>();
		for (OrderSKUItem orderSKUItem : order1.getOrderSKUItems()) {
			imeiMap1.put(orderSKUItem.getId(), "000001");
		}
		createDeliveryOrderCommand.setImeiMap(imeiMap1);
		deliveryOrderService.createDeliveryOrder(createDeliveryOrderCommand);

		createDeliveryOrderCommand.setOrderId(order2.getId());
		createDeliveryOrderCommand.setTrackingCompany("yt");
		createDeliveryOrderCommand.setTrackingId("123456");
		Map<String, String> imeiMap2 = new HashMap<String, String>();
		for (OrderSKUItem orderSKUItem : order2.getOrderSKUItems()) {
			imeiMap2.put(orderSKUItem.getId(), "000002,000003");
		}
		createDeliveryOrderCommand.setImeiMap(imeiMap2);
		deliveryOrderService.createDeliveryOrder(createDeliveryOrderCommand);

		createDeliveryOrderCommand.setOrderId(order3.getId());
		createDeliveryOrderCommand.setTrackingCompany("sf");
		createDeliveryOrderCommand.setTrackingId("123456");
		Map<String, String> imeiMap3 = new HashMap<String, String>();
		for (OrderSKUItem orderSKUItem : order3.getOrderSKUItems()) {
			imeiMap3.put(orderSKUItem.getId(), "000004,000005");
		}
		createDeliveryOrderCommand.setImeiMap(imeiMap3);
		deliveryOrderService.createDeliveryOrder(createDeliveryOrderCommand);

		// 收货
		FinishOrderCommand finishOrderCommand = new FinishOrderCommand();
		finishOrderCommand.setOrderId(order1.getId());
		finishOrderCommand.setAutoConfirm(false);
		try {
			orderService.finishOrder(finishOrderCommand);
		} catch (VirtualAccountException e) {
			e.printStackTrace();
		}

		finishOrderCommand.setOrderId(order2.getId());
		finishOrderCommand.setAutoConfirm(false);
		try {
			orderService.finishOrder(finishOrderCommand);
		} catch (VirtualAccountException e) {
			e.printStackTrace();
		}

		finishOrderCommand.setOrderId(order3.getId());
		finishOrderCommand.setAutoConfirm(false);
		try {
			orderService.finishOrder(finishOrderCommand);
		} catch (VirtualAccountException e) {
			e.printStackTrace();
		}

		// order1的购买 者user1（有上级的区域会员）认证为云商，要先实名认证

		sendSMSValidCodeCommand.setScene("register");
		sendSMSValidCodeCommand.setMobile("13511111111");
		SMSValidateSaga smsValidateSaga4 = null;
		try {
			smsValidateSaga4 = smsValidateSagaService
					.sendSMS(sendSMSValidCodeCommand);
		} catch (SagaException e) {
			e.printStackTrace();
		}

		ModifyUserRealInfoCommand modifyUserRealInfoCommand = new ModifyUserRealInfoCommand();
		modifyUserRealInfoCommand.setUserId(user1.getId());
		modifyUserRealInfoCommand.setIdCardNo("777777222222223333");
		modifyUserRealInfoCommand.setName("小明");
		modifyUserRealInfoCommand.setSagaId(smsValidateSaga4.getId());
		modifyUserRealInfoCommand.setSmsValidCode(smsValidateSaga4
				.getValidCode());
		try {
			userService.modifyUserRealInfo(modifyUserRealInfoCommand);
		} catch (SagaException | UserException e1) {
			e1.printStackTrace();
		}

		sendSMSValidCodeCommand.setScene("register");
		sendSMSValidCodeCommand.setMobile("13511111111");
		SMSValidateSaga smsValidateSaga5 = null;
		try {
			smsValidateSaga5 = smsValidateSagaService
					.sendSMS(sendSMSValidCodeCommand);
		} catch (SagaException e) {
			e.printStackTrace();
		}

		//	通过IMEI认证为云商
		AuthCloudMerchantCommand authCloudMerchantCommand = new AuthCloudMerchantCommand();
		authCloudMerchantCommand.setUserId(user1.getId());
		authCloudMerchantCommand.setImei("000001");
		authCloudMerchantCommand.setSagaId(smsValidateSaga5.getId());
		authCloudMerchantCommand.setSmsValidCode(smsValidateSaga5
				.getValidCode());
		try {
			userService.authCloudMerchantCommand(authCloudMerchantCommand);
		} catch (SagaException | VirtualAccountException | UserException e) {
			e.printStackTrace();
		}
		// 退换货
		
	}

	*//**
	 * 解冻、提现
	 *//*
	@Test
	public void unFreeze() {

		UnFreezeAllOKRebateCommand unFreezeAllOKRebateCommand = new UnFreezeAllOKRebateCommand();
		unFreezeAllOKRebateCommand.setUserId("100003"); // 陈云商
		try {
			orderRebateAccountService.unFreezeAllOKRebate(unFreezeAllOKRebateCommand);
		} catch (VirtualAccountException e1) {
			e1.printStackTrace();
		}
		
		// 申请提现
		ApplyRebateWithdrawCashCommand applyRebateWithdrawCashCommand = new ApplyRebateWithdrawCashCommand();
		applyRebateWithdrawCashCommand.setAmount(300D);
		applyRebateWithdrawCashCommand.setOutAccountId("32425142131321");
		applyRebateWithdrawCashCommand.setOutAccountType("ccb");
		applyRebateWithdrawCashCommand.setUserId("100003");
		WithdrawOrder withdrawOrder = null;
		try {
			withdrawOrder = rebateWithdrawOrderService
					.applyRebateWithdrawCash(applyRebateWithdrawCashCommand);
		} catch (VirtualAccountException e) {
			e.printStackTrace();
		}

		// 同意提现，往提现单加入paymentOrderId支付单号
		AgreeRebateWithdrawOrderCommand agreeRebateWithdrawOrderCommand = new AgreeRebateWithdrawOrderCommand();
		agreeRebateWithdrawOrderCommand.setWithdrawOrderId(withdrawOrder
				.getId());
		rebateWithdrawOrderService
				.agreeRebateWithdrawCash(agreeRebateWithdrawOrderCommand);

		// 刷新提现单，刷出paymentOrderId
		withdrawOrder = rebateWithdrawOrderService.get(withdrawOrder.getId());

		// 确认提现已成功
		ConfirmPaymentOrderSuccessCommand confirmPaymentOrderSuccessCommand = new ConfirmPaymentOrderSuccessCommand();
		confirmPaymentOrderSuccessCommand.setPaymentOrderId(withdrawOrder
				.getPaymentOrderId());
		paymentOrderService
				.confirmPaymentOrderSuccess(confirmPaymentOrderSuccessCommand);
	}

	@Test
	public void query() {
		// VirtualAccountChangeDetailQO virtualAccountChangeDetailQO = new
		// VirtualAccountChangeDetailQO();
		// virtualAccountChangeDetailQO
		// .setVirtualAccountQO(new VirtualAccountQO());
		// virtualAccountChangeDetailQO.getVirtualAccountQO().setId(
		// "58b9514110a74ab49e68df247923355b");
		// virtualAccountChangeDetailService
		// .queryUnique(virtualAccountChangeDetailQO);

		MarketingEntryQO qo = new MarketingEntryQO();
		qo.setSubjectQO(new MarketingSubjectQO());
		qo.setTargetQO(new MarketingTargetQO());
		qo.getSubjectQO().setDomainId("1");
		qo.getTargetQO().setDomainId("1");
		marketingEntryService.queryList(qo);
	}
}
*/