/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
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
    private Set<String> colors = new HashSet<>();
    private Set<Integer> sizes = new HashSet<>();
    
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
        
        try{
        colors.addAll(Arrays.asList(values.get("color")));
        }catch(Exception e){}
        
        try{
        for(String s: values.get("size"))
        {
            int size = Integer.valueOf(s);
            sizes.add(size);
        }}catch(Exception e){}
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
    
    public void findItems() throws SQLException
    {
        int id = 0;
        int cost = 0;
        ResultSet res = null;
        if(this.id!=0)
        {
            res = findById();//поиск по  id
        }
        else
        {
            res = findByName();//поиск по имени
        }
        while(res.next())
        {
            id = res.getInt("itemid");
            cost = res.getInt("itemcost");
            String name = res.getString("itemname");
            if(cost <= this.costMax && cost >= this.costMin)//поиск по цене
            listItem.add(new Item(id,cost,name));
        }
        
        if(!colors.isEmpty())//поиск по цветам
        {
        Iterator<Item> it = listItem.iterator();
            while(it.hasNext())
            {
                Item i = it.next();
                boolean isColor = false;
                ResultSet set = findColorById(i.getId());
                while(set.next())
                {
                    String itemColor = set.getString("color");
                    for(String color : colors)//цвета в поиске
                    {
                        if(color.equals(itemColor)==true)
                            isColor = true;
                    }
                }
                if(isColor == false)
                    it.remove();
            }
        }
        if(!sizes.isEmpty())
        {
            Iterator<Item> it = listItem.iterator();
            while(it.hasNext())
            {
                Item i = it.next();
                boolean isSize = false;
                ResultSet set = findSizeById(i.getId());
                while(set.next())
                {
                    int itemSize = set.getInt("size");
                    for(int size : sizes)//цвета в поиске
                    {
                        if(size == itemSize)
                            isSize = true;
                    }
                }
                if(isSize == false)
                    it.remove();
            }
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
    
    public ResultSet findColorById() throws SQLException
    {
        return stm.executeQuery("SELECT color FROM ItemsColor WHERE itemid = "+id);
    }
    public ResultSet findColorById(int id) throws SQLException
    {
        return stm.executeQuery("SELECT color FROM ItemsColor WHERE itemid = "+id);
    }
    
    public ResultSet findSizeById() throws SQLException
    {
        return stm.executeQuery("SELECT size FROM Size WHERE itemid = "+id);
    }
    public ResultSet findSizeById(int id) throws SQLException
    {
        return stm.executeQuery("SELECT size FROM Size WHERE itemid = "+id);
    }
    
    public void addInDate(int cost, String name,String description, String[] colors, String[] sizes) throws SQLException
    {
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
    
    
    /*
    public String getColors()
    {
        String res = "lol";
        for(String s : colors)
            res+=s;
        return res;
    }*/
}
