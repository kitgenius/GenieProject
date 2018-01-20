package com.genie.entity;
// default package
// Generated 2017-11-8 22:22:22 by Hibernate Tools 4.3.1.Final

/**
 * TContact generated by hbm2java
 */
public class Contact implements java.io.Serializable {

	private int id;
	private String address;
	private Integer areaId;
	private String telephone;
	private String qq;
	private String wechat;
	private String mail;

	public Contact() {
	}

	public Contact(int id) {
		this.id = id;
	}

	public Contact(int id, String address, Integer areaId, String telephone, String qq, String wechat, String mail) {
		this.id = id;
		this.address = address;
		this.areaId = areaId;
		this.telephone = telephone;
		this.qq = qq;
		this.wechat = wechat;
		this.mail = mail;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return this.wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}