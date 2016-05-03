package lt.content.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

@SuppressWarnings("serial")
@QueryConfig(daoBeanId = "forumService")
public class ForumQO extends BaseQO<String> {

	@QueryCondition(name = "type")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
