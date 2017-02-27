/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private String url = "jdbc:mysql://localhost:3306/date";
        private String userName = "root";
        private String password = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Connection connect = null;
        Statement stm = null;
        Class. forName ("com.mysql.jdbc.Driver"). newInstance();//Драйвер
        System.out.println("driver");
        connect = DriverManager. getConnection (url, userName, password);//Подключение к базе
        stm = connect.createStatement();
        response.setContentType("text/html;charset=UTF-8");
        Map<String,String[]> map = request.getParameterMap();
        String login = map.get("login")[0];
        String password = map.get("password")[0];
        
        ResultSet res = stm.executeQuery("SELECT COUNT(itemid) FROM Cart\n" +
"WHERE userid = (Select userid FROM Users\n" +
"WHERE username = \""+login+"\" AND userpassword = \""+password+"\");");
        String count = res.getString(1);
        
        res = stm.executeQuery("SELECT SUM(itemcost) FROM Items,Cart\n" +
"WHERE Cart.itemid = Items.itemid AND userid = (Select userid FROM Users\n" +
"WHERE username = \""+login+"\" AND userpassword = \""+password+"\");");
        String price = res.getString(1);
        
        response.getWriter().write("{\"count\":\""+count+"\",\"price\":\""+price+"\"}");
        
        
        }catch(Exception e){}
        /*
        response.setContentType("text/html;charset=UTF-8");
        Map<String,String[]> map = request.getParameterMap();
        String name = map.get("login")[0];
        String password = map.get("password")[0];
        
        
        Gson gson = new Gson();
        TestJson test = new TestJson();
        String jsonString = gson.toJson(test);
        //response.setContentType("application/xml");
        //response.getWriter().write(jsonString);
        response.getWriter().write("{\"name\":\""+name+"\",\"password\":\""+password+"\"}");*/
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
