package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.ReplyVO;
import com.seayan.dao.BoardDAO;

public class ReplyWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyVO rVo = new ReplyVO();
		rVo.setPnum(Integer.parseInt(request.getParameter("pNum")));
		rVo.setName(request.getParameter("name"));
		rVo.setPassword(request.getParameter("password"));
		rVo.setContent(request.getParameter("content"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertReply(rVo);
		
		request.setAttribute("num", request.getParameter("pNum"));
		
		new BoardViewAction().execute(request, response);
	}

}
