package lt.oneBuy.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NumberCountInfo {

	/**
	 * 参与人数
	 */
	@Column(name = "partake_count", columnDefinition=M.NUM_COLUMN)
	private Integer partakeCount;
	
	/**
	 * 需要人数
	 */
	@Column(name = "total_count", columnDefinition=M.NUM_COLUMN)
	private Integer totalCount;
	
	/**
	 * 剩余数量
	 */
	@Column(name = "residue_count", columnDefinition=M.NUM_COLUMN)
	private Integer residueCount;

	public Integer getPartakeCount() {
		return partakeCount;
	}

	public void setPartakeCount(Integer partakeCount) {
		this.partakeCount = partakeCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getResidueCount() {
		return residueCount;
	}

	public void setResidueCount(Integer residueCount) {
		this.residueCount = residueCount;
	}
	
	
}
