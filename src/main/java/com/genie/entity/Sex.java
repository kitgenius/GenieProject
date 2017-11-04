package com.genie.entity;
/*author:Genie
 *date:2017年10月17日
**/
public enum Sex {
	MALE("male"),FEMALE("female");
	private String sexStr;
	
	Sex(String sexStr){
		this.sexStr = sexStr;
	}
	
	public String getSexStr(){
		return sexStr;
	}
	
}
