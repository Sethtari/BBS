package jp.alhinc.kadono_setsu.bbs_system.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.alhinc.kadono_setsu.bbs_system.beans.User;

@WebFilter("/*")
public class FilterForbidden implements Filter {

	public FilterForbidden() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String encording = "UTF8";

		// 遷移元の文字コード指定
		request.setCharacterEncoding(encording);

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		User user = (User) session.getAttribute("loginUser");
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();

		if (uri.equals(contextPath + "/login.jsp") || uri.equals(contextPath + "/login") || uri.equals(contextPath + "/logout")) {

			// ログイン処理時はなにもしない

		} else if (session == null || user == null) {
			// セッションが切れたらログイン画面に戻る
			List<String> messages = new ArrayList<String>();
			messages.add("システムにはログインした後アクセスしてください");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("login");
			return;

		} else if ((uri.equals(contextPath + "/management") || uri.equals(contextPath + "/management.jsp") )&& !user.getPositionId().equals("1")){
			// ユーザー管理画面への人事部アカウント以外のアクセス禁止
			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権限がありません");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("./");

		} else if ((uri.equals(contextPath + "/settings") || uri.equals(contextPath + "/settings.jsp") )&& !user.getPositionId().equals("1")){
			// ユーザー編集画面への人事部アカウント以外のアクセス禁止
			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権限がありません");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("./");

		} else if ((uri.equals(contextPath + "/signup") || uri.equals(contextPath + "/signup.jsp") )&& !user.getPositionId().equals("1")){
			// ユーザー登録画面への人事部アカウント以外のアクセス禁止
			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権限がありません");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("./");
			}


		// サーブレットの実行
		chain.doFilter(request, response);


		// 遷移先の文字コード指定
		response.setCharacterEncoding(encording);
	}

}