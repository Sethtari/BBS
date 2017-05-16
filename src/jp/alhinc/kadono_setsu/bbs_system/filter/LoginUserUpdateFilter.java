

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

import jp.alhinc.kadono_setsu.bbs_system.beans.User;
import jp.alhinc.kadono_setsu.bbs_system.service.UserService;

@WebFilter("/*")
public class LoginUserUpdateFilter implements Filter {

	public LoginUserUpdateFilter() {
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
		UserService userService = new UserService();
		String uri = req.getRequestURI();

		if(!uri.matches(".*css.*") && !(user == null)){
			User updateUser = userService.getUser(user.getId());

			if(updateUser == null){
				session.removeAttribute("user");
			} else {
				session.setAttribute("loginUser", updateUser);

			}
		}

		// サーブレットの実行
		chain.doFilter(request, response);

	}

}
