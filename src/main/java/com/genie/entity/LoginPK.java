package com.genie.entity;

/***********************************************************************
 * Module:  LoginPK.java
 * Author:  Administrator
 * Purpose: Defines the priamry key class for class Login
 ***********************************************************************/
 
import java.util.*;

/**
 * Generated primary key class for Login. It is used to simplify the query
 * by primary key operation, useful especially when primary key is composite.
 *
 */
public class LoginPK implements java.io.Serializable{
   /** @pdOid 3f54f2cb-334e-4d71-af27-1aeec095ac12 */
   public int id;
   
   /**
    * Empty constructor
    *
    */
   public LoginPK(){
      // TODO Auto-generated constructor stub
   }
   
   /**
    * Constructor with parameters
    *  
    * @param id parameter to set value of field id
    */
   public LoginPK(int id) {
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
   
      LoginPK cast = (LoginPK) other;
   
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
      ret.append( "LoginPK: " );
      ret.append( "id='" + id + "'");
      return ret.toString();
   }
}