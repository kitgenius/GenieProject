package com.genie.entity;

/***********************************************************************
 * Module:  Role.java
 * Author:  Administrator
 * Purpose: Defines the Class Role
 ***********************************************************************/

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/** 角色 */
@Entity
@Table(name = "t_role")
public class Role implements java.io.Serializable {
	/** id，主键 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	/** 角色名称 */
	@Column(name = "roleName", nullable = false)
	public java.lang.String roleName;
	/** 角色描述 */
	@Column(name = "description")
	public java.lang.String description;

	/** 授权，按路径授权 */
	@ManyToMany(targetEntity = com.genie.entity.Auth.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public java.util.Collection<Auth> auth;
	/** 后台登陆账号 */
	@ManyToMany(targetEntity = com.genie.entity.ManagerUser.class)
	public java.util.Collection<ManagerUser> managerUser;

	/**
	 * Empty constructor which is required by Hibernate
	 *
	 */
	public Role() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @pdGenerated default getter
	 */
	public java.util.Collection<Auth> getAuth() {
		if (auth == null)
			auth = new java.util.HashSet<Auth>();
		return auth;
	}

	/**
	 * @pdGenerated default iterator getter
	 */
	public java.util.Iterator getIteratorAuth() {
		if (auth == null)
			auth = new java.util.HashSet<Auth>();
		return auth.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newAuth
	 */
	public void setAuth(java.util.Collection<Auth> newAuth) {
		// removeAllAuth();
		this.auth = newAuth;
	}

	/**
	 * @pdGenerated default add
	 * @param newAuth
	 */
	public void addAuth(Auth newAuth) {
		if (newAuth == null)
			return;
		if (this.auth == null)
			this.auth = new java.util.HashSet<Auth>();
		if (!this.auth.contains(newAuth)) {
			this.auth.add(newAuth);
			newAuth.addRole(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldAuth
	 */
	public void removeAuth(Auth oldAuth) {
		if (oldAuth == null)
			return;
		if (this.auth != null)
			if (this.auth.contains(oldAuth)) {
				this.auth.remove(oldAuth);
				oldAuth.removeRole(this);
			}
	}

	/**
	 * @pdGenerated default removeAll
	 */
	public void removeAllAuth() {
		if (auth != null) {
			Auth oldAuth;
			for (java.util.Iterator iter = getIteratorAuth(); iter.hasNext();) {
				oldAuth = (Auth) iter.next();
				iter.remove();
				oldAuth.removeRole(this);
			}
		}
	}

	/**
	 * @pdGenerated default getter
	 */
	public java.util.Collection<ManagerUser> getmanagerUser() {
		if (managerUser == null)
			managerUser = new java.util.HashSet<ManagerUser>();
		return managerUser;
	}

	/**
	 * @pdGenerated default iterator getter
	 */
	public java.util.Iterator getIteratormanagerUser() {
		if (managerUser == null)
			managerUser = new java.util.HashSet<ManagerUser>();
		return managerUser.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newmanagerUser
	 */
	public void setmanagerUser(java.util.Collection<ManagerUser> newmanagerUser) {
		// removeAllmanagerUser();
		this.managerUser = newmanagerUser;
	}

	/**
	 * @pdGenerated default add
	 * @param newmanagerUser
	 */
	public void addmanagerUser(ManagerUser newmanagerUser) {
		if (newmanagerUser == null)
			return;
		if (this.managerUser == null)
			this.managerUser = new java.util.HashSet<ManagerUser>();
		if (!this.managerUser.contains(newmanagerUser)) {
			this.managerUser.add(newmanagerUser);
			newmanagerUser.addRole(this);
		}
	}

	/**
	 * @pdGenerated default remove
	 * @param oldmanagerUser
	 */
	public void removemanagerUser(ManagerUser oldmanagerUser) {
		if (oldmanagerUser == null)
			return;
		if (this.managerUser != null)
			if (this.managerUser.contains(oldmanagerUser)) {
				this.managerUser.remove(oldmanagerUser);
				oldmanagerUser.removeRole(this);
			}
	}

	/**
	 * @pdGenerated default removeAll
	 */
	public void removeAllmanagerUser() {
		if (managerUser != null) {
			ManagerUser oldmanagerUser;
			for (java.util.Iterator iter = getIteratormanagerUser(); iter.hasNext();) {
				oldmanagerUser = (ManagerUser) iter.next();
				iter.remove();
				oldmanagerUser.removeRole(this);
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
	 * Get value of roleName
	 *
	 * @return roleName
	 */
	public java.lang.String getRoleName() {
		return roleName;
	}

	/**
	 * Set value of roleName
	 *
	 * @param newRoleName
	 */
	public void setRoleName(java.lang.String newRoleName) {
		this.roleName = newRoleName;
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

		if (!(other instanceof Role))
			return false;

		Role cast = (Role) other;

		if (this.id != cast.getId())
			return false;

		if (this.roleName == null ? cast.getRoleName() != this.roleName : !this.roleName.equals(cast.getRoleName()))
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
		if (this.roleName != null)
			hashCode = 29 * hashCode + roleName.hashCode();
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
		ret.append("Role: ");
		ret.append("id='" + id + "'");
		ret.append("roleName='" + roleName + "'");
		ret.append("description='" + description + "'");
		return ret.toString();
	}

}