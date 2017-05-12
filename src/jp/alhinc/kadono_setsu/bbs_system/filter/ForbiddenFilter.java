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
public class ForbiddenFilter implements Filter {

	public ForbiddenFilter() {
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
		if (user == null) {

			// ログイン前は何もしない
		} else if (!(user.getIsStopped() == 0)) {
		// 停止状態の場合アクセスを拒否する
		List<String> messages = new ArrayList<String>();
		messages.add("アカウントが停止状態であるため、ログインできませんでした");
		messages.add("心当たりのない場合は、お手数ですが管理者までお問い合わせください");
		session.setAttribute("errorMessages", messages);
		session.removeAttribute("loginUser");
		((HttpServletResponse) response).sendRedirect("login");
		return;

	} else if ((uri.equals(contextPath + "/management") || uri.equals(contextPath + "/management.jsp") )&& !user.getPositionId().equals("1")){
		// ユーザー管理画面への人事部アカウント以外のアクセス禁止
		List<String> messages = new ArrayList<String>();
		messages.add("当該ページへのアクセス権限がありません");
		session.setAttribute("errorMessages", messages);
		((HttpServletResponse) response).sendRedirect("./");
		return;

	} else if ((uri.equals(contextPath + "/settings") || uri.equals(contextPath + "/settings.jsp") )&& !user.getPositionId().equals("1")){
		// ユーザー編集画面への人事部アカウント以外のアクセス禁止
		List<String> messages = new ArrayList<String>();
		messages.add("当該ページへのアクセス権限がありません");
		session.setAttribute("errorMessages", messages);
		((HttpServletResponse) response).sendRedirect("./");
		return;

	} else if ((uri.equals(contextPath + "/signup") || uri.equals(contextPath + "/signup.jsp") )&& !user.getPositionId().equals("1")){
		// ユーザー登録画面への人事部アカウント以外のアクセス禁止
		List<String> messages = new ArrayList<String>();
		messages.add("当該ページへのアクセス権限がありません");
		session.setAttribute("errorMessages", messages);
		((HttpServletResponse) response).sendRedirect("./");
		return;
			}
		// サーブレットの実行
		chain.doFilter(request, response);

	}

}