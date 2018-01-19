<%-- 
    Document   : ajoutlivre
    Created on : 7 avr. 2016, 23:20:40
    Author     : Ahmadou Waly Ndiaye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@  include file="header.jsp" %>
<%@  include file="nav.jsp" %>

<h1 align="center" class="lead"> Ajout de livres !!!</h1>

<form method="post" action="${pageContext.request.contextPath}/livre">
    <div id="main-content">
        <div class="container">
            <input type="hidden" class="form-control" name="livreadd"
                   value="livreadd"
                   /> 
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4"> 
                        <!-- nom complet -->
                        <div class="form-group">
                            <label for="isbn">ISBN <span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="isbn" value="${donnees.isbn}" 
                                   required/>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <!-- nom complet -->
                        <div class="form-group">
                            <label for="titre">Titre <span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="titre"
                                   required/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4">
                        <!-- nom complet -->
                        <div class="form-group">
                            <label for="name">Auteur <span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="auteur" 
                                   required/>
                        </div>
                        <span class="erreur">${user_exist}</span>
                    </div>
                    <div class="col-md-4">
                        <!-- Pseudo -->
                        <div class="form-group">
                            <label for="editeur">Editeur<span class="text-danger">*</span> </label>
                            <input type="text" class="form-control" name="editeur"
                                   required/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4"> 
                        <!-- Email -->
                        <div class="form-group">
                            <label for="date">Date de l'edition<span class="text-danger">*</span> </label>
                            <input type="date" class="form-control" name="date"
                                   required/>
                        </div>
                    </div>

                    <div class="col-md-4"> 
                        <!-- Mot de passe -->
                        <div class="form-group">
                            <label for="nbexpl">Nombre d'exemplaires<span class="text-danger">*</span> </label>
                            <input type="number" class="form-control" name="nbexpl"
                                   />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-4"> 
                        <!-- Email -->
                        <div class="form-group">
                            <label for="nbexpldispo">Nombre d'exemplaires disponibles<span class="text-danger">*</span> </label>
                            <input type="number" class="form-control" name="nbexpldispo"
                                   required/>
                        </div>
                    </div>

                    <div class="col-md-4"> 
                        <!-- Mot de passe -->
                        <div class="form-group">
                            <label for="nbexpldispo">Type de livre
                                <span class="text-danger">*</span> 
                            </label>
                            <select name="typelivre" class="form-control">
                                <c:forEach var="item" items="${lestypes}" >
                                    <option value="${item.id}">  <c:out value="${item.libelle}" /></option>               
                                </c:forEach>
                            </select>
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
        </div>
    </div>
</form>


<%@  include file="footer.jsp" %>

<%-- 
//
    <c:foreach ...lv>
<c:choose>
<c:when test="${lv.idtype.id==livre.idtype.id"}">
<option ... selected=true...>
</c:when>
<c:otherwise>
</c:ortherwise>
</c:choose>
</c:foreach>
--%>