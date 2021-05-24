package com.koreait.board7.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.koreait.board7.MyUtil;


@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String search=request.getParameter("search");
		if(search==null) {
			search="";
		}
		int page=0;
		if(request.getParameter("page")!=null) {	
			page=MyUtil.ToIntParam("page", request);
		}else {
			page=1;
		}
		int recordCnt=3;
		int sIdx=(page-1)*recordCnt;
		
		response.setCharacterEncoding("UTF-8");
		BoardEntity param = new BoardEntity();
		param.setSearch(search);
		param.setsIdx(sIdx);
		param.setPage(recordCnt);
		
		request.setAttribute("list", BoardDAO.printlist(param));
		request.setAttribute("totalPage", BoardDAO.getAllPage(param));
    	MyUtil.openJSP("List", "board/list", request, response);
	}

}
