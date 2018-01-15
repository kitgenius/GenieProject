package com.genie.entity;

/***********************************************************************
 * Module:  RolePK.java
 * Author:  Administrator
 * Purpose: Defines the priamry key class for class Role
 ***********************************************************************/
 
import java.util.*;

/**
 * Generated primary key class for Role. It is used to simplify the query
 * by primary key operation, useful especially when primary key is composite.
 *
 */
public class RolePK implements java.io.Serializable{
   /** @pdOid 7a3daa4f-55c4-4c25-97ea-2794c25132e9 */
   public int id;
   
   /**
    * Empty constructor
    *
    */
   public RolePK(){
      // TODO Auto-generated constructor stub
   }
   
   /**
    * Constructor with parameters
    *  
    * @param id parameter to set value of field id
    */
   public RolePK(int id) {
      this.id = id;
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

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object other) {
   
      if (other == null) {
         return false;
      }
      
      if (other == this) {
         return true;
      }
      
      if (! (other.getClass() ==  getClass()))
         return false;
   
      RolePK cast = (RolePK) other;
   
      if (this.id != cast.getId())
         return false;
   
      return true;
   }

   
   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   public int hashCode() {
      int _hashCode = 0;
      _hashCode = 29 * _hashCode + (new Integer(id)).hashCode();
      return _hashCode;
   }

  
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      StringBuffer ret = new StringBuffer();
      ret.append( "RolePK: " );
      ret.append( "id='" + id + "'");
      return ret.toString();
   }
}