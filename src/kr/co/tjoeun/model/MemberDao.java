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
	
	public int login(String id, String pw) {
		int cnt=0;
		String sql="select count(*) from guest where guest_id=? and guest_pw=?";
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) cnt=rs.getInt(1);
		
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

		if(cnt>0) return 1;
		return -1;

		
	}
	
	
}
