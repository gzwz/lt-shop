package lt.virtualAccount.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.virtualAccount.command.CreateVAChangeDetailCommand;

import org.hibernate.annotations.DynamicUpdate;


/**
 * 帐户变动明细
 * 
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = M.TABLE_PREFIX + "VA_CHANGE_DETAIL")
@DynamicUpdate
public class VirtualAccountChangeDetail extends StringIdBaseEntity {

	/**
	 * 发生变动的帐户
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIRTUAL_ACCOUNT_ID")
	private VirtualAccount virtualAccount;

	/**
	 * 变动金额
	 */
	@Column(name = "AMOUNT", columnDefinition = M.MONEY_COLUMN)
	private Double amount;

	/**
	 * 帐户原有余额
	 */
	@Column(name = "OLD_BALANCE", columnDefinition = M.MONEY_COLUMN)
	private Double oldBalance;

	/**
	 * 帐户新余额
	 */
	@Column(name = "NEW_BANLANCE", columnDefinition = M.MONEY_COLUMN)
	private Double newBanlance;

	/**
	 * 变动业务类型
	 */
	@Column(name = "BUSINESS_TYPE", length = 32)
	private String businessType;

	/**
	 * 变动类型
	 */
	@Column(name = "TYPE", length = 32)
	private String type;

	/**
	 * 备注信息
	 */
	@Column(name = "REMARK", columnDefinition = M.TEXT_COLUMN)
	private String remark;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_DATE", columnDefinition = M.DATE_COLUMN)
	private Date createDate;

	public void create(CreateVAChangeDetailCommand command, VirtualAccount virtualAccount) {
		setId(UUIDGenerator.getUUID());

		setVirtualAccount(virtualAccount);
		
		setAmount(command.getAmount());
		setNewBanlance(command.getNewBanlance());
		setOldBalance(command.getOldBalance());
		setBusinessType(command.getBusinessType());
		setType(command.getType());
		setRemark(command.getRemark());
	}

	public VirtualAccount getVirtualAccount() {
		return virtualAccount;
	}

	public void setVirtualAccount(VirtualAccount virtualAccount) {
		this.virtualAccount = virtualAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(Double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public Double getNewBanlance() {
		return newBanlance;
	}

	public void setNewBanlance(Double newBanlance) {
		this.newBanlance = newBanlance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

}
