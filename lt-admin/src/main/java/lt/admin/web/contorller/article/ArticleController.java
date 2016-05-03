package lt.admin.web.contorller.article;

import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.articlecate.command.ChangeArticleCommand;
import lt.articlecate.command.CreateArticleCommand;
import lt.articlecate.command.ModifyArticleCommand;
import lt.articlecate.entity.Article;
import lt.articlecate.qo.ArticleQO;
import lt.articlecate.service.ArticleService;
import lt.utils.RESULT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/view")
	public String init(HttpServletRequest request){
		
		return "/article/view.html";
	}
	
	
	/*
	 * 改变状态
	 * stop 意图要 下架
	 * start 意图要 上架
	 */
	@RequestMapping("/chanageStatus")
	@ResponseBody
	public String change(HttpServletRequest request,ChangeArticleCommand command,String type){
		if (type.equals("stop")) {
			command.setDelete(true);
		}else if (type.equals("start")) {
			command.setDelete(false);
		}
		
		if (articleService.ChangeArticle(command)== RESULT.SUCCESS) {
			return ResultJSON.resultToJSONStr(true, "操作成功");
		}else {
			return ResultJSON.resultToJSONStr(false, "操作失败");
		}
		
	}
	
	
	@RequestMapping("/addView")
	public String addView(HttpServletRequest request) {
		return "/article/add.html";
	}
	
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,ArticleQO qo,Model model) {
		Article article=null;
		try {
			qo.setFetchArticleCate(true);
			article=articleService.queryUnique(qo);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("article", article);
		return "/article/edit.html";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(HttpServletRequest request, ArticleQO qo) {
		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		pagination.setCondition(qo);
		try {
			pagination = articleService.queryPagination(pagination);
		} catch (Exception e) {
			 
		}
		return JSONUtils.c(pagination);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save (HttpServletRequest request,CreateArticleCommand command){
		
		if (articleService.createArticle(command)==RESULT.SUCCESS) {
			return ResultJSON.resultToJSONStr(true, "保存成功");
		}else {
			return ResultJSON.resultToJSONStr(true, "保存失败");
		}
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public String modify (HttpServletRequest request,ModifyArticleCommand command){
		
		try {
			articleService.modifyArticle(command);
		} catch (Exception e) {
			return ResultJSON.resultToJSONStr(true, "保存失败");
		}
			
		return ResultJSON.resultToJSONStr(true, "保存成功");
	}
	
	
}
