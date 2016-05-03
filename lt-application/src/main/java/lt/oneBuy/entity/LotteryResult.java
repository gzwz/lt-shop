package lt.oneBuy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lt.order.entity.Order;
import lt.order.entity.OrderBuyerInfo;

import org.hibernate.annotations.DynamicUpdate;

import gzlazypack.common.component.StringIdBaseEntity;

@SuppressWarnings("serial")
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "LotteryResult")
public class LotteryResult extends StringIdBaseEntity {
	

	/**
	 * 	一元购商品id
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "one_buy_id" ,nullable = false)
	private OneBuy oneBuy;
	
	/**
	 * 	订单id
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id" ,nullable = false)
	private Order order;
	
	/**
	 * 	购买者id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyerInfo_id" ,nullable = false)
	private OrderBuyerInfo buyerInfo;
	
	/**
	 * 	重庆时时彩 开奖期数
	 */
	@Column(length = 20)
	private String expect;

	/**
	 * 	时时彩开奖号码
	 */
	@Column(length = 10)
	private String opencode;
	
	/**
	 * 	时时彩开奖时间
	 */
	@Column
	private Date opentime;

	/**
	 * 开奖是时间戳
	 */
	@Column(length = 20)
	private String opentimestamp;
	
	/**
	 *  本系统开奖时间
	 */
	@Column
	private Date openLotteryTime;

	/**
	 *  中奖 幸运号
	 */
	private Integer lotteryNum;
	

	public Integer getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(Integer lotteryNum) {
		this.lotteryNum = lotteryNum;
	}


	public OrderBuyerInfo getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(OrderBuyerInfo buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getOpencode() {
		return opencode;
	}

	public void setOpencode(String opencode) {
		this.opencode = opencode;
	}

	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	public String getOpentimestamp() {
		return opentimestamp;
	}

	public void setOpentimestamp(String opentimestamp) {
		this.opentimestamp = opentimestamp;
	}

	public Date getOpenLotteryTime() {
		return openLotteryTime;
	}

	public void setOpenLotteryTime(Date openLotteryTime) {
		this.openLotteryTime = openLotteryTime;
	}

	public OneBuy getOneBuy() {
		return oneBuy;
	}

	public void setOneBuy(OneBuy oneBuy) {
		this.oneBuy = oneBuy;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
