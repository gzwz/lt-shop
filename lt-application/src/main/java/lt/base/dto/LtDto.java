package lt.base.dto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class LtDto extends BaseDTO{

	private static final long serialVersionUID = 1L;
	
	private List list=new ArrayList();

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
}
