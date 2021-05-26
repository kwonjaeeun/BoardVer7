package com.koreait.board7.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import com.koreait.board7.MyUtil;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loginUser")!=null) {
			request.getSession().setAttribute("loginUser", null);
		}
		request.setAttribute("address", request.getHeader("referer"));
		MyUtil.openJSP("login","user/login", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ckpw=request.getParameter("upw");
		UserEntity vo= new UserEntity();
		vo.setUid(request.getParameter("uid"));
		vo=UserDAO.selUser(vo);
		if(vo==null) {
			request.setAttribute("address", request.getParameter("address"));
			request.setAttribute("errmsg", "아이디를 확인해 주세요");
			MyUtil.openJSP("login","user/login", request, response);
			return;
		}else if(BCrypt.checkpw(ckpw, vo.getUpw())) {
			vo.setUpw(null);
			HttpSession hs= request.getSession();
			hs.setAttribute("loginUser", vo);
			response.sendRedirect(request.getParameter("address"));
			return;
		}else {
			request.setAttribute("address", request.getParameter("address"));
			request.setAttribute("errmsg", "비밀번호를 확인해 주세요");
			MyUtil.openJSP("login","user/login", request, response);
			return;
		}
	}

}
