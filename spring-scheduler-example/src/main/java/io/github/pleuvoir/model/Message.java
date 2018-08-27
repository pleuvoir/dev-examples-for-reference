package io.github.pleuvoir.model;

import io.github.pleuvoir.kit.ToJSON;

public class Message implements ToJSON{

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
