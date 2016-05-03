package lt.user.converter;

import lt.user.dto.UserDTO;
import lt.user.entity.User;

/**
 * 普通会员dto转换类
 * @author wz
 *
 */
public class UserConverter {
	
	public static UserDTO donmainToDTO (User user){
		if (user == null) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		/* UserBaseInfo */
		userDTO.setId(user.getId());
		userDTO.setNickName(user.getBaseInfo().getNickName());
		userDTO.setHeadImage(user.getBaseInfo().getHeadImage());
//		userDTO.setName(user.getBaseInfo().getName());
//		userDTO.setIntegral(user.getBaseInfo().getIntegral());
		/* UserContactInfo */
//		userDTO.setMobile(user.getContactInfo().getMobile());
//		userDTO.setEmail(user.getContactInfo().getEmail());
		/* UserLoginInfo */
		userDTO.setLoginName(user.getUserLoginInfo().getLoginName());
		userDTO.setEncryptPassword(user.getUserLoginInfo().getEncryptPassword());
		/* UserStatus */
//		userDTO.setMobileValid(user.getStatus().getMobileValid());
//		userDTO.setEmailValid(user.getStatus().getMobileValid());
//		userDTO.setRealNameValid(user.getStatus().getRealNameValid());
//		userDTO.setLastLoginDate(user.getStatus().getLastLoginDate());
		
		
		return userDTO;
	}
}
