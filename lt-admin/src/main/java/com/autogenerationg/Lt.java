package com.autogenerationg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 
 * @author lcn
 *
 */

public class Lt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Lt.createN());
	}
	
	public static String createN(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssssss");  
    	Date date = new Date();      	  
        String str = simpleDateFormat.format(date); 
        Random random = new Random();         
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数 
        return "mm"+str+rannum;// 当前时间  
	}
}
