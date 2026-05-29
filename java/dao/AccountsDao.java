package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.AccountsModel;
import util.ConnectionFactory;

public class AccountsDao {
	
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String query ="";
	
	
	  
	
	
	
	
	
	 public ArrayList <AccountsModel> accountsAll(AccountsModel acc){
		 
		 ArrayList<AccountsModel> list = new ArrayList<>();
		 
		 query ="SELECT tb_bank_account.id_bka , tb_bank.name_bnk , tb_bank_account.type_bka , tb_bank_account.number_bka, tb_bank_account.amount_bka FROM tb_bank INNER JOIN tb_bank_account ON (fk_bank = id_bnk ) WHERE tb_bank_account.id_bka not in (?)";
		 
		 try {
			 
			 conn = ConnectionFactory.getConnection();
			 pst = conn.prepareStatement(query);
			 pst.setInt(1, acc.getId());  
			 rs = pst.executeQuery();
			 
			 while(rs.next()) {
				 				 
				 int id = rs.getInt(1);
				 String bank   = rs.getString(2);					
				 String type   = rs.getString(3);		 
				 String number = rs.getString(4);			 
				 BigDecimal amount = rs.getBigDecimal(5);
				 
				 list.add(new AccountsModel(id,bank,type,number,amount));
			 }
			 
			 conn.close() ;
			 return list; 			
		  } catch (Exception e) {
			 System.out.println(e);
			 return null;
	    }	
	
	  }
	
	 
	 
	 
	 
	 
	  
	  public ArrayList<AccountsModel> listAccounts(AccountsModel acc){
		  
		  ArrayList<AccountsModel> list = new ArrayList<>();
		  
		  //query ="select id_bka, number_bka, type_bka, desc_bka, amount_bka from tb_bank_account where fk_bank = ?";
		  
		  
		  query = "SELECT \r\n"
		  		+ "    b.id_bka AS id,\r\n"
		  		+ "    bk.name_bnk AS bank,\r\n"
		  		+ "    b.number_bka AS number,\r\n"
		  		+ "    b.type_bka AS type,\r\n"
		  		+ "    b.amount_bka AS amount,  \r\n"
		  		+ "    b.fk_bank AS fk,  \r\n"
		  		+ "    IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.move_trs = 'in' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) AS total_in,  \r\n"
		  		+ "    IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.move_trs = 'out' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) AS total_out,  \r\n"
		  		+ "    b.amount_bka + IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.move_trs = 'in' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) - IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.move_trs = 'out' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) AS balance  \r\n"
		  		+ "FROM tb_bank_account b\r\n"
		  		+ "LEFT JOIN tb_transactions t \r\n"
		  		+ "    ON b.id_bka = t.fk_bka\r\n"
		  		+ "LEFT JOIN tb_bank bk\r\n"   
		  		+ "    ON b.fk_bank = bk.id_bnk\r\n"
		  		+ "    WHERE b.fk_bank = ?  \r\n"
		  		+ "GROUP BY \r\n"
		  		+ "    b.id_bka,\r\n"
		  		+ "    b.number_bka,\r\n"
		  		+ "    b.type_bka,\r\n"
		  		+ "    b.amount_bka,\r\n"
		  		+ "    bk.name_bnk;";	  
		  
		  
		  try {
			
			  conn = ConnectionFactory.getConnection();			  
			  pst = conn.prepareStatement(query);			  
			  pst.setInt(1, acc.getFkbnk());		  
			  rs = pst.executeQuery();			  
			  while(rs.next()) {
				  
				    int id = rs.getInt(1);
					String bank   = rs.getString(2);
					String number  = rs.getString(3);	
					String type = rs.getString(4);			
					BigDecimal amount = rs.getBigDecimal(5);
					int fk = rs.getInt(6);
					BigDecimal allin = rs.getBigDecimal(7);
					BigDecimal allout = rs.getBigDecimal(8);
					BigDecimal balance = rs.getBigDecimal(9);
					list.add(new AccountsModel(id, bank, number, type, amount, fk, allin, allout, balance ));
					
			  }
		 		
			 conn.close() ;
			 return list; 			
		  } catch (Exception e) {
			 System.out.println(e);
			 return null;
	    }	
	
	  }
	  
	  
	  
	  
	  public void uniqueAccount(AccountsModel acc) {
		  
		  query = "select id_bka, number_bka, type_bka, amount_bka, fk_bank from tb_bank_account tba where fk_bank = ? and id_bka  not in(?)";
		  
		  try {
			
			  conn = ConnectionFactory.getConnection();
			  pst = conn.prepareStatement(query);
			  
			  pst.setInt(1, acc.getFkbnk());
			  pst.setInt(2,acc.getId());
			  
			  rs = pst.executeQuery();
			  
			  if (rs.next()) {
				  
				  acc.setId(rs.getInt(1));
				  acc.setNumber(rs.getString(2));
				  acc.setType(rs.getString(3));
				  acc.setAmount(rs.getBigDecimal(4));
				  acc.setFkbnk(rs.getInt(5));
				  
				  
			  }else {	 
				  System.out.println("error uniqueAccount ");
			  }
			  
			  conn.close();
			
		  } catch (Exception e) {			 
			  
			  System.out.println(e);			
		}
		  
	  }
	  
	  
	  
	  
	  
	  
	  
	  
   public ArrayList<AccountsModel> report(AccountsModel acc){
		  
		  ArrayList<AccountsModel> list = new ArrayList<>();
	
		  
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
		  
		  
		  
	/*	  
	query =" SELECT \r\n"
			+ "    b.id_bka,\r\n"
			+ "    b.number_bka,\r\n"
			+ "    b.type_bka,\r\n"
			+ "    b.amount_bka,\r\n"
			+ "    t.move_trs,\r\n"
			+ "    t.date_trs,\r\n"
			+ "    t.type_trs,\r\n"
			+ "    t.source_trs,\r\n"
			+ "    t.form_trs,\r\n"
			+ "    t.desc_trs,     \r\n"
			+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'in' THEN t.value_trs ELSE 0 END), 0) AS total_in,    \r\n"
			+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'out' THEN t.value_trs ELSE 0 END), 0) AS total_out,    \r\n"
			+ "    b.balance_bka + \r\n"
			+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'in' THEN t.value_trs ELSE 0 END), 0) - \r\n"
			+ "    IFNULL(SUM(CASE WHEN t.move_trs = 'out' THEN t.value_trs ELSE 0 END), 0) AS saldo_dia\r\n"
			+ "FROM \r\n"
			+ "    tb_bank_account b\r\n"
			+ "LEFT JOIN \r\n"
			+ "    tb_transactions t ON b.id_bka = t.fk_bka\r\n"
			+ "WHERE \r\n"
			+ "    b.id_bka = ?\r\n"
			+ "GROUP BY\r\n"
			+ "    b.id_bka,\r\n"
			+ "    b.number_bka,\r\n"
			+ "    b.type_bka,\r\n"
			+ "    b.amount_bka,\r\n"
			+ "    t.move_trs,\r\n"
			+ "    t.date_trs,\r\n"
			+ "    t.type_trs,\r\n"
			+ "    t.source_trs,\r\n"
			+ "    t.form_trs,\r\n"
			+ "    t.desc_trs,\r\n"
			+ "    t.value_trs\r\n"
			+ "ORDER BY \r\n"
			+ "    t.date_trs" ;
	  */
		  
		  
		  
	  try {
			
		  conn = ConnectionFactory.getConnection();			  
		  pst = conn.prepareStatement(query);
		  
		  pst.setInt(1, acc.getId());
		  
		  rs = pst.executeQuery();	
		  		  
		  while(rs.next()) {			  
			  
			   
		  }  
		  	 		
		 conn.close() ;
		 return list; 			
	  } catch (Exception e) {
		 System.out.println(e);
		 return null;
    }	

  }
   
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	  
	  
