package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.BannedWordsVO;
import com.saeyan.dto.BoardVO;
import com.seayan.dao.BannedWordsDAO;
import com.seayan.dao.BoardDAO;

public class BoardUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardUpdate.jsp";
		String num = request.getParameter("num");
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		BannedWordsDAO bwDao = BannedWordsDAO.getInstance();
		List<BannedWordsVO> banList = bwDao.bannedWords();
		
		request.setAttribute("board", bVo);
		request.setAttribute("banList", banList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
