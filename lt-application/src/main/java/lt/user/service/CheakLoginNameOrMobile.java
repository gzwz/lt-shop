package lt.user.service;

import gzlazypack.common.component.BaseDao;






import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.user.entity.User;
import lt.user.qo.UserQO;

@Service
@Transactional
public class  CheakLoginNameOrMobile extends BaseDao<User, UserQO>{

	/**
	 * 
	 * @param mobile
	 * @param loginName
	 * @return 检查用户名或者手机号是否被注册 
	 * 已被注册返回 true
	 * 否则返回false
	 */
	public boolean check(String mobile, String loginName) {
			
		if (mobile != "" && mobile.length() == 11) {
			UserQO userQO=new UserQO();
			userQO.setMobile(mobile);
			Integer count=queryCount(userQO);
			if (count > 0) {
				return true;
			}
		}
		if (loginName!="" && loginName != null) {
			UserQO userQO1=new UserQO();
			userQO1.setLoginName(loginName);
			Integer nameCount=queryCount(userQO1);
			if (nameCount > 0) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, UserQO qo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<User> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

 
	

	
	

}
