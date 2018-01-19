/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.dao;

import bib.beans.Utilisateur;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Local
@Remote
public interface IUser {
    public Utilisateur logon(String login, String pass);
    public void addUser(Utilisateur u);
    public String generatepasswd();
    public Utilisateur findUserByLogin(String login);
    public void updateUser(int id);
    public Utilisateur getUserById(int id);
    public void deleteUser(Utilisateur u);
}
