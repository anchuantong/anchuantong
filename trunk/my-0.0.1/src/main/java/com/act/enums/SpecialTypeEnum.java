package com.act.enums;


public enum SpecialTypeEnum {

	����(1),����(2),ҽѧ(3);
	
        private int value;
	
	
        public int getValue() {
        	return value;
        }

	
        public void setValue(int value) {
        	this.value = value;
        }

        SpecialTypeEnum(int value){
		this.value=value;
	}
}
