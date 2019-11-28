package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.ReplyVO;
import com.seayan.dao.BoardDAO;

public class ReplyUpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String url = "board/replyUpdate.jsp";
//		String no = request.getParameter("no");
//		BoardDAO bDao = BoardDAO.getInstance();
//		ReplyVO rVo = bDao.selectOneReplyByNo(no);
//		BoardVO bVo = bDao.selectOneBoardByNum(Integer.toString(rVo.getPnum()));
//
//		
//		request.setAttribute("reply", rVo);
//		request.setAttribute("board", bVo);
		
		////////////////
		String url = "board/replyUpdate.jsp";
		String no = request.getParameter("no");
		BoardDAO bDao = BoardDAO.getInstance();
		ReplyVO rVo = bDao.selectOneReplyByNo(no);
		String pNum = Integer.toString(rVo.getPnum());
		// 게시글 상세정보
		BoardVO bVo = bDao.selectOneBoardByNum(pNum);
		request.setAttribute("board", bVo);
		// 댓글목록
		List<ReplyVO> replyList = bDao.selectAllReplys(Integer.parseInt(pNum));
		request.setAttribute("replyList", replyList);
		// 수정할 댓글


		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
