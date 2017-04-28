package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.exception.NoRowsUpdatedRuntimeException;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
@MultipartConfig(maxFileSize = 100000)
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (session.getAttribute("editUser") == null) {
			User editUser = new UserService().getUser(loginUser.getID());
			session.setAttribute("editUser", editUser);
		}

		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User editUser = getEditUser(request);
		session.setAttribute("editUser", editUser);

		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);
			} catch (NoRowsUpdatedRuntimeException e) {
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("settings");
			}

			session.setAttribute("loginUser", editUser);
			session.removeAttribute("editUser");

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("settings");
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		editUser.setLoginID(request.getParameter("login_id"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranchID(request.getParameter("branch_id"));
		editUser.setPositionID(request.getParameter("position_id"));
		return editUser;
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
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
