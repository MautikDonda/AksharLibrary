/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class UsersList extends HttpServlet {

  @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        try {
            String id = req.getParameter("click");
            int i=Statics.st.executeUpdate("DELETE FROM users where id='"+id+"';");
            Statics.st.executeUpdate("DETETE FROM issuedbooks WHERE userid="+id);
            
        } catch (Exception ae) {
        }
        process(req, resp);
//        resp.sendRedirect("ReturnBook");
    }
    
    void process(HttpServletRequest request, HttpServletResponse response) {
        try{
         PrintWriter out = response.getWriter();
           request.getRequestDispatcher("admin.html").include(request, response);
       
        HttpSession session = request.getSession(false);
        if(session==null)
            response.sendRedirect("index.jsp");
        else{
//            out.println("Session : "+session.getAttribute("id"));
        try {//for query
            ResultSet rs;
            
            rs= Statics.resetConnection().executeQuery("SELECT * FROM users");

            try {//for printing Issued Books
                
                    out.print("<div class='container'><table class='table'><tr><th>Id</th><th>Name</th><th>Contact</th><th>Created At</th><th>Click to Remove</th></tr>");
                   while(rs.next())
                   {
                       out.print("<tr>");
                       out.print("<td>"+rs.getString("id")+"</td>");
                        out.print("<td>"+rs.getString("name")+"</td>");
                        out.print("<td>"+rs.getString("contact")+"</td>");
                        out.print("<td>"+rs.getString("created-at")+"</td>");
                       out.print("<td><form action='UsersList' method='POST'>  <button type=\"submit\" name='click' value='"+rs.getString("id")+"' class=\"btn btn-outline-danger\">Remove</button></form><td>");
                       out.print("</tr>");
                   }
                }
                catch(Exception e)
                {
                    out.print("Error in Printing " + e);
                }
               
        } catch (Exception e2) {
            
            out.print("ERROR in Query: "+e2);
        }
    }
    }
        catch(Exception ee)
        {}
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

}


