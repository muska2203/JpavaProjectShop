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
    
    private int id,costMin, costMax;
    private String name;
    private Set<String> colors = new HashSet<>();
    private Set<Integer> sizes = new HashSet<>();
    
    Set<Item> listItem = null;
    Set<Item> basket = null;
    
    
    public Items(Map<String,String[]> values){
        setValues(values);
        
        listItem = new HashSet<>();
        basket = new HashSet<>();
        
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
        ResultSet res = SQL.findItemsById(id);
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
            res = SQL.findItemsById(this.id);//поиск по  id
        }
        else
        {
            res = SQL.findItemsByName(this.name);//поиск по имени
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
                ResultSet set = SQL.findColorByItemId(i.getId());
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
                ResultSet set = SQL.findSizeByItemId(i.getId());
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
