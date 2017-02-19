/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Items {
    private String url = "jdbc:mysql://localhost:3306/date";
    private String userName = "root";
    private String password = "root";
    Connection connect = null;
    Statement stm = null;
    public Items(){
        try{
        Class. forName ("com.mysql.jdbc.Driver"). newInstance();//Драйвер
        System.out.println("driver");
        connect = DriverManager. getConnection (url, userName, password);//Подключение к базе
        stm = connect.createStatement();
        }catch(Exception e){}
    }
    
    public ResultSet find() throws SQLException
    {
        return stm.executeQuery("SELECT * FROM baseShop;");
    }
    
    public ResultSet findById(int id) throws SQLException
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE id = "+id);
    }
    
    public ResultSet findByName(String name) throws SQLException
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE name LIKE \"%"+name+"%\"");
    }
    
    public ResultSet findByCost(int cost) throws SQLException
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE cost <= "+cost);
    }
    
    
    
}
