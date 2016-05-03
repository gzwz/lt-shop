package lt.articlecate.service;

import gzlazypack.common.component.BaseDao;
import lt.articlecate.command.CreateArticleCommand;
import lt.articlecate.command.ModifyArticleCommand;
import lt.articlecate.entity.Article;
import lt.articlecate.entity.SeoBaseInfo;
import lt.articlecate.qo.ArticleQO;
import lt.utils.RESULT;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeoBaseInfoService extends BaseDao<SeoBaseInfo, ArticleQO>{
	
	
	public RESULT createArticle(CreateArticleCommand command,Article article){
		
		SeoBaseInfo seoBaseInfo=new SeoBaseInfo();
		seoBaseInfo.createSeoBaseInfo(command, article);
		try {
			save(seoBaseInfo);
			return RESULT.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
		
	}
	
	
	public SeoBaseInfo modifySeoBaseInfo(ModifyArticleCommand command,Article article){
		
		SeoBaseInfo seoBaseInfo=get(command.getSeoBaseInfoId());
		seoBaseInfo.modifySeoBaseInfo(command, article);
        update(seoBaseInfo);
        return seoBaseInfo;
	}
 
 
	@Override
	protected Criteria buildCriteria(Criteria criteria, ArticleQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<SeoBaseInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return SeoBaseInfo.class;
	}
	

 

}
