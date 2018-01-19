package bib.beans;

import bib.beans.Emprunt;
import bib.beans.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-18T22:59:53")
@StaticMetamodel(Lecteur.class)
public class Lecteur_ { 

    public static volatile SingularAttribute<Lecteur, String> code;
    public static volatile SingularAttribute<Lecteur, Utilisateur> utilisateur;
    public static volatile SingularAttribute<Lecteur, String> adresse;
    public static volatile SingularAttribute<Lecteur, String> tel;
    public static volatile SingularAttribute<Lecteur, Integer> id;
    public static volatile CollectionAttribute<Lecteur, Emprunt> empruntCollection;
    public static volatile SingularAttribute<Lecteur, String> email;

}