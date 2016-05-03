package lt.content.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lt.content.command.CreateForumCommand;
import lt.content.command.ModifyForumCommand;

/**
 * 版块
 * 
 * @author yuxx
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "FORUM")
@SuppressWarnings("serial")
public class Forum extends StringIdBaseEntity {

	/**
	 * 版块名称
	 */
	@Column(name = "NAME", length = 64)
	private String name;

	/**
	 * 贴子总数
	 */
	@Column(name = "POST_NUM", length = 12)
	private Integer postNum;

	/**
	 * 板块类型
	 */
	@Column(name = "TYPE")
	private String type;

	public void create(CreateForumCommand command) {
		setId(UUIDGenerator.getUUID());
		
		setName(command.getName());
		setPostNum(0);
		setType(command.getType());
	}

	public void modify(ModifyForumCommand command) {
		setName(command.getName());
	}
	
	public Integer getPostNum() {
		return postNum;
	}

	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
