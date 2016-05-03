package lt.oneBuy.dto;

import lt.base.dto.BaseDTO;

@SuppressWarnings("serial")
public class ResultSSCDTO extends BaseDTO {
	
	 private String productId;
	 
	 private String userId;
	 
	 private String userName;
	 
	 private String mobile;
	 
	 private String lottery;
	 

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLottery() {
		return lottery;
	}

	public void setLottery(String lottery) {
		this.lottery = lottery;
	}
}
