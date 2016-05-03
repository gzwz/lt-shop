package lt.admin.web.contorller.siteMsg;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.FastjsonUtil;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.sitemsg.command.sendplan.CreateMsgSendPlanCommand;
import lt.sitemsg.command.sendplan.ModifyMsgSendPlanCommand;
import lt.sitemsg.entity.MsgSendPlan;
import lt.sitemsg.qo.MsgSendPlanQO;
import lt.sitemsg.service.MsgSendPlanService;
import lt.user.qo.UserQO;
import lt.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *站内消息发送计划
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/system/msgSendPlan")
public class MsgSendPlanController extends BaseController{
	

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(MsgSendPlanController.class);

	/** 站内消息发送计划service */
	@Autowired
	private MsgSendPlanService msgSendPlanService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询用户信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query_user_info")
	@ResponseBody
	public String queryUserInfo(HttpServletRequest request,UserQO qo){
		Pagination pagination = new Pagination();
		try {
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = userService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error("【用户信息查询异常】MsgSendPlanController.queryUserInfo(request, qo)"
					+ e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}
	
	
	@RequestMapping(value = "query_plan_info")
	@ResponseBody
	public String queryPlanInfo(HttpServletRequest request, MsgSendPlanQO qo) {
		qo.setFetchMsgTemplate(true);
		List<MsgSendPlan> msg = msgSendPlanService.queryList(qo);
		return JSONUtils.c(msg);
	}
	
	

	/**
	 * 站内消息发送计划新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateMsgSendPlanCommand command) {
		try {
			msgSendPlanService.createMsgSendPlan(command);
		} catch (Exception e) {
			logger.error(
					"【站内消息发送计划新增异常】MsgSendPlanController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息发送计划新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息发送计划新增成功");
	}
	


	/**
	 * 站内消息发送计划删除
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			for(int i=0;i<id.split(",").length;i++){
				msgSendPlanService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【站内消息发送计划修改异常】MsgSendPlanController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息发送计划删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息发送计划删除成功");
	}
	
	/**
	 * 站内消息发送计划修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,
			ModifyMsgSendPlanCommand command) {

		try {
			msgSendPlanService.modifyMsgSendPlan(command);
		} catch (Exception e) {
			logger.error(
					"【站内消息发送计划修改异常】MsgSendPlanController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息发送计划修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版修改成功");
	}
	
	

	/**
	 * 站内消息发送计划列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, MsgSendPlanQO qo) {
		Pagination pagination = new Pagination();
		try {
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = msgSendPlanService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error("【站内消息发送计划列表查询异常】MsgSendPlanController.query(request, qo)"
					+ e.getMessage(), e);
		}

		return JSONUtils.c(pagination);
	}
	

	/**
	 * 跳转站内消息发送计划类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/msgSendPlan/view.html";
	}
	
	
	@RequestMapping(value = "/user_page")
	public String userPage(HttpServletRequest request) {
		return "/msgSendPlan/user.html";
	}
	
	/**
	 * 跳转站内消息发送计划类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(HttpServletRequest request, MsgSendPlanQO qo) {
		MsgSendPlan pc = null;
		try {
			qo.setFetchMsgTemplate(true);
			pc = msgSendPlanService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【站内消息发送计划详情查询异常】MsgSendPlanController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		return FastjsonUtil.toJSONStringNotNullQuery(pc);
	}

}
