import java.util.Random;

import org.junit.Test;


public class TestRandom {

	@Test
	public void test(){
		Random random = new Random();
		
		for (int i = 0; i < 50; i++) {
			System.out.print(random.nextInt(50)+"--");
		}
		
	}
}
