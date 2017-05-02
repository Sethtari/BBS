package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/deleteuser" })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String id = request.getParameter("deleteId");
		User user = new User();
		user.setId(Integer.parseInt(id));
		new UserService().deleteUser(user);

		response.sendRedirect("management");
	}
}