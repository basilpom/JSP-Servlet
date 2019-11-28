package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.ReplyVO;
import com.seayan.dao.BoardDAO;

public class ReplyDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		BoardDAO bDao = BoardDAO.getInstance();
		ReplyVO rVo = bDao.selectOneReplyByNo(no);
		String pNum = Integer.toString(rVo.getPnum());
		bDao.deleteReply(no);
		  
		request.setAttribute("num", pNum);
		new BoardViewAction().execute(request, response);
		// Servlet에서 보낼 때는 parameter가 아닌 attribute 사용하기!!
//		String url = "BoardServlet?command=board_view&num="+pnum;
//		System.out.println("url : " + url);
//		response.sendRedirect(url);
	}

}
