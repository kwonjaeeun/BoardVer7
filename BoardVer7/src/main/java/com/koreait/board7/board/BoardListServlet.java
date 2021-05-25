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
    	String searchText=request.getParameter("searchText");
    	String searchType=request.getParameter("searchType");
    	int intsT=0;
    	if(searchType==null) {
    		intsT=1; 		
    	}else {
    		if(searchType.equals("")) {
    			intsT=1; 
    		}
    		else {
    			intsT=MyUtil.ToIntParam("searchType", request);
    		}
    	}
    	if(searchText==null) {
			searchText="";
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
		BoardDTO param = new BoardDTO();
		param.setSearchText(searchText);
		param.setStartIdx(sIdx);
		param.setRecordCnt(recordCnt);
		param.setSearchType(intsT);
				
		request.setAttribute("list", BoardDAO.printlist(param));
		request.setAttribute("totalPage", BoardDAO.getAllPage(param));
    	MyUtil.openJSP("List", "board/list", request, response);
	}

}
