package lt.admin.web.contorller;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.FastjsonUtil;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lt.admin.dto.AdminDTO;
import lt.content.command.CreateForumCommand;
import lt.content.command.CreatePostCommand;
import lt.content.command.HidePostCommand;
import lt.content.command.ModifyForumCommand;
import lt.content.command.ModifyPostCommand;
import lt.content.command.ShowPostCommand;
import lt.content.entity.Forum;
import lt.content.entity.Post;
import lt.content.qo.ForumQO;
import lt.content.qo.PostQO;
import lt.content.service.ForumService;
import lt.content.service.PostService;
import lt.utils.SessionUtil;

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
 * 帖子控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/system/post")
public class PostCotroller extends BaseController {

	/** 日志管理 */
	private static Logger logger = LoggerFactory.getLogger(PostCotroller.class);

	/** 帖子service */
	@Autowired
	private PostService postService;

	/** 帖子模板service */
	@Autowired
	private ForumService forumService;

	/**
	 * 帖子新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request, CreatePostCommand command) {

		try {
			AdminDTO admin=	SessionUtil.getLoginAdmin(request);
			if (admin != null) {
				command.setAuthUserId(admin.getId());
				command.setAuthUserName(admin.getName());
			}
			postService.createPost(command);
		} catch (Exception e) {
			logger.error(
					"【帖子新增异常】PostCotroller.save(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子新增成功");
	}

	/**
	 * 版块新增数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String create(HttpServletRequest request, CreateForumCommand command) {
		try {
			forumService.createForum(command);
		} catch (Exception e) {
			logger.error(
					"【帖子新增异常】PostCotroller.create(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子版块新增失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子版块新增成功");
	}

	/**
	 * 帖子修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(HttpServletRequest request, ModifyPostCommand command) {

		try {
			postService.modifyPost(command);
		} catch (Exception e) {
			logger.error(
					"【帖子修改异常】PostCotroller.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子修改成功");
	}

	/**
	 * 帖子显示
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/show")
	@ResponseBody
	public String show(HttpServletRequest request, ShowPostCommand command) {

		try {
			for (int i = 0; i < command.getCommandId().split(",").length; i++) {
				command.setPostId(command.getCommandId().split(",")[i]);
				postService.showPost(command);
			}
		} catch (Exception e) {
			logger.error(
					"【帖子修改异常】PostCotroller.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子显示失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子显示成功");
	}

	/**
	 * 帖子隐藏
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/hide")
	@ResponseBody
	public String hide(HttpServletRequest request, HidePostCommand command) {

		try {
			postService.hidePost(command);
		} catch (Exception e) {
			logger.error(
					"【帖子帖子隐藏异常】PostCotroller.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子隐藏失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子隐藏成功");
	}

	/**
	 * 帖子模板修改数据提交
	 * 
	 * @param request
	 * @param command
	 * @return
	 */
	@RequestMapping("/modifyforum")
	@ResponseBody
	public String modifyForum(HttpServletRequest request,
			ModifyForumCommand command) {

		try {
			forumService.modifyForum(command);
		} catch (Exception e) {
			logger.error(
					"【帖子模板修改异常】PostCotroller.modify(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子模板修改失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子修改成功");
	}

	/**
	 * 帖子删除
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
				postService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【帖子修改异常】PostCotroller.del(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子删除成功");
	}

	/**
	 * 模板删除
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}_deleteforum")
	@ResponseBody
	public String deleteForum(HttpServletRequest request,
			@PathVariable String id) {
		try {
			for (int i = 0; i < id.split(",").length; i++) {
				forumService.deleteById(id.split(",")[i]);
			}
		} catch (Exception e) {
			logger.error(
					"【】PostCotroller.deleteForum(request, command)"
							+ e.getMessage(), e);
			return ResultJSON.resultToJSONStr(false, "帖子模板删除失败");
		}

		return ResultJSON.resultToJSONStr(true, "帖子模板删除成功");
	}

	/**
	 * 帖子列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, PostQO qo) {
		Pagination pagination = new Pagination();
		try {
			qo.setForumQO(new ForumQO());
			qo.getForumQO();
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = postService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error(
					"【帖子列表查询异常】PostCotroller.query(request, qo)"
							+ e.getMessage(), e);
		}

		return JSONUtils.c(pagination);
	}

	/**
	 * 查询所有板块
	 * 
	 * @param request
	 * @param qo
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "query_ForumInfo")
	@ResponseBody
	public String queryForumInfo(HttpServletRequest request, ForumQO qo) {
		List<Forum> forum = forumService.queryList(qo);
		return JSONUtils.c(forum);
	}

	/**
	 * 帖子模板列表查询方法
	 * 
	 * @param request
	 * @param model
	 * @param qo
	 * @return
	 */
	@RequestMapping("/query_forum")
	@ResponseBody
	public String queryForum(HttpServletRequest request, ForumQO qo) {
		Pagination pagination = new Pagination();
		try {
			pagination.setPageNo(qo.getPageNo());
			pagination.setPageSize(qo.getPageSize());
			pagination.setCondition(qo);
			pagination = forumService.queryPagination(pagination);
		} catch (Exception e) {
			logger.error(
					"【帖子列表查询异常】PostCotroller.query(request, qo)"
							+ e.getMessage(), e);
		}

		return FastjsonUtil.toJSONStringNotNullQuery(pagination);
	}

	/**
	 * 跳转帖子类别页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request) {
		return "/admin/post/view.html";
	}

	/**
	 * 跳转帖子页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request) {
		return "/admin/post/add.html";
	}

	/**
	 * 帖子模板页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewforum")
	public String viewForum(HttpServletRequest request) {
		return "/admin/post/forum_view.html";
	}

	/**
	 * 跳转帖子类编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, PostQO qo, Model model) {
		Post pc = null;
		try {
			qo.setForumQO(new ForumQO());
			qo.getForumQO();
			pc = postService.queryUnique(qo);
		} catch (Exception e) {
			logger.error(
					"【帖子详情查询异常】PostCotroller.edit(request, qo)"
							+ e.getMessage(), e);
		}
		model.addAttribute("post", pc);
		return "/admin/post/edite.html";
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public String updateForum(HttpServletRequest request, ForumQO qo) {
		Forum pc = null;
		try {
			pc = forumService.queryUnique(qo);
		} catch (Exception e) {
			logger.error(
					"【帖子详情查询异常】PostCotroller.updateForum(request, qo)"
							+ e.getMessage(), e);
		}
		return FastjsonUtil.toJSONStringNotNullQuery(pc);
	}

}
