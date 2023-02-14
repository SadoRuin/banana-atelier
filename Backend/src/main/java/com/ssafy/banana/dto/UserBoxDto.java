package com.ssafy.banana.dto;

public class UserBoxDto<T extends UserDto> {
	private T t;

	public UserBoxDto() {
	}

	public UserBoxDto(T t) {
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
}
