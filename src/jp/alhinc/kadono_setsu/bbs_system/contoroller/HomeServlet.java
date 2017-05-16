package jp.alhinc.kadono_setsu.bbs_system.contoroller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import jp.alhinc.kadono_setsu.bbs_system.service.UserPostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		HttpSession session = request.getSession();


		String category = request.getParameter("category");

		Date date = new Date();
		SimpleDateFormat formatType = new SimpleDateFormat("yyyy-MM-dd");
		String choisedDate = formatType.format(date); // format(d)のdは、Date d = new Date();のd


		UserPostService userPost = new UserPostService();
		UserPost postSpan = new UserPost();
		postSpan = userPost.getWhenCreated();

		if((postSpan.getFirstPost() == null)){

			List<String> messages = new ArrayList<String>();
			session.setAttribute("category", category);
			session.setAttribute("dateMin", null);
			session.setAttribute("dateMax", null);
			messages.add("まだ投稿がありません");
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		UserPost whenCreated = new UserPostService().getWhenCreated();
		session.setAttribute("whenCreated", whenCreated);
		//最初の投稿及び最後の投稿の日を取得






		String firstPost = formatType.format(whenCreated.getFirstPost());
		//今日の日付の取得後、年月日の内容のみに

		String dateMin = request.getParameter("dateMin");
		String dateMax = request.getParameter("dateMax");

		//日付指定のminが未入力であれば格納
		if(!isNumMatch(dateMin) || dateMin == null || dateMin.isEmpty() ){
			dateMin = firstPost;
		}


		//日付指定のmaxが未入力であれば格納
		if(!isNumMatch(dateMax) || dateMax == null || dateMax.isEmpty()){
			dateMax = choisedDate;
		}

		List<UserPost> categoryList = new UserPostService().getCategories();
		session.setAttribute("categoryList", categoryList);
		//カテゴリ一覧の取得


		if(category == null){
			List<UserPost> userPosts = new UserPostService().getPostsList();
			request.setAttribute("userPosts", userPosts);
		}else{
			if(dateMin == null || dateMin.isEmpty() ){
				dateMin = firstPost;
			}
			dateMax += " 23:59:59";
			List<UserPost> posts = new UserPostService().getCategorizedList(category,dateMin,dateMax);
			request.setAttribute("userPosts", posts);
			dateMax = dateMax.substring(0, 10);
		}

		request.setAttribute("date", date);
		session.setAttribute("category", category);
		session.setAttribute("dateMin", dateMin);
		session.setAttribute("dateMax", dateMax);

		List<UserComment> userComments = new CommentService().getCommentsList();
		request.setAttribute("userComments", userComments);

		request.getRequestDispatcher("home.jsp").forward(request, response);
		return;
	}

	static boolean isNumMatch(String number) {
		if(number == "null" || number == null || number.isEmpty()){
			return false;
		}

		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]{4}[-][0-9]{2}[-][0-9]{2}");
		java.util.regex.Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
}