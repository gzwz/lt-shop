/*package lt.admin.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "BUTTOM")
@SuppressWarnings("serial")
public class Buttom extends StringIdBaseEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TARGET_ID", nullable = false)
	private Resource resource;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "BUTTOM")
	private String buttom;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getButtom() {
		return buttom;
	}

	public void setButtom(String buttom) {
		this.buttom = buttom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
*/