package lt.admin.web.contorller;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.FastjsonUtil;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.base.qo.SMSValidateSagaQO;
import lt.base.service.SMSValidateSagaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短信校验码流程
 * @author wxp
 *
 */
@Controller
@RequestMapping("/smsValidat/sMSValidate")
public class SMSValidateSagaController extends BaseController{
	
	/** 日志管理 */
	private static Logger logger = LoggerFactory
			.getLogger(SMSValidateSagaController.class);

	/** 短信校验码流程service */
	@Autowired
	private SMSValidateSagaService sMSValidateSagaService;


	/**
	 * 短信校验码流程删除
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			sMSValidateSagaService.deleteById(id);
		} catch (Exception e) {
			logger.error(
					"【短信校验码流程修改异常】SMSValidateSagaController.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "短信校验码流程删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "短信校验码流程删除成功");
	}

	/**
	 * 短信校验码流程列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, SMSValidateSagaQO qo) {

		Pagination pagination = new Pagination();

		try {
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = sMSValidateSagaService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error(
					"【短信校验码流程列表查询异常】SMSValidateSagaController.query(request, qo)"
							+ e.getMessage(), e);
		}

		return FastjsonUtil.toJSONStringNotNullQuery(pagination);
	}

	/**
	 * 跳转短信校验码流程
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/admin/sMSValidate/view.html";
	}



}
