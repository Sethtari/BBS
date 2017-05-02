package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.alhinc.kadono_setsu.bbs_system.beans.Post;
import jp.alhinc.kadono_setsu.bbs_system.service.PostService;

@WebServlet(urlPatterns = { "/deletepost" })
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("deletePostId"));

		Post post = new Post();
		post.setId(id);

		new PostService().deletePost(post);

		response.sendRedirect("./");
	}
}