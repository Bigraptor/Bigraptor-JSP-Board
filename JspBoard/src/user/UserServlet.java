package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.SHA256;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		if(pw == null || pw.equals("")) {
			out.println("<script>");
			out.println("alert('비밀번호를 입력해주세요.')");
			out.println("history.back()");
			out.println("</script>");
			return;
		}
		
		int check = check(nickname, email);
		
		if(check == 0) {
			out.println("<script>");
			out.println("alert('닉네임이 이미 존재합니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if(check == 1) {
			out.println("<script>");
			out.println("alert('이메일이 이미 존재합니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
		
			int result = join(id, pw, nickname, email);
			
			if(result == -1) {
				out.println("<script>");
				out.println("alert('아이디가 이미 존재합니다.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				request.getSession().setAttribute("id", id);
				response.sendRedirect("./emailSend");
			}
		}
	}
	
	int check(String nickname, String email) {
		UserDAO userDAO = new UserDAO();
		return userDAO.check(nickname, email);
	}
	
	int join(String id, String pw, String nickname, String email) {
		User user = new User();
		user.setId(id);
		user.setPw(pw);
		user.setNickname(nickname);
		user.setEmail(email);
		user.setEmailHashed(SHA256.getSHA256(email));
		
		UserDAO userDAO = new UserDAO();
		return userDAO.join(user);
	}

}
