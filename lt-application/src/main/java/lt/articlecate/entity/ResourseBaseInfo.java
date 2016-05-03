package lt.articlecate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ResourseBaseInfo {
	
	/**
	 * 自动跳转的外 引用外链地址
	 */
	@Column(name = "rel_url",length = 255)
	private String relUrl;
	
	
	/**
	 * 作者
	 */
	@Column(name = "auther",length = 64)
	private String auther;
	/**
	 * 来源
	 */
	@Column(name = "suorse",length = 64)
	private String suorse;
	public String getRelUrl() {
		return relUrl;
	}
	public void setRelUrl(String relUrl) {
		this.relUrl = relUrl;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getSuorse() {
		return suorse;
	}
	public void setSuorse(String suorse) {
		this.suorse = suorse;
	}
	
}
