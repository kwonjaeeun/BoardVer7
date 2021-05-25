package com.koreait.board7.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.koreait.board7.MyUtil;


@WebServlet("/board/del")
public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (MyUtil.getUser("loginUser", request)==null) {
			response.sendRedirect("/board/list");
		}
		BoardEntity vo = new BoardEntity();
		vo.setIboard(MyUtil.ToIntParam("iboard", request));
		vo.setIuser(MyUtil.getUser("loginUser", request).getIuser());
		BoardDAO.delBoard(vo);
		response.sendRedirect("list");
	}
	

}
