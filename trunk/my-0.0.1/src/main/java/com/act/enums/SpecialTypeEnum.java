package com.act.enums;


public enum SpecialTypeEnum {

	×ãÇò(1),°ËØÔ(2),Ò½Ñ§(3);
	
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
