package lt.product.service;

import lt.product.entity.ProductSnapshot;
import lt.product.qo.ProductSnapshotQO;
import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductSnapshotService extends BaseDao<ProductSnapshot, ProductSnapshotQO> {

	@Override
	protected Criteria buildCriteria(Criteria criteria, ProductSnapshotQO qo) {
		return criteria;
	}

	@Override
	protected Class<ProductSnapshot> getEntityClass() {
		return ProductSnapshot.class;
	}

}
