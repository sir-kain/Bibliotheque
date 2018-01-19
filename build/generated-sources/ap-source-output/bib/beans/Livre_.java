package bib.beans;

import bib.beans.Emprunt;
import bib.beans.TypeLivre;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-18T22:59:53")
@StaticMetamodel(Livre.class)
public class Livre_ { 

    public static volatile SingularAttribute<Livre, Integer> nbExemplaireDispo;
    public static volatile SingularAttribute<Livre, TypeLivre> idType;
    public static volatile SingularAttribute<Livre, String> titre;
    public static volatile SingularAttribute<Livre, String> isbn;
    public static volatile SingularAttribute<Livre, Date> dateedition;
    public static volatile SingularAttribute<Livre, Integer> id;
    public static volatile SingularAttribute<Livre, Integer> nbExemplaire;
    public static volatile CollectionAttribute<Livre, Emprunt> empruntCollection;
    public static volatile SingularAttribute<Livre, String> auteur;
    public static volatile SingularAttribute<Livre, String> editeur;

}