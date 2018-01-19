package bib.beans;

import bib.beans.Lecteur;
import bib.beans.Livre;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-18T22:59:53")
@StaticMetamodel(Emprunt.class)
public class Emprunt_ { 

    public static volatile SingularAttribute<Emprunt, Livre> idLivre;
    public static volatile SingularAttribute<Emprunt, Date> dateretour;
    public static volatile SingularAttribute<Emprunt, Date> dateouv;
    public static volatile SingularAttribute<Emprunt, Date> dateretourreel;
    public static volatile SingularAttribute<Emprunt, Integer> id;
    public static volatile SingularAttribute<Emprunt, Lecteur> idLecteur;

}