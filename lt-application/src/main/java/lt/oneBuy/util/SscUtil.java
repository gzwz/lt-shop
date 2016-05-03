package lt.oneBuy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class  SscUtil {
	
	public static final String GET_URL = "http://f.apiplus.cn/cqssc-1.json";
	
//	@Scheduled(cron="0/20 * *  * * ? ")   //每1分钟秒执行一次  
	
    public static String getSscData(){  
		String data = null;
    	try {
			URL url = new URL(GET_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setConnectTimeout(8000);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = reader.readLine()) != null) {
	            data = line;
	        }
			reader.close();
		    connection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
    } 
	
	
}
