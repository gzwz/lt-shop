package lt.ad.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lt.ad.command.AdCommand;
import lt.ad.command.ChangeCommand;
import lt.ad.entity.Advertisement;
import lt.ad.qo.AdQO;
import lt.admin.entity.Admin;
import lt.admin.service.AdminService;
import lt.utils.RESULT;
import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.ResultJSON;

@Service
@Transactional
public class AdService extends BaseDao<Advertisement, AdQO>{
	@Autowired
	private AdminService adminService;
	

	/**
	 * 改变状态
	 * @param command
	 * @return
	 */
	public String change(ChangeCommand command) {
		// TODO Auto-generated method stub
		Advertisement advertisement = get(command.getId());
		advertisement.setStatus(command.getStatus());
		try {
			save(advertisement);
			return ResultJSON.resultToJSONStr(true, "操作成功");	
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "操作失败");	
		}
		
	}
	
	/**
	 * 查看详情
	 * @param id
	 */
	public Advertisement look(String id) {
		// TODO Auto-generated method stub
		if (!StringUtils.isNoneBlank(id)) {
			return null;
		}
		AdQO qo = new AdQO();
		qo.setId(id);
		Advertisement advertisement = queryUnique(qo);
		return advertisement;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public String del(String id) {
		// TODO Auto-generated method stub
		try {
			deleteById(id);
			return ResultJSON.resultToJSONStr(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "删除失败");
		}
		
	}
	
	public String add(AdCommand command,String id){
		//根据id 获取user
		Admin admin = adminService.get(id);
		
		Advertisement advertisement  = new Advertisement();
		
		advertisement.create(command,admin);
		
		try {
			save(advertisement);
			return ResultJSON.resultToJSONStr(true, "操作成功");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "操作失败");
		}
	}
	
	public RESULT editSave(AdCommand adCommand, String id) {
		// TODO Auto-generated method stub
		//根据id 获取user
		Admin admin = adminService.get(id);
		Advertisement advertisement  = get(adCommand.getAdId());
		advertisement.edit(adCommand,admin);
		
		try {
			save(advertisement);
			return RESULT.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			return RESULT.ERROR;
		}
	}
	
	
	public void getAd(HttpServletRequest request, Model model) {
		AdQO adQO = new AdQO();
		adQO.setAdPosition("index");
		adQO.setOrderBy(1);
		adQO.setStatus(Advertisement.STATUS_ENABLE);
		List<Advertisement> adList = queryList(adQO, 3);
		model.addAttribute("adList", adList);
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, AdQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<Advertisement> getEntityClass() {
		// TODO Auto-generated method stub
		return Advertisement.class;
	}



}
