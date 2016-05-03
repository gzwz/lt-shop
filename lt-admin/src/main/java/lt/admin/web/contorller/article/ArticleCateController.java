package lt.admin.web.contorller.article;

import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import lt.articlecate.command.CreateArticleCateCommand;
import lt.articlecate.command.ModifyArticleCateCommand;
import lt.articlecate.entity.ArticleCate;
import lt.articlecate.qo.ArticleCateQO;
import lt.articlecate.service.ArticleCateService;
import lt.utils.RESULT;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/*
 * 文章管理
 * wz
 */
@Controller
@RequestMapping("/articleCate")
public class ArticleCateController {
	
	@Autowired
	private ArticleCateService articleCateService;

	@RequestMapping("")
	public String view(HttpServletRequest request,Model model,ArticleCateQO qo){
		qo.setOrderBy(1);
		model.addAttribute("articleCateList", articleCateService.queryList(qo));
		return "/article/articleCate/view.html";
	}
	
	@RequestMapping("/addView")
	public String addView(){
		return "/article/articleCate/add.html";
	}
	
	/*@RequestMapping("/edit")
	public String edti (HttpServletRequest request,String articleCateId,String pid){
		if (StringUtils.isNotBlank(pid)) {
			ArticleCate articleCateParent = articleCateService.get(pid);
			request.setAttribute("articleCateParent", articleCateParent);
		}
		
		if (!StringUtils.isNotBlank(articleCateId)) {
			return "/article/articleCate/edit.html";
		}else{
			ArticleCate articleCate = articleCateService.get(articleCateId);
			request.setAttribute("articleCate", articleCate);
			return "/article/articleCate/edit.html";
		}
	}*/
	
	
	/**
	 * 跳转类型编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public String edit(HttpServletRequest request,ArticleCateQO qo) {
		ArticleCate pc = null;
		try {
			qo.setFetchArticleCate(true);
			pc = articleCateService.queryUnique(qo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONUtils.c(pc);
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	public String modify(ModifyArticleCateCommand modityCommand){
		if ( StringUtils.isNotBlank(modityCommand.getName())) {
			if (articleCateService.modifyArticleCate(modityCommand)== RESULT.SUCCESS) {
				return ResultJSON.resultToJSONStr(true, "操作成功");
			}
		}
		return ResultJSON.resultToJSONStr(false, "操作失败");
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save (CreateArticleCateCommand command){
		if (command.getName() == null || command.getName() == "") {
			return ResultJSON.resultToJSONStr(false, "请填写分类名称");
		}
		
		if (articleCateService.createArticleCate(command)== RESULT.SUCCESS) {
			
			return ResultJSON.resultToJSONStr(true, "操作成功");
		}else {
			return ResultJSON.resultToJSONStr(false, "操作失败");
		}
		
		
	}
	
	@RequestMapping(value="/getPermData")
	@ResponseBody
	public String add(HttpServletRequest request, Model model, String id, Integer type){
		
		ArticleCateQO qo =new ArticleCateQO();
		qo.setOrderBy(1);
		List<ArticleCate> resourceList=articleCateService.queryList(qo);
		
		List<Map<String, Object>> strList = new ArrayList<Map<String, Object>>();
		List<ArticleCate> hasPermList = new  ArrayList<ArticleCate>();
		
		for (ArticleCate authPerm : resourceList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String parentId = "#";

			if (!authPerm.getId().equals(authPerm.getParent().getId())) {
				parentId = authPerm.getParent().getId();
			}

			map.put("id", authPerm.getId());
			map.put("text", authPerm.getName());
			map.put("parent", parentId);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("opened", true);

			if (StringUtils.isNotBlank(id)) {
				for (ArticleCate ap : hasPermList) {
					if (authPerm.getId().equals(ap.getId())) {
						map2.put("selected", true);
						break;
					}
				}
			}

			map.put("state", map2);
			Random random = new Random();
			int num = random.nextInt(5);
			if (num % 5 == 0) {
				map.put("icon", "fa fa-file icon-state-default");
			} else if (num % 5 == 1) {
				map.put("icon", "fa fa-file icon-state-success");
			} else if (num % 5 == 2) {
				map.put("icon", "fa fa-file icon-state-warning");
			} else if (num % 5 == 3) {
				map.put("icon", "fa fa-file icon-state-danger");
			} else if (num % 5 == 4) {
				map.put("icon", "fa fa-file icon-state-info");
			}

			strList.add(map);
		}

		return JSON.toJSONString(strList);
	}

	
}
