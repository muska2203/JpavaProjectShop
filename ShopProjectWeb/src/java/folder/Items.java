/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author admin
 */
public class Items {
    
    private int id,costMin, costMax;
    private String name;
    private Set<String> colors = new HashSet<>();
    private Set<Integer> sizes = new HashSet<>();
    private Set<Item> listItem = new HashSet<>();
    
    
    public Items(Map<String,String[]> values) throws SQLException{
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
        findItems();
    }
    
    public void findItems() throws SQLException
    {
        int id;
        int cost;
        ResultSet res;
        if(this.id!=0)
        {
            res = SQL.findItemsById(this.id);//поиск по  id
        }
        else
        {
            res = SQL.findItemsByParameters(this.name, this.colors, this.sizes, this.costMin, this.costMax);//поиск по имени
        }
        while(res.next())
        {
            id = res.getInt("itemid");
            cost = res.getInt("itemcost");
            String name = res.getString("itemname");
            listItem.add(new Item(id,cost,name));
        }
        //Gson gson = new Gson();
        //TestJson test = new TestJson();
        //String jsonString = gson.toJson(test);
    }
    
    //Поправить
    public Set<Item> getItems() throws SQLException
    {
        return listItem;
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
