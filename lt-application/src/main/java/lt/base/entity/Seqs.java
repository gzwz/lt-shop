package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = M.TABLE_PREFIX + "SEQS")
public class Seqs extends StringIdBaseEntity {
	
	@Column(name = "NUM", length = 16)
	private Long num;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
}
