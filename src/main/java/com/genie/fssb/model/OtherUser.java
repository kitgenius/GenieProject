package com.genie.fssb.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/*author:Genie
 *date:2017年4月10日
**/
@XmlRootElement
public class OtherUser {
	LinkedList<attribute> attributes;

	public LinkedList<attribute> getAttirbutes() {
		return attributes;
	}

	@XmlElement(name="att")
	public void setAttirbutes(LinkedList<attribute> attirbutes) {
		this.attributes = attirbutes;
	}
	
	
}

class attribute{
	String name;
	String attValue;

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getAttValue() {
		return attValue;
	}

	@XmlValue
	public void setAttValue(String attValue) {
		this.attValue = attValue;
	}
	
	

	
	
}