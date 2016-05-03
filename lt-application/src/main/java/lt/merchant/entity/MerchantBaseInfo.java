package lt.merchant.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * 商家基本信息
 * @author wangxiaoping
 *
 */

@Embeddable
public class MerchantBaseInfo {

	/**
	 * 店名
	 */
	@Column(name = "name", length = 128)
	private String name;
	
	/**
	 * 店铺简介(备用字段)
	 */
	@Column(name = "introduction", columnDefinition = M.TEXT_COLUMN)
	private String introduction;
	
	/**
	 * 主营
	 */
	@Column(name = "main_core", columnDefinition = M.TEXT_COLUMN)
	private String mainCore;
	
	
	/**
	 * 店铺logo
	 */
	@Column(name = "head_image", length = 256)
	private String headImage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getMainCore() {
		return mainCore;
	}

	public void setMainCore(String mainCore) {
		this.mainCore = mainCore;
	}

	
	
}
