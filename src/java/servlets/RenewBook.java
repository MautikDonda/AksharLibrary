
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RenewBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
       PrintWriter out = resp.getWriter();
        try {     
            System.out.print("Cicked"+req.getParameter("click"));
            
            String book = req.getParameter("click");
            // out.print("Book Id"+book);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
        //    out.println("Current Date: "+sdf.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 7);  
            String newDate = sdf.format(cal.getTime());  

            // out.print("newDate  :"+newDate);
            String que="UPDATE issuedbooks SET ret='"+newDate+"' where bookid='"+book+"';";
            // out.print(que);
            int i=Statics.resetConnection().executeUpdate(que);
            process(req,resp);
            
        } catch (Exception ae) {
            out.print(ae);
        }

    }
    
    void process(HttpServletRequest request, HttpServletResponse response) {
        try{
         PrintWriter out = response.getWriter();
        out.print(Statics.getHead("Renew Books"));
        HttpSession session = request.getSession(false);
        if(session==null)
            response.sendRedirect("index.jsp");
        else{
            String ir =(String) session.getAttribute("id");
        try {//for query
            ResultSet rs;
            try {
                   rs= Statics.resetConnection().executeQuery("SELECT * FROM issuedbooks WHERE userid="+session.getAttribute("id"));

            } catch (Exception e) {
               rs= Statics.resetConnection().executeQuery("SELECT * FROM issuedbooks WHERE userid="+session.getAttribute("id"));
            }
            
            try {//for printing Issued Books
                
            out.print("All the Books in the Library that you issued, are Listed below:<br><br>"
                    + "<div class='container'><table class='table'><tr><th>Book Id</th><th>Issue Date</th><th>Return Date</th><th>Click to Renew</th></tr>");
           while(rs.next())
           {
               out.print("<tr>");
               out.print("<td>"+rs.getString("bookid")+"</td>");
               out.print("<td>"+rs.getString("issue")+"</td>");
               out.print("<td>"+rs.getString("ret")+"</td>");
               out.print("<td><form action='RenewBook' method='POST'>  <button type=\"submit\" name='click' value='"+rs.getString("bookid")+"' class=\"btn btn-outline-primary\">Renew</button></form><td>");
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
