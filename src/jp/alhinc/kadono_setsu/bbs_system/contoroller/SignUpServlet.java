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

import org.apache.commons.lang.StringUtils;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}



	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {	//63行目、アカウントとパスワードが記入されていれば実行
			//書き込み作業
			User user = new User();
			user.setLoginID(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchID(request.getParameter("branch_id"));
			user.setPositionID(request.getParameter("position_id"));

			new UserService().register(user);

			response.sendRedirect("./management");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("login_id",request.getParameter("login_id"));
			session.setAttribute("name",request.getParameter("name"));
			session.setAttribute("branch_id",request.getParameter("branch_id"));
			session.setAttribute("position_id",request.getParameter("positions_id"));
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password_check = request.getParameter("password_check");
		String name =request.getParameter("name");
		String branch_id = request.getParameter("branch_id");
		String position_id = request.getParameter("position_id");

		if (StringUtils.isEmpty(login_id) == true || StringUtils.isEmpty(password) == true || StringUtils.isEmpty(name) == true || StringUtils.isEmpty(branch_id) == true || StringUtils.isEmpty(position_id) == true) {
			messages.add("空白の項目があります。全項目を入力してください");
		}

		if (!(6 <= login_id.length()) || !(login_id.length() <= 20)){
			messages.add("ログイン_idの文字数が規定と異なります");
		}

		if (!(6 <= password.length()) || !(password.length() <= 255)){
			messages.add("パスワードの文字数が規定と異なります");
		}
		if (!password.equals(password_check)){
			messages.add("入力されたパスワードが一致しません");
		}

		if (!(name.length() <= 10)){
			messages.add("ユーザー名称の文字数が規定と異なります");
		}

		if (!(branch_id.matches("^[0-9]+$"))){
			messages.add("支店の項目に数字以外が入力されています");
		}

		if (!(position_id.matches("^[0-9]+$"))){
			messages.add("部署・役職の項目に数字以外が入力されています");
		}
		// TODO アカウントが既に利用されていないかの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
