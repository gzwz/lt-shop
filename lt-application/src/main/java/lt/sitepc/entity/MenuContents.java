package lt.sitepc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





import lt.sitepc.command.CreateMenuContentsCommand;
import lt.sitepc.command.ModifyMenuContentsCommand;

import org.hibernate.annotations.DynamicUpdate;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;
@DynamicUpdate
@Entity
@Table(name = M.TABLE_PREFIX + "MENUCONTENS")
@SuppressWarnings("serial")
public class MenuContents extends StringIdBaseEntity{
	
	@ManyToOne
	@JoinColumn(name="bottom_menu_id")
	private BottomMenu bottomMenu;

	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name = "content",columnDefinition = M.TEXT_COLUMN)
	private String content;

	
	public void create(CreateMenuContentsCommand command,BottomMenu bottomMenu){
		setId(UUIDGenerator.getUUID());
		
		setBottomMenu(bottomMenu);
		setContent(command.getContent());
		setCreateTime(new Date());
	}
	
	
	public void modify(ModifyMenuContentsCommand command,BottomMenu bottomMenu){
		
		setBottomMenu(bottomMenu);
		setContent(command.getContent());
		setCreateTime(new Date());
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BottomMenu getBottomMenu() {
		return bottomMenu;
	}

	public void setBottomMenu(BottomMenu bottomMenu) {
		this.bottomMenu = bottomMenu;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
