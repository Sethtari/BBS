package jp.alhinc.kadono_setsu.bbs_system.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FilterForbidden implements Filter {

	public FilterForbidden() {
		System.out.println("LoginFilter");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init");
	}

	public void destroy() {
		System.out.println("destroy");
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

		String contextPath = req.getContextPath();
		String uri = req.getRequestURI();
		String method = req.getMethod();

		System.out.println("アクセス：" + uri + ":" + method);

/*		if (uri.equals(contextPath + "/login.jsp") || uri.equals(contextPath + "/login")) {

			// ログイン処理時はなにもしない
		} else if (session == null || session.getAttribute("login") == null) {

			// セッションが切れたらログイン画面に戻る
			request.setAttribute("msg", "セッションが切れました");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		}
*/
		// 時間計測開始
		long start = System.currentTimeMillis();

		// サーブレットの実行
		chain.doFilter(request, response);

		// 時間計測終了
		long end = System.currentTimeMillis();
		System.out.println("処理時間：" + (end - start) + "ms");

		// 遷移先の文字コード指定
		response.setCharacterEncoding(encording);
	}

}