package lt.order.exception;

@SuppressWarnings("serial")
public class OrderException extends Exception {

	public final static String STOCK_NOT_FOUND = "stock_not_found";
	public final static String STOCK_NOT_ENOUGH = "stock_not_enough";
	public final static String ALREADY_PAID = "already_paid";
	public final static String ORDER_CANCEL = "order_cancel";
	public final static String ORDER_INFO_MISSING = "order_info_missing";
	public final static String PRODUCT_NOT_SALE = "product_not_sale";
	public final static String ORDER_FAIL = "order_fail";
	
	private String reason;

	public OrderException(String reason, String message) {
		super(message);
		setReason(reason);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
