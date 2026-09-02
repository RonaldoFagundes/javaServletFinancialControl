package model;

import java.math.BigDecimal;

public class RescueModel {
	
	private int id ; 
	private String  date;
	private BigDecimal value ;
	private BigDecimal iof ;
	private BigDecimal irTx ;
	private BigDecimal irValue ; 
	private String   desc  ;
	private int   fkivt;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getIof() {
		return iof;
	}
	public void setIof(BigDecimal iof) {
		this.iof = iof;
	}
	public BigDecimal getIrTx() {
		return irTx;
	}
	public void setIrTx(BigDecimal irTx) {
		this.irTx = irTx;
	}
	public BigDecimal getIrValue() {
		return irValue;
	}
	public void setIrValue(BigDecimal irValue) {
		this.irValue = irValue;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getFkivt() {
		return fkivt;
	}
	public void setFkivt(int fkivt) {
		this.fkivt = fkivt;
	}
	
	
	

}
