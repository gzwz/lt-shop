package lt.utils;

import gzlazypack.common.util.ResultJSON;

import org.apache.commons.lang3.StringUtils;

public class ValidateString {
	/**
	 * 验证字符串是否为空 为空则返回参数1 不为空返回参数2
	 */
	public static Object valid(String param1, String param2) {
		if (StringUtils.isNotBlank(param1)) {
			return param1;
		} else {
			return param2;
		}
	}

}
