package com.mahar.contact.model;

public class Country {
	public int id;
	public String country;
	
	public Country(){
		
	}
	
	public Country(int id, String country){
		this.id = id;
		this.country = country;
	}
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getCountry(){
		return this.country;
	}
	public void setCountry(String country){
		this.country = country;
	}
}
