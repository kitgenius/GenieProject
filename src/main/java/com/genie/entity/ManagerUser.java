package com.genie.entity;

/***********************************************************************
 * Module:  Login.java
 * Author:  Administrator
 * Purpose: Defines the Class Login
 ***********************************************************************/

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/** 后台用户登陆账号 */
@Entity
@Table(name = "t_managerUser")
public class ManagerUser implements java.io.Serializable {
	/** id，主键 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** 用户名 */
	@Column(name = "username", nullable = false)
	private java.lang.String username;
	/** 密码 */
	@Column(name = "password", nullable = false)
	private java.lang.String password;

	/**
	 * @pdRoleInfo migr=no name=Role assc=角色关联关系 coll=java.util.Collection
	 *             impl=java.util.HashSet mult=0..*
	 */
	@ManyToMany(targetEntity = com.genie.entity.Role.class)
	@JoinTable(name = "t_manageruser_role", joinColumns = @JoinColumn(name = "managerUserId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private java.util.Collection<Role> role;

	/**
	 * Empty constructor which is required by Hibernate
	 *
	 */
	public ManagerUser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @pdGenerated default getter
	 */
	public java.util.Collection<Role> getRole() {
		if (role == null)
			role = new java.util.HashSet<Role>();
		return role;
	}

	/**
	 * @pdGenerated default iterator getter
	 */
	public java.util.Iterator getIteratorRole() {
		if (role == null)
			role = new java.util.HashSet<Role>();
		return role.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newRole
	 */
	public void setRole(java.util.Collection<Role> newRole) {
		// removeAllRole();
		this.role = newRole;
	}

	/**
	 * @pdGenerated default add
	 * @param newRole
	 */
	public void addRole(Role newRole) {
		if (newRole == null)
			return;
		if (this.role == null)
			this.role = new java.util.HashSet<Role>();
		if (!this.role.contains(newRole)) {
			this.role.add(newRole);
			newRole.addManagerUser(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldRole
	 */
	public void removeRole(Role oldRole) {
		if (oldRole == null)
			return;
		if (this.role != null)
			if (this.role.contains(oldRole)) {
				this.role.remove(oldRole);
				oldRole.removeManagerUser(this);
			}
	}

	/**
	 * Get value of id
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set value of id
	 *
	 * @param newId
	 */
	public void setId(int newId) {
		this.id = newId;
	}

	/**
	 * Get value of username
	 *
	 * @return username
	 */
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * Set value of username
	 *
	 * @param newUsername
	 */
	public void setUsername(java.lang.String newUsername) {
		this.username = newUsername;
	}

	/**
	 * Get value of password
	 *
	 * @return password
	 */
	public java.lang.String getPassword() {
		return password;
	}

	/**
	 * Set value of password
	 *
	 * @param newPassword
	 */
	public void setPassword(java.lang.String newPassword) {
		this.password = newPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {

		if (other == null)
			return false;

		if (other == this)
			return true;

		if (!(other instanceof ManagerUser))
			return false;

		ManagerUser cast = (ManagerUser) other;

		if (this.id != cast.getId())
			return false;

		if (this.username == null ? cast.getUsername() != this.username : !this.username.equals(cast.getUsername()))
			return false;

		if (this.password == null ? cast.getPassword() != this.password : !this.password.equals(cast.getPassword()))
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int hashCode = 0;
		hashCode = 29 * hashCode + (new Integer(id)).hashCode();
		if (this.username != null)
			hashCode = 29 * hashCode + username.hashCode();
		if (this.password != null)
			hashCode = 29 * hashCode + password.hashCode();
		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Login: ");
		ret.append("id='" + id + "'");
		ret.append("username='" + username + "'");
		ret.append("password='" + password + "'");
		return ret.toString();
	}

}