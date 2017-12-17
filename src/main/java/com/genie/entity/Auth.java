package com.genie.entity;

/***********************************************************************
 * Module:  Auth.java
 * Author:  Administrator
 * Purpose: Defines the Class Auth
 ***********************************************************************/

import java.util.*;

/** @pdOid bcb1a670-8e66-4506-a0dd-6fed3f3ce21e */
public class Auth implements java.io.Serializable {
   /** @pdOid 908b595b-aec5-4883-ac2f-8fbb5a66938b */
   public int id;
   /** @pdOid a29d2b39-3e85-42d0-918e-cf09d7e48d06 */
   public java.lang.String authName;
   /** @pdOid f92fe73e-115d-408b-b1a9-46a44a0a8a95 */
   public java.lang.String authPath;
   /** @pdOid 744a5564-c22f-4117-9df2-eb4ce52edfac */
   public java.lang.String description;
   
   /** @pdRoleInfo migr=no name=Role assc=relationship3 coll=java.util.Collection impl=java.util.HashSet mult=0..* side=A */
   public java.util.Collection<Role> role;
   
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
      //removeAllRole();
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
      if (!this.role.contains(newRole))
      {
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
         if (this.role.contains(oldRole))
         {
            this.role.remove(oldRole);
            oldRole.removeAuth(this);
         }
   }
   
   /**
    * @pdGenerated default removeAll
    */
   public void removeAllRole() {
      if (role != null)
      {
         Role oldRole;
         for (java.util.Iterator iter = getIteratorRole(); iter.hasNext();)
         {
            oldRole = (Role)iter.next();
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
    * Get value of authName
    *
    * @return authName 
    */
   public java.lang.String getAuthName()
   {
      return authName;
   }
   
   /**
    * Set value of authName
    *
    * @param newAuthName 
    */
   public void setAuthName(java.lang.String newAuthName)
   {
      this.authName = newAuthName;
   }
   
   /**
    * Get value of authPath
    *
    * @return authPath 
    */
   public java.lang.String getAuthPath()
   {
      return authPath;
   }
   
   /**
    * Set value of authPath
    *
    * @param newAuthPath 
    */
   public void setAuthPath(java.lang.String newAuthPath)
   {
      this.authPath = newAuthPath;
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
    * Get value of authPK.
    *
    * @return authPK object
    */
   public AuthPK getAuthPK() {
      return new AuthPK(id);
   }
   
   /**
    * Set value of authPK.
    * 
    * @param pk
    */
   public void setAuthPK(AuthPK pk) {
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
   
      if (!(other instanceof Auth))
         return false;
   
      Auth cast = (Auth) other;
   
      if (this.id != cast.getId())
         return false;
   
      if (this.authName == null ? cast.getAuthName() != this.authName : !this.authName.equals( cast.getAuthName()))
         return false;
   
      if (this.authPath == null ? cast.getAuthPath() != this.authPath : !this.authPath.equals( cast.getAuthPath()))
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
      if (this.authName != null) 
         hashCode = 29 * hashCode + authName.hashCode();
      if (this.authPath != null) 
         hashCode = 29 * hashCode + authPath.hashCode();
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
      ret.append( "Auth: " );
      ret.append( "id='" + id + "'");
      ret.append( "authName='" + authName + "'");
      ret.append( "authPath='" + authPath + "'");
      ret.append( "description='" + description + "'");
      return ret.toString();
   }

}