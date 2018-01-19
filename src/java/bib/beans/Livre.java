/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@Entity
@Table(name = "livre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l"),
    @NamedQuery(name = "Livre.findById", query = "SELECT l FROM Livre l WHERE l.id = :id"),
    @NamedQuery(name = "Livre.findByIsbn", query = "SELECT l FROM Livre l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Livre.findByTitre", query = "SELECT l FROM Livre l WHERE l.titre = :titre"),
    @NamedQuery(name = "Livre.findByAuteur", query = "SELECT l FROM Livre l WHERE l.auteur = :auteur"),
    @NamedQuery(name = "Livre.findByEditeur", query = "SELECT l FROM Livre l WHERE l.editeur = :editeur"),
    @NamedQuery(name = "Livre.findByDateedition", query = "SELECT l FROM Livre l WHERE l.dateedition = :dateedition"),
    @NamedQuery(name = "Livre.findByNbExemplaire", query = "SELECT l FROM Livre l WHERE l.nbExemplaire = :nbExemplaire"),
    @NamedQuery(name = "Livre.findByNbExemplaireDispo", query = "SELECT l FROM Livre l WHERE l.nbExemplaireDispo = :nbExemplaireDispo")})
public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "auteur")
    private String auteur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "editeur")
    private String editeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateedition")
    @Temporal(TemporalType.DATE)
    private Date dateedition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_exemplaire")
    private int nbExemplaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_exemplaire_dispo")
    private int nbExemplaireDispo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLivre")
    private Collection<Emprunt> empruntCollection;
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TypeLivre idType;

    public Livre() {
    }

    public Livre(Integer id) {
        this.id = id;
    }

    public Livre(Integer id, String isbn, String titre, String auteur, String editeur, Date dateedition, int nbExemplaire, int nbExemplaireDispo) {
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.dateedition = dateedition;
        this.nbExemplaire = nbExemplaire;
        this.nbExemplaireDispo = nbExemplaireDispo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Date getDateedition() {
        return dateedition;
    }

    public void setDateedition(Date dateedition) {
        this.dateedition = dateedition;
    }

    public int getNbExemplaire() {
        return nbExemplaire;
    }

    public void setNbExemplaire(int nbExemplaire) {
        this.nbExemplaire = nbExemplaire;
    }

    public int getNbExemplaireDispo() {
        return nbExemplaireDispo;
    }

    public void setNbExemplaireDispo(int nbExemplaireDispo) {
        this.nbExemplaireDispo = nbExemplaireDispo;
    }

    @XmlTransient
    public Collection<Emprunt> getEmpruntCollection() {
        return empruntCollection;
    }

    public void setEmpruntCollection(Collection<Emprunt> empruntCollection) {
        this.empruntCollection = empruntCollection;
    }

    public TypeLivre getIdType() {
        return idType;
    }

    public void setIdType(TypeLivre idType) {
        this.idType = idType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bib.beans.Livre[ id=" + id + " ]";
    }
    
}
