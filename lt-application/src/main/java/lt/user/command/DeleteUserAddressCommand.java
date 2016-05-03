package lt.user.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 普通用户删除收货地址
 * 
 * @author wxp
 * 
 */
@SuppressWarnings("serial")
public class DeleteUserAddressCommand extends BaseCommand {

	/**
	 * 地址id
	 */
	private String addressId;
	
	private String addressIds;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressIds() {
		return addressIds;
	}

	public void setAddressIds(String addressIds) {
		this.addressIds = addressIds;
	}
	
	

}
