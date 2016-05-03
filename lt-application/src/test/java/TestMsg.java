import java.util.Calendar;
import java.util.List;
import lt.sitemsg.command.notice.CheckMsgNoticeCommand;
import lt.sitemsg.command.notice.OpenMsgNoticeCommand;
import lt.sitemsg.command.notice.SendBatchMsgCommand;
import lt.sitemsg.command.notice.SendSingleMsgCommand;
import lt.sitemsg.command.sendplan.CreateMsgSendPlanCommand;
import lt.sitemsg.command.template.CreateMsgTemplateCommand;
import lt.sitemsg.entity.MsgNotice;
import lt.sitemsg.entity.MsgSendPlan;
import lt.sitemsg.exception.MsgException;
import lt.sitemsg.service.MsgNoticeService;
import lt.sitemsg.service.MsgSendPlanService;
import lt.sitemsg.service.MsgTemplateService;
import lt.sitemsg.service.MsgTextService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class TestMsg {
	
	@Autowired
	private MsgNoticeService msgNoticeService;
	@Autowired
	private MsgTemplateService msgTemplateService;
	@Autowired
	private MsgTextService msgTextService;
	@Autowired
	private MsgSendPlanService msgSendPlanService;
	
	@Test
	public void testTemplate() {
		
		CreateMsgTemplateCommand command = new CreateMsgTemplateCommand();
		command.setName("测试模版1");
		command.setTitle("本月活动通知");
		command.setContent("您好，尊敬的{P}，欢迎来参加我们的现场活动，地址是{C}，日期{C}，您的优惠码为{P}");
		command.setMsgType(1);
		command.setBatch(true);
		msgTemplateService.createMsgTemplate(command);
		
		CreateMsgTemplateCommand command1 = new CreateMsgTemplateCommand();
		command1.setName("测试模版2");
		command1.setTitle("发货提醒");
		command1.setContent("您好，尊敬的{P}，您的货物已发出，单号是{P}");
		command1.setMsgType(1);
		msgTemplateService.createMsgTemplate(command1);
		
	}
	
	@Test
	public void testSingleMsg() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		
		SendSingleMsgCommand command = new SendSingleMsgCommand();
		
		command.setUserId("001");
		command.setMsgTemplateId("988c19ead64a42e0bac0d491f45fed27");
		command.setPastDueDate(c.getTime());
		String[] params = new String[2];
		params[0] = "旺财";
		params[1] = "123456";
		command.setParams(params);
		
		msgNoticeService.sendSingleMsg(command);
	}
	
	@Test
	public void testBatchMsg() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 1);
		
		CreateMsgSendPlanCommand command = new CreateMsgSendPlanCommand();
		command.setMsgTemplateId("9d5d503e4ad2410fbbcba83ff327734c");
		command.setSendDate(c.getTime());
		
		String[] userIds = new String[2];
		userIds[0] = "001";
		userIds[1] = "002";
		
		command.setUserIds(userIds);
		
		String[] params = new String[2];
		params[0] = "人民广场";
		params[1] = "17:30";
		
		command.setParams(params);
		
		c.add(Calendar.MINUTE, 5);
		command.setPastDueDate(c.getTime());
		MsgSendPlan plan = msgSendPlanService.createMsgSendPlan(command);
		
		SendBatchMsgCommand command2 = new SendBatchMsgCommand();
		command2.setMsgSendPlanId(plan.getId());
		try {
			msgNoticeService.sendBatchMsg(command2);
		} catch (MsgException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheck() {
		CheckMsgNoticeCommand command = new CheckMsgNoticeCommand();
		command.setUserId("001");
		List<MsgNotice> list = msgNoticeService.checkMsgNoice(command);
//		System.out.println(JSON.toJSONString(list, true));
	}
	
	@Test
	public void testSee() {
		OpenMsgNoticeCommand command = new OpenMsgNoticeCommand();
		command.setMsgNoticeId("86d9666966504fa381ffda29a559f7b5");
		
		String[] params = new String[2];
		params[0] = "旺财";
		params[1] = "123456";
		command.setParams(params);
		MsgNotice notice = msgNoticeService.openMsgNotice(command);
		System.out.println(notice.getMsgText().getContent());
	}
}
