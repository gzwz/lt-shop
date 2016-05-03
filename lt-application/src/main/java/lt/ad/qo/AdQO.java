package lt.ad.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "adService")
@SuppressWarnings("serial")
public class AdQO extends BaseQO<String> {

	@QueryCondition(name = "adPosition")
	private String adPosition;
	
	@QueryCondition(name = "status")
	private String status;
	
	/**
	 * 排序（含）
	 */
	@QueryCondition(name = "sort", type = QueryConditionType.ORDER)
	private Integer orderBy;

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	

	public String getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
