package servlets;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Statics {
   
   public static String driver;
   public static Statement st;
   public static Statement resetConnection(){
       try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root", "");
          st =  con.createStatement();   
       return st;
       }
       catch(Exception e)
       {return st;
       }
   }
    static String db;
   public static String getHead(String title)
   {
       String head ="<!doctype html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">\n" +
"        <title>"+title+"</title>\n" +
"         \n" +
"          <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">\n" +
"\n" +
"        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl\" crossorigin=\"anonymous\">\n" +
"    </head>";
       return head+nav;
   }
   public static String getCss(){return "<!doctype html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">\n" +
"         \n" +
"          <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i\" rel=\"stylesheet\">\n" +
"\n" +
"        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl\" crossorigin=\"anonymous\">\n" +
"    </head>";}
   
   public static String nav="   <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
"  <div class=\"container-fluid\">\n" +
"\n" +
"    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"      <span class=\"navbar-toggler-icon\"></span>\n" +
"    </button>\n" +
"    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
"      <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\n" +
"        <li class=\"nav-item\">\n" +
"          <a class=\"nav-link active\" aria-current=\"page\" href=\"Homepage\">Home</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"            <a class=\"nav-link active\" href=\"Profile\">Profile</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"            <a class=\"nav-link active\" href=\"IssueBook\">Issue</a>\n" +
"        </li>\n" +
"                <li class=\"nav-item\">\n" +
"                    <a class=\"nav-link active\" href=\"RenewBook\">Renew</a>\n" +
"        </li>\n" +
"                <li class=\"nav-item\">\n" +
"                    <a class=\"nav-link active\" href=\"ReturnBook\">Return</a>\n" +
"        </li>\n" +
"                <li class=\"nav-item\">\n" +
"                    <a class=\"nav-link active\" href=\"about.html\">About Us</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"            <a class=\"nav-link active\" href=\"contact.html \">Contact Us</a>\n" +
"        </li>        \n" +
"        <form action='SignOut'><input type=\"submit\" class=\"btn btn-outline-warning\" value='Logout'></form>" +
"      </ul>\n" +
"      <form class=\"d-flex\">\n" +
"        <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n" +
"        <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\n" +
"      </form>\n" +
"    </div>\n" +
"  </div>\n" +
"</nav>\n" +
"      ";
   
}
