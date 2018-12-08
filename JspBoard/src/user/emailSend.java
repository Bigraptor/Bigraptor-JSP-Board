package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import function.Gmail;
import function.SHA256;

@WebServlet("/emailSend")
public class emailSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") != null) {
			id = (String)session.getAttribute("id");
		}
		if(id == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요합니다.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
			return;
		}
		UserDAO userDAO = new UserDAO();
		if(userDAO.emailChecked(id) == true) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 인증이 되어 있습니다.');");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
			return;
		}
		
		String host = "http://localhost:8080/JspBoard/";
		String from = "bigraptor@jspboard.com";
		String to = userDAO.getUserEmail(id);
		String subject = "Bigraptor JSP Board 인증메일입니다.";
		String content = "다음 링크에 접속해서 인증해주세요."
		+ "<a href = '" + host + "emailCheck?code=" + new SHA256().getSHA256(to) + "'>인증</a>";
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		try {
			Authenticator auth = new Gmail();
			Session sess = Session.getInstance(p, auth);
			sess.setDebug(true);
			MimeMessage msg = new MimeMessage(sess);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=UTF8");
			Transport.send(msg);
			response.sendRedirect("./sendAction.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.')");
			script.println("histroy.back()");
			script.println("</script>");
			return;
		}
	}

}
