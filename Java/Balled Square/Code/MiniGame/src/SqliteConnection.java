import java.sql.*;
import javax.swing.*;
public class SqliteConnection {
	static Connection conn;
	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/leaderboard2538", "pheonix", "password!");
			JOptionPane.showMessageDialog(null, "Conncetion to database was successful. Please Proceed.");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Failed to connect to database. Please try again.");
		}
		return conn;
	}
    
}
