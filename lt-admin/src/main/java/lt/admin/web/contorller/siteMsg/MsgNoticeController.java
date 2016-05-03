package lt.admin.web.contorller.siteMsg;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.FastjsonUtil;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.sitemsg.command.notice.SendBatchMsgCommand;
import lt.sitemsg.command.notice.SendSingleMsgCommand;
import lt.sitemsg.entity.MsgNotice;
import lt.sitemsg.qo.MsgNoticeQO;
import lt.sitemsg.service.MsgNoticeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *消息通知
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/system/msgNotice")
public class MsgNoticeController extends BaseController{

	

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(MsgNoticeController.class);

	/** 消息通知service */
	@Autowired
	private MsgNoticeService msgNoticeService;
	
	
	

	/**
	 * 发送站内信计划
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save_plan")
	@ResponseBody
	public String savePlan(HttpServletRequest request,
			SendBatchMsgCommand command) {
		try {
				msgNoticeService.sendBatchMsg(command);
		} catch (Exception e) {
			logger.error(
					"【发送站内信计划异常】MsgNoticeController.savePlan(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "发送站内信计划失败");
		}

		return ResultJSON.resultToJSONStr(true, "发送站内信计划成功");
	}
	
	
	
	/**
	 * 消息通知新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			SendSingleMsgCommand command) {
		try {
			for(int i=0;i<command.getCommandId().split(",").length;i++){
				command.setUserId(command.getCommandId().split(",")[i]);
				msgNoticeService.sendSingleMsg(command);
			}
		} catch (Exception e) {
			logger.error(
					"【消息通知新增异常】MsgNoticeController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "消息通知新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "消息通知新增成功");
	}
	
	
	


	/**
	 * 消息通知删除
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
				msgNoticeService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【消息通知修改异常】MsgNoticeController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "消息通知删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "消息通知删除成功");
	}
	
	/**
	 * 消息通知修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,
			SendBatchMsgCommand command) {

		try {
			msgNoticeService.sendBatchMsg(command);
		} catch (Exception e) {
			logger.error(
					"【消息通知修改异常】MsgNoticeController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "消息通知修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版修改成功");
	}
	
	

	/**
	 * 消息通知列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, MsgNoticeQO qo) {
		Pagination pagination = new Pagination();
		try {
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = msgNoticeService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error("【消息通知列表查询异常】MsgNoticeController.query(request, qo)"
					+ e.getMessage(), e);
		}

		return JSONUtils.c(pagination);
	}
	

	/**
	 * 跳转消息通知类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/msgNotice/view.html";
	}
	
	
	/**
	 * 跳转消息通知类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(HttpServletRequest request, MsgNoticeQO qo) {
		MsgNotice pc = null;
		try {
			pc = msgNoticeService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【消息通知详情查询异常】MsgNoticeController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		return FastjsonUtil.toJSONStringNotNullQuery(pc);
	}
	
}
