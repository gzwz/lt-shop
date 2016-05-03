package lt.content.command;

import gzlazypack.common.component.BaseCommand;

/**
 * 创建导航条目
 * 
 * @author yuxx
 *
 */
@SuppressWarnings("serial")
public class CreateNavigationItemCommand extends BaseCommand {

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 链接
	 */
	private String url;
	
	/**
	 * 链接业务实体id，如商品类别id，文章频道id等
	 */
	private String linkDomainId;
	
	/**
	 * 链接业务实体类型
	 */
	private String linkDomainType;
	
	public final static String DOMAIN_TYPE_PRODUCT_CATEGORY = "product_category";		//	链接到商品类目页面
	public final static String DOMAIN_TYPE_ARTICLE_CATEGORY = "article_category";		//	链接到文章类目页面
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkDomainId() {
		return linkDomainId;
	}

	public void setLinkDomainId(String linkDomainId) {
		this.linkDomainId = linkDomainId;
	}

	public String getLinkDomainType() {
		return linkDomainType;
	}

	public void setLinkDomainType(String linkDomainType) {
		this.linkDomainType = linkDomainType;
	}

}
