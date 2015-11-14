package client;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Date;


public class tester {

    public static void main(String[] args) throws SQLException {
        Date date1 = new Date();
        System.out.println(date1.toString());
        
        
        myDB local = new myDB();
        // myDB otherDB = new myDB("otherDB", "root", "root");
        local.connect();
        // System.out.println(otherDB.testConnection());
        local.resetDB();
        local.getTables();
        local.disconnect();
       /*    // Connection conn = local.getConn();
         local.addUser(4, "Kostas", "papaki", "Y");
      //  local.deleteUser(20);
        local.updateUserPassword(1, "TIPOTA");
        try{
        ResultSet allUsers= local.findAllUsers();
         while (allUsers.next()) {
               System.out.println(allUsers.getString("username") 
                                + allUsers.getString("password"));
         }  
        allUsers.close();
        ResultSet searchResults = local.findUserbyName("I");
        while (searchResults.next()) {
           System.out.println(searchResults.getString("username"));
           }
        searchResults.close(); 
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
        
        //Manually create a Date object
        Date date2 = new Date(2015, 1, 03, 20, 33);
        System.out.println(date2.toString());
        
        System.out.println(date1.before(date2));
    }
    
     */
    }
}
