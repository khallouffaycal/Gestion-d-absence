package com.pfa1.API.model;
//Token d'authentification
public class Tokenlog {
	String token;
	String type;
	public Tokenlog() {
	}
	public Tokenlog(String token, String type) {
		super();
		this.token = token;
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Tokenlog [token=" + token + ", type=" + type + "]";
	}	
}
