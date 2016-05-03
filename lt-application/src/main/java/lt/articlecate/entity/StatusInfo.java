package lt.articlecate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

@Embeddable
public class StatusInfo {

	/**
	 * 是否外连
	 */
	@Type(type = "yes_no")
	@Column(name = "is_rel_url")
	private Boolean isRelUrl;
	
	/**
	 * 本周必读
	 */
	@Type(type = "yes_no")
	@Column(name = "is_week")
	private Boolean isWeek;
	/**
	 * 热门
	 */
	@Type(type = "yes_no")
	@Column(name = "is_hot")
	private Boolean isHot;
	
	/**
	 * 
	 */
	@Type(type = "yes_no")
	@Column(name = "is_special")
	private Boolean isSpecial;
	

	
	/**
	 * 有效性标识符
	 */
	@Type(type = "yes_no")
	@Column(name = "is_effect")
	private Boolean isEffect;

	public Boolean getIsWeek() {
		return isWeek;
	}

	public void setIsWeek(Boolean isWeek) {
		this.isWeek = isWeek;
	}

	public Boolean getIsHot() {
		return isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}


	public Boolean getIsRelUrl() {
		return isRelUrl;
	}

	public void setIsRelUrl(Boolean isRelUrl) {
		this.isRelUrl = isRelUrl;
	}

	public Boolean getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}
	
	
}
