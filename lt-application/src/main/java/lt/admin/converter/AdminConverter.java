package lt.admin.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import lt.admin.dto.AdminDTO;
import lt.admin.entity.Admin;
import lt.admin.entity.AuthAccount;
/**
 * 后台管理员dto转换类
 * @author chenhaohao
 *
 */
public class AdminConverter {
	
	public static List<AdminDTO> domainToDTO(List<Admin> adminList){
		if (CollectionUtils.isEmpty(adminList)) {
			return null;
		}
		List<AdminDTO> dtoList = new ArrayList<>();
		AdminDTO dto = null;
		for (Admin admin : adminList) {
			dto = domainToDTO(admin, null);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public static AdminDTO domainToDTO(Admin admin, AuthAccount account){
		if (null == admin) {
			return null;
		}
		AdminDTO dto = new AdminDTO();
		dto.setId(admin.getId());
		
		dto.setLastLoginDate(admin.getLastLoginDate());
		dto.setName(admin.getName());
		dto.setTelephone(admin.getTelephone());
		dto.setRoles(admin.getRoles());
		dto.setType(admin.getType());
		if (null != account) {
			dto.setLoginName(account.getLoginName());
		}
		return dto;
	}
}
