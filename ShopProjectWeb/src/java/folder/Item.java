/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

/**
 *
 * @author admin
 */
public class Item {
    private int id, cost;
    private String name;
    private boolean inBasket = false;
    Item(int id, int cost, String name)
    {
        this.id = id;
        this.cost = cost;
        this.name = name;
    }
    
    public int getId()
    {
        return id;
    }
    public int getCost()
    {
        return cost;
    }
    public String getName()
    {
        return name;
    }
    
    public void putInBasket()
    {
        
    }
    public boolean inBasket()
    {
        return  inBasket;
    }
}
