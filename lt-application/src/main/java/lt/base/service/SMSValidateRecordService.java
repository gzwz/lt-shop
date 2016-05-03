package lt.base.service;

import gzlazypack.common.component.BaseDao;
import lt.base.entity.SMSValidateRecord;
import lt.base.qo.SMSValidateRecordQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SMSValidateRecordService extends
		BaseDao<SMSValidateRecord, SMSValidateRecordQO> {

	@Override
	protected Criteria buildCriteria(Criteria criteria, SMSValidateRecordQO qo) {
		return criteria;
	}

	@Override
	protected Class<SMSValidateRecord> getEntityClass() {
		return SMSValidateRecord.class;
	}

}
