package lt.admin.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 
 * @author suellen
 * 
 */
@QueryConfig(daoBeanId = "employeeDao")
@SuppressWarnings("serial")
public class AdminQO extends BaseQO<String> {

	@QueryCondition(name = "name", ifTrueUseLike = "nameLike")
	private String name;

	private Boolean nameLike = false;
	
	@QueryCondition(name = "authAccountId")
	private String authAccountId;

	@QueryCondition(name = "roles", type = QueryConditionType.FETCH_EAGER)
	private boolean fetchRoles;

	@QueryCondition(name = "authAccount", type = QueryConditionType.FETCH_EAGER)
	private boolean fetchAuthAccount;

	public Boolean getNameLike() {
		return nameLike;
	}

	public void setNameLike(Boolean nameLike) {
		this.nameLike = nameLike;
	}

	public boolean isFetchRoles() {
		return fetchRoles;
	}

	public void setFetchRoles(boolean fetchRoles) {
		this.fetchRoles = fetchRoles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFetchAuthAccount() {
		return fetchAuthAccount;
	}

	public void setFetchAuthAccount(boolean fetchAuthAccount) {
		this.fetchAuthAccount = fetchAuthAccount;
	}

	public String getAuthAccountId() {
		return authAccountId;
	}

	public void setAuthAccountId(String authAccountId) {
		this.authAccountId = authAccountId;
	}

}
