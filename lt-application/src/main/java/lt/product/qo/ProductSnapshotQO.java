package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;


/**
 * 商品
 * 
 *
 */
@QueryConfig(daoBeanId = "productService")
@SuppressWarnings("serial")
public class ProductSnapshotQO extends BaseQO<String> {

	@QueryCondition(name = "productId")
	private String productId;

	@QueryCondition(name = "createDate", type = QueryConditionType.ORDER)
	private Integer sortByCreateDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getSortByCreateDate() {
		return sortByCreateDate;
	}

	public void setSortByCreateDate(Integer sortByCreateDate) {
		this.sortByCreateDate = sortByCreateDate;
	}

}
