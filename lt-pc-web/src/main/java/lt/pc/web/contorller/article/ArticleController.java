package lt.pc.web.contorller.article;


import gzlazypack.common.component.BaseQO;
import gzlazypack.common.page.Pagination;
import gzlazypack.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lt.articlecate.entity.Article;
import lt.articlecate.qo.ArticleCateQO;
import lt.articlecate.qo.ArticleQO;
import lt.articlecate.service.ArticleCateService;
import lt.articlecate.service.ArticleService;
import lt.pc.web.contorller.BaseController;
import lt.utils.PageUtils;
/**
 * 控制层
 * 
 * @author wxp
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController{
	
	@Autowired
	private ArticleCateService articleCateService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/article-list")
	public String articleList(HttpServletRequest request, Model model,ArticleCateQO qo) {
		try {
			articleCateService.getAirticleCate(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("qo", qo);
		return "/news/list.html";
	}
	
	
	@RequestMapping(value = "/article-contents")
	public String articleContents(HttpServletRequest request, Model model,ArticleQO articleQO) {
		Article article=null;
		try {
			articleQO.setOrderBy(-1);
			articleQO.setFetchArticleCate(true);
			articleQO.setIsDelete(false);
			articleCateService.getAirticleCate(request, model);
			article=articleService.queryUnique(articleQO);
		} catch (Exception e) {
           e.printStackTrace();
		}
		model.addAttribute("article", article);
		return "/news/contents.html";
	}
	
	@RequestMapping(value = "/ajaxLoad")
	@ResponseBody
	public String ajaxLoad(HttpServletRequest request,ArticleQO articleQO) {
		Pagination pagination=new Pagination();
		PageUtils pageUtils=null;
		try {
			articleQO.setOrderBy(-1);
			if(StringUtils.isNotBlank(articleQO.getArtickeCateId())){
				articleQO.setArticleCateQO(new ArticleCateQO());
				articleQO.getArticleCateQO().setId(articleQO.getArtickeCateId());
			}
			articleQO.setIsDelete(false);
			pagination.setPageNo(Integer.parseInt(articleQO.getPage()));
			pagination.setPageSize(articleQO.getPageSize());
			pagination.setCondition(articleQO);
			pagination = articleService.queryPagination(pagination);
			pageUtils = PageUtils.getPageNation(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSONUtils.c(pageUtils);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/contents")
    public String getContents(HttpServletRequest request,ArticleQO articleQO){
		Article article=null;
		try {
			if(StringUtils.isNotBlank(articleQO.getArtickeCateId())){
				articleQO.setArticleCateQO(new ArticleCateQO());
				articleQO.getArticleCateQO().setId(articleQO.getArtickeCateId());
			}
			articleQO.setIsDelete(false);
			articleQO.setOrderBy(BaseQO.ORDER_DESC);
			article=articleService.queryUnique(articleQO);
		} catch (Exception e) {
			
		}
		
		return JSONUtils.c(article);
	}	

}
