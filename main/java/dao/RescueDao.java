package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import model.RescueModel;
import util.ConnectionFactory;

public class RescueDao {
	
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	
	private String query = "";
	private boolean res ; 
	
	
	
	public boolean insert(RescueModel rm) {
		
		query ="insert into tb_rescue (date_rsc, value_rsc, iof_value_rsc, ir_tx_rsc, ir_value_rsc, desc_rsc, fk_ivt)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ? );";
		
		try {
			
			conn = ConnectionFactory.getConnection();
			
			pst = conn.prepareStatement(query);					
			
			pst.setString(1, rm.getDate());
			pst.setBigDecimal(2, rm.getValue());
			pst.setBigDecimal(3, rm.getIof());
			pst.setBigDecimal(4, rm.getIrTx());
			pst.setBigDecimal(5, rm.getIrValue());
			pst.setString(6, rm.getDesc());
			pst.setInt(7, rm.getFkivt());
			
			if (pst.executeUpdate() == 1) {					
				res = true;
			}else {					
				res = false;
			}
			
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		 return res ;
	}
	
	
	

}
