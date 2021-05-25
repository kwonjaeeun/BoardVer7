package com.koreait.board7.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.koreait.board7.MyUtil;


@WebServlet("/board/detail2")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MyUtil.getUser("loginUser", request)==null) {
			response.sendRedirect("/user/login");
			return;
		}
		BoardEntity vo =new BoardEntity();
		vo.setIuser(MyUtil.getUser("loginUser", request).getIuser());
		vo.setIboard(MyUtil.ToIntParam("iboard", request));
		request.setAttribute("likeck",BoardDAO.likeck(vo));
		request.setAttribute("data",BoardDAO.printdetail(MyUtil.ToIntParam("iboard", request)));
		MyUtil.openJSP("detail","/board/detail", request, response);
	}
}
