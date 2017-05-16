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

import jp.alhinc.kadono_setsu.bbs_system.beans.Post;
import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.PostService;

@WebServlet(urlPatterns = { "/newpost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("newPost.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {

			User user = (User) session.getAttribute("loginUser");

			Post post = new Post();
			post.setUsersId(user.getId());
			post.setTitle(request.getParameter("title"));
			post.setText(request.getParameter("text"));
			post.setCategory(request.getParameter("category"));


			new PostService().register(post);
/*
			messages.add("投稿しました");
			session.setAttribute("errorMessages", messages);
*/
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("title",request.getParameter("title"));
			session.setAttribute("text", request.getParameter("text"));
			session.setAttribute("category", request.getParameter("category"));
			request.getRequestDispatcher("newPost.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String titleCheck = request.getParameter("title");
		String textCheck = request.getParameter("text");
		String categoryCheck = request.getParameter("category");


		if (StringUtils.isEmpty(titleCheck) == true){
			messages.add("件名を入力してください");
		}
		if (titleCheck.length() > 50){
			messages.add("件名が規定を"+(titleCheck.length()-50)+"字オーバーしています");
			messages.add("件名は50文字以内で入力してください");
		}

		if (StringUtils.isEmpty(textCheck) == true) {
			messages.add("本文を入力してください");
		}
		if (textCheck.length() > 1000){
			messages.add("本文が規定を"+(textCheck.length()-1000)+"字オーバーしています");
			messages.add("本文は1000文字以内で入力してください");
		}

		if (StringUtils.isEmpty(categoryCheck) == true){
			messages.add("カテゴリーを入力してください");
		}
		if (categoryCheck.length() > 10){
			messages.add("カテゴリーが規定を"+(categoryCheck.length()-10)+"字オーバーしています");
			messages.add("カテゴリーは10文字以内で入力してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}