package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.LoginModel;
import util.ConnectionFactory;

public class LoginDao {
	
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String query ="";
	
	
	
	
	public boolean getLogIn(LoginModel lgm) {
		
		query ="";
		boolean  result = false;
		
		try {
			
			conn = ConnectionFactory.getConnection();
			pst = conn.prepareStatement(query);
			
			rs = pst.executeQuery();
			
			String user = rs.getString(1);
			String password = rs.getString(2);
			
			if( user.equals(lgm.getUser()) && password.equals(lgm.getPassword()) ) {
				result = true;
			}else {
				result =false;
			}		
			
			
		  } catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return result;
		
		
	}

}
