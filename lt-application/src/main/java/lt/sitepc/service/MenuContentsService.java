package lt.sitepc.service;

import lt.sitepc.command.CreateMenuContentsCommand;
import lt.sitepc.command.ModifyMenuContentsCommand;
import lt.sitepc.entity.BottomMenu;
import lt.sitepc.entity.MenuContents;
import lt.sitepc.qo.MenuContentsQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuContentsService extends BaseDao<MenuContents, MenuContentsQO>{
	
	@Autowired
	private BottomMenuService bottomMenuService;
	
	public MenuContents createMenuContents(CreateMenuContentsCommand command){
		
		 BottomMenu  bottomMenu=bottomMenuService.get(command.getBottomMenuId());
		 
		 MenuContents menuContents=new MenuContents();
		 menuContents.create(command, bottomMenu);
		 save(menuContents);
		return menuContents;
	}
	
	
	public MenuContents modifyMenuContents(ModifyMenuContentsCommand command){
		
		 BottomMenu  bottomMenu=bottomMenuService.get(command.getBottomMenuId());
		 
		 MenuContents menuContents=get(command.getMenuContentsId());
		 menuContents.modify(command, bottomMenu);
		 update(menuContents);
		return menuContents;
	}
	

	@Override
	protected Criteria buildCriteria(Criteria criteria, MenuContentsQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<MenuContents> getEntityClass() {
		// TODO Auto-generated method stub
		return MenuContents.class;
	}

	
}
