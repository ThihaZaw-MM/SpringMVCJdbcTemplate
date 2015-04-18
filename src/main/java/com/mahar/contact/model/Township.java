package com.mahar.contact.model;

public class Township {
	private int id;
	private String townshipname;
	private String division;
	
	public Township(){
		
	}
	public Township(int id, String townshipname, String division){
		this.id = id;
		this.townshipname = townshipname;
		this.division = division;
	}
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getTownshipname(){
		return this.townshipname;
	}
	public void setTownshipname(String townshipname){
		this.townshipname = townshipname;
	}
	
	public String getDivision(){
		return this.division;
	}
	public void setDivision(String division){
		this.division = division;
	}
}
