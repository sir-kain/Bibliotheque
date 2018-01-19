<%-- 
    Document   : listelecteur
    Created on : 21 mars 2016, 14:22:35
    Author     : Ahmadou Waly Ndiaye
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@  include file="header.jsp" %>
<%@  include file="nav.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#table").dataTable();
        //"suppression" en ajax :)
        $(".btn-delete-lecteur").click(function (event) {
            var boutton = $(event.target).closest('button');
            var id = $(boutton).attr('data-id');
            $.ajax({
                url: "admin",
                type: "GET",
                //data: "action=deletelecteur&id="+id,
                data: {
                    action: 'deletelecteur',
                    idlecteur: id
                },
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (item, value) {
                        if (value === 'ok') {
                            $(boutton).closest('tr').fadeOut(500, function () {
                                $(this).remove();
                            });
                        }
                    });
                },
                error: function (x, y, z) {
                    alert(x + ' ' + y + ' ' + z);
                }
            });
        });
    });
</script> 
<div class="row">
    <div id="main-content">
        <div class="container">
            <div class="row">
                ${message}
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">Les Lecteurs Inscrits</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered" id="table">
                            <thead>  <tr class="danger"><td>CODE</td><td>ADRESSE</td><td>EMAIL</td><td>NOM </td><td> </td><td> </td></tr> </thead>
                            <tbody>
                                <c:forEach var="item" items="${leslecteurs}" >

                                    <tr><td class="info">
                                            <c:out value="${item.code}" />
                                        </td>
                                        <td class="">
                                            <c:out value="${item.adresse}" />
                                        </td>
                                        <td class="">
                                            <c:out value="${item.email}" />
                                        </td>
                                        <td class="">
                                            <c:out value="${item.utilisateur.nomcomplet}" />
                                        </td>
                                        <td>
                                            <a href="<c:url value='/admin?action=modifierLecteur'>
                                                   <c:param value="${item.id}" name="id"/></c:url>">
                                                   <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                               </a>
                                            </td>
                                            <td>
                                                <button class="btn btn-default btn-delete-lecteur" data-id="${item.id}">
                                                <span class="glyphicon glyphicon-remove btn-danger" aria-hidden="true"></span>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- <%
        List<Integer> list
                = (ArrayList<Integer>) request.getAttribute("leslecteurs");
        for (int i = 0; i < list.size(); i++) {
            out.println(list.get(i));
        }
    %> --%>
</div>

<%@  include file="footer.jsp" %>
