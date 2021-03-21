package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.print(Statics.getCss());
            String cpass = request.getParameter("confirm-password");
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String contact = request.getParameter("contact");
            Statics.db="jdbc:mysql://localhost:3306/library";
            if(pass.equals(cpass))
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con =  DriverManager.getConnection(Statics.db,"root", "");
                Statement st =  con.createStatement();
                Statics.st=st;
                String qur= "INSERT INTO users(name,contact,password) VALUES ('"+ name+ "','"+ contact+ "','"+ pass+ "');";
                int i= st.executeUpdate(qur);      
                if(i==1)
                {
                    out.print("<center><div role='alert' class='alert alert-success'>Registration Completed!</div></center>");
                    request.getRequestDispatcher("index.jsp").include(request, response);
                }
                else
                {
                    out.print("<center><div role='alert' class='alert alert-danger'>Error in Registration !</div></center>");
                    request.getRequestDispatcher("index.jsp").include(request, response);
                }
            }
            else{
                out.print("<center><div role='alert' class='alert alert-danger'>Both Password is not Same</div></center>");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            
        }
        catch(Exception e)
        {
             out.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
" <center> "+e.getMessage()+"</center>\n" +
"</div>");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        finally {
            out.close();
        }
    }    
}
