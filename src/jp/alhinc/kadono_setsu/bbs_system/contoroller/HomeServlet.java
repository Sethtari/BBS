package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.alhinc.kadono_setsu.bbs_system.beans.UserComment;
import jp.alhinc.kadono_setsu.bbs_system.beans.UserPost;
import jp.alhinc.kadono_setsu.bbs_system.service.CommentService;
import jp.alhinc.kadono_setsu.bbs_system.service.PostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		List<UserPost> userPosts = new PostService().getPostsList();
		request.setAttribute("userPosts", userPosts);

		List<UserComment> userComments = new CommentService().getCommentsList();
		request.setAttribute("userComments", userComments);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
