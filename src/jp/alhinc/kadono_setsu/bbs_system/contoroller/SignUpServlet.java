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
import jp.alhinc.kadono_setsu.bbs_system.service.BranchService;
import jp.alhinc.kadono_setsu.bbs_system.service.PositionService;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<Branch> branches = new BranchService().getBranchList();
		session.setAttribute("branches", branches);

		List<Position> positions = new PositionService().getPositionList();
		session.setAttribute("positions", positions);
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
			user.setLoginID(request.getParameter("newId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("newName"));
			user.setBranchId(request.getParameter("branchId"));
			user.setPositionId(request.getParameter("positionId"));

			messages.add(request.getParameter("newName")+"さんをデータベースに登録しました");
			session.setAttribute("errorMessages", messages);

			new UserService().register(user);

			response.sendRedirect("./management");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("newId",request.getParameter("newId"));
			session.setAttribute("newName",request.getParameter("newName"));
			session.setAttribute("branchId",request.getParameter("branchId"));
			session.setAttribute("positionId",request.getParameter("positionId"));
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		UserService check = new UserService();
		String newId = request.getParameter("newId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String newName =request.getParameter("newName");
		String branchId = request.getParameter("branchId");
		String positionId = request.getParameter("positionId");
		int loginIdLen = newId.length();
		int passwordLen = password.length();
		byte[] loginIdBytes = newId.getBytes();
		byte[] passwordBytes = password.getBytes();


		if (!check.newUserIdCheck(newId)){
			messages.add("そのログインIDはすでに使われています");
		}


		if (StringUtils.isEmpty(newId) == true || StringUtils.isEmpty(password) == true || StringUtils.isEmpty(newName) == true || StringUtils.isEmpty(branchId) == true || StringUtils.isEmpty(positionId) == true) {
			messages.add("空白の項目があります。全項目を入力してください");
		}

		if( loginIdLen != loginIdBytes.length || !newId.matches("^[0-9a-zA-Z]{6,20}$")){
			messages.add("ログインIDは6字以上20字以内の半角英数のみで入力してください");
		}

		if(passwordLen < 6 || passwordLen >255){
			messages.add("入力されたパスワードの文字数が規定と異なります");
		}

		if (passwordLen != passwordBytes.length){
			messages.add("パスワードは半角英数のみで入力してください");
		}
		if (!password.equals(passwordCheck)){
			messages.add("入力されたパスワードが一致しません");
		}

		if (!(newName.length() <= 10)){
			messages.add("ユーザー名称の文字数が規定と異なります");
		}


		if ((positionId.matches("2") ||positionId.matches("1")) && !branchId.matches("1")){
			messages.add("支店と部署・役職の組み合わせが規定に沿っていません");
		}

		if ((positionId.matches("3") || positionId.matches("4")) && branchId.matches("1")){
			messages.add("支店と部署・役職の組み合わせが規定に沿っていません");
		}

		// TODO アカウントが既に利用されていないかの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
