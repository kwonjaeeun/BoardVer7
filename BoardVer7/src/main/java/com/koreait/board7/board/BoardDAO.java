package com.koreait.board7.board;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.koreait.board7.MyUtil;

public class BoardDAO {
	public static List<BoardDomain> printlist(BoardDTO param) {
		List<BoardDomain> list = new ArrayList<BoardDomain>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDomain vo = null;
		try {
			conn = MyUtil.connect();
			String pstmtctnt="select A.iboard,A.title,A.ctnt,B.unm as writerNm, left(A.regdt,10) from t_board A inner JOIN t_user B ON A.iuser=B.iuser where ";
			switch (param.getSearchType()) {
			case 1:
				pstmtctnt+=" title like '%"+param.getSearchText()+"%' or ctnt ";
				break;
			case 2:
				pstmtctnt+=" title ";
				break;
			case 3:
				pstmtctnt+= " ctnt ";
				break;
			case 4:
				pstmtctnt+= " B.unm ";
				break;
			}
			pstmtctnt+="like ? order by A.iboard desc limit ? , ?";
			pstmt = conn.prepareStatement(pstmtctnt);
			pstmt.setString(1, "%" + param.getSearchText() + "%");
			pstmt.setInt(2, param.getStartIdx());
			pstmt.setInt(3, param.getRecordCnt());
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

	public static int getAllPage(BoardDTO param) {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyUtil.connect();
			String pstmtctnt="select ceil(count(*)/?) from t_board A inner JOIN t_user B ON A.iuser=B.iuser where ";
			switch (param.getSearchType()) {
			case 1:
				pstmtctnt+=" title like '%"+param.getSearchText()+"%' or ctnt ";
				break;
			case 2:
				pstmtctnt+=" title ";
				break;
			case 3:
				pstmtctnt+= " ctnt ";
				break;
			case 4:
				pstmtctnt+= " B.unm ";
				break;
			}
			pstmtctnt+="like ?";
			pstmt = conn.prepareStatement(pstmtctnt);
			pstmt.setInt(1, param.getRecordCnt());
			pstmt.setString(2, "%" + param.getSearchText() + "%");
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			return result;
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
		} finally {
			MyUtil.close(conn, pstmt, rs);
		}
		return result;
	}

	public static BoardDomain selBoard(BoardEntity param) {
		BoardDomain vo = null;
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
				vo = new BoardDomain();
				vo.setIboard(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setCtnt(rs.getString(3));
				vo.setWriterNm(rs.getString(4));
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

	public static int insert(BoardEntity vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement("insert into t_board(title,ctnt,iuser) values(?,?,?)");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getCtnt());
			pstmt.setInt(3, vo.getIuser());

			return pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
			return 0;
		} finally {
			MyUtil.close(conn, pstmt);
		}
	}

	public static BoardDomain printdetail(int iboard) {
		BoardDomain vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement(
					"select A.iboard,A.title,A.ctnt,B.unm,A.Regdt,A.iuser from t_board A LEFT JOIN t_user B ON A.iuser=B.iuser where iboard=?");
			pstmt.setInt(1, iboard);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardDomain();
				vo.setIboard(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setCtnt(rs.getString(3));
				vo.setWriterNm(rs.getString(4));
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
	
	public static int modBoard(BoardEntity vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement("update t_board set title=?,ctnt=? where iboard=? and iuser=?");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getCtnt());
			pstmt.setInt(3, vo.getIboard());
			pstmt.setInt(4, vo.getIuser());
			return pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
			return 0;
		} finally {
			MyUtil.close(conn, pstmt);
		}

	}
	
	@SuppressWarnings("resource")
	public static int likechng(BoardEntity vo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement(
					"select * from like_board where iboard=? and iuser=?");
			pstmt.setInt(1, vo.getIboard());
			pstmt.setInt(2, vo.getIuser());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt = conn.prepareStatement(
						"delete from like_board where iboard=? and iuser=?");
				pstmt.setInt(1, vo.getIboard());
				pstmt.setInt(2, vo.getIuser());
				pstmt.executeUpdate();
				return 0;
			}else {
				pstmt = conn.prepareStatement(
						"insert into like_board values(?,?)");
				pstmt.setInt(1, vo.getIboard());
				pstmt.setInt(2, vo.getIuser());
				pstmt.executeUpdate();
				return 1;
			}
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
		} finally {
			MyUtil.close(conn, pstmt, rs);
		}
		return result;
	
	}
	
	
	public static int delBoard(BoardEntity vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement("delete from t_board where iboard=? and iuser=?");
			pstmt.setInt(1, vo.getIboard());
			pstmt.setInt(2, vo.getIuser());
			return pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
			return 0;
		} finally {
			MyUtil.close(conn, pstmt);
		}
	}

	public static int likeck(BoardEntity vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyUtil.connect();
			pstmt = conn.prepareStatement(
					"select * from like_board where iboard=? and iuser=?");
			pstmt.setInt(1, vo.getIboard());
			pstmt.setInt(2, vo.getIuser());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
			return 0;
		} catch (Exception ex) {
			System.out.println("오류발생:" + ex);
			return 0;
		} finally {
			MyUtil.close(conn, pstmt, rs);
		}
	}

	
	
}
