package lt.order.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderMarketingInfo {

	/**
	 * 订单归属商
	 */
	@Column(name = "OPERATION_MERCHANT_ID")
	private String operationMerchantId;

	/**
	 * 商名称
	 */
	@Column(name = "OPERATION_MERCHANT_NAME")
	private String operationMerchantName;

	public String getOperationMerchantId() {
		return operationMerchantId;
	}

	public void setOperationMerchantId(String operationMerchantId) {
		this.operationMerchantId = operationMerchantId;
	}

	public String getOperationMerchantName() {
		return operationMerchantName;
	}

	public void setOperationMerchantName(String operationMerchantName) {
		this.operationMerchantName = operationMerchantName;
	}


}
