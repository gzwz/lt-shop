package lt.content.service;

import gzlazypack.common.component.BaseDao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.content.command.CreateNavigationItemCommand;
import lt.content.command.DeleteNavigationItemCommand;
import lt.content.command.ModifyNavigationItemCommand;
import lt.content.entity.NavigationItem;
import lt.content.qo.NavigationItemQO;

@Service
@Transactional
public class NavigationItemService extends
		BaseDao<NavigationItem, NavigationItemQO> {

	public NavigationItem createNavigationItem(
			CreateNavigationItemCommand command) {

		String generateUrl = generateNavigationItemUrl(command.getLinkDomainId(), command.getLinkDomainType());
		if (generateUrl != null) {
			command.setUrl(generateUrl);
		}

		NavigationItem item = new NavigationItem();
		item.create(command);
        save(item);
		return item;
	}

	public NavigationItem modifyNavigationItem(
			ModifyNavigationItemCommand command) {
		NavigationItem item = get(command.getNavigationItemId());

		item.modify(command);
		update(item);
		return item;
	}

	private String generateNavigationItemUrl(String linkDomainId, String linkDomainType) {
		if (StringUtils.isNotBlank(linkDomainId)) {
			String contextPath = "";

			if (StringUtils.equals(linkDomainType,
					CreateNavigationItemCommand.DOMAIN_TYPE_PRODUCT_CATEGORY)) {
				return (contextPath + "/product/category/"
						+ linkDomainId);
			}

			if (StringUtils.equals(linkDomainType,
					CreateNavigationItemCommand.DOMAIN_TYPE_ARTICLE_CATEGORY)) {
				return (contextPath + "/article/category/"
						+ linkDomainId);
			}
		}
		return null;
	}
	
	public void DeleteNavigationItemCommand(DeleteNavigationItemCommand command) {
		deleteById(command.getNavigationItemId());
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, NavigationItemQO qo) {
		return criteria;
	}

	@Override
	protected Class<NavigationItem> getEntityClass() {
		return NavigationItem.class;
	}

}
