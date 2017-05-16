package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/deleteuser" })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

			HttpSession session = request.getSession();
			List<String> messages = new ArrayList<String>();
			messages.add("不正なアクセスです");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			return;
		}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		String id = request.getParameter("deleteId");
		User user = new User();
		String deletedUser = request.getParameter("name");

		user.setId(Integer.parseInt(id));
		new UserService().deleteUser(user);

		messages.add(deletedUser+"さんをデータベースから削除しました");
		session.setAttribute("errorMessages", messages);
		response.sendRedirect("management");
		return;
	}
}