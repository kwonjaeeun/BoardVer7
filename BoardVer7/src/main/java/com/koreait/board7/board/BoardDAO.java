package com.koreait.board7.board;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.koreait.board7.MyUtil;


public class BoardDAO {
	public static List<BoardDomain> printlist(BoardEntity param) {
		List<BoardDomain> list = new ArrayList<BoardDomain>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDomain vo = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement(
					"select A.iboard,A.title,A.ctnt,B.unm as writerNm, left(A.regdt,10) from t_board A inner JOIN t_user B ON A.iuser=B.iuser where title like ? order by A.iboard desc limit ? , ? ");
			pstmt.setString(1, "%"+param.getSearch()+"%");
			pstmt.setInt(2, param.getsIdx());
			pstmt.setInt(3, param.getPage());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardDomain();
				vo.setIboard(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setCtnt(rs.getString(3));
				vo.setWriterNm(rs.getString(4));
				vo.setRegdt(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
		} finally {
			MyUtil.close(conn, pstmt, rs);
		}
		return list;
	}

	public static int getAllPage(BoardEntity param) {

		int result=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardEntity vo = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement(
					"select ceil(count(*)/?) from t_board where title like ?");
			pstmt.setInt(1, param.getPage());
			pstmt.setString(2, "%"+param.getSearch()+"%");
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getInt(1);
			return result;
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
		} finally {
			MyUtil.close(conn, pstmt, rs);
		}
		return result;
	}

	public static BoardEntity selBoard(BoardEntity param) {
		BoardEntity vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement(
					"select A.iboard,A.title,A.ctnt,B.unm,A.Regdt,A.iuser from t_board A LEFT JOIN t_user B ON A.iuser=B.iuser where iboard=?");
			pstmt.setInt(1, param.getIboard());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardEntity();
				vo.setIboard(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setCtnt(rs.getString(3));
				vo.setUnm(rs.getString(4));
				vo.setRegdt(rs.getString(5));
				vo.setIuser(rs.getInt(6));
			}
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
		} finally {
			MyUtil.close(conn, pstmt, rs);
		}
		return vo;
	}

	public static void insert(BoardEntity vo) {
		// TODO Auto-generated method stub
		
	}
	
}
