/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.io.IOException;
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
        String message = "hellowushki";
        res.setContentType("text");
        res.getWriter().write(message);
    }
    
}
