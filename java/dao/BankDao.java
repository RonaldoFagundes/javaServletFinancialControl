package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.BankModel;
import util.ConnectionFactory;

public class BankDao {
	
	
	
	
	public ArrayList<BankModel>listBanks(){
		
		 ArrayList<BankModel> list = new ArrayList<>();
		 
		 String query ="SELECT id_bnk, name_bnk, contact_bnk, img_bnk from tb_bank";
		 
		 try {			
				Connection conn = ConnectionFactory.getConnection();			
				PreparedStatement pst = conn.prepareStatement(query);			
				ResultSet rs = pst.executeQuery();
				
				while( rs.next() ) {
					
					int id = rs.getInt(1);
					String name   = rs.getString(2);					
					String contact = rs.getString(3);					
					String img = rs.getString(4);
					
					list.add( new BankModel(id, name, contact, img));
					
				}			
				conn.close() ;
				return list; 			
			} catch (Exception e) {
				 System.out.println(e);
				return null;
		}		
			
	

     }

	
	
	
}