package com.seayan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BannedWordsVO;

import util.DBManager;

public class BannedWordsDAO {
	private BannedWordsDAO() {
	}
	private static BannedWordsDAO instance = new BannedWordsDAO();
	public static BannedWordsDAO getInstance() {
		return instance;
	}
	
	public List<BannedWordsVO> bannedWords() {
		String sql = "select * from banned_words";
		List<BannedWordsVO> banList = new ArrayList<BannedWordsVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				BannedWordsVO bwVO = new BannedWordsVO();
				bwVO.setKeyword(rs.getString("keyword"));
				banList.add(bwVO);
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
		return banList;
	}
	
//	public void wordsChecker(BoardVO bVo)
//	{
//		BannedWordsDAO bwDao = BannedWordsDAO.getInstance();
//		List<BannedWordsVO> banList = bwDao.bannedWords();
//		String sql = "select count(*) from "
//	}
//	
}
