/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Lecteur;
import bib.beans.Utilisateur;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LecteurDao implements ILecteur {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private EJBContext context;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addLecteur(Lecteur l) {
        UserTransaction tx = context.getUserTransaction();
        try {

            tx.begin();
            if (l.getId() == null) {
                em.persist(l);
            } else {
                em.merge(l);
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
    public String generateCode() {
        Query query = em.createQuery("SELECT MAX(l.id) FROM Lecteur l");
        Integer i = (Integer) query.getSingleResult();
        if (i == null) {
            return "L00001";
        }
        return "L" + new DecimalFormat("000000").format(i + 1);
    }

    @Override
    public List<Lecteur> getAllLecteurs() {
        //Query query = em.createQuery("SELECT l FROM Lecteur l");
        Query query = em.createQuery("SELECT l FROM Lecteur l");

        try {
            List<Lecteur> listlect = query.getResultList();

            return listlect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Lecteur getLecteurById(int id) {
        return em.find(Lecteur.class, id);
    }

    @Override
    public void deleteLecteur(Lecteur l) {
        l = em.find(Lecteur.class, l.getId());
        UserTransaction tx = context.getUserTransaction();
        try {

            tx.begin();
            Utilisateur user = l.getUtilisateur();
            em.remove(em.merge(l));
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
    public Lecteur getLecteurByCode(String code) {
        try {
            return (Lecteur) em.createNamedQuery("Lecteur.findByCode").setParameter("code", code).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
