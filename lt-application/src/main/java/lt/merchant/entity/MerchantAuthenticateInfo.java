package lt.merchant.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;







import lt.merchant.command.CreateMerchantCommand;
import lt.merchant.command.ModifyMerchantCommand;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 认证信息
 * @author wangxiaoping
 *
 */
@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "merchant_Authenticate_Info")
public class MerchantAuthenticateInfo extends StringIdBaseEntity{

	
	@OneToOne(cascade= CascadeType.ALL ,fetch=FetchType.LAZY,targetEntity=Merchant.class)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	
	/**
	 * 身份证正面
	 */
	@Column(name = "front", length = 500)
	private String front;
	
	/**
	 * 身份证反面
	 * 
	 */
	@Column(name = "opposite", length = 500)
	private String opposite;
	
	/**
	 * 身份证图片
	 */
	@Column(name = "identify_image", length = 500)
	private String identifyImage;
	
	/**
	 * 营业执照图片
	 */
	@Column(name = "charter_image", length = 500)
	private String charterImage;
	
	/**
	 * 组织机构代码证图片
	 */
	@Column(name = "organizations_image", length = 500)
	private String organizationsImage;
	
	/**
	 * 登记证书图片
	 */
	@Column(name = "registration_image", length = 500)
	private String registrationImage;
	
	/**
	 * 纳税人资质证书(图片)
	 */
	@Column(name = "aptitudes_image", length = 500)
	private String aptitudesImage;
	
	public void create(CreateMerchantCommand command,Merchant merchant){
		
		setId(UUIDGenerator.getUUID());
		
		setAptitudesImage(command.getAptitudesImage());
		setCharterImage(command.getCharterImage());
		setFront(command.getFront());
		setIdentifyImage(command.getIdentifyImage());
		setMerchant(merchant);
		setOpposite(command.getOpposite());
		setOrganizationsImage(command.getOrganizationsImage());
		setRegistrationImage(command.getRegistrationImage());
	}
	
  public void modify(ModifyMerchantCommand command,Merchant merchant){
		
		
		setAptitudesImage(command.getAptitudesImage());
		setCharterImage(command.getCharterImage());
		setFront(command.getFront());
		setIdentifyImage(command.getIdentifyImage());
		setMerchant(merchant);
		setOpposite(command.getOpposite());
		setOrganizationsImage(command.getOrganizationsImage());
		setRegistrationImage(command.getRegistrationImage());
	}
	
	
	

	public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getOpposite() {
		return opposite;
	}

	public void setOpposite(String opposite) {
		this.opposite = opposite;
	}


	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getIdentifyImage() {
		return identifyImage;
	}

	public void setIdentifyImage(String identifyImage) {
		this.identifyImage = identifyImage;
	}

	public String getCharterImage() {
		return charterImage;
	}

	public void setCharterImage(String charterImage) {
		this.charterImage = charterImage;
	}

	public String getOrganizationsImage() {
		return organizationsImage;
	}

	public void setOrganizationsImage(String organizationsImage) {
		this.organizationsImage = organizationsImage;
	}

	public String getRegistrationImage() {
		return registrationImage;
	}

	public void setRegistrationImage(String registrationImage) {
		this.registrationImage = registrationImage;
	}

	public String getAptitudesImage() {
		return aptitudesImage;
	}

	public void setAptitudesImage(String aptitudesImage) {
		this.aptitudesImage = aptitudesImage;
	}
	
	
}
