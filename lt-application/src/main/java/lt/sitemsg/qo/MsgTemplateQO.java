package lt.sitemsg.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;

/**
 * 查询消息发送模版
 * 
 * @author yuxiaoxiang
 * 
 */
@QueryConfig(daoBeanId = "msgTemplateDao")
@SuppressWarnings("serial")
public class MsgTemplateQO extends BaseQO<String> {

	
	@QueryCondition(name = "enable")
	private Boolean enable;

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
