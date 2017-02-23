/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

/**
 *
 * @author admin
 */
public class TestJSON extends HttpServlet{
    
    
    
    
    @Override
    public void doPost(HttpServletRequest req, 
  HttpServletResponse res) throws IOException
    {
        res.setContentType("text/html;charset=utf-8");
 
        PrintWriter pw = res.getWriter();
        pw.println("<H1>Hello, world! или Привет мир</H1>");
        String message = "hellowushki";
        res.setContentType("text");
        res.getWriter().write(message);
        System.out.print(res.getStatus());
    }
    
}
