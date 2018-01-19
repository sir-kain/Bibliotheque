<%-- 
    Document   : index
    Created on : 25 févr. 2016, 16:33:55
    Author     : DIOUF ABBAS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@  include file="header.jsp" %>
<br/><br/>
<div id="main-content">
    <div class="container">

        <form action="${pageContext.request.contextPath}/user" method="post" >
            <div class="col-md-8 col-md-offset-2" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center"> Connexion</h3>
                </div>
                <div class="panel-body">
                    <!-- Identifiant-->
                    <div class="form-group">
                        <label for="log">Nom d'utilisateur<span class="text-danger">*</span> </label>
                        <input type="text" class="form-control" name="log"
                               required/>
                    </div>


                    <!-- Mot de passe -->
                    <div class="form-group">
                        <label for="password">Mot de passe<span class="text-danger">*</span> </label>
                        <input type="password" class="form-control" id="pwd" name="pwd"
                               required/>
                    </div>

                    <!-- Bouton validation-->
                    <button type="submit" class="btn btn-success" name="cnx">Valider les informations</button>
                </div>
                <div class="panel-danger">
                    ${msg}
                </div>
            </div>
                </div>
        </form>
        
    </div>
</div>


<%@  include file="footer.jsp" %>

