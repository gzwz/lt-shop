package lt.ad.command;


import java.io.Serializable;
import java.util.Date;

import lt.admin.entity.Admin;
import lt.product.entity.Product;

@SuppressWarnings("serial")
public class AdCommand implements Serializable{
	
	/**
	 *	广告名字 
	 */
	private String adId;
	
	
	private Product productId;
	/**
	 *	广告名字 
	 */
	private String adName;
	
	/**
	 * 	广告位置
	 * 	1：亿元购车下
	 * 	2：卡车商城下
	 * 	3：轿车下面
	 * 
	 */
	private String adPosition;
	
	/**
	 * 图片
	 */
	private String image;
	
	/**
	 * 	广告链接
	 */
	private String url;
	
	/**
	 * 	媒介类型：文字 flash ... 
	 */
//	private String mediaTypes;
	
	/**
	 * 	创建时间
	 */
	private Date createTime;
	
	/**
	 * 	开始时间
	 */
	private Date startTime;
	
	/**
	 *  结束时间
	 */
	private Date endTime;
	
	/**
	 * 	广告状态，是否可用
	 */
	private String status;

	/**
	 * 点击次数
	 */
//	private Integer clickNum;
	
	/**
	 * 操作人员id
	 */
	private Admin userId;
	
	private Integer sort;

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Admin getUserId() {
		return userId;
	}

	public void setUserId(Admin userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

}
