package lt.ad.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.ad.command.AdCommand;
import lt.admin.entity.Admin;

import org.hibernate.annotations.DynamicUpdate;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.AD_PREFIX + "Advertisement")
public class Advertisement extends StringIdBaseEntity {
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product productId;*/

	/**
	 *	广告名字 
	 */
	private String adName;
	
	/**
	 * 	广告位置
	 * 	
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
	private String mediaTypes;
	
	
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

	public final static String STATUS_ENABLE = "enable";
	public final static String STATUS_DISABLE = "disable";
	public final static String STATUS_REMOVE = "remove";

	
	/**
	 * 点击次数
	 */
	private Integer clickNum;
	
	/**
	 * 操作人员id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Admin userId;

	/**
	 * 排序
	 */
	@Column(name = "sort", columnDefinition = M.NUM_COLUMN)
	private Integer sort;
	
	public void create(AdCommand command,Admin admin) {
		// TODO Auto-generated method stub
		setId(UUIDGenerator.getUUID());
		setCreateTime(new Date());
		setClickNum(0);
		setMediaTypes("未知");
		
		setAdName(command.getAdName());
		setAdPosition(command.getAdPosition());
		setImage(command.getImage());
		setUrl(command.getUrl());
		setStartTime(command.getStartTime());
		setEndTime(command.getEndTime());
		setUserId(admin);
		setStatus(command.getStatus());
		setSort(command.getSort());
		
	}
	
	public void edit(AdCommand command, Admin admin) {
		// TODO Auto-generated method stub
		setMediaTypes("未知");
		
		setAdName(command.getAdName());
		setAdPosition(command.getAdPosition());
		setImage(command.getImage());
		setUrl(command.getUrl());
		setStartTime(command.getStartTime());
		setEndTime(command.getEndTime());
		setUserId(admin);
		setStatus(command.getStatus());
		setSort(command.getSort());
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getMediaTypes() {
		return mediaTypes;
	}


	public void setMediaTypes(String mediaTypes) {
		this.mediaTypes = mediaTypes;
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


	public Integer getClickNum() {
		return clickNum;
	}


	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
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



	
}
