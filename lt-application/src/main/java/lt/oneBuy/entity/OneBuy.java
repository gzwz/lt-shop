package lt.oneBuy.entity;

import java.util.Date;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.oneBuy.command.CreateOneBuyCommand;
import lt.oneBuy.command.ModifyOneBuyCommand;

import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "ONEBUY")
public class OneBuy extends StringIdBaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cloud_category_id", nullable = false)
	private CloudCategory cloudCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cloudBrand_id", nullable = false)
	private CloudBrand cloudBrand;
	
	/**
	 * 基本信息
	 */
	private ShowBaseInfo baseInfo;

	/**
	 * 数目记录
	 */
	private NumberCountInfo countInfo;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 状态
	 */
	private String status;

	public final static String STATUS_CREATED = "create"; // 创建
	public final static String STATUS_ENABLE = "enable"; // 上架
	public final static String STATUS_DISABLE = "disable"; // 下架
	public final static String STATUS_REMOVE = "remove"; // 已被删除
	
	@Column(name = "show_status",length = 20)
	private String showStatus;
	public final static String SHOW_NORMAL = "normal"; // 正常情况
	public final static String SHOW_WILL_PUBLISH =  "willPublish"; // 即将揭晓
	public final static String SHOW_NEW_PUBLISHED = "newPublished"; // 最新揭晓
	
	public void changeWillPublish() {
		// TODO Auto-generated method stub
		setShowStatus(SHOW_WILL_PUBLISH);
	}
	
	public void changeNewPublish() {
		// TODO Auto-generated method stub
		setShowStatus(SHOW_NEW_PUBLISHED);
	}
	
	/**
	 * 上架时间
	 */
	private Date enableDate;

	public void modify(ModifyOneBuyCommand command) {

		setBaseInfo(new ShowBaseInfo());
		getBaseInfo().setTitleImage(command.getTitleImage());
		getBaseInfo().setIntro(command.getIntro());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setOriginalPrice(command.getOriginalPrice());

		getBaseInfo().setPrice(command.getPrice());
		getBaseInfo().setMobileDetail(command.getMobileDetail());
		getBaseInfo().setPcDetail(command.getPcDetail());
	}

	/**
	 * 创建
	 * 
	 * @param command
	 * @param brand
	 * @param productCategory
	 */
	public void create(CreateOneBuyCommand command,
			CloudCategory cloudCategory, CloudBrand cloudBrand) {
		setId(UUIDGenerator.getUUID());
		setStatus(STATUS_CREATED); // 创建
		setShowStatus(SHOW_NORMAL);
		setEnableDate(new Date());

		setBaseInfo(new ShowBaseInfo());
		getBaseInfo().setName(command.getName());
		getBaseInfo().setOriginalPrice(command.getOriginalPrice());
		getBaseInfo().setTitleImage(command.getTitleImage());
		getBaseInfo().setIntro(command.getIntro());
		getBaseInfo().setPrice(command.getPrice());
		getBaseInfo().setMobileDetail(command.getMobileDetail());
		getBaseInfo().setPcDetail(command.getPcDetail());
		getBaseInfo().setHot(false);

		setCountInfo(new NumberCountInfo());
		getCountInfo().setPartakeCount(command.getPartakeCount());
		getCountInfo().setTotalCount(command.getTotalCount());
		getCountInfo().setResidueCount(
				command.getTotalCount() - command.getPartakeCount());

		// ////////
		setCreateTime(new Date());
		setCloudBrand(cloudBrand);
		setCloudCategory(cloudCategory);

	}

	/**
	 * 下架
	 */
	public void disable() {
		setStatus(STATUS_DISABLE);
	}

	/**
	 * 上架
	 */
	public void enable() {
		setStatus(STATUS_ENABLE);
		setEnableDate(new Date());
	}
	
	public void t(){
		getBaseInfo().setHot(true);
	}

	public void f(){
		getBaseInfo().setHot(false);
	}
	
	public CloudCategory getCloudCategory() {
		return cloudCategory;
	}

	public void setCloudCategory(CloudCategory cloudCategory) {
		this.cloudCategory = cloudCategory;
	}

	public CloudBrand getCloudBrand() {
		return cloudBrand;
	}

	public void setCloudBrand(CloudBrand cloudBrand) {
		this.cloudBrand = cloudBrand;
	}

	public ShowBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(ShowBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public NumberCountInfo getCountInfo() {
		return countInfo;
	}

	public void setCountInfo(NumberCountInfo countInfo) {
		this.countInfo = countInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	
	
}
