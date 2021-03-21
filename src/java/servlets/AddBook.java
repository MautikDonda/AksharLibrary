/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Admin
 */
public class AddBook extends HttpServlet {

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
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        processRequest(request, response);
        try{
            String id =request.getParameter("id");
            String name =request.getParameter("name");
            String author = request.getParameter("author");
            String details = request.getParameter("details");
//            out.print(id+name+author+details);
            String driver = getServletConfig().getInitParameter("driver");

            String db =  getServletConfig().getInitParameter("database");
            
          
            Statement st =  Statics.resetConnection();
            String qur ="INSERT INTO BOOKS(ID,NAME,AUTHOR,DETAILS) VALUES (\""+id+ "\",\""+name+"\",\""+author+"\",\""+details+"\");";
//            out.print(qur);
            int i =st.executeUpdate(qur);
            if(i==1) 
            out.print("<div class=\"alert alert-success\" role=\"alert\">\n" +
" <center>Book Added Successfuly!</center>\n" +
"</div>");
            
            else
            out.print("<div class=\"alert alert-success\" role=\"alert\">\n" +
" <center> Invalid Username or Password!</center>\n" +
"</div>");
                request.getRequestDispatcher("addNewBook.html").include(request, response);
        }
        catch(Exception e)
        {
            
            out.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
" <center>"+ e+"</center>\n" +
"</div>");request.getRequestDispatcher("addNewBook.html").include(request, response);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
