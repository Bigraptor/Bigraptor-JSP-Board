package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	final String dbURL = "jdbc:mysql://localhost:3306/jspboard";
	final String dbID = "root";
	final String dbPW = "tjrwn8259!";
	Connection conn;
	
	public UserDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	int check(String nickname, String email) {
		String sql = "SELECT nickname, email FROM user WHERE nickname=? OR email = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(nickname))
					return 0;
				else if(rs.getString(2).equals(email))
					return 1;
			}
			
			return 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	} //// 닉네임, 이메일의 중복을 체크한다.
	
	int join(User user) {
		String sql = "INSERT INTO user(id, pw, nickname, email, emailHashed, emailChecked) VALUES(?, ?, ?, ?, ?, false)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPw());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getEmailHashed());
			
			return ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int login(String id, String pw){
		String sql = "SELECT id, pw FROM user WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(2).equals(pw))
					return 1; /// 로그인 성공
				else
					return 0; /// 비밀번호가 틀립니다.
			}
		
			return 2; /// 아이디가 존재하지 않습니다.
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; /// DB 오류발생
	}
	
	public String getUserEmail(String id) {
		String sql = "SELECT email FROM user WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}

			return null; /// 아이디에 맞는 이메일이 없음.
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null; /// DB 오류 발생.
	}
	
	public boolean emailChecked(String id) {
		String sql = "SELECT emailChecked FROM user WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getBoolean(1);
			}
			
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	} /// 이메일 인증을 했는지 여부.
	
	public void setEmailChecked(String id) {
		String sql = "UPDATE user SET emailChecked = true WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNickname(String id) {
		String sql = "SELECT nickname FROM user WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
			return null; /// 입력한 id의 닉네임이 없다.
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null; /// DB오
	}
}
