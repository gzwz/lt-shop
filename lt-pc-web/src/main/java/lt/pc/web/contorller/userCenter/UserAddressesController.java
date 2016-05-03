package lt.pc.web.contorller.userCenter;

import java.util.ArrayList;
import java.util.List;

import gzlazypack.common.util.JSONUtils;
import gzlazypack.common.util.ResultJSON;

import javax.servlet.http.HttpServletRequest;

import lt.base.entity.Area;
import lt.base.entity.City;
import lt.base.entity.Province;
import lt.pc.web.contorller.BaseController;
import lt.user.command.CreateUserAddressCommand;
import lt.user.dto.UserAddressDTO;
import lt.user.dto.UserDTO;
import lt.user.entity.UserAddress;
import lt.user.qo.AddressesQO;
import lt.user.qo.UserQO;
import lt.user.service.AddressesService;
import lt.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/intercept/userAddesses")
public class UserAddressesController extends BaseController {
	
	@Autowired
	private AddressesService addrService;
	
	@RequestMapping("setDefault")
	@ResponseBody
	public String setDefault(String addressId){
		return addrService.modifyUserAddress(addressId);
	}
	@RequestMapping("delAddr")
	@ResponseBody
	public String delAddr(String addressId){
		return addrService.delAddress(addressId);
	}
	
	@RequestMapping("editAddr")
	public String editAddr(HttpServletRequest request,String addressId){
		UserAddress address =  addrService.get(addressId);
		request.setAttribute("address", address);
		return "userCenter/add.html";
	}

	@RequestMapping("getAddress")
	public String getAddressView(HttpServletRequest request){
		
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		AddressesQO addressesQO=new AddressesQO();
		addressesQO.setFetchArea(true);
		addressesQO.setFetchCity(true);
		addressesQO.setFetchProvince(true);
		if(null!=userDTO){
			addressesQO.setUserQO(new UserQO());
			addressesQO.getUserQO().setId(userDTO.getId());
		}
		List<UserAddress> addressesList=addrService.queryList(addressesQO);
		
		request.setAttribute("addrList", addressesList);
		
		return "userCenter/address.html";
	}
	@RequestMapping("addView")
	public String addView(){
		return "userCenter/add.html";
	}
	
	@RequestMapping("addAddress")
	public String addAddress(){
		return "/order/product-order/add.html";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public String save(HttpServletRequest request,CreateUserAddressCommand addCommand){
		UserDTO userDTO =SessionUtil.getLoginUser(request);
		if (addCommand.getUserId()==null) {
			addCommand.setUserId(userDTO.getId());
		}else {
			return ResultJSON.resultToJSONStr(false, "用户信息错误");
		}
		return addrService.create(addCommand ,userDTO);
	}
	
	@ResponseBody
	@RequestMapping("/getProvince")
	public String  getProvince(){
		List<Province> list = addrService.getProvince();
		return JSONUtils.c(list);
	}
	@ResponseBody
	@RequestMapping("/getCity")
	public String  getCity(String provinceId){
		List<City> list = addrService.getCity(provinceId);
		return JSONUtils.c(list);
	}
	@ResponseBody
	@RequestMapping("/getArea")
	public String  getArea(String cityId){
		List<Area> list = addrService.getArea(cityId);
		return JSONUtils.c(list);
	}
	
	
	@ResponseBody
	@RequestMapping("/getuserAddesses")
	public String  getuserAddesses(HttpServletRequest request){
		UserDTO userDTO = SessionUtil.getLoginUser(request);
		AddressesQO addressesQO=new AddressesQO();
		addressesQO.setFetchArea(true);
		addressesQO.setFetchCity(true);
		addressesQO.setFetchProvince(true);
		if(null!=userDTO){
			addressesQO.setUserQO(new UserQO());
			addressesQO.getUserQO().setId(userDTO.getId());
		}
		List<UserAddress> addressesList=addrService.queryList(addressesQO);
		List<UserAddressDTO> userAddressDTOList=new ArrayList<UserAddressDTO>();
		for(UserAddress userAddress:addressesList){
			UserAddressDTO userAddressDTO=new UserAddressDTO();
			userAddressDTO.setId(userAddress.getId());
			userAddressDTO.setAreaName(userAddress.getArea().getName());
			userAddressDTO.setCityName(userAddress.getCity().getName());
			userAddressDTO.setProvinceName(userAddress.getProvince().getName());
			userAddressDTO.setDetail(userAddress.getDetail());
			userAddressDTO.setMobile(userAddress.getMobile());
			userAddressDTO.setName(userAddress.getName());
			userAddressDTO.setZipCode(userAddress.getZipCode());
			userAddressDTO.setDefaultAddress(userAddress.getDefaultAddress());
			userAddressDTOList.add(userAddressDTO);
			
		}
		return JSONUtils.c(userAddressDTOList);
	}
	
}
