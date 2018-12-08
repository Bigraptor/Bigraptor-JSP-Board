package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	Connection conn;
	ResultSet rs;
	
	public BoardDAO() {
		String dbURL = "jdbc:mysql://localhost:3306/jspboard";
		String dbID = "root";
		String dbPW = "tjrwn8259!";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNext() {
		String sql = "SELECT no FROM board ORDER BY no desc";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return result.getInt(1)+1;
			}
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public String getDate() {
		String sql = "SELECT NOW()";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				return rs.getString(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ""; /// error
	}
	
	public int write(Board board) {
		String sql = "INSERT INTO board VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getNext());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContents());
			ps.setString(4, board.getAuthor());
			ps.setString(5, getDate());
			
			return ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public ArrayList<Board> getList(String search, int pageNumber) {
		String sql = "SELECT no, title, author, created FROM board WHERE title LIKE ? ORDER BY no DESC LIMIT " +
					pageNumber*10 + ", " + pageNumber*10+11;
		
		ArrayList<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setAuthor(rs.getString(3));
				board.setCreated(rs.getString(4));
				
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Board getBoard(int no) {
		String sql = "SELECT title, contents, author, created FROM board WHERE no = ?";
		Board board = new Board();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				board.setTitle(rs.getString(1));
				board.setContents(rs.getString(2));
				board.setAuthor(rs.getString(3));
				board.setCreated(rs.getString(4));
				
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	public int delete(int no) {
		String sql = "DELETE FROM board WHERE no = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			return ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; /// DB error
	}
	
	public String getNickname(int no) {
		String sql = "SELECT author FROM board WHERE no = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
