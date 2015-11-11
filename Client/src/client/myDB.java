package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class myDB {
        private String databaseName;
        private String jdbcUrl;
        private String userid;
        private String password;
        private Connection conn;

    public myDB() {
        databaseName="localDB";
        jdbcUrl = "jdbc:derby:" + databaseName +";create=true";
        userid = "maira";
        password = "maira";
    }
    
    public myDB(String databaseName, String userid, String password) {
        jdbcUrl = "jdbc:derby:" + databaseName +";create=true";
        this.databaseName = databaseName;
        this.userid = userid;
        this.password = password;
    }


    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        try {
          conn = DriverManager.getConnection(jdbcUrl, userid, password);
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        
        return conn;
    }
    
    public boolean testConnection() {

        try {
          conn = DriverManager.getConnection(jdbcUrl, userid, password);
        } catch (SQLException e) {
            System.out.println("Error " + e);
            return false;
        }
        return true;
    }

    public void createUsersTable() {
    Statement statement;

        try {
            statement = conn.createStatement();
            statement.execute("CREATE TABLE users (" + 
            "	id INT PRIMARY KEY," + 
            "	username VARCHAR(30)," + 
            "	password VARCHAR(20)," + 
            "	is_admin CHAR(1)" + 
            "	)");   
            System.out.println("Table created successfully");
            statement.execute("INSERT INTO users VALUES (1,'admin', 'admin', 'Y')");
            statement.execute("INSERT INTO users VALUES (2,'user', 'user', 'N')");                         
        } catch (SQLException e) {
            System.out.println("Error creating table users..." + e);
        }
        

        
    }
    
    public void dropUsersTable() {
        
        Statement statement;

            try {
                statement = conn.createStatement();
                statement.execute("DROP TABLE users");   
                System.out.println("Table dropped successfully");
            } catch (SQLException e) {
                System.out.println("Error dropping table users..." + e);
            }
            
    }
    
    
    public void createProductsTable() {
        Statement statement;

            try {
                statement = conn.createStatement();
                statement.execute("CREATE TABLE products (" + 
                "	id INT PRIMARY KEY," + 
                "	pname VARCHAR(30)," + 
                "	price DECIMAL(6,2)," + 
                "	quantity INT" + 
                "	)");   
                System.out.println("Table created successfully");
                statement.execute("INSERT INTO products VALUES (1,'pen', 0.4, 100)");
                
            } catch (SQLException e) {
                System.out.println("Error creating table products..." + e);
            }
        
        
    }
    
    public void dropProductsTable() { 
        Statement statement;
            try {
                statement = conn.createStatement();
                statement.execute("DROP TABLE products");   
                System.out.println("Table dropped successfully");
            } catch (SQLException e) {
                System.out.println("Error dropping table products..." + e);
            }
    }
    
    public void resetDB() {
            dropProductsTable();
            dropUsersTable();
            createUsersTable();
            createProductsTable();
        }
    
    public void addUser(int id, String username, String password, String is_admin) {
    Statement statement;
   // "INSERT INTO users VALUES (2,'user', 'user', 'N')"
    String myInsert = "INSERT INTO users values (" +
                       id + ",'" + username + "', '" +
                       password + "', '" + is_admin + "')";
    System.out.println(myInsert);
    try {
        statement = conn.createStatement();
        statement.execute(myInsert);                         
        } catch (SQLException e) {
        System.out.println("Error inserting user " 
                           + username + " "+ e);
        }
    }
    
    public void deleteUser(int id) {
    Statement statement;          
    String myDelete = "DELETE FROM users where id = " + id;
       System.out.println(myDelete);
       try {
           statement = conn.createStatement();
           statement.execute(myDelete);                         
           } catch (SQLException e) {
           System.out.println("Error deleting  user " 
                              + id + " " + e);
           }
   }
    public void updateUserPassword(int id, String newpass) {
            Statement statement;          
            String myUpdate = "UPDATE users set password='"
                + newpass + "' where id=" + id;
               System.out.println(myUpdate);
               try {
                   statement = conn.createStatement();
                   statement.execute(myUpdate);                         
                   } catch (SQLException e) {
                   System.out.println("Error updating  user " 
                                      + id + " " + e);
                   }
        }
    
    public ResultSet findUserbyName(String search) {
    ResultSet results=null;
    Statement statement;
      try {
      statement = conn.createStatement();
      results = 
        statement.executeQuery("select * from users " +
          "where lower(username) like '%" + 
           search.toLowerCase()+"%'");
      } catch (Exception e) {
        System.out.println("Error " + e.getMessage());
      }
        return results;
    }
    
    public ResultSet findAllUsers() {
    ResultSet results=null;
    Statement statement;
      try {
      statement = conn.createStatement();
      results = 
        statement.executeQuery("select * from users");
      } catch (Exception e) {
        System.out.println("Error " + e.getMessage());
      }
        return results;
    }
}

