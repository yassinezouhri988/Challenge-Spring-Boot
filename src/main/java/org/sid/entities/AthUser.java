package org.sid.entities;

public class AthUser {
	private String Username;
	private String password;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AthUser(String username, String password) {
		super();
		Username = username;
		this.password = password;
	}
	public AthUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
