package lt.admin.dto;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.collections.CollectionUtils;

import lt.admin.entity.Resource;
import lt.base.dto.BaseDTO;

public class ResourcesDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String name;

	private String url;

	private String clientType;

	private String parent;

	private String icon;
	
	private Integer level;
	
    private Boolean isLeaf;
    
    private Boolean loaded;
    
    private Boolean expanded;
    
    private String remark;
    
    private Integer sort;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	public static List<ResourcesDTO> domainToDTO(List<Resource> resources) {

		if (CollectionUtils.isEmpty(resources))
			return new ArrayList<>();

		List<ResourcesDTO> list = new ArrayList<>();
		ResourcesDTO dto = null;
		for (Resource resource : resources) {
			dto = new ResourcesDTO();
			dto.setId(resource.getId());
			dto.setIcon(resource.getIcon());
			dto.setClientType(resource.getClientType());
			dto.setName(resource.getName());
			dto.setParent(resource.getParent().getId());
			dto.setIsLeaf(true);
			dto.setSort(resource.getSort());
			dto.setLoaded(true);
			dto.setExpanded(false);
			dto.setUrl(resource.getUrl());
			dto.setRemark(resource.getRemark());
			list.add(dto);
		}

		return list;
	}

	
}
