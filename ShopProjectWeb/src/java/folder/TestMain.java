/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author admin
 */
public class TestMain {
    public static void main(String[] args) throws SQLException
    {
        Map<String,String[]> lol =new HashMap<>();
        
        Items it = new Items(lol);
        it.findItems();
    }
    
}
