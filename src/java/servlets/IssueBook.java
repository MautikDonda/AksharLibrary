package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IssueBook extends HttpServlet {  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();       
        
         try {
              HttpSession session = request.getSession(false);
              if(session!=null)
              {
                  String id =(String) session.getAttribute("id");
                  if(id==null)
                  {
                      response.sendRedirect("index.jsp");
                  }
                  out.print(Statics.getHead("IssueBook"));
            out.println("      <center>\n<div class=\"container \"> <form action =\"IssueBook\"  method='POST' >" +
"                        <label for=\"id\">Enter Book Id</label>\n" +
"                        <input id='id' type=\"text\" name=\"bookid\" >" +
"                        <input class=\"btn btn-primary\" type=\"submit\" value=\"Submit\">\n" +
"                    </form>        </div></center>");           
           
				} 
				 else
				  {
					  out.print("<center><div role='alert' class='alert alert-danger'>Login Required</div></center>");
					  request.getRequestDispatcher("index.jsp").include(request, response);
				  }
         } 
          catch (Exception e) {
            out.print("ERROR : "+e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        PrintWriter out  = response.getWriter();
        try {
            
            HttpSession session = request.getSession(false);
            out.print(Statics.getHead("IssueBook"));
            
            out.println("      <center>\n<div class=\"container \"> <form action =\"IssueBook\"  method='POST' >" +
"                        <label for=\"id\">Enter Book Id</label>\n" +
"                        <input id='id' type=\"text\" name=\"bookid\" >" +
"                        <input class=\"btn btn-primary\" type=\"submit\" value=\"Submit\">\n" +
"                    </form>        </div></center>");   
            
             if(session!=null)
            {
                try{//checking for books in books table
                    out.print(request.getParameter("bookid") );
                ResultSet r23= Statics.resetConnection().executeQuery("SELECT * FROM books WHERE id="+request.getParameter("bookid"));
                r23.next();
               out.print( r23.getString("id"));
//                out.print("Session is valid");
                ResultSet r= Statics.resetConnection().executeQuery("SELECT * FROM issuedbooks WHERE bookid="+request.getParameter("bookid"));
					try {//book issued ?
						r.next();
						r.getString(1);
						
					 out.print("<div class=\"alert alert-danger\" role=\"alert\"> <center>Book already Issued by Someone!</center></div>");
					
					}//book issued??
					catch(Exception e){
						
						ResultSet r2= Statics.resetConnection().executeQuery("SELECT * FROM issuedbooks WHERE userid="+session.getAttribute("id"));
					   int book=0;
					
						while(r2.next()) book++;
					  
					if(book<5)
					{
                                                                                           
//                                                                                            
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            //Getting current date
                            Calendar cal = Calendar.getInstance();
                            //Displaying current date in the desired format
                            System.out.println("Current Date: "+sdf.format(cal.getTime()));

                            //Number of Days to add
                            cal.add(Calendar.DAY_OF_MONTH, 7);  
                            //Date after adding the days to the current date
                            String newDate = sdf.format(cal.getTime());  
                            //Displaying the new Date after addition of Days to current date
                            System.out.println("Date after Addition: "+newDate);
						  int i= Statics.resetConnection().executeUpdate("INSERT INTO issuedbooks(bookid,userid,ret) VALUES ("+request.getParameter("bookid")+",'"+session.getAttribute("id")+"','"+newDate+"');");
						if(i==1) 
							  out.print("<div class=\"alert alert-success\" role=\"alert\"> <center>Congrets! You Issued new Book!</center></div>");
						else out.print("<div class=\"alert alert-danger\" role=\"alert\"><center>There is an Error!</center></div>");
					}
					else out.print("<div class=\"alert alert-danger\" role=\"alert\"> <center>You can not Issue Books more than 5!</center></div>");
				  
					}
            
            
			}//books 
			  catch(Exception e){ out.print("<div class=\"alert alert-danger\" role=\"alert\"> <center>No Book available for given Id!</center></div>");}

			}
			else response.sendRedirect("index.jsp");
        }
        catch (Exception e) {
             response.sendRedirect("Homepage");
        }
    }
    
}
