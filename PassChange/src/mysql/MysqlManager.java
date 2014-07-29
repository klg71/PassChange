package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlManager {
	public MysqlManager(String user,String pass){
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://klg71.us.to:3306/PassChange?user=%%&password=%%");

            Statement statement = conn.createStatement();
            // resultSet gets the result of the SQL query
            ResultSet resultSet = statement
                .executeQuery("SHOW TABLES;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(0));
            }

         
        } catch (SQLException ex) {
            // Fehler behandeln
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
