package lt.utils;


import lt.pay.utils.RandomUtil;
import gzlazypack.common.util.DateUtil;

/**
 * 
 * @author wz
 *	整站生成订单ID
 *	
 */
public class OrderUtil {

	/**
	 * 	整站生产订单ID 统一调用次方法
	 * @return
	 */
	public static String getOrderId(){
		return DateUtil.getDate()+RandomUtil.numberString(3);
	}
	
	/**
	 * 合并生产的id
	 * @return
	 */
	public static String getConsolidation(){
		
		return "cs-"+DateUtil.getDate()+RandomUtil.numberString(4);
	}
	
}
