
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReturnBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        try {
            System.out.print("Cicked"+req.getParameter("click"));
            int i=Statics.st.executeUpdate("DELETE FROM issuedbooks where bookid='"+req.getParameter("click")+"';");
            
        } catch (Exception ae) {
        }
        process(req, resp);
//        resp.sendRedirect("ReturnBook");
    }
    
    void process(HttpServletRequest request, HttpServletResponse response) {
        try{
         PrintWriter out = response.getWriter();
       out.print(Statics.getHead("Return Books"));
       
        HttpSession session = request.getSession(false);
        if(session==null)
            response.sendRedirect("index.jsp");
        else{
//            out.println("Session : "+session.getAttribute("id"));
        try {//for query
            ResultSet rs;
            
            rs= Statics.resetConnection().executeQuery("SELECT * FROM issuedbooks WHERE userid="+session.getAttribute("id"));

            try {//for printing Issued Books
                
                    out.print("All the Books in the Library are Listed below:<br><br>"
                            + "<div class='container'><table class='table'><tr><th>Book Id</th><th>Issue Date</th><th>Return Date</th><th>Click to Return</th></tr>");
                   while(rs.next())
                   {
                       out.print("<tr>");
                       out.print("<td>"+rs.getString("bookid")+"</td>");
                        out.print("<td>"+rs.getString("issue")+"</td>");
                        out.print("<td>"+rs.getString("ret")+"</td>");
                       out.print("<td><form action='ReturnBook' method='POST'>  <button type=\"submit\" name='click' value='"+rs.getString(1)+"' class=\"btn btn-outline-primary\">Return</button></form><td>");
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
