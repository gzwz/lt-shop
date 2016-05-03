package lt.utils;

import gzlazypack.common.page.Pagination;

import java.util.ArrayList;
import java.util.List;


public class PageUtils {

	private int pageCount=1;
	
	private List<?> list=new ArrayList<>();

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	
	
	// pc分页
		public static PageUtils getPageNation(Pagination pagination) {
			PageUtils pageUtils = new PageUtils();
			pageUtils.setPageCount(pagination.getTotalPage());
			pageUtils.setList(pagination.getList());
			return pageUtils;
		}
	
}
