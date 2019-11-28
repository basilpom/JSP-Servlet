package com.seayan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.ReplyVO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {
	}
	
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	// 전체 레코드 갯수 조회
	public int selectCountBoard() {
		String sql = "select count(*) as recordCount from board";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int recordCount = 0;
		try
		{
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				recordCount = rs.getInt("recordCount");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, stmt, rs);
		}
		
		return recordCount;
	}
	// 모든 게시글 불러오기
	public List<BoardVO> selectAllBoards(int pageno){
//		String sql = "select * from board order by num desc";
		String sql = "select X.* "
				   + " from ( "
				   + "	select rownum as rnum, A.* "
				   + "	from (select * from board order by num desc) A "
				   + "	where rownum <= ?) X "
				   + "where X.rnum >= ?";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageno*10);
			pstmt.setInt(2, (pageno-1)*10+1);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// reply table의 전체 레코드 조회
	public List<ReplyVO> selectAllReplys(int pNum){
		String sql = "select * from reply where pNum=? order by no";
		
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ReplyVO rVo = new ReplyVO();
				rVo.setNo(rs.getInt("no"));
				rVo.setPnum(rs.getInt("pNum"));
				rVo.setName(rs.getString("name"));
				rVo.setPassword(rs.getString("password"));
				rVo.setContent(rs.getString("content"));
				rVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(rVo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	// 게시글 작성
	public void insertBoard(BoardVO bVo)
	{
		String sql = "insert into board values(board_seq.nextval, "
				   + "?, ?, ?, ?, ?, default, default)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getPass());
			pstmt.setString(2, bVo.getName());
			pstmt.setString(3, bVo.getEmail());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
	}
	// 댓글 작성
	public void insertReply(ReplyVO rVo)
	{
		String sql = "insert into reply values(reply_seq.nextval, ?, ?, ?, ?, default)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rVo.getPnum());
			pstmt.setString(2, rVo.getName());
			pstmt.setString(3, rVo.getPassword());
			pstmt.setString(4, rVo.getContent());
			pstmt.executeQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
	}
	// 게시글 한 건 불러오기.
	public BoardVO selectOneBoardByNum(String num)
	{
		String sql = "select * from board where num=?";
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setReadcount(rs.getInt("readcount"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}
	// 조회수 증가
	public void updateReadCount(String num)
	{
		String sql = "update board set readcount=readcount+1 where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
	}
	// 댓글 한 건 불러오기
	public ReplyVO selectOneReplyByNo(String no)
	{
		String sql = "select * from reply where no=?";
		ReplyVO rVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				rVo = new ReplyVO();
				rVo.setNo(rs.getInt("no"));
				rVo.setPnum(rs.getInt("pNum"));
				rVo.setName(rs.getString("name"));
				rVo.setPassword(rs.getString("password"));
				rVo.setContent(rs.getString("content"));
				rVo.setWritedate(rs.getTimestamp("writedate"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt, rs);
		}
		
		return rVo;
		
	}
	
//	public BoardVO checkPassWord(String pass, String num)
//	{
//		String sql = "select * from board where pass=? and num=?";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		BoardVO bVo = null;
//		try
//		{
//			conn = DBManager.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, pass);
//			pstmt.setString(2, num);
//			rs = pstmt.executeQuery();
//			while(rs.next())
//			{
//				bVo = new BoardVO();
//				bVo.setNum(rs.getInt("num"));
//				bVo.setPass(rs.getString("pass"));
//				bVo.setName(rs.getString("name"));
//				bVo.setEmail(rs.getString("email"));
//				bVo.setTitle(rs.getString("title"));
//				bVo.setContent(rs.getString("content"));
//				bVo.setWritedate(rs.getTimestamp("writedate"));
//				bVo.setReadcount(rs.getInt("readcount"));
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			DBManager.close(conn, pstmt, rs);
//		}
//		return bVo;
//	}
	// 게시글 수정
	public void updateBoard(BoardVO bVo)
	{
		String sql = "update board set name=?, email=?, pass=?, title=?, content=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			pstmt.executeQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
	}
	// 댓글 수정
	public void updateReply(ReplyVO rVo)
	{
		String sql = "update reply set name=?, password=?, content=?, writedate=default where no=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			System.out.println("Here is DAO before update");
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rVo.getName());
			pstmt.setString(2, rVo.getPassword());
			pstmt.setString(3, rVo.getContent());
			pstmt.setInt(4, rVo.getNo());
			pstmt.executeQuery();
			System.out.println("Here is DAO after update");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
		
	}
	// 게시글 삭제
	public void deleteBoard(String num)
	{
		String sql = "delete from board where num=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
	}
	// 댓글 삭제
	public void deleteReply(String no)
	{
		String sql = "delete from reply where no=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.executeQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBManager.close(conn, pstmt);
		}
	}
}
