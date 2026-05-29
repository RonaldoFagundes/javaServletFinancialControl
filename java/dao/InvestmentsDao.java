package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.InvestmentsModel;
import util.ConnectionFactory;

public class InvestmentsDao {

	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String query ="";
	private boolean res ; 
	
	
	
	public boolean insert(InvestmentsModel invest) {

		query = "insert into tb_investments(broker_name_ivt, cat_ivt, type_ivt, open_ivt, expery_ivt, rate_type_ivt, rate_ivt, value_ivt, desc_ivt, fk_bka)values(?,?,?,?,?,?,?,?,?,?)";

		try {

			conn = ConnectionFactory.getConnection();

			pst = conn.prepareStatement(query);

			pst.setString(1, invest.getBroker());
			pst.setString(2, invest.getCat());
			pst.setString(3, invest.getType());
			pst.setString(4, invest.getOpen());
			pst.setString(5, invest.getExpery());
			pst.setString(6, invest.getRateType());
			pst.setBigDecimal(7, invest.getRate());
			pst.setBigDecimal(8, invest.getValue());
			pst.setString(9, invest.getDesc());
			pst.setInt(10, invest.getFkBka());
			
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
	
	
	
	

	public ArrayList<InvestmentsModel> listInvestments(InvestmentsModel invm) {

		ArrayList<InvestmentsModel> list = new ArrayList<>();

		query = "select id_ivt, broker_name_ivt, type_ivt, open_ivt, expery_ivt,  rate_type_ivt, rate_ivt, value_ivt from tb_investments where fk_bka = ?";

		// String query ="select id_ivt, broker_name_ivt, type_ivt, rate_type_ivt,
		// rate_ivt, value_ivt from tb_investments";

		try {
			conn = ConnectionFactory.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, invm.getFkBka());
			rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String broker = rs.getString(2);
				String type = rs.getString(3);
				String open = rs.getString(4);
				String expery = rs.getString(5);
				String rate_type = rs.getString(6);
				BigDecimal rate = rs.getBigDecimal(7);
				BigDecimal value = rs.getBigDecimal(8);
				list.add(new InvestmentsModel(id, broker, type, open, expery, rate_type, rate, value));
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	
	
	
public void selectInvestById(InvestmentsModel invm) {
		
 query = "SELECT i.id_ivt AS id, i.broker_name_ivt AS broker, i.type_ivt AS type, i.open_ivt AS open, i.expery_ivt AS expery, i.rate_type_ivt AS `index`, i.rate_ivt AS rate, i.value_ivt AS aplication, IFNULL(SUM(p.income_pro), 0) AS profitability, IFNULL(SUM(r.value_rsc), 0)  AS rescue, i.value_ivt + IFNULL(SUM(p.income_pro), 0) - IFNULL(SUM(r.value_rsc), 0) AS amount FROM tb_investments i LEFT JOIN tb_profitability p ON i.id_ivt = p.fk_ivt LEFT JOIN tb_rescue r ON i.id_ivt = r.fk_ivt WHERE i.id_ivt = ? GROUP BY i.id_ivt, i.broker_name_ivt, i.type_ivt, i.open_ivt, i.expery_ivt, i.rate_type_ivt, i.rate_ivt, i.value_ivt";
				
		try {
			
			conn = ConnectionFactory.getConnection();
			pst = conn.prepareStatement(query);
			pst.setInt(1, invm.getId());			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				
				invm.setBroker(rs.getString("broker"));
				invm.setType(rs.getString("type"));
				invm.setOpen(rs.getString("open"));
				invm.setExpery(rs.getString("expery"));
				invm.setRateType(rs.getString("index"));
				invm.setRate(rs.getBigDecimal("rate"));
				invm.setValue(rs.getBigDecimal("aplication"));
				invm.setProfitability(rs.getBigDecimal("profitability"));
				invm.setRescue(rs.getBigDecimal("rescue"));
				invm.setAmount(rs.getBigDecimal("amount"));	
				
			}else {
				
				System.out.println("error selectInvestById "+invm.getId());
			}
			
			conn.close();			
			
		} catch (Exception e) {
			System.out.println(e);
		}				
		
	}









}
