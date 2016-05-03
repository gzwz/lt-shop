package lt.base.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import lt.base.command.CreateImageCommand;
import lt.base.entity.Image;
import lt.base.qo.ImageQO;

@Service
@Transactional
public class ImageService extends BaseDao<Image, ImageQO> {

	public Image createImage(CreateImageCommand command) {
		Image image = new Image();
		image.create(command);
		save(image);
		return image;
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, ImageQO qo) {
		return criteria;
	}

	@Override
	protected Class<Image> getEntityClass() {
		return Image.class;
	}

}
