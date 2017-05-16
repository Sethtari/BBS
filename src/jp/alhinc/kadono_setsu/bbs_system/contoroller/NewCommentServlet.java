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

import jp.alhinc.kadono_setsu.bbs_system.beans.Comment;
import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.CommentService;

@WebServlet(urlPatterns = { "/newcomment" })
public class NewCommentServlet extends HttpServlet {
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
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();
		String id = request.getParameter("comenttedPostId");
		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");


			Comment comment = new Comment();
			comment.setPostId(Integer.parseInt(id));
			comment.setUserId(user.getId());
			comment.setText(request.getParameter("text"));

			new CommentService().register(comment);
/*
			messages.add("投稿にコメントしました");
			session.setAttribute("errorMessages", messages);
*/
			response.sendRedirect("./");
			return;

		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("missCommentId", id);
			session.setAttribute("text", request.getParameter("text"));

			response.sendRedirect("./");
			return;
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {


		String textCheck = request.getParameter("text");




		if (StringUtils.isBlank(textCheck)) {
			messages.add("本文を入力してください");
		}else if (textCheck.length() > 500){
			messages.add("コメント文が規定を"+(textCheck.length()-500)+"字オーバーしています");
			messages.add("コメントは500文字以内で入力してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}