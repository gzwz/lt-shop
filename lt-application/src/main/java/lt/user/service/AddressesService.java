package lt.user.service;

import java.util.List;

import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Province;
import lt.base.qo.AreaQO;
import lt.base.qo.CityQO;
import lt.base.qo.ProvinceQO;
import lt.base.service.AreaService;
import lt.base.service.CityService;
import lt.base.service.ProvinceService;
import lt.user.command.CreateUserAddressCommand;
import lt.user.command.ModifyUserAddressCommand;
import lt.user.dto.UserDTO;
import lt.user.entity.User;
import lt.user.entity.UserAddress;
import lt.user.qo.AddressesQO;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzlazypack.common.component.BaseDao;
import gzlazypack.common.util.ResultJSON;

@Service
@Transactional
public class AddressesService extends BaseDao<UserAddress, AddressesQO>{
	
	@Autowired
	private UserService userService; 
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;

	
	public String delAddress(String addressId) {
		// TODO Auto-generated method stub
		UserAddress address = get(addressId);
		if (address.getDefaultAddress()) {
			return ResultJSON.resultToJSONStr(false, "对不起不能删除默认地址");
		}
		try {
			delete(address);
			return ResultJSON.resultToJSONStr(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "删除失败");
		}
		
	}
	
	public String modifyUserAddress(String addressId) {
		try {
			AddressesQO qo = new AddressesQO();
			qo.setDefaultAddress(true);
			UserAddress userAddress1  = queryUnique(qo);
			userAddress1.setDefaultAddress(false);
			update(userAddress1);
			
			UserAddress userAddress = get(addressId);
			userAddress.setDefaultAddress(true);
			update(userAddress);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResultJSON.resultToJSONStr(false, "设置失败");
		}
		return ResultJSON.resultToJSONStr(true, "设置成功");
	}

	public String create(CreateUserAddressCommand addCommand, UserDTO userDTO) {
		// TODO Auto-generated method stub
		UserAddress address = new UserAddress();
		User user = userService.get(userDTO.getId());
		Province province = provinceService.get(addCommand.getProvinceId());
		City city = cityService.get(addCommand.getCityId());
		Area area = areaService.get(addCommand.getAreaId());
		
		AddressesQO addressesQO = new AddressesQO();
		address.create(addCommand, user, province, city, area);
		Integer num = queryCount(addressesQO);
		if (num < 1 ) {
		address.setDefaultAddress(true);
		}
		try {
			save(address);
			return ResultJSON.resultToJSONStr(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJSON.resultToJSONStr(false, "操作失败");
		}
	}
	
	public List<Province> getProvince() {
		// TODO Auto-generated method stub
		ProvinceQO qo = new ProvinceQO();
		return provinceService.queryList(qo);
	}
	public List<City> getCity(String provinceId) {
		// TODO Auto-generated method stub
		ProvinceQO provinceQO = new ProvinceQO();
		provinceQO.setId(provinceId);
		CityQO qo = new CityQO();
		qo.setProvinceQO(provinceQO);
		return cityService.queryList(qo);
	}
	
	public List<Area> getArea(String cityId) {
		// TODO Auto-generated method stub
		CityQO cityQO = new CityQO();
		cityQO.setId(cityId);
		AreaQO qo = new AreaQO();
		qo.setCityQO(cityQO);
		return areaService.queryList(qo);
	}

	@Override
	protected Criteria buildCriteria(Criteria criteria, AddressesQO qo) {
		// TODO Auto-generated method stub
		return criteria;
	}

	@Override
	protected Class<UserAddress> getEntityClass() {
		// TODO Auto-generated method stub
		return UserAddress.class;
	}



}
