package user;

public class User {
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private String emailHashed;
	
	public String getEmailHashed() {
		return emailHashed;
	}
	public void setEmailHashed(String emailHashed) {
		this.emailHashed = emailHashed;
	}
	private boolean admin = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getAdmin() {
		return admin;
	}
	
}
