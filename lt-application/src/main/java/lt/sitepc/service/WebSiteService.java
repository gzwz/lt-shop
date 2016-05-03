package lt.sitepc.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.sitepc.command.CreateWebSiteCommand;
import lt.sitepc.command.ModifyWebSiteCommand;
import lt.sitepc.entity.WebSite;
import lt.sitepc.qo.WebSiteQO;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.PropertiesUtil;

@Service
@Transactional
public class WebSiteService extends BaseDao<WebSite, WebSiteQO> {

	@SuppressWarnings("rawtypes")
	public void createWebSite(CreateWebSiteCommand command) {
		Map<Object, Object> configMap = PropertiesUtil
				.getAllProperiesEntryMap("system.setup.properties");
		WebSiteQO qo=new WebSiteQO();
		
		List<WebSite> list=queryList(qo);
		if(list.size()<1){
			for (Iterator iter = configMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				command.setKey(key);
				command.setName(value);
				WebSite webSite=new WebSite();
				command.setInputType(Integer.parseInt(key.substring(key.length()-1)));
				webSite.create(command);
				save(webSite);
			}
		}
	}
	
	public void modifyWeb(ModifyWebSiteCommand command) {
		
		WebSite webSite=get(command.getWebSiteId());
		webSite.modify(command);
		update(webSite);
		
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, WebSiteQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<WebSite> getEntityClass() {
		// TODO Auto-generated method stub
		return WebSite.class;
	}

	public static void main(String[] args) {

	}

}
