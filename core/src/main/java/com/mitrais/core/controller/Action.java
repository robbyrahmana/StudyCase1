package com.mitrais.core.controller;

public enum Action {
	ADD("add"),
	SAVE("save"),
	UPDATE("update"),
	DELETE("delete"),
	GETID("getId"),
	LIST("list");

	String name;

	Action(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
