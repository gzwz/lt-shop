package lt.admin.web.contorller.sys;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.admin.web.contorller.BaseController;
import lt.base.command.CreateBannerCommand;
import lt.base.command.ModifyBannerCommand;
import lt.base.entity.Banner;
import lt.base.qo.FlagQO;
import lt.base.service.BannerService;

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
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/system/banner")
public class BannerController extends BaseController {

	/**
	 * 对象
	 */
	private static Logger logger = LoggerFactory
			.getLogger(BannerController.class);
	/**
	 * service注解
	 */
	@Autowired
	private BannerService bannerService;

	/**
	 * banner查询
	 * 
	 * @param request
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, FlagQO qo) {
		Pagination pagination = new Pagination();
		qo.setOrderBy(1);
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = bannerService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error(
					"【banner查询异常】BannerController.query(request, qo)"
							+ e.getMessage(), e);
		}
		return JSONUtils.c(pagination);
	}

	/**
	 * 新增数据
	 * 
	 * @param command
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(CreateBannerCommand command) {
		try {
			bannerService.createBanner(command);
		} catch (Exception e) {
			logger.error(
					"【banner新增异常】BannerController.create(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "banner新增失败");
		}
		return ResultJSON.resultToJSONStr(true, "banner新增成功");
	}

	/**
	 * 修改数据
	 * 
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(ModifyBannerCommand command) {
		try {
			bannerService.modifyBanner(command);
		} catch (Exception e) {
			logger.error(
					"【banner编辑异常】BannerController.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "banner编辑失败");
		}

		return ResultJSON.resultToJSONStr(true, "编辑成功");
	}

	@RequestMapping("/view")
	public String view(HttpServletRequest request) {
		return "/webSite/banner/view.html";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		return "/webSite/banner/add.html";
	}

	/**
	 * 根据id获得数据
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model, FlagQO qo) {
		Banner banner = null;
		try {
			banner = bannerService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("banner", banner);
		return "/webSite/banner/edit.html";
	}

	/**
	 * 根据id删除
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String del(HttpServletRequest request, @PathVariable String id) {
		try {
			for (int i = 0; i < id.split(",").length; i++) {
				bannerService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error("【banner删除异常】BannerController.del(request, command)"
					+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "banner删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "banner删除成功");
	}
}
