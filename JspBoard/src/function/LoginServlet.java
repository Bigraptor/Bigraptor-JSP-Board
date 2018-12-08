package function;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		int result = login(id, pw);
		
		if(result == 2) {
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else if(result == 1) {
			request.getSession().setAttribute("id", id);
			out.println("<script>");
			out.println("location.href = 'index.jsp'");
			out.println("</script>");
		} else if(result == 0) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('DB에 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	public int login(String id, String pw) {
		UserDAO userDAO = new UserDAO();
		return userDAO.login(id, pw);
	}
}
