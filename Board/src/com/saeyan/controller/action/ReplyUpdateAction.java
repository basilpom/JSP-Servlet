package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.ReplyVO;
import com.seayan.dao.BoardDAO;

public class ReplyUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyVO rVo = new ReplyVO();
		rVo.setNo(Integer.parseInt(request.getParameter("no")));
		rVo.setName(request.getParameter("name"));
		rVo.setPassword(request.getParameter("password"));
		rVo.setContent(request.getParameter("content"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateReply(rVo);
		System.out.println("Update done");
		
		String pNum = request.getParameter("pNum");
		String url = "BoardServlet?command=board_view&num="+pNum;
		System.out.println("url : " + url);
		response.sendRedirect(url);
		
	}

}
