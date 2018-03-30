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
@Table(name="t_role")
public class Role implements java.io.Serializable {
   /** id，主键 */
   public int id;
   /** 角色名称 */
   public java.lang.String roleName;
   /** 角色描述 */
   public java.lang.String description;
   
   /** 授权，按路径授权  */
   public java.util.Collection<Auth> auth;
   /** 后台登陆账号 */
   public java.util.Collection<Login> login;
   
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
   @ManyToMany(targetEntity=com.genie.entity.Auth.class,cascade={CascadeType.PERSIST,CascadeType.MERGE})
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
      //removeAllAuth();
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
      if (!this.auth.contains(newAuth))
      {
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
         if (this.auth.contains(oldAuth))
         {
            this.auth.remove(oldAuth);
            oldAuth.removeRole(this);
         }
   }
   
   /**
    * @pdGenerated default removeAll
    */
   public void removeAllAuth() {
      if (auth != null)
      {
         Auth oldAuth;
         for (java.util.Iterator iter = getIteratorAuth(); iter.hasNext();)
         {
            oldAuth = (Auth)iter.next();
            iter.remove();
            oldAuth.removeRole(this);
         }
      }
   }
   /**
    * @pdGenerated default getter
    */
   @ManyToMany(targetEntity=com.genie.entity.Login.class)
   public java.util.Collection<Login> getLogin() {
      if (login == null)
         login = new java.util.HashSet<Login>();
      return login;
   }
   
   /**
    * @pdGenerated default iterator getter
    */
   public java.util.Iterator getIteratorLogin() {
      if (login == null)
         login = new java.util.HashSet<Login>();
      return login.iterator();
   }
   
   /** 
    * @pdGenerated default setter
    * @param newLogin
    */
   public void setLogin(java.util.Collection<Login> newLogin) {
      //removeAllLogin();
      this.login = newLogin;   
   }
   
   /** 
    * @pdGenerated default add
    * @param newLogin
    */
   public void addLogin(Login newLogin) {
      if (newLogin == null)
         return;
      if (this.login == null)
         this.login = new java.util.HashSet<Login>();
      if (!this.login.contains(newLogin))
      {
         this.login.add(newLogin);
         newLogin.addRole(this);
      }
   }
   
   /** 
    * @pdGenerated default remove
    * @param oldLogin
    */
   public void removeLogin(Login oldLogin) {
      if (oldLogin == null)
         return;
      if (this.login != null)
         if (this.login.contains(oldLogin))
         {
            this.login.remove(oldLogin);
            oldLogin.removeRole(this);
         }
   }
   
   /**
    * @pdGenerated default removeAll
    */
   public void removeAllLogin() {
      if (login != null)
      {
         Login oldLogin;
         for (java.util.Iterator iter = getIteratorLogin(); iter.hasNext();)
         {
            oldLogin = (Login)iter.next();
            iter.remove();
            oldLogin.removeRole(this);
         }
      }
   }
   
   /**
    * Get value of id
    *
    * @return id 
    */
   @Id
   @Column(name="id")
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   public int getId()
   {
      return id;
   }
   
   /**
    * Set value of id
    *
    * @param newId 
    */
   public void setId(int newId)
   {
      this.id = newId;
   }
   
   /**
    * Get value of roleName
    *
    * @return roleName 
    */
   @Column(name="roleName",nullable=false)
   public java.lang.String getRoleName()
   {
      return roleName;
   }
   
   /**
    * Set value of roleName
    *
    * @param newRoleName 
    */
   public void setRoleName(java.lang.String newRoleName)
   {
      this.roleName = newRoleName;
   }
   
   /**
    * Get value of description
    *
    * @return description 
    */
   @Column(name="description")
   public java.lang.String getDescription()
   {
      return description;
   }
   
   /**
    * Set value of description
    *
    * @param newDescription 
    */
   public void setDescription(java.lang.String newDescription)
   {
      this.description = newDescription;
   }
   
   
   /* (non-Javadoc)
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
   
      if (this.roleName == null ? cast.getRoleName() != this.roleName : !this.roleName.equals( cast.getRoleName()))
         return false;
   
      if (this.description == null ? cast.getDescription() != this.description : !this.description.equals( cast.getDescription()))
         return false;
   
      return true;
   }
   
   /* (non-Javadoc)
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
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      StringBuffer ret = new StringBuffer();
      ret.append( "Role: " );
      ret.append( "id='" + id + "'");
      ret.append( "roleName='" + roleName + "'");
      ret.append( "description='" + description + "'");
      return ret.toString();
   }

}