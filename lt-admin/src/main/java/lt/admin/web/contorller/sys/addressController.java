package lt.admin.web.contorller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import lt.admin.web.contorller.BaseController;
import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Province;
import lt.base.qo.AreaQO;
import lt.base.qo.CityQO;
import lt.base.qo.ProvinceQO;
import lt.base.service.AreaService;
import lt.base.service.CityService;
import lt.base.service.ProvinceService;
/**
 * 
 * @author chenhaohao
 *
 */
@Controller
public class addressController extends BaseController {

	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	
	/**
	 * 区县列表查询
	 * @return
	 */
	@RequestMapping(value ="query_area")
	@ResponseBody
	public String queryArea(HttpServletRequest request,@ModelAttribute AreaQO qo,String code){
		qo.setFetchCity(true);
		qo.setCityQO(new CityQO());
		qo.getCityQO().setId(code);
		qo.getCityQO().setFetchProvince(true);
		List<Area> areas = areaService.queryList(qo);
		return JSON.toJSONString(areas);
	}
	/**
	 * 地级市列表查询
	 * @return
	 */
	@RequestMapping(value ="query_city")
	@ResponseBody
	public String queryCity(HttpServletRequest request,@ModelAttribute CityQO qo,String code){
		qo.setFetchProvince(true);
		qo.setProvinceQO(new ProvinceQO());
		qo.getProvinceQO().setId(code);
		List<City> citys = cityService.queryList(qo);
		return JSON.toJSONString(citys);
	}
	/**
	 * 省份列表查询
	 * @return
	 */
	@RequestMapping(value ="query_province")
	@ResponseBody
	public String queryProvince(HttpServletRequest request,@ModelAttribute ProvinceQO qo){
		List<Province> provinces = provinceService.queryList(qo);
		return JSON.toJSONString(provinces);
	}
}
