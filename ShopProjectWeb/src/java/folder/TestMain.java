/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package folder;

import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class TestMain {
    public static void main(String[] args) throws SQLException
    {
        System.out.println(SQL.isUserEMail("Admin@mail.ru"));
    }
    
}
