import gzlazypack.common.util.PwdUtil;

import org.junit.Test;



public class pwdTest {
	@Test
	public static void main(String args[]){
		
		String s = PwdUtil.getPwd("123456");
		System.out.println(s);
	}
}
