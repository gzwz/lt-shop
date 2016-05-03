package lt.admin.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.admin.command.CreateAuthAccountCommand;
import lt.admin.command.ModifyAuthAccountCommand;
import lt.admin.entity.AuthAccount;
import lt.admin.qo.AuthAccountQO;

@Service
@Transactional
public class AuthAccountService extends BaseDao<AuthAccount, AuthAccountQO> {

	public AuthAccount createAuthAccount(CreateAuthAccountCommand command) {

		AuthAccount account = new AuthAccount();
		account.create(command);

		save(account);
		return account;
	}

	public AuthAccount modifyAuthAccount(ModifyAuthAccountCommand command) {
		AuthAccount account = get(command.getAuthAccountId());
		
		account.modify(command);
		
		return null;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, AuthAccountQO qo) {
		return criteria;
	}

	@Override
	protected Class<AuthAccount> getEntityClass() {
		return AuthAccount.class;
	}

}
