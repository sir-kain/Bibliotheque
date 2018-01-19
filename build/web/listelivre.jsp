<%-- 
    Document   : listelivre
    Created on : 8 avr. 2016, 11:10:58
    Author     : Ahmadou Waly Ndiaye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@  include file="header.jsp" %>
<%@  include file="nav.jsp" %>

<script type="text/javascript">
    $(document).ready(function () {
        $("#table").dataTable();
    });
</script> 

<div id="main-content">
    <div class="container">

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title" align="center">Les livres</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="table">
                    <thead>  <tr class="danger"><td>ISBN</td><td>TITRE</td><td>AUTEUR</td><td>EDITEUR</td>
                            <td>DATE EDITION</td><td>EXEMPLAIRES</td><td>EXEMPLAIRES DISPO</td><td>LIBELLE</td></tr> 
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${lesLivres}" >
                            <tr>
                                <td class="info">
                                    <c:out value="${item.isbn}" />
                                </td>
                                <td class="">
                                    <c:out value="${item.titre}" />
                                </td>
                                <td class="">
                                    <c:out value="${item.auteur}" />
                                </td>
                                <td class="">
                                    <c:out value="${item.editeur}" />
                                </td>
                                <td class="">
                                    <c:out value="${item.dateedition}" />
                                </td>
                                <td class="">
                                    <c:out value="${item.nbExemplaire}" />
                                </td>
                                <td class="">
                                    <c:out value="${item.nbExemplaireDispo}" />
                                </td>
                               <td class="">
                                    <c:out value="${item.idType.libelle}" />
                                </td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@  include file="footer.jsp" %>