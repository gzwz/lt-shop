package lt.pc.web.contorller.oneBuy;


import gzlazypack.common.util.DateUtil;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lt.base.entity.Image;
import lt.base.qo.ImageQO;
import lt.base.service.ImageService;
import lt.oneBuy.command.SscCommand;
import lt.oneBuy.dto.ResultSSCDTO;
import lt.oneBuy.entity.LotteryResult;
import lt.oneBuy.entity.OneBuy;
import lt.oneBuy.qo.OneBuyQO;
import lt.oneBuy.service.LuckNumService;
import lt.oneBuy.service.OneBuyService;
import lt.oneBuy.util.GetSscCommand;
import lt.order.service.OrderBuyerInfoService;
import lt.pc.web.contorller.BaseController;
/**
 * 控制层
 * 
 * @author
 */
@Controller
@RequestMapping(value = "/oneBuy")
public class CloudProductController extends BaseController {
	
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(CloudProductController.class);
	
	@Autowired
	private OneBuyService oneBuyService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private LuckNumService luckNumService;
	
	@Autowired
	private OrderBuyerInfoService buyerInfoService;
	

	
	@RequestMapping(value = "")
	public String view(HttpServletRequest request,Model model) {
	//	model.addAttribute("show", "2");
		
		//是否热门
		OneBuyQO hotQO = new OneBuyQO();
		hotQO.setHot(true);
		hotQO.setOrderBy(-1);
		hotQO.setStatus(OneBuy.STATUS_ENABLE);
		List<OneBuy> hotList = oneBuyService.queryList(hotQO,4);
		request.setAttribute("hotList", hotList);
		
		//即将揭晓
		OneBuyQO willQO = new OneBuyQO();
		willQO.setShowStatus(OneBuy.SHOW_WILL_PUBLISH);
		willQO.setStatus(OneBuy.STATUS_ENABLE);
		willQO.setOrderBy(-1);
		List<OneBuy> wplist = oneBuyService.queryList(willQO, 4);
		request.setAttribute("willPublished", wplist);
		
		//最新揭晓
		OneBuyQO newqo = new OneBuyQO();
		newqo.setShowStatus(OneBuy.SHOW_NEW_PUBLISHED);
		newqo.setOrderBy(-1);
		newqo.setStatus(OneBuy.STATUS_ENABLE);
		List<OneBuy> newlist = oneBuyService.queryList(newqo, 4);
		request.setAttribute("newPublished", newlist);
		
		return "/onebuy/oneBuyIndex.html";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/one-detaile")
	public String viewDetaile(HttpServletRequest request,OneBuyQO oneBuyQO) {
		OneBuy oneBuy = null;
		
		ImageQO iqo = new ImageQO();
		iqo.setDomainId(oneBuyQO.getId());
		iqo.setDomainType("product_image");
		List<Image> image = imageService.queryList(iqo);
		Map<String, String> mp = (Map<String, String>) JSON.parse(image.get(0)
				.getSpecImageMapJSON());
		request.setAttribute("imageList", mp);
		try {
			oneBuy = oneBuyService.queryUnique(oneBuyQO);
		} catch (Exception e) {
			logger.error("【查询异常】CloudProductController.queryPagination(pagination)" + e.getMessage(), e);
		}
		System.out.println(oneBuy.getShowStatus());
		request.setAttribute("obp", oneBuy);
		if (StringUtils.equals(oneBuy.getShowStatus(), "normal")) {
			return "/onebuy/detail.html";
		}else{
			return "/onebuy/lotteryDetails.html";
		}
	}
	
	@RequestMapping(value = "/one-list")
	public String oneBuyList(HttpServletRequest request,OneBuyQO qo,String type) {
		System.out.println(type);
		if (StringUtils.isNotBlank(type)) {
			qo.setShowStatus(type);
		}
		List<OneBuy> oneBuyList = oneBuyService.queryList(qo);
		request.setAttribute("oneBuyList", oneBuyList);
		request.setAttribute("type", type);
		return "/onebuy/oneBuyList.html";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/calculate")
	public String calculate(){
		SscCommand sscCommand = GetSscCommand.get();
		Date optime = DateUtil.parseDateTime(sscCommand.getOpentime());
		Integer min =(int) (((new Date().getTime()) - optime.getTime())/(1000*60));
		if (min > 60) {
			return ResultJSON.resultToJSONStr(false, "时时彩尚未开奖！");
		}
		OneBuyQO buyQO = new OneBuyQO();
		buyQO.setShowStatus(OneBuy.SHOW_WILL_PUBLISH);;
		List<OneBuy> oneList = oneBuyService.queryList(buyQO);
		List<ResultSSCDTO> rsList=new ArrayList<ResultSSCDTO>();
		for (OneBuy oneBuy : oneList) {
			oneBuyService.publish(oneBuy);
			ResultSSCDTO rs = oneBuyService.getLuckNum(oneBuy,sscCommand);
			rsList.add(rs);
		}
		return JSONUtils.c(rsList);
	}
	
//	@Scheduled(cron="0 */15 * * * ?")   //每15分钟秒执行一次  
/*	public void task(){
		calculate();
		System.out.println("执行了任务调度 用于请求彩票数据 15分钟"+ new Date());
	}*/
	
	/**
	 * 根据商品id获取 开奖结果
	 */
	@RequestMapping("getLotteryResult")
	@ResponseBody
	public String getLotteryResult(OneBuyQO oneBuyQO){
		
		LotteryResult lotteryResult = oneBuyService.getLotteryResult(oneBuyQO.getId());
		return JSONUtils.c(lotteryResult);
	}
	
}
