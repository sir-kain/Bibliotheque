/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Emprunt;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
public interface IEmprunt {
    public Emprunt getEmpruntByLecteur(int id);
    public void addEmprunt(Emprunt emp);
}
