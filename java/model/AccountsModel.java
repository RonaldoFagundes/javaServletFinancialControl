package model;

import java.math.BigDecimal;

public class AccountsModel {

	private int id;
	private String bank;
	private String number;
	private String type;
	private String desc;
	private BigDecimal amount;
	private BigDecimal allin;
	private BigDecimal allout;
	private BigDecimal balance;
	private int fkbnk;

	public AccountsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountsModel(int id, String bank, String number, String type, BigDecimal amount, int fk, BigDecimal allin, BigDecimal allout, BigDecimal balance ) {
		super();
		this.id = id;
		this.bank = bank;
		this.number = number;
		this.type = type;		
		this.amount = amount;
		this.fkbnk = fk;
		this.allin = allin;
		this.allout = allout;
		this.balance = balance; 
	}
	
	
	
	
	public AccountsModel(int id, String bank, String type, String number, BigDecimal amount ) {
		super();
		this.id = id;
		this.bank = bank;
		this.type = type;
		this.number = number;
		this.amount = amount;
	}
	
	

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getFkbnk() {
		return fkbnk;
	}

	public void setFkbnk(int fkbnk) {
		this.fkbnk = fkbnk;
	}

}
