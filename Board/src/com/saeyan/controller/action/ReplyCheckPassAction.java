package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.ReplyVO;
import com.seayan.dao.BoardDAO;

public class ReplyCheckPassAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/replyCheckPass.jsp";
//		String pNum = request.getParameter("pNum");
		String no = request.getParameter("no");
		String password = request.getParameter("password");

		BoardDAO bDao = BoardDAO.getInstance();
		ReplyVO rVo = bDao.selectOneReplyByNo(no);
		if(rVo.getPassword().equals(password))
		{
			url = "board/replyCheckSuccess.jsp";
		}
		else
		{
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
