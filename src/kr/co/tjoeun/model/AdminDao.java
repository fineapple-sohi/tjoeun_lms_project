package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.tjoeun.util.MyOracle;

public class AdminDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;

	public void insertAdmin(String adminId, String adminName,
			String adminTel, String adminEmail, String adminAddr, 
			String adminPw) {
		String sql="insert into Staf values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, adminName);
			pstmt.setString(3, adminTel);
			pstmt.setString(4, adminEmail);
			pstmt.setString(5, adminAddr);
			pstmt.setString(6, adminPw);
			pstmt.setString(7, "admin");
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
	} //insertAdmin
	
	public AdminDto selectAdmin(String adminId) {
		AdminDto bean = new AdminDto();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from Staf where Staf_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bean.setAdminId(rs.getString("Staf_Id"));
				bean.setAdminName(rs.getString("Staf_Name"));
				bean.setAdminTel(rs.getString("Staf_Tel"));
				bean.setAdminEmail(rs.getString("Staf_Email"));
				bean.setAdminAddr(rs.getString("Staf_Addr"));
				bean.setAdminPw(rs.getString("Staf_Pw"));
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
	} //selectAdmin
	
	public ArrayList<AdminDto> selectAdminList(){
		ArrayList<AdminDto> list = new ArrayList<AdminDto>();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from Staf";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AdminDto bean = new AdminDto();
				bean.setAdminId(rs.getString("Staf_Id"));
				bean.setAdminName(rs.getString("Staf_Name"));
				bean.setAdminTel(rs.getString("Staf_Tel"));
				bean.setAdminEmail(rs.getString("Staf_Email"));
				bean.setAdminAddr(rs.getString("Staf_Addr"));
				bean.setAdminPw(rs.getString("Staf_Pw"));
				list.add(bean);
			} //while loop
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
		return list;
	} //selectAdminList
	
	public void updateAdmin(String adminId, String adminTel, 
			String adminEmail, String adminAddr, String adminPw) {
		String sql="update Staf set Staf_Tel=?, Staf_Email=?, Staf_Addr=?, Staf_Pw=? where Staf_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, adminTel);
			pstmt.setString(2, adminEmail);
			pstmt.setString(3, adminAddr);
			pstmt.setString(4, adminPw);
			pstmt.setString(5, adminId);
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
	} //updateAdmin

}
