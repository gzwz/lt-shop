package lt.utils;

import java.util.HashMap;
import java.util.Map;

public class AppUtil {
	
	/**
	 * app接口返回json数据 （成功）
	 * @param mData 内层数据
	 * @return
	 */
	public static Map<String,Object> getMap(Map<String, Object> mData){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result_code","0");
		map.put("result_data",mData);
		return map;
	}
	
	/**
	 * app接口返回json数据 （失败）
	 * @param mData 内层数据
	 * @return
	 */
	public static Map<String,Object> getMapError(Map<String, Object> mData){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result_code","1");
		map.put("result_data",mData);
		return map;
	}
}
