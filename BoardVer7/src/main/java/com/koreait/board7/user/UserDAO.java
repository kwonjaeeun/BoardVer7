package com.koreait.board7.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.koreait.board7.MyUtil;

public class UserDAO {

	public static int searchId(String uid) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try {
			con=MyUtil.connect();
			pstmt=con.prepareStatement("select uid from t_user where uid= ?");
			pstmt.setString(1, uid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyUtil.close(con, pstmt, rs);
		}
		return 0;
	}

	public static UserEntity selUser(UserEntity vo) {
		Connection con =null;
		PreparedStatement ps=null;
		ResultSet rs= null;
		UserEntity result=null;
			
		try {
			con=MyUtil.connect();
			ps=con.prepareStatement("Select iuser, uid,upw,unm from t_user where uid=?");
			ps.setString(1, vo.getUid());
			rs=ps.executeQuery();
			if(rs.next()) {
				int iuser= rs.getInt("iuser");
				String uid= rs.getString("uid");
				String upw= rs.getString("upw");
				String unm= rs.getString("unm");
				result=new UserEntity();
				result.setIuser(iuser);
				result.setUid(uid);
				result.setUpw(upw);
				result.setUnm(unm);
			}
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

}
