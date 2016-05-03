package lt.content.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.content.command.CreatePostCommand;
import lt.content.command.DeletePostCommand;
import lt.content.command.HidePostCommand;
import lt.content.command.ModifyPostCommand;
import lt.content.command.ShowPostCommand;
import lt.content.entity.Forum;
import lt.content.entity.Post;
import lt.content.qo.PostQO;

@Service
@Transactional
public class PostService extends BaseDao<Post, PostQO> {
	
	@Autowired
	private ForumService forumService;
	
	public Post createPost(CreatePostCommand command) {
		Forum forum = forumService.get(command.getForumId());
		Post post = new Post();
		post.create(command, forum);
		save(post);
		return post;
	}
	
	public Post modifyPost(ModifyPostCommand command) {
		Post post = get(command.getPostId());
		post.modify(command);
		update(post);
		return post;
	}
	
	public void deletePost(DeletePostCommand command) {
		deleteById(command.getPostId());
	}
	
	public void showPost(ShowPostCommand command) {
		Post post = get(command.getPostId());
		post.show();
		update(post);
	}
	
	public void hidePost(HidePostCommand command) {
		Post post = get(command.getPostId());
		post.hide();
		update(post);
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, PostQO qo) {
		return criteria;
	}

	@Override
	protected Class<Post> getEntityClass() {
		return Post.class;
	}

}
