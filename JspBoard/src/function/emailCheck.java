package function;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.UserDAO;

@WebServlet("/emailCheck")
public class emailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		UserDAO userDAO = new UserDAO();
		String code = null;
		String id = null;
		HttpSession session = request.getSession();
		if(request.getParameter("code") != null) {
			code = (String)request.getParameter("code");
		}
		if(session.getAttribute("id") != null) {
			id = (String)session.getAttribute("id");
		}
		
		if(id == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
			return;
		}
		String email = userDAO.getUserEmail(id);
		boolean isRight = SHA256.getSHA256(email).equals(code) ? true : false;
		
		if(isRight == true) {
			userDAO.setEmailChecked(id);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('인증되었습니다.')");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
			return;
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 코드입니다.')");
			script.println("location.href = 'index.jsp");
			script.println("</script>");
			return;
		}
	}

}
