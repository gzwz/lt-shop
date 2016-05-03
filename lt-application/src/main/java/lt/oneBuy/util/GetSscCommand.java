package lt.oneBuy.util;

import org.junit.Test;




import com.alibaba.fastjson.JSON;

import lt.oneBuy.command.SscCommand;

public class GetSscCommand {
	/**
	 * 
	 * @return 将获取的时时彩数据转换成command
	 *  static SscCommand
	 */
	public static SscCommand get(){
		
		SscCommand command = new SscCommand();
		String data = SscUtil.getSscData();
		System.out.println(data);
		Root root = new Root();
		root =  JSON.parseObject(data, Root.class);
		String openCode =  root.getData().get(0).getOpencode().replaceAll(",", "");
		command.setExpect(root.getData().get(0).getExpect());
		command.setOpencode(openCode);
		command.setOpentime(root.getData().get(0).getOpentime());
		command.setOpentimestamp(root.getData().get(0).getOpentimestamp());

		return command;
		
		
	}
}
