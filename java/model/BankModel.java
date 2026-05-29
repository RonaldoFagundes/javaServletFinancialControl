package model;

public class BankModel {
	
	private int id;
	private String number;
	private String name;
	private String ein;
	private String contact;
	private String desc;
	private String img;
	
	
	
	
	public BankModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public BankModel(int id,String name, String contact, String img) {
		super();
		this.id = id;
		this.name = name;		
		this.contact = contact;		
		this.img = img;
	}

	
	/*
	public BankModel(String number, String name, String ein, String contact, String desc, String img) {
		super();
		this.number = number;
		this.name = name;
		this.ein = ein;
		this.contact = contact;
		this.desc = desc;
		this.img = img;
	}
   */



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEin() {
		return ein;
	}
	public void setEin(String ein) {
		this.ein = ein;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

}
