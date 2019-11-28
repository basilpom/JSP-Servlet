package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.BoardVO;
import com.seayan.dao.BoardDAO;

public class BoardListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageno = 1;	// list에 처음 접근할 때
		if(request.getParameter("pageno") != null)
		{
			pageno = Integer.parseInt(request.getParameter("pageno"));
		}

		String url = "/board/boardList.jsp";
		// 상세보기 페이지로 넘겨주기 위한 쿠키
		Cookie c = new Cookie("num", "");
		response.addCookie(c);
		
		BoardDAO bDao = BoardDAO.getInstance();
		List<BoardVO> boardList = bDao.selectAllBoards(pageno);
		int recordCount = bDao.selectCountBoard();
		request.setAttribute("boardList", boardList);
		request.setAttribute("recordCount", recordCount);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
