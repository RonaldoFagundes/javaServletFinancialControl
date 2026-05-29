package util;


import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	
	
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://127.0.0.1:3306/db_financial_control?useTimezone=true&serverTimezone=UTC";
	
	private static final String user="RFactory";
	private static final String password ="jH7&p_f7*1M";
	
	
	
	
    public static  Connection getConnection () {
		
		Connection conn = null;
		
		try {
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
			
			return conn;
			
		} catch (Exception e) {
			
			System.out.println(e);
			
			return null;
			
		}
		
    }
    
    
    

}
