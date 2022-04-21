package com.rra.base.enums;

public enum UserOptionsEnum {

	DATABASE(1,"database"),
	FILE(2,"txt"),
	REST(3,"rest"),
	EMAIL(4,"email"),
	UNKNOWN(9999, "unknown");

	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	UserOptionsEnum(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public UserOptionsEnum get(String name) {
		UserOptionsEnum types[] = UserOptionsEnum.values();
		for (UserOptionsEnum type : types) {
			String i = type.getName();
			if (i.equals(name)) {
				return type;
			}
		}
		return UserOptionsEnum.UNKNOWN;
	}

	public static UserOptionsEnum get(int id) {

		UserOptionsEnum types[] = UserOptionsEnum.values();
		for (UserOptionsEnum type : types) {
			int i = type.getId();
			if (i == id) {
				return type;
			}
		}
		return UserOptionsEnum.UNKNOWN;

	}
}
