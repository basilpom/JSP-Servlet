package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.BoardVO;
import com.seayan.dao.BoardDAO;

public class BoardViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardView.jsp";
		String num = request.getParameter("num");
		BoardDAO bDao = BoardDAO.getInstance();
		
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies)
		{
			System.out.println("coockie : "+c.getValue());
			if(c.getName().equals("num") && !c.getValue().equals(num))
			{
				System.out.println("조회수증가");
				bDao.updateReadCount(num);
			}
		}
		
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		request.setAttribute("board", bVo);
		
		// 새로고침시 조회수 증가 막기 위한 쿠키
		Cookie c = new Cookie("num", num);
		response.addCookie(c);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
