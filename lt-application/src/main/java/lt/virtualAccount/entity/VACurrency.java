package lt.virtualAccount.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;


/**
 * 汇银虚拟币种
 * 
 * 
 */
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "VA_CURRENCY")
@SuppressWarnings("serial")
public class VACurrency extends StringIdBaseEntity {

	/**
	 * 名称
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 是否启用
	 */
	@Type(type = "yes_no")
	@Column(name = "ENABLE")
	private Boolean enable;

	/**
	 * 基准汇率，多少抵1元人民币
	 */
	@Column(name = "EXCHANGE_ONE_YUAN", columnDefinition = M.MONEY_COLUMN)
	private Double exchangeOneYuan;

	/**
	 * 单位名称
	 */
	@Column(name = "UNIT")
	private String unit;

	/**
	 * 是否是代理币种
	 */
	@Type(type = "yes_no")
	@Column(name = "PROXY_CURRENCY")
	private Boolean proxyCurrency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Double getExchangeOneYuan() {
		return exchangeOneYuan;
	}

	public void setExchangeOneYuan(Double exchangeOneYuan) {
		this.exchangeOneYuan = exchangeOneYuan;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getProxyCurrency() {
		return proxyCurrency;
	}

	public void setProxyCurrency(Boolean proxyCurrency) {
		this.proxyCurrency = proxyCurrency;
	}

}
