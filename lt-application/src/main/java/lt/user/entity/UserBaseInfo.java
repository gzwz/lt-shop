package lt.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;



/**
 * 用户基本信息
 * 
 * @author wxp
 * 
 */
@Embeddable
public class UserBaseInfo {
	
	/**
	 * 昵称
	 */
	@Column(name = "nick_name")
	private String nickName;

	/**
	 * 真实姓名
	 */
	@Column(name = "name", length = 64)
	private String name;

	/**
	 * 身份证号
	 */
	@Column(name = "id_card_no", length = 32)
	private String idCardNo;

	/**
	 * 头像
	 */
	@Column(name = "head_image", length = 1000)
	private String headImage;

	/**
	 * 积分
	 */
	@Column(name = "integral")
	private Integer integral;
	
	/**
	 * 注册时间
	 */
	@Column(name = "register_time")
	private Date registerTime;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}


	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
}
