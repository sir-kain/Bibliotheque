<%-- 
    Document   : inscription
    Created on : 10 mars 2016, 16:22:12
    Author     : Ahmadou Waly Ndiaye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@  include file="header.jsp" %>
<%@  include file="nav.jsp" %>

<h1 align="center" class="lead"> Ajouter lecteur !!!</h1>

<form method="post" action="${pageContext.request.contextPath}/admin">
    <div id="main-content">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4"> 
                        <!-- nom complet -->
                        <div class="form-group">
                            <label for="name">Code Lecteur <span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" id="code" name="code" 
                                   value="${code == null?lecteur.code:code}"
                                   readonly="readonly" required=required/>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <!-- nom complet -->
                        <div class="form-group">
                            <label for="name">Nom complet <span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="nom"
                                   value="${lecteur.utilisateur.login}"
                                   required=required/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4">
                        <!-- nom complet -->
                        <div class="form-group">
                            <label for="name">login <span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="login"
                                   value="${lecteur.utilisateur.login}"
                                   ${lecteur == null?"":"readonly='readonly'"}
                                   required=required/>
                        </div>
                        <span class="erreur">${user_exist}</span>
                    </div>
                    <div class="col-md-4">
                        <!-- Pseudo -->
                        <div class="form-group">
                            <label for="pseudo">Telephone<span class="text-danger">*</span> </label>
                            <input type="number" class="form-control" name="tel"
                                   value="${lecteur.tel}"
                                   required=required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4"> 
                        <!-- Email -->
                        <div class="form-group">
                            <label for="email">Adresse email<span class="text-danger">*</span> </label>
                            <input type="email" class="form-control" name="email"
                                   value="${lecteur.email}"
                                   required=required/>
                        </div>
                    </div>

                    <div class="col-md-4"> 
                        <!-- Mot de passe -->
                        <div class="form-group">
                            <label for="adresse">Adresse<span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="adresse"
                                   value="${lecteur.adresse}"
                                   />
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-offset-5">
                    <!-- Bouton validation-->
                    <button type="submit" class="btn btn-success" name="ajouter">Valider les informations</button>
                </div>
            </div>
            <input type="hidden" class="form-control" name="idlecteur"
                   value="${lecteur.id}"
                   /> 
        </div>
    </div>
</form>




<%@  include file="footer.jsp" %>
