package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();

		List<UserPost> userPosts = new PostService().getPostsList();
		session.setAttribute("userPosts", userPosts);

		List<UserComment> userComments = new CommentService().getCommentsList();
		session.setAttribute("userComments", userComments);

		List<UserPost> categories = new PostService().getCategories();
		session.setAttribute("posts", categories);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		String category = request.getParameter("category");
		System.out.println(category);
		if(category.isEmpty() || category.equals("all")){
			List<UserComment> userComments = new CommentService().getCommentsList();
			session.setAttribute("userComments", userComments);
		}else{
			List<UserPost> posts = new PostService().getCategorizedList(category);
			session.setAttribute("userPosts", posts);
		}

		List<UserPost> categories = new PostService().getCategories();
		session.setAttribute("posts", categories);

		List<UserComment> userComments = new CommentService().getCommentsList();
		session.setAttribute("userComments", userComments);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}