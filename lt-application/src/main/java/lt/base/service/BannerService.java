package lt.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gzlazypack.common.component.BaseDao;
import lt.base.command.CreateBannerCommand;
import lt.base.command.ModifyBannerCommand;
import lt.base.entity.Banner;
import lt.base.qo.FlagQO;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
@Transactional
public class BannerService extends BaseDao<Banner, FlagQO> {
	
	public Banner createBanner(CreateBannerCommand command){
		
		Banner banner=new Banner();
		banner.create(command);
		save(banner);
		return banner;
	}
	
	public Banner modifyBanner(ModifyBannerCommand command){
		
		Banner banner=get(command.getBannerId());
		banner.modify(command);
		save(banner);
		return banner;
	}
	
	public void getFlag(HttpServletRequest request, Model model) {
		FlagQO flagQO = new FlagQO();
		flagQO.setOrderBy(1);
		List<Banner> flagList =queryList(flagQO);
		model.addAttribute("bannerList", flagList);
	}
	
	@Override
	protected Criteria buildCriteria(Criteria criteria, FlagQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Banner> getEntityClass() {
		// TODO Auto-generated method stub
		return Banner.class;
	}

}
