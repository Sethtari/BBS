

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
public class LoginFilter implements Filter {

	public LoginFilter() {
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

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loginUser");
		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();
		if (uri.equals(contextPath + "/login.jsp") || uri.equals(contextPath + "/login") || uri.equals(contextPath + "/logout")) {

			// ログイン処理時はなにもしない
		} else if (!uri.matches(".*css.*") && (session == null || user == null)) {
			// セッションが切れたらログイン画面に戻る
			List<String> messages = new ArrayList<String>();
			messages.add("システムにはログインした後アクセスしてください");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect(contextPath + "/login");
			return;

			}

		// サーブレットの実行
		chain.doFilter(request, response);

	}

}