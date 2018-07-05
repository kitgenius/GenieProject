package com.genie.entity;

/***********************************************************************
 * Module:  Auth.java
 * Author:  Administrator
 * Purpose: Defines the Class Auth
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

/** 权限 */
@Entity
@Table(name = "t_auth")
public class Auth implements java.io.Serializable {
	/** id,主键 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** 权限名称 */
	@Column(name="authName", nullable=false)
	private java.lang.String authName;
	/** 访问路径，正则表达式 */
	@Column(name="authPath", nullable=false)
	private java.lang.String authPath;
	/** 描述 */
	@Column(name="description")
	private java.lang.String description;

	/** 角色 */
	@ManyToMany(targetEntity = com.genie.entity.Role.class)
	@JoinTable(name="t_role_auth",joinColumns=@JoinColumn(name="authId"),inverseJoinColumns=@JoinColumn(name="roleId"))
	private java.util.Collection<Role> role;

	/**
	 * Empty constructor which is required by Hibernate
	 *
	 */
	public Auth() {
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
			newRole.addAuth(this);
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
				oldRole.removeAuth(this);
			}
	}

	/**
	 * @pdGenerated default removeAll
	 */
	public void removeAllRole() {
		if (role != null) {
			Role oldRole;
			for (java.util.Iterator iter = getIteratorRole(); iter.hasNext();) {
				oldRole = (Role) iter.next();
				iter.remove();
				oldRole.removeAuth(this);
			}
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
	 * Get value of authName
	 *
	 * @return authName
	 */
	public java.lang.String getAuthName() {
		return authName;
	}

	/**
	 * Set value of authName
	 *
	 * @param newAuthName
	 */
	public void setAuthName(java.lang.String newAuthName) {
		this.authName = newAuthName;
	}

	/**
	 * Get value of authPath
	 *
	 * @return authPath
	 */
	public java.lang.String getAuthPath() {
		return authPath;
	}

	/**
	 * Set value of authPath
	 *
	 * @param newAuthPath
	 */
	public void setAuthPath(java.lang.String newAuthPath) {
		this.authPath = newAuthPath;
	}

	/**
	 * Get value of description
	 *
	 * @return description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Set value of description
	 *
	 * @param newDescription
	 */
	public void setDescription(java.lang.String newDescription) {
		this.description = newDescription;
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

		if (!(other instanceof Auth))
			return false;

		Auth cast = (Auth) other;

		if (this.id != cast.getId())
			return false;

		if (this.authName == null ? cast.getAuthName() != this.authName : !this.authName.equals(cast.getAuthName()))
			return false;

		if (this.authPath == null ? cast.getAuthPath() != this.authPath : !this.authPath.equals(cast.getAuthPath()))
			return false;

		if (this.description == null ? cast.getDescription() != this.description
				: !this.description.equals(cast.getDescription()))
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
		if (this.authName != null)
			hashCode = 29 * hashCode + authName.hashCode();
		if (this.authPath != null)
			hashCode = 29 * hashCode + authPath.hashCode();
		if (this.description != null)
			hashCode = 29 * hashCode + description.hashCode();
		return hashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("Auth: ");
		ret.append("id='" + id + "'");
		ret.append("authName='" + authName + "'");
		ret.append("authPath='" + authPath + "'");
		ret.append("description='" + description + "'");
		return ret.toString();
	}

}