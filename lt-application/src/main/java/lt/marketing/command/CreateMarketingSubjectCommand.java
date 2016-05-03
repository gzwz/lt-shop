package lt.marketing.command;

import gzlazypack.common.component.BaseCommand;


/**
 * 创建推广主体
 * 
 *
 */
@SuppressWarnings("serial")
public class CreateMarketingSubjectCommand extends BaseCommand {

	private String subjectId;

	private String subjectName;

	private String subjectType;

	private String parentSubjectId;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getParentSubjectId() {
		return parentSubjectId;
	}

	public void setParentSubjectId(String parentSubjectId) {
		this.parentSubjectId = parentSubjectId;
	}

}
