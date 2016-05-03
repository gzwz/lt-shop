package lt.pay.alipay.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import lt.pay.alipay.entity.Alipay;
import lt.pay.alipay.entity.AlipayBank;
import lt.pay.alipay.qo.AlipayQO;
import lt.pay.alipay.util.AlipaySubmit;
import lt.pay.utils.RandomUtil;
import lt.pay.utils.UrlUtil;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.DateUtil;

import org.dom4j.DocumentException;
import org.hibernate.Criteria;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AlipayService extends BaseDao<Alipay,AlipayQO>{
			
	
	public void WebPay(Alipay payInfo,HttpServletResponse res) throws MalformedURLException, DocumentException, IOException, JDOMException{
		if(payInfo.getOutTradeNo() == null || "".equals(payInfo.getOutTradeNo())) {
			payInfo.setOutTradeNo(DateUtil.getDate()+RandomUtil.numberString(3));
		}
		//读取配置文件 并加载到map 
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", UrlUtil.readUrl("pay_service"));
        sParaTemp.put("partner", UrlUtil.readUrl("partner"));
        sParaTemp.put("seller_email", UrlUtil.readUrl("seller_email"));
        sParaTemp.put("_input_charset", UrlUtil.readUrl("input_charset"));
		sParaTemp.put("payment_type", UrlUtil.readUrl("payment_type"));
		sParaTemp.put("notify_url", UrlUtil.readUrl("notify_url"));
		sParaTemp.put("return_url", UrlUtil.readUrl("return_url"));
		sParaTemp.put("out_trade_no", payInfo.getOutTradeNo());
		sParaTemp.put("subject", payInfo.getSubject());
		sParaTemp.put("total_fee", payInfo.getTotalFee().toString());
		sParaTemp.put("body", payInfo.getBody());
		sParaTemp.put("show_url", payInfo.getShowUrl());
		sParaTemp.put("anti_phishing_key", AlipaySubmit.query_timestamp());
		sParaTemp.put("exter_invoke_ip", UrlUtil.readUrl("exter_invoke_ip"));
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
		HttpServletResponse response = res;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sHtmlText);
	}
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, AlipayQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Alipay> getEntityClass() {
		// TODO Auto-generated method stub
		return Alipay.class;
	}

}
