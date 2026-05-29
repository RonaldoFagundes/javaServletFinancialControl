package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TransactionsModel;
import util.ConnectionFactory;






public class TransactionsDao {
	
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
    private String query = "";	  
    
    
	public void insert(TransactionsModel trm) {
		
		
		query = "insert into tb_transactions (move_trs, date_trs, type_trs, source_trs, form_trs, desc_trs, value_trs, balance_trs, fk_bka)\r\n"
				+ " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		
		/*
		query = "INSERT INTO tb_transactions (move_trs, date_trs, type_trs, source_trs, form_trs, desc_trs, value_trs, balance_trs, fk_bka)\r\n"
				+ "SELECT \r\n"
				+ "    ?, \r\n"
				+ "    ?,  \r\n"
				+ "    ?, \r\n"
				+ "    ?, \r\n"
				+ "    ?, \r\n"
				+ "    ?, \r\n"
				+ "    ?, \r\n"
				+ "    t.balance_trs - ?,  \r\n"
				+ "    ? \r\n"
				+ "FROM tb_transactions t\r\n"
				+ "WHERE t.fk_bka = ? \r\n"
				+ "ORDER BY t.date_trs DESC\r\n"
				+ "LIMIT 1;";
		*/
		try {
			
		  conn = ConnectionFactory.getConnection();
		  
		  pst = conn.prepareStatement(query);
		  
		  pst.setString(1, trm.getMov());
		  pst.setString(2, trm.getDate());
		  pst.setString(3, trm.getType());
		  pst.setString(4, trm.getSource());
		  pst.setString(5, trm.getForm());
		  pst.setString(6, trm.getDesc());
		  pst.setBigDecimal(7, trm.getValue());
		  pst.setBigDecimal(8, trm.getValue());
		  pst.setInt(9, trm.getFkbka());	  
		  		  
		  pst.executeUpdate();
		  conn.close();
		  
			
		} catch (Exception e) {
			
			System.out.println(e);
	}
		
}
	
	
	
	public ArrayList <TransactionsModel> report(TransactionsModel trm){
		
		ArrayList<TransactionsModel> list = new ArrayList<>();
		
		query = "SELECT    \r\n"
				+ "    t.move_trs,\r\n"
				+ "    t.date_trs,\r\n"
				+ "    t.type_trs,\r\n"
				+ "    t.source_trs,\r\n"
				+ "    t.form_trs,\r\n"
				+ "    t.desc_trs,  \r\n"
				+ "    -- Total de transações 'in'\r\n"
				+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'in' THEN t.value_trs ELSE 0 END), 0) AS total_in,    \r\n"
				+ "    -- Total de transações 'out'\r\n"
				+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'out' THEN t.value_trs ELSE 0 END), 0) AS total_out,\r\n"
				+ "    -- Calculando o saldo do dia (balance_bka + total_in - total_out)\r\n"
				+ "    b.balance_bka + \r\n"
				+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'in' THEN t.value_trs ELSE 0 END), 0) - \r\n"
				+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'out' THEN t.value_trs ELSE 0 END), 0) AS saldo_dia\r\n"
				+ "FROM \r\n"
				+ "    tb_bank_account b\r\n"
				+ "LEFT JOIN \r\n"
				+ "    tb_transactions t ON b.id_bka = t.fk_bka\r\n"
				+ "WHERE \r\n"
				+ "    b.id_bka = ?\r\n"
				+ "GROUP BY  \r\n"
				+ "    t.move_trs,\r\n"
				+ "    t.date_trs,\r\n"
				+ "    t.type_trs,\r\n"
				+ "    t.source_trs,\r\n"
				+ "    t.form_trs,\r\n"
				+ "    t.desc_trs,\r\n"
				+ "    t.value_trs\r\n"
				+ "ORDER BY \r\n"
				+ "    t.date_trs;";	  
		
		
		try {
			conn = ConnectionFactory.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, trm.getFkbka());
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				String mov = rs.getString(1); 
				String date  = rs.getString(2);  
				String type = rs.getString(3);  
				String source = rs.getString(4);  
				String form = rs.getString(5); 
				String desc = rs.getString(6); 
				BigDecimal allin = rs.getBigDecimal(7); 
				BigDecimal allout = rs.getBigDecimal(8); 
				BigDecimal totalDay = rs.getBigDecimal(9);
				
				list.add( new TransactionsModel(mov, date, type, source, form, desc, allin, allout, totalDay));				
			}
			
			conn.close();
			return list;
			
		  } catch (Exception e) {
			  System.out.println(e);
				 return null;
		}
	}
	
	
	
	

}
