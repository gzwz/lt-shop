package lt.sitepc.service;

import gzlazypack.common.component.BaseDao;
import lt.sitepc.command.CreateBottoMenuCommand;
import lt.sitepc.command.ModifyBottomMenuCommand;
import lt.sitepc.entity.BottomMenu;
import lt.sitepc.qo.BottomMenuQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author 大爷
 *
 */
@Service
@Transactional
public class BottomMenuService extends BaseDao<BottomMenu, BottomMenuQO> {

	public BottomMenu createBottomMenu(CreateBottoMenuCommand command){
		
		BottomMenu parent=get(command.getParentId());
		
		BottomMenu bottomMenu=new BottomMenu();
		bottomMenu.create(command, parent);
		if(null==parent){
			bottomMenu.setParent(bottomMenu);
		}
		save(bottomMenu);
		return bottomMenu;
	}
	
	
	public BottomMenu modifyBottomMenu(ModifyBottomMenuCommand command){
			
			BottomMenu parent=get(command.getParentId());
			
			BottomMenu bottomMenu=get(command.getBottomMenuId());
			bottomMenu.modify(command, parent);
			if(null==parent){
				bottomMenu.setParent(bottomMenu);
			}
			update(bottomMenu);
			return bottomMenu;
		}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, BottomMenuQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<BottomMenu> getEntityClass() {
		// TODO Auto-generated method stub
		return BottomMenu.class;
	}

}
