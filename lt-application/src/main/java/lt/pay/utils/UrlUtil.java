package lt.pay.utils;

import java.io.IOException;
import java.util.Properties;

public class UrlUtil {
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(UrlUtil.class.getClassLoader().getResourceAsStream("param.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readUrl(String key){
		if(key == null || "".equals(key)) return"";
		return (String)properties.get(key);
	}
	
}
