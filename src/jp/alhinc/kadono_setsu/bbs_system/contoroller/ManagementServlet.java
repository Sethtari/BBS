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

import jp.alhinc.kadono_setsu.bbs_system.beans.Branch;
import jp.alhinc.kadono_setsu.bbs_system.beans.Position;
import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.BranchService;
import jp.alhinc.kadono_setsu.bbs_system.service.PositionService;
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

		List<Branch> branches = new BranchService().getBranchList();
		request.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositionList();
		request.setAttribute("positions", positions);

		request.getRequestDispatcher("management.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {



		User user = new User();
		int id = Integer.parseInt(request.getParameter("id"));

		int isStopped = Integer.parseInt(request.getParameter("isStopped"));

/*
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		String name = request.getParameter("name");

		String change = null;

		if(isStopped ==1){
			change = "停止";
		} else {
			change = "復活";
		}
*/

		if(isStopped == 1){
			user.setId(id);
			user.setIsStopped(1);
		};

		if(isStopped == 0){
			user.setId(id);
			user.setIsStopped(0);
		}

		new UserService().changeStoppedOrNot(user);
/*
		messages.add(name+"さんを"+change+"しました");
		session.setAttribute("errorMessages", messages);
*/
		response.sendRedirect("management");
	}
}