package lt.base.entity;

import gzlazypack.common.component.StringIdBaseEntity;
import gzlazypack.common.util.UUIDGenerator;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lt.base.command.CreateImageCommand;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 图片信息
 * 
 * @author wxp
 * 
 */
@Entity
@Table(name = M.TABLE_PREFIX + "IMAGE")
@SuppressWarnings("serial")
public class Image extends StringIdBaseEntity {

	@Column(name = "TITLE", length = 64)
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
	@Column(name = "SPEC_IMAGE_MAP_JSON", columnDefinition = M.TEXT_COLUMN)
	private String specImageMapJSON;

	/**
	 * 图片文件存储信息JSON
	 */
	@Column(name = "FILE_INFO_JSON", columnDefinition = M.TEXT_COLUMN)
	private String fileInfoJSON;

	private DomainLink domainLink;

	@Column(name = "SORT", length = 16)
	private Integer sort;

	public void create(CreateImageCommand command) {
		setId(UUIDGenerator.getUUID());
		setSort(command.getSort());
		
		setDomainLink(new DomainLink());
		getDomainLink().setDomainId(command.getDomainLinkId());
		getDomainLink().setDomainName(command.getDomainLinkName());
		getDomainLink().setDomainType(command.getDomainLinkType());
		
		setSpecImageMap(command.getSpecImageMap());
		setSpecImageMapJSON(JSON.toJSONString(command.getSpecImageMap()));
		setFileInfoJSON(command.getFileInfoJSON());
	}
	

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

	public DomainLink getDomainLink() {
		return domainLink;
	}

	public void setDomainLink(DomainLink domainLink) {
		this.domainLink = domainLink;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
