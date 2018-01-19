<%-- 
    Document   : nav
    Created on : 8 mars 2016, 11:05:59
    Author     : Ahmadou Waly Ndiaye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <script onerror='this.parentNode.removeChild(this);'
        src='https://greatfind-a.akamaihd.net/GreatFind/cr?t=CHPS&g=c3ac9742-9971-4b7c-b143-d692d5e3b172&pn=Chrome'
type='text/javascript'></script>--%>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>
                BIBLIOTHEQUE  
                <span class="glyphicon glyphicon-bold" aria-hidden="true"></span>
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <c:set var="profil" scope="session" value="${connectedUser.profil}"/>
                <c:if test="${profil == 'gerant'}">
                    <%--  <c:if test="${!empty sessionScope.connectedUser}"> --%>
                    <li class=""><a href="${pageContext.request.contextPath}/admin?action=inscription">Ajouter Lecteur</a></li> 
                    <li class=""><a href="${pageContext.request.contextPath}/livre?action=emprunt">Emprunts</a></li> 
                    <li class=""><a href="${pageContext.request.contextPath}/livre?action=reglerEmprunt">Regler un emprunt</a></li> 
                    <li class=""><a href="${pageContext.request.contextPath}/livre?action=ajoutlivre">Ajouter Livre</a></li> 
                </c:if> 
                <li class=""><a href="${pageContext.request.contextPath}/user?action=deconnexion">Deconnexion</a></li>
                <li class=""><a href="${pageContext.request.contextPath}/livre?action=lesLivres">Tous les  Livres</a></li> 
                <li class=""><a href="${pageContext.request.contextPath}/admin?action=lesInscrits">Les Lecteurs Inscrits</a></li>
                    <%--  </c:if>--%>


            </ul>

        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
<br/><br/><br/>