	  /*
	  public void getamount(AccountsModel acc) {
		  
		  query = " SELECT \r\n"		  		
		  		+ "    b.number_bka AS number,\r\n"
		  		+ "    b.type_bka AS account_type,\r\n"
		  		+ "    b.amount_bka AS amount,  \r\n"
		  		+ "    IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.mov_trs = 'in' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) AS total_in,  \r\n"
		  		+ "    IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.mov_trs = 'out' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) AS total_out,  \r\n"
		  		+ "    b.amount_bka + IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.mov_trs = 'in' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) - IFNULL(SUM(CASE \r\n"
		  		+ "        WHEN t.mov_trs = 'out' THEN t.value_trs \r\n"
		  		+ "        ELSE 0 \r\n"
		  		+ "    END), 0) AS balance  \r\n"
		  		+ "FROM tb_bank_account b\r\n"
		  		+ "INNER JOIN tb_transactions t \r\n"
		  		+ "    ON b.id_bka = t.fk_bka\r\n"
		  		+ "    WHERE b.id_bka = 1  \r\n"
		  		+ "GROUP BY \r\n"
		  		+ "    b.id_bka,\r\n"
		  		+ "    b.number_bka,\r\n"
		  		+ "    b.type_bka,\r\n"
		  		+ "    b.amount_bka;";
		  
		   try {
			   
			  conn = ConnectionFactory.getConnection();
			  pst = conn.prepareStatement(query);
			  
			  pst.setInt(1, acc.getId());
			  
			  rs = pst.executeQuery();
			  
			  if(rs.next()) {
				 
				 acc.setNumber(rs.getString(1));
				 acc.setType(rs.getString(2));
				 acc.setAmount(rs.getBigDecimal(3));
				 acc.setAllin(rs.getBigDecimal(4));
				 acc.setAllout(rs.getBigDecimal(5));
				 acc.setBalance(rs.getBigDecimal(5));				  
				  
			  }
			  
			  
			  
		  } catch (Exception e) {
			// TODO: handle exception
		}
	  }
	  */
	  
}
