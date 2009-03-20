
package com.act.enums;

public enum ModuleTypeEnum {
	other(0),article(1), picture(2), links(3);
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	ModuleTypeEnum(int value) {
		this.value = value;
	}
}
