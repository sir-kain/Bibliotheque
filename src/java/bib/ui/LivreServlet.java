/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.ui;

import bib.beans.Emprunt;
import bib.beans.Lecteur;
import bib.beans.Livre;
import bib.beans.TypeLivre;
import bib.dao.IEmprunt;
import bib.dao.ILecteur;
import bib.dao.ILivre;
import bib.dao.ITypeLivre;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@WebServlet(name = "livre", urlPatterns = {"/livre"})
public class LivreServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LivreServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LivreServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private ILivre livreEjb;
    @EJB
    private ITypeLivre typelivreEjb;
    @EJB
    private ILecteur lecteurEjb;
    @EJB
    private IEmprunt empEjb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Calendar cal = Calendar.getInstance();
        if (action.equals("ajoutlivre")) {
            List<TypeLivre> tp = typelivreEjb.getAllTypeLivre();
            if (tp != null) {
                request.setAttribute("lestypes", tp);
                //getServletContext().getRequestDispatcher("/listelecteur.jsp").forward(request, response);
            }
            getServletContext().getRequestDispatcher("/ajoutlivre.jsp").forward(request, response);
        } else if (action.equals("lesLivres")) {
            request.setAttribute("lesLivres", livreEjb.getAllLivres());

            getServletContext().getRequestDispatcher("/listelivre.jsp").forward(request, response);

        } else if (action.equals("emprunt")) {
            request.setAttribute("dateAuj", cal.getTime());
            getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);

        } else if (action.equals("getlecteur")) {
            try {
                Lecteur l = lecteurEjb.getLecteurByCode(request.getParameter("code"));
                String retour = "{\"data\":{";
                if (l != null) {
                    Emprunt emp = empEjb.getEmpruntByLecteur(l.getId());
                    if (request.getParameter("rgemp").equals("reglerEmprunt")) {
                        if (emp != null) {
                            retour += "\"id\":" + l.getId() + ",";
                            retour += "\"name\":\"" + l.getUtilisateur().getNomcomplet() + "\",";
                            retour += "\"tel\":\"" + l.getTel() + "\",";
                            retour += "\"isbn\":\"" + emp.getIdLivre().getIsbn() + "\",";
                            retour += "\"titre\":\"" + emp.getIdLivre().getTitre() + "\",";
                            retour += "\"auteur\":\"" + emp.getIdLivre().getAuteur() + "\",";
                            retour += "\"editeur\":\"" + emp.getIdLivre().getEditeur() + "\",";
                            retour += "\"email\":\"" + l.getEmail() + "\"}}";
                        } else {
                            String message = l.getUtilisateur().getNomcomplet()
                                    + " n'a pas empruntÃ© de livre , Merci de reverifier le code du lecteur";
                            retour += "\"id\":-1,\"message\":\"" + message + "\"}}";
                        }
                    } else if (request.getParameter("rgemp").equals("")) {
                        if (emp == null) {
                            retour += "\"id\":" + l.getId() + ",";
                            retour += "\"name\":\"" + l.getUtilisateur().getNomcomplet() + "\",";
                            retour += "\"tel\":\"" + l.getTel() + "\",";
                            retour += "\"email\":\"" + l.getEmail() + "\"}}";
                        } else {

                            String message = emp.getIdLecteur().getUtilisateur().getNomcomplet()
                                    + " a empruntÃ© le livre " + emp.getIdLivre().getTitre() + ", emprunt impossible";
                            retour += "\"id\":-1,\"message\":\"" + message + "\"}}";
                        }
                    }
                } else {
                    retour += "\"id\":0}}";
                }
                response.getWriter().println(retour);

            } catch (Exception e) {
                response.getWriter().println("{\"retour\":\"ko\"}");
            }

        } else if (action.equals("getlivre")) {
            try {
                Livre liv = livreEjb.getLivreByIsbn(request.getParameter("isbn"));
                String retour = "{\"data\":{";
                if (liv != null) {
                    if (liv.getNbExemplaireDispo() > 0) {
                        retour += "\"id\":" + liv.getId() + ",";
                        retour += "\"auteur\":\"" + liv.getAuteur() + "\",";
                        retour += "\"titre\":\"" + liv.getTitre() + "\",";
                        retour += "\"editeur\":\"" + liv.getEditeur() + "\"}}";
                    } else {
                        String message = "Le livre qui a comme isbn " + liv.getIsbn()
                                + " n'est pas disponible";
                        retour += "\"id\":-1,\"message\":\"" + message + "\"}}";
                    }

                } else {
                    retour += "\"id\":0}}";
                }
                response.getWriter().println(retour);

            } catch (Exception e) {
                response.getWriter().println("{\"retour\":\"ko\"}");
            }

        } else if (action.equals("reglerEmprunt")) {
            request.setAttribute("reglerEmprunt", "reglerEmprunt");
            request.setAttribute("remboursement",
                    "<marquee><h1>Operation: Remboursement de livre</h1></marquee>"
            );

            request.setAttribute("dateAuj", cal.getTime());

            getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        if ("livreadd".equals(request.getParameter("livreadd"))) {

            String titre = request.getParameter("titre");
            String editeur = request.getParameter("editeur");
            String auteur = request.getParameter("auteur");
            Date date = Date.valueOf(request.getParameter("date"));
            String nbexpl = request.getParameter("nbexpl");
            String nbexpldispo = request.getParameter("nbexpldispo");

            TypeLivre typ = new TypeLivre();
            typ.setId(Integer.parseInt(request.getParameter("typelivre")));
            //String typelivre = request.getParameter("typelivre");

            Livre liv = new Livre();
            liv.setIsbn(isbn);
            liv.setTitre(titre);
            liv.setAuteur(auteur);
            liv.setEditeur(editeur);
            liv.setDateedition(date);
            if (Integer.parseInt(nbexpl) > 0) {
                liv.setNbExemplaire(Integer.parseInt(nbexpl));
            }
            
            liv.setNbExemplaireDispo(Integer.parseInt(nbexpldispo));
            //liv.setIdType(typ.setId(Integer.parseInt(request.getParameter("typelivre"))));
            liv.setIdType(typ);
            try {

                livreEjb.addLivre(liv);
                request.setAttribute("addlivre",
                        "<br/><div class=\"alert alert-success\">"
                        + "<strong>Le livre a été bien ajouté</strong>"
                        + "</div>"
                );
                request.setAttribute("lesLivres", livreEjb.getAllLivres());
                getServletContext().getRequestDispatcher("/listelivre.jsp").forward(request, response);
            } catch (Exception e) {
               request.setAttribute("donnees", liv);
               getServletContext().getRequestDispatcher("/ajoutlivre.jsp").forward(request, response);
                e.printStackTrace();
            }

        } else if (request.getParameter("reglerEmprunt").equals("")) {
            Livre l = livreEjb.getLivreByIsbn(isbn);
            Lecteur lec = lecteurEjb.getLecteurByCode(request.getParameter("code"));
            if (l != null || lec != null) {
                Emprunt emp = empEjb.getEmpruntByLecteur(lec.getId());
                if (emp == null) {
                    if (l.getNbExemplaireDispo() > 0) {
                        Emprunt emprunt = new Emprunt();
                        emprunt.setIdLecteur(lec);
                        emprunt.setIdLivre(l);
                        //java.util.Date d = new java.util.Date();
                        //Date date = Date.valueOf(request.getParameter("dateemp"));
                        Calendar cal = Calendar.getInstance();
//                        java.util.Date d = new java.util.Date();
//                        cal.setTime(d);
                        emprunt.setDateouv(cal.getTime());
                        cal.add(Calendar.DATE, 10);
                        emprunt.setDateretour(cal.getTime());
                        emprunt.setDateretourreel(null);
                        //
                        int nbexpldispo = l.getNbExemplaireDispo();
                        l.setNbExemplaireDispo(nbexpldispo - 1);
                        empEjb.addEmprunt(emprunt);
                        livreEjb.addLivre(l);
                        request.setAttribute("successEmprunt",
                                "<div class=\"alert alert-success\">"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                                + "	<span aria-hidden=\"true\">&times;</span></button>"
                                + "<strong>L'emprunt a bien été enregistré</strong>"
                                + "</div>"
                        );
                    } else {
                        request.setAttribute("noDispo",
                                "<div class=\"alert alert-success\">"
                                + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                                + "	<span aria-hidden=\"true\">&times;</span></button>"
                                + "<strong>Le livre n'est pas disponible pour le moment</strong>"
                                + "</div>"
                        );
                    }
                    getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
                } else {
                    request.setAttribute("dejaEmprunt",
                            "<div class=\"alert alert-danger\">"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                            + "	<span aria-hidden=\"true\">&times;</span></button>"
                            + "<strong>Impossible !!!" + lec.getUtilisateur().getNomcomplet() + " a déjà emprunté un livre</strong>"
                            + "</div>"
                    );
                    getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("echecEmprunt",
                        "<div class=\"alert alert-danger\">"
                        + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                        + "	<span aria-hidden=\"true\">&times;</span></button>"
                        + "<strong>L'emprunt a échoué</strong>"
                        + "</div>"
                );
                getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
            }

        } else if (request.getParameter("reglerEmprunt").equals("reglerEmprunt")) {
            Livre l = livreEjb.getLivreByIsbn(isbn);
            Lecteur lec = lecteurEjb.getLecteurByCode(request.getParameter("code"));
            if (l != null || lec != null) {
                Emprunt emp = empEjb.getEmpruntByLecteur(lec.getId());
                if (emp != null && (emp.getIdLivre().getId() == l.getId())) {
                    try {
                        Calendar cal = Calendar.getInstance();
                        emp.setDateretourreel(cal.getTime());
                        empEjb.addEmprunt(emp);
                        int nbexpldispo = l.getNbExemplaireDispo();
                        l.setNbExemplaireDispo(nbexpldispo + 1);
                        livreEjb.addLivre(l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("successEmprunt",
                            "<div class=\"alert alert-success\">"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                            + "	<span aria-hidden=\"true\">&times;</span></button>"
                            + "<strong>Le remboursement a bien réussi</strong>"
                            + "</div>"
                    );
                    request.setAttribute("reglerEmprunt", "reglerEmprunt");
                    request.setAttribute("remboursement",
                            "<marquee><h1>Operation: Remboursement de livre</h1></marquee>"
                    );
                    getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
//                    response.sendRedirect("http://localhost:8080/Bibliotheque1/livre?action=reglerEmprunt");

                } else {
                    request.setAttribute("dejaEmprunt",
                            "<div class=\"alert alert-danger\">"
                            + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                            + "	<span aria-hidden=\"true\">&times;</span></button>"
                            + "<strong> le lecteur '" + lec.getUtilisateur().getNomcomplet() + "' qui a "
                            + "pour code '" + lec.getCode() + "' n'a pas emprunté le livre "
                            + "" + l.getIsbn() + ": "
                            + " <br/> Merci de verifier Les données saisies </strong>"
                            + "</div>"
                    );
                    request.setAttribute("reglerEmprunt", "reglerEmprunt");
                    request.setAttribute("remboursement",
                            "<marquee><h1>Operation: Remboursement de livre</h1></marquee>"
                    );
                    getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("echecEmprunt",
                        "<div class=\"alert alert-danger\">"
                        + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                        + "	<span aria-hidden=\"true\">&times;</span></button>"
                        + "<strong>L'operation a échoué"
                        + " <br/> Merci de verifier Les données saisies</strong>"
                        + "</div>"
                );
                request.setAttribute("reglerEmprunt", "reglerEmprunt");
                    request.setAttribute("remboursement",
                            "<marquee><h1>Operation: Remboursement de livre</h1></marquee>"
                    );
                getServletContext().getRequestDispatcher("/emprunt.jsp").forward(request, response);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
