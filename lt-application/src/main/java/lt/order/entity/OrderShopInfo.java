package lt.order.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 订单所属的线上店铺
 * @author yuxx
 *
 */
@Embeddable
public class OrderShopInfo {
	
	/**
	 * 订单归属的线上店铺信息
	 */
	@Column(name = "ONLINE_SHOP_ID")
	private String onlineShopId;
	
	/**
	 * 订单归属的线上店铺名称
	 */
	@Column(name = "ONLINE_SHOP_NAME")
	private String onlineShopName;

	public String getOnlineShopId() {
		return onlineShopId;
	}

	public void setOnlineShopId(String onlineShopId) {
		this.onlineShopId = onlineShopId;
	}

	public String getOnlineShopName() {
		return onlineShopName;
	}

	public void setOnlineShopName(String onlineShopName) {
		this.onlineShopName = onlineShopName;
	}
	
	
}
