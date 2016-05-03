package lt.admin.web.contorller.siteMsg;


import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.sitemsg.command.template.CreateMsgTemplateCommand;
import lt.sitemsg.command.template.DisableMsgTemplateCommand;
import lt.sitemsg.command.template.EnableMsgTemplateCommand;
import lt.sitemsg.command.template.ModifyMsgTemplateCommand;
import lt.sitemsg.entity.MsgTemplate;
import lt.sitemsg.qo.MsgTemplateQO;
import lt.sitemsg.service.MsgTemplateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *站内消息模版
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/system/msgTemplate")
public class MsgTemplateController extends BaseController{
	
	
	

	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(MsgTemplateController.class);

	/** 站内消息模版service */
	@Autowired
	private MsgTemplateService msgTemplateService;
	

	/**
	 * 站内消息模版新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,
			CreateMsgTemplateCommand command) {
		try {
			msgTemplateService.createMsgTemplate( command);
		} catch (Exception e) {
			logger.error(
					"【站内消息模版新增异常】MsgTemplateController.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息模版新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版新增成功");
	}
	

	/**
	 * 站内消息模版修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request,
			ModifyMsgTemplateCommand command) {

		try {
			msgTemplateService.modifyMsgTemplate(command);
		} catch (Exception e) {
			logger.error(
					"【站内消息模版修改异常】MsgTemplateController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息模版修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版修改成功");
	}
	
	
	@RequestMapping(value = "query_msgTemplateInfo")
	@ResponseBody
	public String queryMsgTemplateInfo(HttpServletRequest request, MsgTemplateQO qo) {
		qo.setEnable(true);
		List<MsgTemplate> msg = msgTemplateService.queryList(qo);
		return JSONUtils.c(msg);
	}
	
	/**
	 * 站内消息模版显示
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/show")
	@ResponseBody
	public String show(HttpServletRequest request,
			EnableMsgTemplateCommand command) {

		try {
			for(int i=0;i<command.getCommandId().split(",").length;i++){
				command.setMsgTemplateId(command.getCommandId().split(",")[i]);
				msgTemplateService.enableMsgTemplate(command);
			}
		} catch (Exception e) {
			logger.error(
					"【站内消息模版修改异常】MsgTemplateController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息模版显示失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版显示成功");
	}
	
	/**
	 * 站内消息模版隐藏
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/hide")
	@ResponseBody
	public String hide(HttpServletRequest request,
			DisableMsgTemplateCommand command) {

		try {
			for(int i=0;i<command.getCommandId().split(",").length;i++){
				command.setMsgTemplateId(command.getCommandId().split(",")[i]);
				msgTemplateService.disableMsgTemplate(command);
			}
			
		} catch (Exception e) {
			logger.error(
					"【站内消息模版站内消息模版隐藏异常】MsgTemplateController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息模版隐藏失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版隐藏成功");
	}
	

	/**
	 * 站内消息模版删除
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
				msgTemplateService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【站内消息模版修改异常】MsgTemplateController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "站内消息模版删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "站内消息模版删除成功");
	}
	
	

	/**
	 * 站内消息模版列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, MsgTemplateQO qo) {
		Pagination pagination = new Pagination();
		try {
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = msgTemplateService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error("【站内消息模版列表查询异常】MsgTemplateController.query(request, qo)"
					+ e.getMessage(), e);
		}

		return JSONUtils.c(pagination);
	}
	

	/**
	 * 跳转站内消息模版类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/msgTemplate/view.html";
	}
	
	/**
	 * 跳转站内消息模版页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/msgTemplate/add.html";
	}
	
	
	/**
	 * 站内消息模版模板页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewforum")
	public String viewForum(HttpServletRequest request) {
		return "/msgTemplate/forum_view.html";
	}

	/**
	 * 跳转站内消息模版类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, MsgTemplateQO qo,Model model) {
		MsgTemplate pc = null;
		try {
			pc = msgTemplateService.queryUnique(qo);
		} catch (Exception e) {
			logger.error("【站内消息模版详情查询异常】MsgTemplateController.edit(request, qo)"
					+ e.getMessage(), e);
		}
		model.addAttribute("msgTemplate", pc);
		return "/msgTemplate/edite.html";
	}

}
