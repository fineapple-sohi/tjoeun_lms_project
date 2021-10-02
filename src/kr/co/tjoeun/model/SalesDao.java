package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.tjoeun.util.MyOracle;

public class SalesDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static final int SALES_AUTH=3;
	
	public void insertSales(String salesId, String salesName,
			String salesTel, String salesEmail, String salesAddr,
			String salesPw) {
		String sql="insert into Staf values (?, ?, ?, ?, ?, ?, ?)";
		String sql1="insert into Member values (?, ?, ?)";
		boolean isIdValid=true;
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, salesId);
			pstmt.setString(2, salesPw);
			pstmt.setInt(3, SALES_AUTH);
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
			pstmt.setString(1, salesId);
			pstmt.setString(2, salesName);
			pstmt.setString(3, salesTel);
			pstmt.setString(4, salesEmail);
			pstmt.setString(5, salesAddr);
			pstmt.setString(6, salesPw);
			pstmt.setString(7, "sales");
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
	} //insertSales
	
	public SalesDto selectSales(String salesId) {
		SalesDto bean = new SalesDto();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from Staf where Staf_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, salesId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bean.setSalesId(rs.getString("Staf_Id"));
				bean.setSalesName(rs.getString("Staf_Name"));
				bean.setSalesTel(rs.getString("Staf_Tel"));
				bean.setSalesEmail(rs.getString("Staf_Email"));
				bean.setSalesAddr(rs.getString("Staf_Addr"));
				bean.setSalesPw(rs.getString("Staf_Pw"));
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
	} //selectSales
	
	public ArrayList<SalesDto> selectSalesList(){
		ArrayList<SalesDto> list = new ArrayList<SalesDto>();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from Staf where Staf_DepCode=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "sales");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				SalesDto bean = new SalesDto();
				bean.setSalesId(rs.getString("Staf_Id"));
				bean.setSalesName(rs.getString("Staf_Name"));
				bean.setSalesTel(rs.getString("Staf_Tel"));
				bean.setSalesEmail(rs.getString("Staf_Email"));
				bean.setSalesAddr(rs.getString("Staf_Addr"));
				bean.setSalesPw(rs.getString("Staf_Pw"));
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
	} //selectSalesList
	
	public void updateSales(String salesId, String salesTel, 
			String salesEmail, String salesAddr, String salesPw) {
		String sql="update Staf set Staf_Tel=?, Staf_Email=?, Staf_Addr=?, Staf_Pw=? where Staf_Id=? and Staf_DepCode=?";
		String sql1="update Member set Member_Pw=? where Member_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, salesTel);
			pstmt.setString(2, salesEmail);
			pstmt.setString(3, salesAddr);
			pstmt.setString(4, salesPw);
			pstmt.setString(5, salesId);
			pstmt.setString(6, "sales");
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
			pstmt.setString(1, salesPw);
			pstmt.setString(2, salesId);
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
	} //updateSales

}
