package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 图片信息
 * 
 * @author wxp
 * 
 */
@Embeddable
@SuppressWarnings("serial")
public class EmImage extends StringIdBaseEntity {

	@Column(name = "IMG_TITLE", length = 64)
	private String title;

	/**
	 * 不同尺寸图片访问地址 例：{"default":"xxx/xxx.jpg", "big":"xxx/xxx1.jpg",
	 * "small":"xxx/xxx2.jpg}
	 */
	@Transient
	private Map<String, String> specImageMap;

	/**
	 * specImageMap JSON化存储
	 */
	@Column(name = "IMG_SPEC_IMAGE_MAP_JSON", columnDefinition = M.TEXT_COLUMN)
	private String specImageMapJSON;

	/**
	 * 图片文件存储信息JSON
	 */
	@Column(name = "IMG_FILE_INFO_JSON", columnDefinition = M.TEXT_COLUMN)
	private String fileInfoJSON;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getSpecImageMap() {
		if (specImageMap == null && StringUtils.isNotBlank(specImageMapJSON)) {
			setSpecImageMap((Map<String, String>) JSON.parse(specImageMapJSON));
		}

		return specImageMap;
	}

	public void setSpecImageMap(Map<String, String> specImageMap) {
		this.specImageMap = specImageMap;
	}

	public String getSpecImageMapJSON() {
		if (StringUtils.isBlank(specImageMapJSON) && specImageMap != null) {
			specImageMapJSON = JSON.toJSONString(specImageMap);
		}

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
