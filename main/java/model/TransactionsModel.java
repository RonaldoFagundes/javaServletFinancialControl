package model;

import java.math.BigDecimal;

public class TransactionsModel {
	
	  private int id;
	  private String mov ;
	  private String date ;
	  private String type;  
	  private String source;
	  private String form ;
	  private String desc ;
	  private BigDecimal value;
	  private BigDecimal allin;
	  private BigDecimal allout;
	  private BigDecimal totalDay;
	  private BigDecimal balance;
	  private int  fkbka;
	  
	  
	  
	  
	  
	public TransactionsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public TransactionsModel(int id, String mov, String date, String type, String source, String form, String desc,
			BigDecimal value, int fkbka) {
		super();
		this.id = id;
		this.mov = mov;
		this.date = date;
		this.type = type;
		this.source = source;
		this.form = form;
		this.desc = desc;
		this.value = value;		
		this.fkbka = fkbka;
	}

	
	
	
	
	public TransactionsModel(String mov, String date, String type, String source, String form, String desc,
			BigDecimal allin, BigDecimal allout, BigDecimal totalDay ) {
		super();		
		this.mov = mov;
		this.date = date;
		this.type = type;
		this.source = source;
		this.form = form;
		this.desc = desc;
		this.allin = allin;
		this.allout = allout;
		this.totalDay = totalDay;
	}



	
	
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMov() {
		return mov;
	}
	public void setMov(String mov) {
		this.mov = mov;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
   	public BigDecimal getAllin() {
		return allin;
	}
	public void setAllin(BigDecimal allin) {
		this.allin = allin;
	}
	public BigDecimal getAllout() {
		return allout;
	}
	public void setAllout(BigDecimal allout) {
		this.allout = allout;
	}
	public BigDecimal getTotalDay() {
		return totalDay;
	}
	public void setTotalDay(BigDecimal totalDay) {
		this.totalDay = totalDay;
	}
	public int getFkbka() {
		return fkbka;
	}
	public void setFkbka(int fkbka) {
		this.fkbka = fkbka;
	}
	  
	  

}
