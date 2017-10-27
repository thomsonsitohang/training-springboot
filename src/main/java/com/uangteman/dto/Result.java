package com.uangteman.dto;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {
	private List<String> message = new ArrayList<String>();
	private T payload;
	
	public Result() {}

	public Result(List<String> message, T payload) {
		super();
		this.message = message;
		this.payload = payload;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}
	
}
