/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import com.google.gson.Gson;
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
        lol.put("id",new String[]{"0"});
        lol.put("name",new String[]{"shoes"});
        
        Items it = new Items(lol);
        Gson gson = new Gson();
        String g = gson.toJson(it.getItems());
        
            System.out.println(g);
    }
    
}
