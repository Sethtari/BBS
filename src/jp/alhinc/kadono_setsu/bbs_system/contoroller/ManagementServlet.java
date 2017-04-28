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

		request.getRequestDispatcher("/management.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = new User();
		String check = request.getParameter("isStopButton");
		int isStoppedCheck = Integer.parseInt(check.substring(check.length() - 1));
		int idCheck = Integer.parseInt(check.substring(0, check.length() - 1));

		if(isStoppedCheck == 1){
			user.setID(idCheck);
			user.setIsStopped(1);
		};

		if(isStoppedCheck == 0){
			user.setID(idCheck);
			user.setIsStopped(0);
		}

		new UserService().changeStoppedOrNot(user);

		response.sendRedirect("./management");
	}
}