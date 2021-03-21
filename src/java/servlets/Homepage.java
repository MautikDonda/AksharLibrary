
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Homepage extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         PrintWriter out = response.getWriter();
        try {
        out.print(Statics.getHead("Homepage"));
        Statement st = Statics.st;
         ResultSet rs =  st.executeQuery("SELECT * FROM books");
        try{
            out.print("All the Books in the Library are Listed below:<br><br>"
                    + "<div class='container'><table class='table'><tr><th>Id</th><th>Title</th><th>Author</th></tr>");
           while(rs.next())
           {
               out.print("<tr>");
               out.print("<td>"+rs.getString(1)+"</td>");
               out.println("<td>"+rs.getString(2)+"</td>");
                              out.println("<td>"+rs.getString(3)+"</td>");
               out.print("</tr>");
           }
        }
        catch(Exception e)
        {
            out.print(e);
        }
           out.print("</div></div></body></html>");
//            response.sendRedirect("Welcome.html");
        }
        catch(Exception e){
          
            
        }        finally {
            out.close();
        }
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ss= request.getSession(false);
        PrintWriter out = response.getWriter();
//        out.print(ss.getAttribute("user"));
        if(ss.getAttribute("user")==null)
            response.sendRedirect("index.jsp");
        else {
            String driver = getServletConfig().getInitParameter("driver");
            String db =  getServletConfig().getInitParameter("database");
            try {
                Class.forName(driver);
            Connection con =  DriverManager.getConnection(db,"root", "");
            Statement st =  con.createStatement();
             Statics.driver = driver;
            Statics.db = db;
            Statics.st = st;
            } catch (Exception e) {
            }
            processRequest(request, response);
        }
        
                                                                                                
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
        
        response.setContentType("text/html;charset=UTF-8");
        try {
            String uname =request.getParameter("username");
            String pass =request.getParameter("password");
            String driver = getServletConfig().getInitParameter("driver");
            String db =  getServletConfig().getInitParameter("database");
            Class.forName(driver);
            Connection con =  DriverManager.getConnection(db,"root", "");
            Statement st =  con.createStatement();
            String qur= "SELECT * FROM users where name='"+uname+"' and password ='"+pass+"';";
            
            ResultSet r= st.executeQuery(qur);
            r.first();
           HttpSession ss = request.getSession();
           ss.setAttribute("user", uname);
           ss.setAttribute("pass", pass);
           ss.setAttribute("id", r.getString("id"));
           out.print("new Session  "+(ss.getAttribute("user")));
           Statics.driver = driver;
            Statics.db = db;
            Statics.st = st;
            if(uname.equals("Admin")) response.sendRedirect("admin.html");
            processRequest(request, response);
        } catch (Exception e) {
              
            out.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
" <center> Invalid Username or Password!</center>\n" +
"</div>");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
       
        
        
    }
}
