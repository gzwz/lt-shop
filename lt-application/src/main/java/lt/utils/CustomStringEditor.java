package lt.utils;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

public class CustomStringEditor extends PropertyEditorSupport{
	
	private String charset;

	public CustomStringEditor() {}
	
	public CustomStringEditor(String charset) {
		this.charset = charset;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			if (StringUtils.isNotBlank(text)) {
				text = ConverStr.changeCharset(text,ConverStr.ISO_8859_1, charset);
			}
			setValue(text);
			return;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
}
