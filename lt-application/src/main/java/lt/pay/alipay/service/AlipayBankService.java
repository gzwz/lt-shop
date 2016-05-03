package lt.pay.alipay.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import lt.pay.alipay.entity.AlipayBank;
import lt.pay.alipay.qo.AlipayBankQO;
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
public class AlipayBankService extends BaseDao<AlipayBank,AlipayBankQO>{
	
	
	//默认支付方式
			String paymethod = "bankPay";
			
			public void WebPayBank(AlipayBank payInfo,HttpServletResponse res) throws MalformedURLException, DocumentException, IOException, JDOMException{
				if(payInfo.getWIDout_trade_no() == null || "".equals(payInfo.getWIDout_trade_no())) {
					payInfo.setWIDout_trade_no(DateUtil.getDate()+RandomUtil.numberString(3));
				}
				//读取配置文件 并加载到map 
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", UrlUtil.readUrl("pay_service"));
		        sParaTemp.put("partner", UrlUtil.readUrl("partner"));
		        sParaTemp.put("seller_email", UrlUtil.readUrl("seller_email"));
		        sParaTemp.put("_input_charset", UrlUtil.readUrl("input_charset"));
				sParaTemp.put("payment_type", UrlUtil.readUrl("payment_type"));
				sParaTemp.put("notify_url", UrlUtil.readUrl("bank_notify_url"));
				sParaTemp.put("return_url", UrlUtil.readUrl("bank_return_url"));
				sParaTemp.put("out_trade_no", payInfo.getWIDout_trade_no());
				sParaTemp.put("subject", payInfo.getWIDsubject());
				sParaTemp.put("total_fee", payInfo.getWIDtotal_fee().toString());
				sParaTemp.put("body", payInfo.getWIDbody());
				sParaTemp.put("paymethod", UrlUtil.readUrl("paymethod"));
				sParaTemp.put("defaultbank", payInfo.getWIDdefaultbank());
				sParaTemp.put("show_url", payInfo.getWIDshow_url());
				sParaTemp.put("anti_phishing_key", AlipaySubmit.query_timestamp());
				sParaTemp.put("exter_invoke_ip", UrlUtil.readUrl("exter_invoke_ip"));
				//建立请求
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
				HttpServletResponse response = res;
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(sHtmlText);
			}

			@Override
			protected Criteria buildCriteria(Criteria criteria, AlipayBankQO qo) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Class<AlipayBank> getEntityClass() {
				// TODO Auto-generated method stub
				return null;
			}
			
	
	
 

}
