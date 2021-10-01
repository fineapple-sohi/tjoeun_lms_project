package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.tjoeun.util.MyOracle;

public class GuestDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static final int GUEST_AUTH=0;
	
	public void insertGuest(String guestId, String guestName, String guestTel, String guestEmail,
			String guestAddr, String guestPw) {
		String sql="insert into Guest values (?, ?, ?, ?, ?, ?)";
		String sql1="insert into Member values (?, ?, ?)";
		boolean isIdValid=true;
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, guestId);
			pstmt.setString(2, guestPw);
			pstmt.setInt(3, GUEST_AUTH);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			isIdValid=false;
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //try-catch-finally1
		if(!isIdValid) return;
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, guestId);
			pstmt.setString(2, guestName);
			pstmt.setString(3, guestTel);
			pstmt.setString(4, guestEmail);
			pstmt.setString(5, guestAddr);
			pstmt.setString(6, guestPw);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //try-catch-finally
	} //insertGuest
	
	public GuestDto selectGuest(String guestId) {
		GuestDto bean = new GuestDto();
		String sql="select Guest_Id, Guest_Name, Guest_Tel, Guest_Email, Guest_Addr, Guest_Pw from Guest where Guest_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, guestId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bean.setGuestId(rs.getString("Guest_Id"));
				bean.setGuestName(rs.getString("Guest_Name"));
				bean.setGuestTel(rs.getString("Guest_Tel"));
				bean.setGuestEmail(rs.getString("Guest_Email"));
				bean.setGuestAddr(rs.getString("Guest_Addr"));
				bean.setGuestPw(rs.getString("Guest_Pw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //try-catch-finally
		return bean;
	} //selectGuest
	
	public void updateGuest(String guestId, String guestTel, String guestEmail, String guestAddr, String guestPw) {
		String sql="update Guest set guest_Tel=?, guest_Email=?, guest_Addr=?, guest_Pw=? where guest_Id=?";
		String sql1="update Member set Member_Pw=? where Member_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, guestTel);
			pstmt.setString(2, guestEmail);
			pstmt.setString(3, guestAddr);
			pstmt.setString(4, guestPw);
			pstmt.setString(5, guestId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //try-catch-finally
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, guestPw);
			pstmt.setString(2, guestId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} //try-catch-finally1
	} //updateGuest

}
