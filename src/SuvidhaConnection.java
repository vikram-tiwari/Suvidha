import java.sql.Connection;
import java.sql.DriverManager;


public class SuvidhaConnection {
	private static Connection con;
	private static String className="com.mysql.jdbc.Driver";
	private static String dbURL="jdbc:mysql://localhost:3306/suvidhadb";
	private static String userName="root";
	private static String password="root";
	
	public static Connection getConnection(){
		try {
			Class.forName(className);
			con=DriverManager.getConnection(dbURL,userName,password);
			return con;
		} catch (Exception e) {
			return null;
		}
	}
	

}
