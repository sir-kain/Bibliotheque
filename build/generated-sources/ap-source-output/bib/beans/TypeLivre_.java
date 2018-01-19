package bib.beans;

import bib.beans.Livre;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-18T22:59:53")
@StaticMetamodel(TypeLivre.class)
public class TypeLivre_ { 

    public static volatile CollectionAttribute<TypeLivre, Livre> livreCollection;
    public static volatile SingularAttribute<TypeLivre, String> libelle;
    public static volatile SingularAttribute<TypeLivre, Integer> id;

}