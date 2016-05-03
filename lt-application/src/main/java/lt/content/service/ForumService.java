package lt.content.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.content.command.CreateForumCommand;
import lt.content.command.DeleteForumCommand;
import lt.content.command.ModifyForumCommand;
import lt.content.entity.Forum;
import lt.content.qo.ForumQO;

@Service
@Transactional
public class ForumService extends BaseDao<Forum, ForumQO> {
	
	public Forum createForum(CreateForumCommand command) {
		Forum forum = new Forum();
		forum.create(command);
		save(forum);
		return forum;
	}
	
	public Forum modifyForum(ModifyForumCommand command) {
		Forum forum = get(command.getForumId());
		forum.modify(command);
		update(forum);
		return forum;
	}
	
	public void deleteForum(DeleteForumCommand command) {
		deleteById(command.getForumId());
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, ForumQO qo) {
		return criteria;
	}

	@Override
	protected Class<Forum> getEntityClass() {
		return Forum.class;
	}

}
