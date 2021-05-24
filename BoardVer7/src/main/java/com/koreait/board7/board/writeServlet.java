package com.koreait.board7.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board7.MyUtil;
import com.koreait.board7.user.UserEntity;


@WebServlet("/board/write")
public class writeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MyUtil.getUser("loginUser", request)==null) {
			response.sendRedirect("/user/login");
		}
		MyUtil.openJSP("write","/board/write", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserEntity user = MyUtil.getUser("loginUser", request);
		BoardEntity vo= new BoardEntity();
		vo.setTitle(request.getParameter("title"));
		vo.setCtnt(request.getParameter("ctnt"));
		vo.setIuser(user.getIuser());
		
		BoardDAO.insert(vo);
		response.sendRedirect("list");
		
	}

}
