package folder;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
@WebServlet("/hello")
public class HelloWorld extends HttpServlet {
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html>\n"+
                "<head><title>A Test Servlet</title></head>\n"+
                "<body>\n"+
                "<h1>Text</h1>"+
                "<p>Message for User</p>\n"+
                "</body></html>");
        
    }
}
