package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.tjoeun.util.MyOracle;

public class MemberDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static MemberDao mbDao;

	
	private MemberDao() {
		
	}
	
	public static synchronized MemberDao getInstance() {
		if (mbDao == null) {
			mbDao = new MemberDao();
		}
		return mbDao;
	}
	
	public int login(String memberId, String memberPw) {
		int authNum=-1;
		String sql="select member_auth from member where member_id=? and member_pw=?";
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) authNum = rs.getInt(1);
			System.out.println(authNum);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}

		
		return authNum;
//		if(authNum > 0) {
//			return authNum;		
//		}
//		return -1;

		
	}
	
	
}
