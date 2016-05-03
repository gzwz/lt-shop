package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = M.TABLE_PREFIX + "PROVINCE")
public class Province extends StringIdBaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 32)
	private String name;
	
	@Column(name = "CODE", length = 8)
	private String code;
	
	public Province() {
	}
	
	public Province(String code, String name) {
		this.name = name;
		this.code = code;
	}
	
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

}
