package lt.order.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 发票信息
 * 
 * @author yuxx
 * 
 */
@Embeddable
public class OrderInvoiceInfo {

	/**
	 * 发票类型
	 */
	@Column(name = "INVOICE_TYPE")
	private String type;

	public final static String INVOICE_TYPE_PERSONAL = "personal"; // 个人
	public final static String INVOICE_TYPE_ENTERPRISE = "enterprise"; // 企业

	/**
	 * 发票抬头
	 */
	@Column(name = "INVOICE_TITLE")
	private String title;

	/**
	 * 发票内容
	 */
	@Column(name = "INVOICE_CONTENT", length = 256)
	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
