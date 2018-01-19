/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bib.ui;

import bib.beans.Lecteur;
import bib.beans.Utilisateur;
import bib.dao.ILecteur;
import bib.dao.IUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.GenerationType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ahmadou Waly Ndiaye
 */
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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
    private ILecteur lecteurEjb;
    @EJB
    private IUser userEjb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("connectedUser");
        if (u == null) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

        } else if (!u.getProfil().equals("gerant")) {
            getServletContext().getRequestDispatcher("/accueil_lecteur.jsp").forward(request, response);

        }
        String action = request.getParameter("action");
        if (action == null) {
            if (u.getProfil().equals("gerant")) {
                getServletContext().getRequestDispatcher("/acceuil_admin.jsp").forward(request, response);

            } else {
                getServletContext().getRequestDispatcher("/accueil_lecteur.jsp").forward(request, response);

            }
        } else if (action.equals("inscription")) {
            String code = lecteurEjb.generateCode();
            request.setAttribute("code", code);
            getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);

        } else if (action.equals("lesInscrits")) {
            // String code = lecteurEjb.generateCode();
            // request.setAttribute("code", code);
            request.setAttribute("leslecteurs", lecteurEjb.getAllLecteurs());

            getServletContext().getRequestDispatcher("/listelecteur.jsp").forward(request, response);

        } else if (action.equals("modifierLecteur")) {
            // String code = lecteurEjb.generateCode();
            int id = Integer.parseInt(request.getParameter("id"));
            Lecteur lec = lecteurEjb.getLecteurById(id);
            request.setAttribute("lecteur", lec);
            getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);

        } else if (action.equals("deletelecteur")) {
            try {
                Lecteur l = lecteurEjb.getLecteurById(Integer.parseInt(request.getParameter("idlecteur")));
                lecteurEjb.deleteLecteur(l);
                Utilisateur utilis = userEjb.getUserById(Integer.parseInt(request.getParameter("idlecteur")));
                userEjb.deleteUser(utilis);
                response.getWriter().println("{\"delete\":\"ok\"}");
            } catch (Exception e) {
                response.getWriter().println("{\"delete\":\"ko\"}");
            }

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
        String log = request.getParameter("login");
        Utilisateur u = userEjb.findUserByLogin(log);
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        String pw = userEjb.generatepasswd();
        String profil = "lecteur";
        String tel = request.getParameter("tel");
        String adresse = request.getParameter("adresse");
        String mail = request.getParameter("email");

        Utilisateur us = new Utilisateur();

        us.setLogin(log);
        us.setNomcomplet(nom);
        us.setPwd(pw);
        us.setProfil(profil);

        Lecteur lect = new Lecteur();
        lect.setCode(code);
        lect.setEmail(mail);
        lect.setAdresse(adresse);
        lect.setTel(tel);
        lect.setUtilisateur(us);

        try {
            if (!request.getParameter("idlecteur").equals("")) {
                us.setId(Integer.parseInt(request.getParameter("idlecteur")));
            } else if (u != null) {
                request.setAttribute("user_exist",
                        "<div class=\"alert alert-danger\">"
                        + "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n"
                        + "<span aria-hidden=\"true\">&times;</span></button>"
                        + "<strong>Le login existe déjà</strong>"
                        + "</div>"
                );
                //sendredirect
                String codelec = lecteurEjb.generateCode();
                request.setAttribute("code", codelec);
                getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);

                //response.sendRedirect("http://localhost:8080/Bibliotheque1/admin?action=inscription");
                return;
            }

            userEjb.addUser(us);
            lect.setId(us.getId());
            lecteurEjb.addLecteur(lect);

            request.setAttribute("message",
                    "<br/><div class=\"alert alert-success\">"
                    + "<strong>Le lecteur a été bien inscrit</strong>"
                    + "</div>"
            );

            //    getServletContext().getRequestDispatcher("/acceuil_admin.jsp").forward(request, response);
            List<Lecteur> l = lecteurEjb.getAllLecteurs();
            if (l != null) {
                request.setAttribute("leslecteurs", l);
                getServletContext().getRequestDispatcher("/listelecteur.jsp").forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msgerreur",
                    "<div class=\"alert alert-danger\">"
                    + "<strong>"
                    + "Une erreur s'est produite veuillez contacter l'administrateur !!!"
                    + "</strong>"
                    + "</div>"
            );
            getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);

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
