package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {
	
	static String driver;
	static String dbName;
	static String connectionURL;
	static String username;
	static String password;
	
   Connection connection;//Creating object of Connection class
   Statement statement;//Creating object of Statement class

   public connect()
   {      
		driver = "com.mysql.cj.jdbc.Driver";
		connectionURL ="jdbc:mysql://localhost:3306/";
		dbName = "kioskappdb_dev";
		username = "root";
		password = "";
   }
   
   public Connection getConnection() throws ClassNotFoundException, SQLException {
	   Class.forName(driver);
	   Connection connection = DriverManager.getConnection(connectionURL+dbName+"?serverTimezone=UTC",username,password); //Creating connection with database
	   return connection;
   }
}
