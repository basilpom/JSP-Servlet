package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.ReplyVO;
import com.seayan.dao.BoardDAO;

public class BoardViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardView.jsp";
//		String num = request.getParameter("num");
		String num = null;
		// 목록에서 들어올 때
		if(request.getParameter("num") != null)
		{
			num = request.getParameter("num");
		}
		// 댓글 작성 후
		if(request.getAttribute("num") != null)
		{
			num = (String)request.getAttribute("num");
		}
		// 댓글의 부모 번호 = 현재 페이지 글 번호 
		int pNum = Integer.parseInt(num);
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		// 쿠키에 현재 페이지 정보가 있는지 확인 (조회수 증가)
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
		// 새로고침시 조회수 증가 막기 위한 쿠키 추가
		Cookie c = new Cookie("num", num);
		response.addCookie(c);
		// 게시글 상세정보
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		request.setAttribute("board", bVo);
		// 댓글목록
		List<ReplyVO> replyList = bDao.selectAllReplys(pNum);
		request.setAttribute("replyList", replyList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
