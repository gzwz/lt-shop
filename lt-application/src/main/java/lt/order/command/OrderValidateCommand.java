package lt.order.command;

import gzlazypack.common.component.BaseCommand;

@SuppressWarnings("serial")
public class OrderValidateCommand extends BaseCommand{

	private Double totalFee;
	
	private String outTradeNo;
	
	private Double WIDtotal_fee;
	
	private String WIDout_trade_no;

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Double getWIDtotal_fee() {
		return WIDtotal_fee;
	}

	public void setWIDtotal_fee(Double wIDtotal_fee) {
		WIDtotal_fee = wIDtotal_fee;
	}

	public String getWIDout_trade_no() {
		return WIDout_trade_no;
	}

	public void setWIDout_trade_no(String wIDout_trade_no) {
		WIDout_trade_no = wIDout_trade_no;
	}

	
	
	
}
