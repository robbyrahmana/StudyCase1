package com.mitrais.tags;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class For extends SimpleTagSupport {

	private Collection<Object> items;
	private String value;

	public Collection<Object> getItems() {
		return items;
	}

	public void setItems(Collection<Object> items) {
		this.items = items;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void doTag() throws JspException, IOException {
		for (Object object : items) {
			getJspContext().setAttribute(value, object);
			getJspBody().invoke(null);
		}
	}
}
