package lt.virtualAccount.command;

import gzlazypack.common.component.BaseCommand;

import java.util.Date;


/**
 * 创建虚拟帐户
 * 
 * 
 */
@SuppressWarnings("serial")
public class CreateVirtualAccountCommand extends BaseCommand {

	/**
	 * 币种
	 */
	private String VACurrencyId;

	/**
	 * 帐号名称
	 */
	private String name;

	/**
	 * 失效时间
	 */
	private Date invalidDate;

	/**
	 * 父帐号
	 */
	private String parentId;

	/**
	 * 帐号归属主体名称
	 */
	private String ownerName;

	/**
	 * 帐号归属主体ID
	 */
	private String ownerId;

	/**
	 * 帐号归属主体类型
	 */
	private String ownerType;

	public final static String TYPE_MERCHANT = "merchant"; // 用户
	public final static String TYPE_PLATFORM = "platform"; // 平台

	/*
	 * 账户余额
	 */
	private double totalAmount = 1D;

	/**
	 * 帐户用途业务类型
	 */
	private String businessType;

	public String getVACurrencyId() {
		return VACurrencyId;
	}

	public void setVACurrencyId(String VACurrencyId) {
		this.VACurrencyId = VACurrencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}
