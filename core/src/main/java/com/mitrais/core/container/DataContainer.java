package com.mitrais.core.container;

public class DataContainer<T extends Object> {
	private T data;
	private boolean status = true;
	private String message;
	private String redirectPage;
	private String redirectAction;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRedirectPage() {
		return redirectPage;
	}

	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

	public String getRedirectAction() {
		return redirectAction;
	}

	public void setRedirectAction(String redirectAction) {
		this.redirectAction = redirectAction;
	}

}
