package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/management" })
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<User> users = new UserService().getUserList();
		session.setAttribute("users", users);

		request.getRequestDispatcher("management.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = new User();
		int id = Integer.parseInt(request.getParameter("isStopButton"));
		int isStopped = Integer.parseInt(request.getParameter("hidden"));

		if(isStopped == 1){
			user.setID(id);
			user.setIsStopped(1);
		};

		if(isStopped == 0){
			user.setID(id);
			user.setIsStopped(0);
		}

		new UserService().changeStoppedOrNot(user);

		response.sendRedirect("management");
	}
}