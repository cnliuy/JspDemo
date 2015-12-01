package model.vo;

import java.util.Date;

public class User {
	private String userid;
	private String username;
	private String password;
	private String useremail;
	private Date intime;
	private boolean userstat;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public boolean isUserstat() {
		return userstat;
	}
	public void setUserstat(boolean userstat) {
		this.userstat = userstat;
	}
	
	public User() {
		
	}
	public User(String userid, String username, String password) {
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String userid, String username, String password ,String useremail ,Date intime ) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.intime= intime ;
		this.useremail = useremail;

	}
	public User(String userid, String username, String password ,String useremail ,Date intime ,boolean userstat) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.intime= intime ;
		this.useremail = useremail;
		this.userstat = userstat;
	}
	
	
}	
