package com.genie.entity;

/***********************************************************************
 * Module:  Role.java
 * Author:  Administrator
 * Purpose: Defines the Class Role
 ***********************************************************************/

import java.util.*;

/** @pdOid 96a1c8ef-eded-4cfa-b569-05ded798c2ba */
public class Role implements java.io.Serializable {
   /** @pdOid 7a3daa4f-55c4-4c25-97ea-2794c25132e9 */
   public int id;
   /** @pdOid 383912ef-98b9-41b1-831d-059f0213c1b2 */
   public java.lang.String roleName;
   /** @pdOid e60cd72d-3749-43b7-9f3d-99e031b0334a */
   public java.lang.String description;
   
   /** @pdRoleInfo migr=no name=Auth assc=relationship3 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<Auth> auth;
   /** @pdRoleInfo migr=no name=Login assc=relationship2 coll=java.util.Collection impl=java.util.HashSet mult=0..* side=A */
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
   
   /**
    * Get value of rolePK.
    *
    * @return rolePK object
    */
   public RolePK getRolePK() {
      return new RolePK(id);
   }
   
   /**
    * Set value of rolePK.
    * 
    * @param pk
    */
   public void setRolePK(RolePK pk) {
      if (pk != null) {
         this.id = pk.getId();
      }
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