package lt.merchant.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;
/**
 * 验证
 * @author wangxiaoping
 *
 */
@Embeddable
public class ValidateStatus{
	
	/**
	 * 身份证正面验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "front_valid")
	private Boolean frontValid;
	
	/**
	 * 身份证反面验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "opposite_valid")
	private Boolean oppositeValid;
	
	/**
	 * 身份证验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "identify_valid")
	private Boolean identifyValid;
	
	/**
	 * 营业执照验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "charter_valid")
	private Boolean charterValid;
	
	/**
	 * 组织机构代码验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "organizations_valid")
	private Boolean organizationsValid;
	
	/**
	 * 登记证书验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "registration_valid")
	private Boolean registrationValid;
	
	/**
	 * 纳税人资质验证状态
	 */
	@Type(type = "yes_no")
	@Column(name = "aptitudes_valid")
	private Boolean aptitudesValid;

	public Boolean getFrontValid() {
		return frontValid;
	}

	public void setFrontValid(Boolean frontValid) {
		this.frontValid = frontValid;
	}

	public Boolean getOppositeValid() {
		return oppositeValid;
	}

	public void setOppositeValid(Boolean oppositeValid) {
		this.oppositeValid = oppositeValid;
	}

	public Boolean getIdentifyValid() {
		return identifyValid;
	}

	public void setIdentifyValid(Boolean identifyValid) {
		this.identifyValid = identifyValid;
	}

	public Boolean getCharterValid() {
		return charterValid;
	}

	public void setCharterValid(Boolean charterValid) {
		this.charterValid = charterValid;
	}

	public Boolean getOrganizationsValid() {
		return organizationsValid;
	}

	public void setOrganizationsValid(Boolean organizationsValid) {
		this.organizationsValid = organizationsValid;
	}

	public Boolean getRegistrationValid() {
		return registrationValid;
	}

	public void setRegistrationValid(Boolean registrationValid) {
		this.registrationValid = registrationValid;
	}

	public Boolean getAptitudesValid() {
		return aptitudesValid;
	}

	public void setAptitudesValid(Boolean aptitudesValid) {
		this.aptitudesValid = aptitudesValid;
	}

}
