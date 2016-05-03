package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = M.TABLE_PREFIX + "AREA")
public class Area extends StringIdBaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 32)
	private String name;

	@Column(name = "CODE", length = 8)
	private String code;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	private City city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
