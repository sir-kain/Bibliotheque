/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Utilisateur;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserDao implements IUser {
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private EJBContext context;

    @Override
    public Utilisateur logon(String login, String pass) {
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login=:log AND u.pwd =:pw");
        query.setParameter("log", login);
        query.setParameter("pw", pass);
        List<Utilisateur> users = query.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
      @Override
    public void addUser(Utilisateur u) {
        UserTransaction tx = context.getUserTransaction();
        try {
            tx.begin();
            //em.persist(l.getUtilisateur());
            if (u.getId() == null) {
                em.persist(u);
            }else{
                em.merge(u);
            }
            
            tx.commit();
            //merge , find etc,,,
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();            
            } 
        }
    }

    @Override
    public String generatepasswd() {
        Query query = em.createQuery("SELECT MAX(u.id) FROM Utilisateur u");
        Integer i = (Integer) query.getSingleResult();
        if(i==null){
            return "L00001";
        }
        long res = Math.round((1+Math.random())*999);
        return res +"L" + new DecimalFormat("000000").format(i+1);
    }   

    @Override
    public Utilisateur findUserByLogin(String login) {
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login=:log");
        query.setParameter("log", login);
        //Utilisateur u = (Utilisateur) query.getSingleResult();
        List<Utilisateur> users = query.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(int id) {
        try {
            
             em.merge(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    
    @Override
    public void deleteUser(Utilisateur u) {
           u = em.find(Utilisateur.class, u.getId());
        UserTransaction tx = context.getUserTransaction();
        try {

            tx.begin();
            //Utilisateur user = l.getUtilisateur();
            em.remove(em.merge(u));
            //em.remove(em.merge(user));
            tx.commit();
            //merge , find etc,,,
        } catch (Exception e) {
            try {
                tx.rollback();
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Utilisateur getUserById(int id) {
          return em.find(Utilisateur.class, id);
    }

    
   
    
}

