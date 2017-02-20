/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    
    int id,cost;
    String name;
    
    Set<Item> listItem = null;
    Set<Item> basket = null;
    
    //Map<String,String[]> values = null;
    public Items(Map<String,String[]> values){
        setValues(values);
        
        listItem = new HashSet<>();
        basket = new HashSet<>();
        
        try{
        Class. forName ("com.mysql.jdbc.Driver"). newInstance();//Драйвер
        System.out.println("driver");
        connect = DriverManager. getConnection (url, userName, password);//Подключение к базе
        stm = connect.createStatement();
        }catch(Exception e){}
    }
    
    private void setValues(Map<String,String[]> values)
    {
        try{
            id = Integer.valueOf(values.get("id")[0]);
        }catch(Exception e)
        {id = 0;}
        try{
            cost = Integer.valueOf(values.get("cost")[0]);
        }catch(Exception e)
        {cost = 0;}
        try{
            name = values.get("name")[0];
        }catch(Exception e)
        {name = "";}
    }
    
    public void addBasket(int id) throws SQLException
    {
        ResultSet res = findById(id);
        res.next();
        int cost = res.getInt("cost");
        String name = res.getString("name");
        basket.add(new Item(id,cost,name));
    }
    
    public int getMiniBasketCount()
    {
        return basket.size();
    }
    
    public int getMiniBasketPrice()
    {
        int sum = 0;
        for(Item it : basket)
        {
            sum+=it.getCost();
        }
        return sum;
    }
    
    //поправить
    public void findItems() throws SQLException
    {
        ResultSet res = findByName();
        while(res.next())
        {
            int id = res.getInt("id");
            int cost = res.getInt("cost");
            String name = res.getString("name");
            listItem.add(new Item(id,cost,name));
        }
    }
    
    //Поправить
    public Set<Item> getItems() throws SQLException
    {
        Set<Item> s = new HashSet<>();
        for(Item it : listItem)
        {
            addBasket(it.getId());
            s.add(it);
        }
        return s;
    }
    
    
    public ResultSet find() throws SQLException//Вывод всех элементов
    {
        return stm.executeQuery("SELECT * FROM baseShop;");
    }
    
    public ResultSet findById() throws SQLException//Поиск по id
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE id = "+id);
    }
    public ResultSet findById(int id) throws SQLException//Поиск по id
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE id = "+id);
    }
    
    public ResultSet findByName() throws SQLException //Поиск по name
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE name LIKE \"%"+name+"%\"");
    }
    public ResultSet findByName(String name) throws SQLException //Поиск по name
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE name LIKE \"%"+name+"%\"");
    }
    
    public ResultSet findByCost() throws SQLException //Поиск по cost
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE cost <= "+cost);
    }
    public ResultSet findByCost(int cost) throws SQLException //Поиск по cost
    {
        return stm.executeQuery("SELECT * FROM baseShop WHERE cost <= "+cost);
    }
    
}
