package lt.virtualAccount.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

import java.util.Date;


/**
 * 虚拟帐户查询条件
 * 
 * 
 */
@QueryConfig(daoBeanId = "virtualAccountService")
@SuppressWarnings("serial")
public class VirtualAccountQO extends BaseQO<String> {

	@QueryCondition(name = "currency", type = QueryConditionType.LEFT_JOIN)
	private VACurrencyQO VACurrencyQO;

	@QueryCondition(name = "currency", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchCurrency = false;

	@QueryCondition(name = "parent", type = QueryConditionType.LEFT_JOIN)
	private VirtualAccountQO parentQO;

	@QueryCondition(name = "parent.id", type = QueryConditionType.IS_NULL)
	private String mainAccount;

	@QueryCondition(name = "parent", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchParent = false;

	@QueryCondition(name = "baseInfo.name", ifTrueUseLike = "nameLike")
	private String name;

	private Boolean nameLike = false;

	/**
	 * 帐户归属者ID
	 */
	@QueryCondition(name = "owner.domainId")
	private String subjectId;

	/**
	 * 帐户归属者ID
	 */
	@QueryCondition(name = "owner.domainId", type = QueryConditionType.IN)
	private String[] subjectIds;

	/**
	 * 账户归属者Name
	 */
	@QueryCondition(name = "owner.domainName", ifTrueUseLike = "subjectNameLike")
	private String subjectName;

	private Boolean subjectNameLike = false;

	/**
	 * 帐户归属者类型
	 */
	@QueryCondition(name = "owner.domainType")
	private String subjectType;

	public final static String OWNER_TYPE_USER = "user"; // 个人用户
	public final static String OWNER_TYPE_MERCHANT = "merchant"; // 商户
	public final static String OWNER_TYPE_PLATFORM = "platform"; // 平台

	/**
	 * 是否已启用
	 */
	@QueryCondition(name = "status.enable")
	private Boolean enable;

	/**
	 * 是否已使用完关闭，不会再启用
	 */
	@QueryCondition(name = "status.close")
	private Boolean close;

	/**
	 * 最小总余额
	 */
	@QueryCondition(name = "balance.totalAmount", type = QueryConditionType.GE)
	private Double geTotalAmount;

	/**
	 * 最大总余额
	 */
	@QueryCondition(name = "balance.totalAmount", type = QueryConditionType.LE)
	private Double leTotalAmount;

	/**
	 * 最小可用余额
	 */
	@QueryCondition(name = "balance.avaiableAmount", type = QueryConditionType.GE)
	private Double geAvaiableAmount;

	/**
	 * 最大可用余额
	 */
	@QueryCondition(name = "balance.avaiableAmount", type = QueryConditionType.LE)
	private Double leAvaiableAmount;

	/**
	 * 已经过了失效时间的
	 */
	@QueryCondition(name = "baseInfo.invalidDate", type = QueryConditionType.LT)
	private Date ltInvalidDate;

	/**
	 * 帐号用途业用类型
	 */
	@QueryCondition(name = "businessType")
	private String businessType;

	/**
	 * 查某商户的主帐户
	 * 
	 * @param userId
	 */
	public void queryMerchantMainAccount(String userId) {
		setSubjectId(userId);
		setSubjectType("merchant");
		setMainAccount("yes");
	}

	/**
	 * 查某商户已到期应解冻尚未解冻的返佣冻结帐户
	 * 
	 * @param userId
	 */
	public void queryMerchantFreezeRebateAccount(String userId) {
		setSubjectId(userId);
		setSubjectType("merchant");
		setClose(false);
		setBusinessType("prepared_rebate_frozen");
		setLtInvalidDate(new Date());
	}

	public Boolean getFetchCurrency() {
		return fetchCurrency;
	}

	public void setFetchCurrency(Boolean fetchCurrency) {
		this.fetchCurrency = fetchCurrency;
	}

	public VirtualAccountQO getParentQO() {
		return parentQO;
	}

	public void setParentQO(VirtualAccountQO parentQO) {
		this.parentQO = parentQO;
	}

	public Boolean getFetchParent() {
		return fetchParent;
	}

	public void setFetchParent(Boolean fetchParent) {
		this.fetchParent = fetchParent;
	}

	public VACurrencyQO getVACurrencyQO() {
		return VACurrencyQO;
	}

	public void setVACurrencyQO(VACurrencyQO VACurrencyQO) {
		this.VACurrencyQO = VACurrencyQO;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String[] getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String[] subjectIds) {
		this.subjectIds = subjectIds;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Boolean getSubjectNameLike() {
		return subjectNameLike;
	}

	public void setSubjectNameLike(Boolean subjectNameLike) {
		this.subjectNameLike = subjectNameLike;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public Double getGeTotalAmount() {
		return geTotalAmount;
	}

	public void setGeTotalAmount(Double geTotalAmount) {
		this.geTotalAmount = geTotalAmount;
	}

	public Double getLeTotalAmount() {
		return leTotalAmount;
	}

	public void setLeTotalAmount(Double leTotalAmount) {
		this.leTotalAmount = leTotalAmount;
	}

	public Double getGeAvaiableAmount() {
		return geAvaiableAmount;
	}

	public void setGeAvaiableAmount(Double geAvaiableAmount) {
		this.geAvaiableAmount = geAvaiableAmount;
	}

	public Double getLeAvaiableAmount() {
		return leAvaiableAmount;
	}

	public void setLeAvaiableAmount(Double leAvaiableAmount) {
		this.leAvaiableAmount = leAvaiableAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getNameLike() {
		return nameLike;
	}

	public void setNameLike(Boolean nameLike) {
		this.nameLike = nameLike;
	}

	public String getMainAccount() {
		return mainAccount;
	}

	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Date getLtInvalidDate() {
		return ltInvalidDate;
	}

	public void setLtInvalidDate(Date ltInvalidDate) {
		this.ltInvalidDate = ltInvalidDate;
	}

}
