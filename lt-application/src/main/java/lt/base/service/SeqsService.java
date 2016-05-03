package lt.base.service;

import gzlazypack.common.component.BaseDao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.base.entity.Seqs;
import lt.base.qo.SeqsQO;

@Service
@Transactional
public class SeqsService extends BaseDao<Seqs, SeqsQO> {

	@Override
	protected Criteria buildCriteria(Criteria criteria, SeqsQO qo) {
		return criteria;
	}

	@Override
	protected Class<Seqs> getEntityClass() {
		return Seqs.class;
	}

}
