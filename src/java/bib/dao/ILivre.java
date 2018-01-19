/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Livre;
import java.util.List;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
public interface ILivre {
    public void addLivre(Livre livre);
    public List<Livre> getAllLivres();
    public Livre getLivreByIsbn(String isbn);
}
