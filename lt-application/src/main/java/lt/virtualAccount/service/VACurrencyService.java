package lt.virtualAccount.service;

import lt.virtualAccount.command.ModifyCurrentExchangeRateCommand;
import lt.virtualAccount.entity.VACurrency;
import lt.virtualAccount.exception.VirtualAccountException;
import lt.virtualAccount.qo.VACurrencyQO;
import gzlazypack.common.component.BaseDao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("vaCurrencyService")
@Transactional
public class VACurrencyService extends BaseDao<VACurrency, VACurrencyQO> {
	
	public VACurrency modifyExchangeRate(ModifyCurrentExchangeRateCommand command) throws VirtualAccountException {
		String id = command.getId();
		Double exchangeOneYuan = command.getExchangeOneYuan();

		if (StringUtils.isBlank(id))
			throw new VirtualAccountException(VirtualAccountException.NECESSARY_PARAM_NULL, "缺少必要的参数：id");

		if (exchangeOneYuan == null)
			throw new VirtualAccountException(VirtualAccountException.NECESSARY_PARAM_NULL, "缺少必要的参数：exchangeOneYuan");

		VACurrency vaCurrency = get(id);

		if (vaCurrency == null)
			throw new VirtualAccountException(VirtualAccountException.CURRENCY_NOT_EXIST, "指定虚拟货币类型不存在");

		vaCurrency.setExchangeOneYuan(exchangeOneYuan);

		return vaCurrency;
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, VACurrencyQO qo) {
		return criteria;
	}

	@Override
	protected Class<VACurrency> getEntityClass() {
		return VACurrency.class;
	}

}
