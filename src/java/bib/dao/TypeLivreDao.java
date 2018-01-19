/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.TypeLivre;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Stateless
public class TypeLivreDao implements ITypeLivre {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private EJBContext context;

    @Override
    public List<TypeLivre> getAllTypeLivre() {
        try {
            Query query = em.createQuery("SELECT t FROM TypeLivre t");
            List<TypeLivre> listType = query.getResultList();
            return listType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
