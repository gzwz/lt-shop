package lt.virtualAccount.service;

import lt.virtualAccount.command.CreateVAChangeDetailCommand;
import lt.virtualAccount.entity.VirtualAccount;
import lt.virtualAccount.entity.VirtualAccountChangeDetail;
import lt.virtualAccount.qo.VirtualAccountChangeDetailQO;
import lt.virtualAccount.qo.VirtualAccountQO;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.page.Pagination;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class VirtualAccountChangeDetailService extends
		BaseDao<VirtualAccountChangeDetail, VirtualAccountChangeDetailQO> {

	@Autowired
	private VirtualAccountService virtualAccountService;
	
	public VirtualAccountChangeDetail createVAChangeDatail(
			CreateVAChangeDetailCommand command) {
		VirtualAccount virtualAccount = virtualAccountService.get(command.getVirtualAccountId());
		
		VirtualAccountChangeDetail changeDetail = new VirtualAccountChangeDetail();
		changeDetail.create(command, virtualAccount);
		save(changeDetail);
		return changeDetail;
	}

	public Pagination selectDetail(VirtualAccountChangeDetailQO qo) {
		Pagination pagination = new Pagination();
		pagination.setPageNo(qo.getPageNo());
		pagination.setPageSize(qo.getPageSize());
		qo.setFetchVirtualAccount(true);
		qo.setVirtualAccountQO(new VirtualAccountQO());
		qo.getVirtualAccountQO().setId(qo.getVirtualAccountId());
		pagination.setCondition(qo);
		pagination = queryPagination(pagination);
		return pagination;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria,
			VirtualAccountChangeDetailQO qo) {
		return criteria;
	}

	@Override
	protected Class<VirtualAccountChangeDetail> getEntityClass() {
		return VirtualAccountChangeDetail.class;
	}

}
