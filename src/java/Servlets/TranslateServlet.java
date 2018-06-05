/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matthew
 */
public class TranslateServlet extends HttpServlet {

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
        
        String input = "";
        String output = "";
        String language = "";
        try {
            input = request.getParameter("language");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        if (null == input) {
            output = "<p>un, deux, trois, quatre, cinq, six, sept, huit, neuf, dix.</p>";
            language = "<h2>French</h2>";
        }
        else switch (input) {
            case "spanish":
                output = "<p>uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, diez.</p>";
                language = "<h2>Spanish</h2>";
                break;
            case "german":
                output = "<p>eins, zwei, drei, vier, funf, sechs, sieben, acht neun, zehn.</p>";
                language = "<h2>German</h2>";
                break;
            default:
                output = "<p>un, deux, trois, quatre, cinq, six, sept, huit, neuf, dix.</p>";
                language = "<h2>French</h2>";
                break;
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TranslateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>1-10 Translation</h1>");
            out.println(language);
            out.println(output);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
