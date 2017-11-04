package com.genie.entity;
// Generated 2017-6-17 22:36:31 by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TEvent generated by hbm2java
 */
@XmlRootElement
public class TEvent implements java.io.Serializable {

	private Integer id;
	private String code;
	private String name;
	private Date time;
	private String description;

	public TEvent() {
	}

	public TEvent(String code, String name, Date time) {
		this.code = code;
		this.name = name;
		this.time = time;
	}

	public TEvent(String code, String name, Date time, String description) {
		this.code = code;
		this.name = name;
		this.time = time;
		this.description = description;
	}
	
	@XmlElement
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@XmlElement
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
