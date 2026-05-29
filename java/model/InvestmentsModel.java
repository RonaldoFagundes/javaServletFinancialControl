package model;

import java.math.BigDecimal;

public class InvestmentsModel {
	
	private int id;   
	private String broker;
	private String cat ;	
	private String type;	
	private String open;
	private String expery;
	private String rateType;
	private BigDecimal rate;
	private BigDecimal value;
	private BigDecimal profitability;
	private BigDecimal rescue;
	private BigDecimal amount;
	private String desc;	
	private int fkbka;
	
	
	
	
	public InvestmentsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*	
	public InvestmentsModel(String broker_name, String cat, String type, String open, String expery, String rate_type,
			BigDecimal rate, BigDecimal value, String desc, int fk_bka) {
		super();
		this.broker_name = broker_name;
		this.cat = cat;
		this.type = type;
		this.open = open;
		this.expery = expery;
		this.rate_type = rate_type;
		this.rate = rate;
		this.value = value;
		this.desc = desc;
		this.fk_bka = fk_bka;
	}
	*/
	
	public InvestmentsModel(int id, String broker, String type, String open, String expery,  String rateType, BigDecimal rate, BigDecimal value) {
		super();
		this.id = id;
		this.broker = broker;	
		this.type = type;
		this.open = open;
		this.expery = expery;				
		this.rateType = rateType;
		this.rate = rate;
		this.value = value;		
	}




    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getExpery() {
		return expery;
	}
	public void setExpery(String expery) {
		this.expery = expery;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getFkBka(){
		return fkbka;
	}
	public void setFkBka(int fkbka) {
		this.fkbka = fkbka;
	} 
	
	public BigDecimal getProfitability() {
		return profitability;
	}

	public void setProfitability(BigDecimal profitability) {
		this.profitability = profitability;
	}

	public BigDecimal getRescue() {
		return rescue;
	}

	public void setRescue(BigDecimal rescue) {
		this.rescue = rescue;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}	
	
		
      
    
    
    
    
    
    
    
    
    
    
    
	
	

}
