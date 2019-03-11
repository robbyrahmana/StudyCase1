package com.mitrais.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class If extends SimpleTagSupport {

	private boolean test;

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public void doTag() throws JspException, IOException {
		getJspBody().invoke(null);
	}

}
