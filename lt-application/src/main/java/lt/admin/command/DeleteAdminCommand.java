package lt.admin.command;

import gzlazypack.common.component.BaseCommand;
/**
 * 
 * @author suellen
 *
 */
@SuppressWarnings("serial")
public class DeleteAdminCommand extends BaseCommand{
	private String empId;
	
	private String [] empIds;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String[] getEmpIds() {
		return empIds;
	}

	public void setEmpIds(String[] empIds) {
		this.empIds = empIds;
	}
	
	
}
