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

import jp.alhinc.kadono_setsu.bbs_system.beans.Branch;
import jp.alhinc.kadono_setsu.bbs_system.beans.Position;
import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.exception.NoRowsUpdatedRuntimeException;
import jp.alhinc.kadono_setsu.bbs_system.service.BranchService;
import jp.alhinc.kadono_setsu.bbs_system.service.PositionService;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User editUser = new UserService().getUser(Integer.parseInt(request.getParameter("settingsButton")));
		session.setAttribute("editUser", editUser);

		List<Branch> branches = new BranchService().getBranchList();
		session.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositionList();
		session.setAttribute("positions", positions);

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
				request.getRequestDispatcher("settings.jsp").forward(request, response);
			} catch(Exception e) {
				return;
			}

			session.removeAttribute("editUser");

			response.sendRedirect("management");
		} else {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("settings.jsp").forward(request, response);
		}
	}

	private User getEditUser(HttpServletRequest request)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");

		if(!StringUtils.isEmpty(request.getParameter("login_id"))){
			editUser.setLoginID(request.getParameter("login_id"));
		}

		if(!StringUtils.isEmpty(request.getParameter("password"))){
			editUser.setPassword(request.getParameter("password"));
		}

		if(!StringUtils.isEmpty(request.getParameter("name"))){
			editUser.setName(request.getParameter("name"));
		}

		if(!StringUtils.isEmpty(request.getParameter("branch_id"))){
			editUser.setBranchId(request.getParameter("branch_id"));
		}

		if(!StringUtils.isEmpty(request.getParameter("position_id"))){
			editUser.setPositionId(request.getParameter("position_id"));
		}

		if(StringUtils.isEmpty(request.getParameter("password"))){
			editUser.setPassCheck(1);
		}else{
			editUser.setPassCheck(0);
		}
		return editUser;
	}

	private boolean isValid(HttpServletRequest request, List<String> messages)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = (User) session.getAttribute("editUser");


		if (!(6 <= editUser.getLoginId().length()) || !(editUser.getLoginId().length() <= 20)){
			messages.add("ログイン_idの文字数が規定と異なります");
		}

		if (!(6 <= editUser.getPassword().length()) || !(editUser.getPassword().length() <= 255)){
			messages.add("パスワードの文字数が規定と異なります");
		}
		if (!StringUtils.isEmpty(request.getParameter("password")) && !editUser.getPassword().equals(request.getParameter("passwordCheck"))){
			messages.add("入力されたパスワードが一致しません");
		}

		if (!(editUser.getName().length() <= 10)){
			messages.add("ユーザー名称の文字数が規定と異なります");
		}

		if (!(editUser.getBranchId().matches("^[0-9]+$"))){
			messages.add("支店の項目に数字以外が入力されています");
		}

		if (!(editUser.getPositionId().matches("^[0-9]+$"))){
			messages.add("部署・役職の項目に数字以外が入力されています");
		}

		if ((editUser.getPositionId().matches("2") ||editUser.getPositionId().matches("1") )&& !editUser.getBranchId().matches("1")){
			messages.add("支店と部署・役職の組み合わせが規定に沿っていません");
		}

		if ((editUser.getPositionId().matches("3") ||editUser.getPositionId().matches("4") )&& editUser.getBranchId().matches("1")){
			messages.add("支店と部署・役職の組み合わせが規定に沿っていません");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
