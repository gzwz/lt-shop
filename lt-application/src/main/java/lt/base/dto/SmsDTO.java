package lt.base.dto;

import java.util.Map;

@SuppressWarnings("serial")
public class SmsDTO extends BaseDTO {
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 消息 
	 */
	private String msg;
	/**
	 * 结果
	 */
	private Map<String, Object> result;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
}
