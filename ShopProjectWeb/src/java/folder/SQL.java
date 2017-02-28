/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class SQL {
    private static String url = "jdbc:mysql://localhost:3306/date";
    private static String userName = "root";
    private static String password = "root";
    static Connection connect = null;
    static Statement stm = null;
    
    private static void connect()
    {
        try{
        Class. forName ("com.mysql.jdbc.Driver"). newInstance();//Драйвер
        System.out.println("driver");
        connect = DriverManager. getConnection (url, userName, password);//Подключение к базе
        stm = connect.createStatement();
        }catch(Exception e){System.out.println("Error SQL");}
    }
    
    public static  ResultSet findItems() throws SQLException//Вывод всех элементов
    {
        connect();
        return stm.executeQuery("SELECT * FROM Items;");
    }
    
    public static ResultSet findItemsById(int id) throws SQLException//Поиск по id
    {
        connect();
        return stm.executeQuery("SELECT * FROM Items WHERE itemid = "+id);
    }
    
    public static ResultSet findItemsByName(String name) throws SQLException //Поиск по name
    {
        connect();
        return stm.executeQuery("SELECT * FROM Items WHERE itemname LIKE \"%"+name+"%\"");
    }
    
    public static ResultSet findItemsByCost(int costMax, int costMin) throws SQLException //Поиск по cost
    {
        connect();
        return stm.executeQuery("SELECT * FROM Items WHERE itemcost <= "+costMax+" AND itemcost >= "+costMin);
    }
    
    public static ResultSet findColorByItemId(int id) throws SQLException
    {
        connect();
        return stm.executeQuery("SELECT color FROM ItemsColor WHERE itemid = "+id);
    }
    public static ResultSet findSizeByItemId(int id) throws SQLException
    {
        connect();
        return stm.executeQuery("SELECT size FROM Size WHERE itemid = "+id);
    }
    
    public static void addItemsIntoDate(int cost, String name,String description, String[] colors, String[] sizes) throws SQLException
    {
        connect();
        int id = 0;
        stm.addBatch("INSERT INTO Items(itemname,itemcost,itemdescription) VALUES(\""+name+"\","+cost+",\""+description+"\")");
        ResultSet set = stm.executeQuery("SELECT itemid FROM Items WHERE itemname = "+name+" AND description = "+description);
        set.next();
        id = set.getInt("itemid");
        for(String color:colors)
        {
        stm.addBatch("INSERT INTO ItemsColor(itemid,color) VALUES("+id+",\""+color+"\")");
        }
        for(String size:sizes)
        {
        stm.addBatch("INSERT INTO Size(itemid,size) VALUES("+id+","+Integer.valueOf(size)+")");
        }
    }
}
