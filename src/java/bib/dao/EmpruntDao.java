/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Emprunt;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Stateless
public class EmpruntDao implements IEmprunt {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Emprunt getEmpruntByLecteur(int id) {
        try {
            return (Emprunt) em.createQuery("SELECT e FROM Emprunt e "
                    + "WHERE e.idLecteur.id = :id "
                    + "AND e.dateretourreel IS NULL")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void addEmprunt(Emprunt emp) {
        try {
            if (emp.getId()==null) {
                em.persist(emp);
            }else{
                em.merge(emp);
            }

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
