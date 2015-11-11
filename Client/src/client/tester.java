package client;

import java.sql.Connection;
import java.sql.ResultSet;

public class tester {

    public static void main(String[] args) {

        myDB local = new myDB();
        // myDB otherDB = new myDB("otherDB", "root", "root");
        System.out.println(local.testConnection());
        // System.out.println(otherDB.testConnection());
        //local.resetDB();

        Connection conn = local.getConn();
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
    }
}
