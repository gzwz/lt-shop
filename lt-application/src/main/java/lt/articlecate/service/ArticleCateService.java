package lt.articlecate.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lt.articlecate.command.CreateArticleCateCommand;
import lt.articlecate.command.ModifyArticleCateCommand;
import lt.articlecate.entity.ArticleCate;
import lt.articlecate.qo.ArticleCateQO;
import lt.utils.RESULT;
import gzlazypack.common.component.BaseDao;

@Service
@Transactional
public class ArticleCateService extends BaseDao<ArticleCate, ArticleCateQO> {

	public RESULT createArticleCate(CreateArticleCateCommand command) {

		ArticleCate articleCate = new ArticleCate();
		
		articleCate.create(command);
		
		if (StringUtils.isNotBlank(command.getParentId())) {
			ArticleCate articleCate1 = new ArticleCate();
			articleCate1.setId(command.getParentId());
			articleCate.setParent(articleCate1);
		} else {
			articleCate.setParent(articleCate);
		}
		try {
			save(articleCate);
			return RESULT.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
		
	}

	public RESULT modifyArticleCate(ModifyArticleCateCommand command) {

		ArticleCate articleCate = get(command.getArticleCateId());
		articleCate.modify(command);
		if (StringUtils.isNotBlank(command.getParentId())) {
			ArticleCate articleCate1 = new ArticleCate();
			articleCate1.setId(command.getParentId());
			articleCate.setParent(articleCate1);
		} else {
			articleCate.setParent(articleCate);
		}
		try {
			update(articleCate);
			return RESULT.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
		
	}
	
	
   public void getAirticleCate(HttpServletRequest request, Model model){
		
		ArticleCateQO articleCateQO=new ArticleCateQO();
		articleCateQO.setParentQO(new ArticleCateQO());
		articleCateQO.getParentQO().setName("新闻中心");
		articleCateQO.setOrderBy(1);
		articleCateQO.setFetchArticleCate(true);
		List<ArticleCate> articleCateList=queryList(articleCateQO, 5);
		
		model.addAttribute("articleCateList", articleCateList);
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, ArticleCateQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<ArticleCate> getEntityClass() {
		// TODO Auto-generated method stub
		return ArticleCate.class;
	}

}
