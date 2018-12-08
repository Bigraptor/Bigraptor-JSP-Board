package board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = (String)request.getSession().getAttribute("id");
		UserDAO userDAO= new UserDAO();
		String nickName = userDAO.getNickname(id);
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO boardDAO = new BoardDAO();
		
		if(!nickName.equals(boardDAO.getNickname(no))) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('허용되지 않은 접근입니다.');");
			script.println("location.href='index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		int result = boardDAO.delete(no);
		if(result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href='index.jsp'");
			script.println("</script>");
			script.close();
			return;
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.');");
			script.println("history.back()");
			script.println("</script>");
			script.close();
			return;
		}
	}

}
