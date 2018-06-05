/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.net.ssl.HttpsURLConnection;
import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONUtilities;
import org.quickconnectfamily.json.ParseException;

/**
 *
 * @author Matthew
 */
public class DadJokesServlet extends HttpServlet {

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
            out.println("<title>Dad Jokes Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dad Jokes</h1>");
            out.println("<p>" + this.getServletInfo() + "</p>");
            out.println("<p>" + this.getDadJoke() + "</p>");
            out.println("<a href='http://localhost:8084/FirstWebApp/DadJokesServlet'>Click for new joke</a>");
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
        return "This servlet uses URL/HTTP to request a dad joke from a Dad Joke "
                + "API which is then received in a JSON format. It is then parsed "
                + "and displayed for you to get a little laugh.  Warning: they can "
                + "be extremely corny!";
    }// </editor-fold>

    public String getDadJoke() throws MalformedURLException, IOException {
        //Read from site
        //Create URL object
        URL site = new URL("https://icanhazdadjoke.com/");
        //variable for request header
        String USER_AGENT = "Mozilla/5.0";
        //Create HttpURLConnection object from URL object connection
        HttpURLConnection connect = (HttpURLConnection) site.openConnection();
        //Build request header
        connect.setRequestMethod("GET");
        connect.setRequestProperty("User-Agent", USER_AGENT);
        connect.setRequestProperty("Accept", "application/json");
        //variable for use with json
        String joke = "";
        String output = "";
        
        //Send request, read json response, parse, and use Hashmap for output
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connect.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            joke += response.toString();
            in.close();
            try {
                HashMap json = (HashMap) JSONUtilities.parse(joke);
                output = "Joke: " + json.get("joke");
            } catch (JSONException | ParseException ex) {
                Logger.getLogger(DadJokesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("**********HttpUrl Error");
        }
        
        return output;
    }
}
