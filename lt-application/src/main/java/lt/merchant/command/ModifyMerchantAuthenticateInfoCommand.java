package lt.merchant.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class ModifyMerchantAuthenticateInfoCommand extends BaseCommand{
	
private String merchantId;
	
	/**
	 * 身份证正面
	 */
	private String front;
	
	/**
	 * 身份证反面
	 * 
	 */
	private String opposite;
	
	/**
	 * 身份证图片
	 */
	private String identifyImage;
	
	/**
	 * 营业执照图片
	 */
	private String charterImage;
	
	/**
	 * 组织机构代码证图片
	 */
	private String organizationsImage;
	
	/**
	 * 登记证书图片
	 */
	private String registrationImage;
	
	/**
	 * 纳税人资质证书(图片)
	 */
	private String aptitudesImage;

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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
}
