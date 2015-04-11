package com.mahar.contact.model;

public class User {
	private int id;
	private String login;
	private String password;
	
	public User(){
		
	}
	
	public User(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public String getLogin(){
		return this.login;
	}
	public void setLoginId(String login){
		this.login = login;
	}
	
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
}
