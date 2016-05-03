package lt.ad.command;

public class ChangeCommand {

	/**
	 * 
	 */
	private String id ;
	/**
	 * 	广告状态，是否可用
	 */
	private String status;
	
//	public final static String STATUS_ENABLE = "enable";
//	public final static String STATUS_DISABLE = "disable";
//	public final static String STATUS_REMOVE = "remove";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
}
