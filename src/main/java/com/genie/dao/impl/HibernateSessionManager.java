/***********************************************************************
 * Module:  HibernateSessionManager.java
 * Author:  Administrator
 * Purpose: Defines class for Hibernate session management
 ***********************************************************************/
 
package com.genie.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.genie.dao.DaoException;



/**
 * Class used for session management.
 *
 */
 
public class HibernateSessionManager {

   private static final SessionFactory sessionFactory;

   private static final ThreadLocal threadSession = new ThreadLocal();

   private static final ThreadLocal threadTransaction = new ThreadLocal();

   private static Log log = LogFactory.getLog(HibernateSessionManager.class);

   static {
      try {
         sessionFactory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
         log.error("Initial SessionFactory creation failed.", ex);
         throw new DaoException("Initial SessionFactory creation failed.", ex);
      }
   }

   /**
    * Private empty constructor. Cannot be used to create instance. Call static
    * methods directly.
    *  
    */
   private HibernateSessionManager() {

   }

   /**
    * Get session associated with current thread. Create new one from session
    * factory if no session found and openSession is true.
    * 
    * @param openSession whether to open new session if no one found
    * @return session
    * @throws DaoException
    */
   public static Session getSession(boolean openSession) throws DaoException {
      Session s = (Session) threadSession.get();
      if (openSession) {
         try {
            if (s == null) {
               s = sessionFactory.openSession();
               threadSession.set(s);
            }
         } catch (HibernateException ex) {
            log.error("Fail to open session", ex);
            throw new DaoException("Fail to open session", ex);
         }
      }
      return s;
   }

   /**
    * Set new session associated with current thread. Return old session
    * if any.
    * 
    * @param session new session
    * @return session replaced
    */
   public static Session setSession(Session session) {
      Session s = (Session) threadSession.get();
      threadSession.set(session);
      return s;
   }

   /**
    * Close session associated with current thread if exists.
    * 
    * @throws DaoException
    */
   public static void closeSession() throws DaoException {
      try {
         Session s = (Session) threadSession.get();
         threadSession.set(null);
         if (s != null && s.isOpen())
            s.close();
      } catch (HibernateException ex) {
         log.error("Fail to close session", ex);
         throw new DaoException("Fail to open session", ex);
      }
   }

   /**
    * Begin transaction associated with current thread. Begin new one from
    * session associated with current thread if no transaction found.
    * 
    * @throws DaoException
    */
   public static void beginTransaction() throws DaoException {
      Transaction tx = (Transaction) threadTransaction.get();
      try {
         if (tx == null) {
            tx = getSession(true).beginTransaction();
            threadTransaction.set(tx);
         }
      } catch (HibernateException ex) {
         log.error("Fail to begin transaction", ex);
         throw new DaoException("Fail to begin transaction", ex);
      }
   }

   /**
    * Commit transaction associated with current thread.
    * 
    * @throws DaoException
    */
   public static void commitTransaction() throws DaoException {
      Transaction tx = (Transaction) threadTransaction.get();
      try {
         if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
            tx.commit();
         threadTransaction.set(null);
      } catch (HibernateException ex) {
         rollbackTransaction();
         log.error("Fail to commit transaction", ex);
         throw new DaoException("Fail to commit transaction", ex);
      }
   }

   /**
    * Rollback transaction associated with current thread. Close session in the end.
    * 
    * @throws DaoException
    */
   public static void rollbackTransaction() throws DaoException {
      Transaction tx = (Transaction) threadTransaction.get();
      try {
         threadTransaction.set(null);
         if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
            tx.rollback();
         }
      } catch (HibernateException ex) {
         log.error("Fail to rollback transaction", ex);
         throw new DaoException("Fail to rollback transaction", ex);
      } finally {
         closeSession();
      }
   }

   /**
    * Get static session factory.
    * 
    * @return session factory
    */
   public static SessionFactory getSessionFactory() {
      return sessionFactory;
   }
}