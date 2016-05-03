package lt.base.dto;

import java.util.Map;

import lt.base.entity.EmImage;

/**
 * 图片信息
 * 
 * @author yuxiaoxiang
 * 
 */
public class EmImageDTO{

	private String title;

	/**
	 * 不同尺寸图片访问地址 例：{"default":"xxx/xxx.jpg", "big":"xxx/xxx1.jpg",
	 * "small":"xxx/xxx2.jpg}
	 */
	private Map<String, String> specImageMap;

	/**
	 * specImageMap JSON化存储
	 */
	private String specImageMapJSON;

	/**
	 * 图片文件存储信息JSON
	 */
	private String fileInfoJSON;
	
	public EmImageDTO eiDTOConverter(EmImage ei){
		if(null == ei){
			return null;
		}
		EmImageDTO eiDTO = new EmImageDTO();
		eiDTO.setTitle(ei.getTitle());
		eiDTO.setFileInfoJSON(ei.getFileInfoJSON());
		eiDTO.setSpecImageMap(ei.getSpecImageMap());
		eiDTO.setSpecImageMapJSON(ei.getSpecImageMapJSON());
		return eiDTO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, String> getSpecImageMap() {
		return specImageMap;
	}

	public void setSpecImageMap(Map<String, String> specImageMap) {
		this.specImageMap = specImageMap;
	}

	public String getSpecImageMapJSON() {
		return specImageMapJSON;
	}

	public void setSpecImageMapJSON(String specImageMapJSON) {
		this.specImageMapJSON = specImageMapJSON;
	}

	public String getFileInfoJSON() {
		return fileInfoJSON;
	}

	public void setFileInfoJSON(String fileInfoJSON) {
		this.fileInfoJSON = fileInfoJSON;
	}


}
