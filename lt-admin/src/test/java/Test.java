import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		String [][] arrayStr = new String[4][];
		arrayStr[0] = new String[]{"红","黄","旅"};
		arrayStr[1] = new String[]{"2","3","4"};
		arrayStr[2] = new String[]{"A","B","C"};
		arrayStr[3] = new String[]{"A1","B1"};
		
		List<List<String>> table = new ArrayList<>();
		test.test(arrayStr, table);
		System.out.println(table.size());
		List<String> c1 = table.get(0);
		List<String[]> rows = new ArrayList<>();
		for (int i = 0; i < c1.size(); i++) {
			String[] row = new String[arrayStr.length];
			for (int j = 0; j < row.length; j++) {
				List<String> c = table.get(j);
				row[j] = c.get(i%c.size());
			}
			rows.add(row);
		}
		for (int i = 0; i < rows.size(); i++) {
			System.out.println(JSON.toJSONString(rows.get(i)));
		}
		/*int groupCount = arrayStr[0].length;
		
		List<String> c1 = new ArrayList<>();
		int k = 0;
		for (int i = 0; i < arrayStr[k].length; i++) {
			int groupSize = 1;
			for (int j = k + 1; j < arrayStr.length; j++) {
				groupSize = groupSize * arrayStr[j].length;
			}
			for (int j = 0; j < groupSize; j++) {
				c1.add(arrayStr[k][i]);
			}
		}
		System.out.println(JSON.toJSONString(c1));*/
		
		
		//列数 3列
		//按照第一个数组进行分组，分组数等于第一个数组长度
		//前一个数组显示条数等于后面所有数组长度乘积
		//test.test(arrayStr, 0);
		/*test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][0],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][0],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][0],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][1],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][1],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][1],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][2],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][2],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][0],arrayStr[1][2],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][0],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][0],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][0],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][1],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][1],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][1],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][2],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][2],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][1],arrayStr[1][2],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][0],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][0],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][0],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][1],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][1],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][1],arrayStr[2][2],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][2],arrayStr[2][0],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][2],arrayStr[2][1],""});
		test.test(arrayStr[3], new String[]{arrayStr[0][2],arrayStr[1][2],arrayStr[2][2],""});*/
	}
	
	public void test(String [][] arrayStr , List<List<String>> table){
		List<String> clumn = new ArrayList<>();
		int k = table.size();
		for (int i = 0; i < arrayStr[k].length; i++) {
			int groupSize = 1;
			for (int j = k + 1; j < arrayStr.length; j++) {
				groupSize = groupSize * arrayStr[j].length;
			}
			for (int j = 0; j < groupSize; j++) {
				clumn.add(arrayStr[k][i]);
			}
		}
		
		System.out.println(JSON.toJSONString(clumn));
		table.add(clumn);
		if (arrayStr.length > table.size()) {
			test(arrayStr, table);
		}
		if(arrayStr.length == table.size()){
			clumn = new ArrayList<>();
			for (int i = 0; i < clumn.size() / arrayStr[arrayStr.length - 1].length ; i++) {
				for (int j = 0; j < arrayStr[arrayStr.length - 1].length; j++) {
					clumn.add(arrayStr[arrayStr.length - 1][j]);
				}
			}
			table.add(clumn);
		}
	}
	
	public void test(String [] arrayStr, String [] str2){
		for (int i = 0; i < arrayStr.length; i ++) {
			str2[str2.length - 1] = arrayStr[i];
			System.out.println(ArrayUtils.toString(str2));
		}
	}
}
