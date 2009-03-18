package com.act.enums;


public enum ModuleEnum {

	article(0),article_picture(1),article_both(2),picture(3),body(4),links(5),videos(6);
	
	private int value;
	
	
        public int getValue() {
        	return value;
        }

	
        public void setValue(int value) {
        	this.value = value;
        }

	ModuleEnum(int value){
		this.value=value;
	}
}
