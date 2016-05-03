package lt.articlecate.service;

import gzlazypack.common.component.BaseDao;
import lt.articlecate.command.ChangeArticleCommand;
import lt.articlecate.command.CreateArticleCommand;
import lt.articlecate.command.DeleteArticleCommand;
import lt.articlecate.command.ModifyArticleCommand;
import lt.articlecate.entity.Article;
import lt.articlecate.entity.ArticleCate;
import lt.articlecate.qo.ArticleQO;
import lt.utils.RESULT;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService extends BaseDao<Article, ArticleQO>{
	
	@Autowired
	private SeoBaseInfoService seoBaseInfoService;
	
	@Autowired
	private ArticleCateService articleCateService;
	
	
	
	public RESULT ChangeArticle(ChangeArticleCommand command){
		
		
		
		Article article = get(command.getId());
		try {
			article.changeStatus(command,article);
			update(article);
			return RESULT.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
		
	}
	
	public RESULT createArticle(CreateArticleCommand command){
		
		ArticleCate articleCate=articleCateService.get(command.getParentId());
		
		Article article=new Article();
		article.createArticle(command, articleCate);
		try {
			save(article);
			if (seoBaseInfoService.createArticle(command, article)== RESULT.SUCCESS) {
				return RESULT.SUCCESS;
			}else {
				return RESULT.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
		
		
		
	}
	
	public Article modifyArticle(ModifyArticleCommand command){
		
		ArticleCate articleCate=articleCateService.get(command.getParentId());
		command.setArticleCate(articleCate);
		
		Article article=get(command.getArticleId());
		article.modify(command, articleCate);
		update(article);
		
		seoBaseInfoService.modifySeoBaseInfo(command, article);
		return article;
	}
	
	public Article deleteArticle(DeleteArticleCommand command){
		
		
		ArticleCate articleCate=articleCateService.get(command.getArticleCate().getId());
		command.setArticleCate(articleCate);
		
		Article article=get(command.getArticleCate().getId());
		update(article);
		
		return article;
	}
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, ArticleQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Article> getEntityClass() {
		// TODO Auto-generated method stub
		return Article.class;
	}

}
