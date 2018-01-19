/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Lecteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Local
public interface ILecteur {
     public void addLecteur(Lecteur l);
     public String generateCode();
     public List<Lecteur> getAllLecteurs();
     public Lecteur getLecteurById(int id);
     public Lecteur getLecteurByCode(String code);
     public void deleteLecteur(Lecteur l);
    
}
