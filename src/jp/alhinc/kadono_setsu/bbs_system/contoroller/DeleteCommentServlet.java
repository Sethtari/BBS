package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.alhinc.kadono_setsu.bbs_system.beans.Comment;
import jp.alhinc.kadono_setsu.bbs_system.service.CommentService;

@WebServlet(urlPatterns = { "/deletecomment" })
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("deleteCommentId"));

		Comment comment = new Comment();
		comment.setId(id);

		new CommentService().delete(comment);

		response.sendRedirect("./");
	}
}