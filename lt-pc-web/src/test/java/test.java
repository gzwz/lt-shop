import gzlazypack.common.util.DateUtil;

import org.junit.Test;


public class test {
	
	
	public static String stringToAscii(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1)  
	        {  
	            sbu.append((int)chars[i]).append(DateUtil.getDate());  
	        }  
	        else {  
	            sbu.append((int)chars[i]);  
	        }  
	    }  
	    return sbu.toString();  
	} 
	
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    String[] chars = value.split("&a&@");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	    }  
	    return sbu.toString();  
	} 
	
	@Test
	public void wz(){
		String a = "http://127.0.0.1:8080/lt-pc-web/login/logout";
		String[] b = a.split("/");
		System.out.println(b.length);
		System.out.println(b[b.length-1]);
		
	}

}
