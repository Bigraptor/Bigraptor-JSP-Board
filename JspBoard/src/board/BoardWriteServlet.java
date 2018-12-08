package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/BoardWriteServlet")

public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = (String)request.getSession().getAttribute("id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		UserDAO userDAO = new UserDAO();
		String nickname = userDAO.getNickname(id);
		int result = boardWrite(title, contents, nickname);
		if(result == -1) {
			response.sendRedirect("./write.jsp");
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	public int boardWrite(String title, String contents, String nickname) {
		Board board = new Board();
		board.setTitle(title);
		board.setContents(contents);
		board.setAuthor(nickname);
		
		BoardDAO boardDAO = new BoardDAO();
		return boardDAO.write(board);
	}
}
