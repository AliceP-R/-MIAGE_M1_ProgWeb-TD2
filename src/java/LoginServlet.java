/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alpottie
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet { 
    
   @Override
   public void init()
   {
       /** Chargement du driver jdbc - étape 1 */ 
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
        }
        catch(Exception ex)
        {
            System.err.println("Erreur lors du chargement du driver");
            System.exit(1); 
        }
   }

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
        
        Authentification a = new Authentification(); 
        boolean res; 
        res=a.estReconnu(request.getParameter("nom"), request.getParameter("mdp")); 
        
        try (PrintWriter out = response.getWriter()) {
           
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reponse connexion</title>");            
            out.println("</head>");
            out.println("<body>");
            if(res == true)
            {
                out.println("<h1>Bonjour "+request.getParameter("nom")+"</h1>");
            }
            else
            {
                out.println("<h1 style='color:RED;'>Erreur lors de la connexion.</h1>");
                out.println("<h1 style='color:RED;'>Sois vous n'existez pas, sois vos idenfifants sont faux :</h1>");
                out.println("<h1 style='color:RED;'>Nom : "+request.getParameter("nom")+" et Pwd :"+request.getParameter("mdp")+"</h1>");
            }
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
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response )
                throws ServletException, IOException {
        response.setContentType( "text/html" );
        processRequest(request, response);
        
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
        response.setContentType( "text/html" );
        processRequest(request, response);
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
