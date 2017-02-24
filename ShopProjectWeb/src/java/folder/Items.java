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
    
    private int id,costMin, costMax;
    private String name;
    
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
            costMax = Integer.valueOf(values.get("costMax")[0]);
        }catch(Exception e)
        {costMax = 100000;}
        try{
            costMin = Integer.valueOf(values.get("costMin")[0]);
        }catch(Exception e)
        {costMin = 0;}
        try{
            name = values.get("name")[0];
        }catch(Exception e)
        {name = "";}
    }
    
    public void addBasket(int id) throws SQLException
    {
        ResultSet res = findById(id);
        res.next();
        int cost = res.getInt("itemcost");
        String name = res.getString("itemname");
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
        ResultSet res = null;
        if(id!=0)
        {
            res = findById();
        }
        else
        {
            res = findByName();
        }
        while(res.next())
        {
            int id = res.getInt("itemid");
            int cost = res.getInt("itemcost");
            String name = res.getString("itemname");
            if(cost <= this.costMax && cost >= this.costMin)
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
        return stm.executeQuery("SELECT * FROM Items;");
    }
    
    public ResultSet findById() throws SQLException//Поиск по id
    {
        return stm.executeQuery("SELECT * FROM Items WHERE itemid = "+id);
    }
    public ResultSet findById(int id) throws SQLException//Поиск по id
    {
        return stm.executeQuery("SELECT * FROM Items WHERE itemid = "+id);
    }
    
    public ResultSet findByName() throws SQLException //Поиск по name
    {
        return stm.executeQuery("SELECT * FROM Items WHERE itemname LIKE \"%"+name+"%\"");
    }
    public ResultSet findByName(String name) throws SQLException //Поиск по name
    {
        return stm.executeQuery("SELECT * FROM Items WHERE itemname LIKE \"%"+name+"%\"");
    }
    
    public ResultSet findByCost() throws SQLException //Поиск по cost
    {
        return stm.executeQuery("SELECT * FROM Items WHERE itemcost <= "+costMax+" AND itemcost >= "+costMin);
    }
    public ResultSet findByCost(int costMax, int costMin) throws SQLException //Поиск по cost
    {
        return stm.executeQuery("SELECT * FROM Items WHERE itemcost <= "+costMax+" AND itemcost >= "+costMin);
    }
    
    public void addInDate(int cost, String name,String description) throws SQLException
    {
        ResultSet set = stm.executeQuery("INSERT INTO Items(itemname,itemcost) VALUES("+name+","+cost+","+description+")");
    }
    
    public int getId()
    {
        return id;
    }
    public int getCostMax()
    {
        return costMax;
    }
    public int getCostMin()
    {
        return costMin;
    }
    public String getName()
    {
        return name;
    }
}
