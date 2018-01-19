/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Livre;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Stateless
public class LivreDao implements ILivre {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private EJBContext context;

    @Override
    public void addLivre(Livre livre) {
        //UserTransaction tx = context.getUserTransaction();
        try {

            // tx.begin();
            if (livre.getId() == null) {
                em.persist(livre);
            } else {
                em.merge(livre);
            }
            // tx.commit();
            //merge , find etc,,,
        } catch (Exception e) {
            e.printStackTrace();
//            try {
//                tx.rollback();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
        }
    }

    @Override
    public List<Livre> getAllLivres() {
        //Query query = em.createQuery("SELECT l FROM Lecteur l");
        Query query = em.createQuery("SELECT l FROM Livre l");

        try {
            List<Livre> listliv = query.getResultList();

            return listliv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Livre getLivreByIsbn(String isbn) {
        try {
            return (Livre) em.createNamedQuery("Livre.findByIsbn").setParameter("isbn", isbn).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
