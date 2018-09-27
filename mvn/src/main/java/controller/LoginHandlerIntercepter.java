package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginHandlerIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		String url = arg0.getRequestURI(); 
		if(url.indexOf("login.do")>=0){
			return true;
		}
		if(url.indexOf("asset")>=0){
			return true;
		}
		HttpSession session = arg0.getSession();
		String username = (String) session.getAttribute("username");
		if(username != null){
			return true;
		}
		arg1.sendRedirect(arg0.getContextPath() +"/user/login.do");
//		arg0.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(arg0, arg1);
		return false;
	}

}
