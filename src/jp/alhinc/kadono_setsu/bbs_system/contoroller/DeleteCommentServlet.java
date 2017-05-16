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

import jp.alhinc.kadono_setsu.bbs_system.beans.Comment;
import jp.alhinc.kadono_setsu.bbs_system.service.CommentService;

@WebServlet(urlPatterns = { "/deletecomment" })
public class DeleteCommentServlet extends HttpServlet {
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

/*
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
*/
		int id = Integer.parseInt(request.getParameter("deleteCommentId"));


		Comment comment = new Comment();
		comment.setId(id);

		new CommentService().delete(comment);

/*
		messages.add("コメントを削除しました");
		session.setAttribute("errorMessages", messages);
*/
		response.sendRedirect("./");

		return;
	}
}