package lt.merchant.entity;

import java.util.Date;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.base.entity.DomainLink;
import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;
import lt.user.entity.User;

import org.hibernate.annotations.DynamicUpdate;

import gzlazypack.common.util.UUIDGenerator;


@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "MERCHANT")
public class Merchant extends StringIdBaseEntity{
	
	/**
	 * 商家基本信息
	 */
	private MerchantBaseInfo baseInfo;
    
    /**
     * 验证状态
     */
    private ValidateStatus status;
    
    /**
	 * 所属业务实体id
	 */
	private DomainLink domainLink;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
    
	
	
    
	/**
	 * 所属店铺类别
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_category_id")
	private MerchantCategory merchantCategory;

	public void create(CreateMerchantCommand command,User user,MerchantCategory merchantCategory){
		
		setId(UUIDGenerator.getUUID());
		
		setBaseInfo(new MerchantBaseInfo());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setIntroduction(command.getIntroduction());
		getBaseInfo().setHeadImage(command.getHeadImage());
		getBaseInfo().setMainCore(command.getMainCore());
		
		
		
		setStatus(new ValidateStatus());
		getStatus().setFrontValid(false);
		getStatus().setOppositeValid(false);
		getStatus().setIdentifyValid(false);
		getStatus().setCharterValid(false);
		getStatus().setOrganizationsValid(false);
		getStatus().setRegistrationValid(false);
		getStatus().setAptitudesValid(false);
		
		setDomainLink(new DomainLink());
		getDomainLink().setDomainId(user.getId());
		getDomainLink().setDomainName(user.getBaseInfo().getName());
		getDomainLink().setDomainType(user.getStatus().getValidateShop());
		
		setCreateTime(new Date());
		
		setMerchantCategory(merchantCategory);
	}
	
	public void modify(ModifyMerchantCommand command){
		
		setBaseInfo(new MerchantBaseInfo());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setIntroduction(command.getIntroduction());
		getBaseInfo().setHeadImage(command.getHeadImage());
		
		setDomainLink(new DomainLink());
		getDomainLink().setDomainId(command.getDomainId());
		getDomainLink().setDomainName(command.getDomainName());
		getDomainLink().setDomainType(command.getDomainType());
	}
	
	
	public void modifyMerchant(ModifyMerchantCommand command,MerchantCategory merchantCategory){
		
		setBaseInfo(new MerchantBaseInfo());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setIntroduction(command.getIntroduction());
		getBaseInfo().setHeadImage(command.getHeadImage());
		getBaseInfo().setMainCore(command.getMainCore());
		
		setMerchantCategory(merchantCategory);
	}
	

	public MerchantBaseInfo getBaseInfo() {
		return baseInfo;
	}



	public void setBaseInfo(MerchantBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}



	public ValidateStatus getStatus() {
		return status;
	}

	public void setStatus(ValidateStatus status) {
		this.status = status;
	}

	public DomainLink getDomainLink() {
		return domainLink;
	}

	public void setDomainLink(DomainLink domainLink) {
		this.domainLink = domainLink;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MerchantCategory getMerchantCategory() {
		return merchantCategory;
	}

	public void setMerchantCategory(MerchantCategory merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	
}
