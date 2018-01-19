<%-- 
    Document   : emprunt
    Created on : 8 avr. 2016, 16:25:08
    Author     : Ahmadou Waly Ndiaye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%@  include file="header.jsp" %>
<%@  include file="nav.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#b").attr("disabled", "true");
        $("#code").change(function () {
            if ($("#code").val() !== "") {
                var code = $("#code").val();
                var rgemp = $("#reglerEmprunt").val();
                $.ajax({
                    url: "livre",
                    data: {
                        action: "getlecteur",
                        code: code,
                        rgemp: rgemp
                    },
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        $.each(data, function (item, value) {
                            if (value.id !== 0)
                            {
                                if (value.id === -1)
                                {
                                    $("#code").css({
                                        "color": "red",
                                        "font-size": "100%"
                                    });
                                    alert(value.message);
                                    $("#nam").val("");
                                    $("#tel").val("");
                                    $("#email").val("");
                                    $("#idlecteur").val("");
                                    if (rgemp === "reglerEmprunt") {
                                        $("#isbn").val("");
                                        $("#titre").val("");
                                        $("#auteur").val("");
                                        $("#editeur").val("");
                                    }
                                    $("#b").attr("disabled", "true");
                                    return;
                                }
                                $("#code").css({
                                    "color": "green",
                                    "font-size": "100%"
                                });
                                $("#el").fadeIn("slow");
                                $("#row").fadeIn("slow");
                                $("#nam").val(value.name);
                                $("#tel").val(value.tel);
                                $("#email").val(value.email);
                                $("#idlecteur").val(value.id);
                                if (rgemp === "reglerEmprunt") {
                                    $("#isbn").val(value.isbn);
                                    $("#titre").val(value.titre);
                                    $("#auteur").val(value.auteur);
                                    $("#editeur").val(value.editeur);
                                    $("#b").removeAttr("disabled");
                                } else {
                                    if ($("#idlecteur").val() !== "" && $("#idlivre").val() !== "") {
                                        $("#b").removeAttr("disabled");
                                    } else {
                                        $("#b").attr("disabled", "true");
                                    }
                                }
                            } else
                            {
                                $("#code").css({
                                    "color": "red",
                                    "font-size": "100%"
                                });
                                $("#el").fadeOut();
                                $("#row").fadeOut();
                                $("#nam").val("");
                                $("#tel").val("");
                                $("#email").val("");
                                $("#idlecteur").val("");
                                if (rgemp === "reglerEmprunt") {
                                    $("#isbn").val("");
                                    $("#titre").val("");
                                    $("#auteur").val("");
                                    $("#editeur").val("");
                                }
                                $("#b").attr("disabled", "true");



                            }

                        });
                    },
                    error: function (x, y, z) {
                        alert(x + ' ' + y + ' ' + z);
                    }

                });
            }

        });
        $("#isbn").change(function () {
            if ($("#isbn").val() !== "") {
                var isbn = $("#isbn").val();
                $.ajax({
                    url: "livre",
                    data: {
                        action: "getlivre",
                        isbn: isbn
                    },
                    type: "GET",
                    dataType: "json",
                    success: function (datalivre) {
                        $.each(datalivre, function (item, valeur) {
                            if (valeur.id !== 0)
                            {
                                if (valeur.id === -1)
                                {
                                    alert(valeur.message);
                                    $("#nam").val("");
                                    $("#tel").val("");
                                    $("#email").val("");
                                    $("#idlivre").val("");
                                    $("#b").attr("disabled", "true");
                                    return;
                                }
                                $("#titre").val(valeur.titre);
                                $("#auteur").val(valeur.auteur);
                                $("#editeur").val(valeur.editeur);
                                $("#idlivre").val(valeur.id);
                                if ($("#idlecteur").val() !== "" && $("#idlivre").val() !== "") {
                                    $("#b").removeAttr("disabled");
                                } else {
                                    $("#b").attr("disabled", "true");
                                }
                            } else
                            {

                                $("#titre").val("");
                                $("#auteur").val("");
                                $("#editeur").val("");
                                $("#idlivre").val("");
                                $("#b").attr("disabled", "true");


                            }

                        });
                    },
                    error: function (x, y, z) {
                        alert(x + ' ' + y + ' ' + z);
                    }

                });
            }
        });

    });
</script>

<form method="post" action="${pageContext.request.contextPath}/livre">
    <div id="main-content">
        <div class="container">
            <div class="row">
                ${remboursement}
            </div>
            <div class="row">
                <fieldset class="">
                    <legend>Lecteur</legend>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="code">Code Lecteur <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" id="code" name="code" 
                                       required/>
                            </div>
                        </div>

                        <div class="col-md-4" id="el" style="display: none">
                            <div class="form-group" >
                                <label for="email">Email du Lecteur <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" id="email" name="email" 
                                       readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="row" style="display: none">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="nam">Nom Lecteur <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" id="nam" name="name" 
                                       readonly="readonly"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="tel">Telephone Lecteur <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" id="tel" name="tel" 
                                       readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="row">
                <fieldset class="">
                    <legend>Livre</legend>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="isbn">ISBN <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" id="isbn" name="isbn" 
                                       required/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="titre">Titre <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" name="titre" id="titre"
                                       readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="auteur">Auteur <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" name="auteur" id="auteur"
                                       readonly="readonly"/>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="editeur">Editeur <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" id="editeur" name="editeur" 
                                       readonly="readonly"/>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="row">
                <fieldset class="">
                    <legend>Emprunt</legend>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="dateemp">Date d'emprunt <span class="text-danger">*</span> </label>
                                <input type="text" class="form-control" name="dateemp"
                                       value="${dateAuj}"
                                       required/>
                            </div>
                        </div>
                        <div class="col-md-4 col-md-offset-2">
                            <button type="submit" class="btn btn-success" name="ok" id="b"
                                    disabled>Valider les informations</button>
                        </div>
                    </div>
                </fieldset>

            </div>
            <div class="row">
                <input type="hidden" id="reglerEmprunt" name="reglerEmprunt" value="${reglerEmprunt}"/> 
                <input type="hidden" id="idlivre" name="idlivre" value=""/>
                <input type="hidden" id="idlecteur" name="idlecteur" value=""/>
            </div>
            <div class="row">
                ${echecEmprunt}
                ${successEmprunt}
                ${dejaEmprunt}
                ${noDispo}
            </div>
        </div>
    </div>
</form>

<%@  include file="footer.jsp" %>
