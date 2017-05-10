package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

		String category = request.getParameter("category");

		Date date = new Date();
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
		String choisedDate = date1.format(date); // format(d)のdは、Date d = new Date();のd

		String dateMin = request.getParameter("dateMin");
		if(dateMin == null || dateMin.isEmpty() ){
			dateMin = "2000-01-01";
		}

		String dateMax = request.getParameter("dateMax");
		if(dateMax == null || dateMax.isEmpty()){
			dateMax = choisedDate;
		}

		if(category == null){
			List<UserPost> userPosts = new PostService().getPostsList();
			session.setAttribute("userPosts", userPosts);
		}else{
			List<UserPost> posts = new PostService().getCategorizedList(category,dateMin,dateMax);
			session.setAttribute("userPosts", posts);
		}

		session.setAttribute("date", date);
		session.setAttribute("dateMin", dateMin);
		session.setAttribute("dateMax", dateMax);

		List<UserPost> categories = new PostService().getCategories();
		session.setAttribute("posts", categories);

		List<UserComment> userComments = new CommentService().getCommentsList();
		session.setAttribute("userComments", userComments);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}