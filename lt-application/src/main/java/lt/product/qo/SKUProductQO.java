package lt.product.qo;

import gzlazypack.common.annotation.QueryCondition;
import gzlazypack.common.annotation.QueryConditionType;
import gzlazypack.common.annotation.QueryConfig;
import gzlazypack.common.component.BaseQO;
@QueryConfig(daoBeanId = "sKUProductService")
public class SKUProductQO extends BaseQO<String>{

	private static final long  serialVersionUID=1L;
	
	@QueryCondition(name = "product", type = QueryConditionType.LEFT_JOIN)
	private ProductQO productQO;
	
	@QueryCondition(name = "skuSpecInfo", ifTrueUseLike = "skuSpecInfoLike")
	private String skuSpecInfo;
	
	@QueryCondition(name = "skuSpecInfo")
	private String skuInfo;
	
	/**
	 * 是否立即加载,创建qo后需要调用qo.setBatchResult(true)
	 */
	@QueryCondition(name = "product", type = QueryConditionType.FETCH_EAGER)
	private Boolean fetchProduct;
	
	private Boolean skuSpecInfoLike;
	
	private String productId;

	public ProductQO getProductQO() {
		return productQO;
	}

	public void setProductQO(ProductQO productQO) {
		this.productQO = productQO;
	}

	public Boolean getFetchProduct() {
		return fetchProduct;
	}

	public void setFetchProduct(Boolean fetchProduct) {
		this.fetchProduct = fetchProduct;
	}

	public String getSkuSpecInfo() {
		return skuSpecInfo;
	}

	public void setSkuSpecInfo(String skuSpecInfo) {
		this.skuSpecInfo = skuSpecInfo;
	}

	public Boolean getSkuSpecInfoLike() {
		return skuSpecInfoLike;
	}

	public void setSkuSpecInfoLike(Boolean skuSpecInfoLike) {
		this.skuSpecInfoLike = skuSpecInfoLike;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSkuInfo() {
		return skuInfo;
	}

	public void setSkuInfo(String skuInfo) {
		this.skuInfo = skuInfo;
	}
	
}
