package com.mitrais.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Then extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		If conditional = (If) getParent();

		if (conditional.isTest()) {
			getJspBody().invoke(null);
		}
	}
}
