package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.tjoeun.util.MyOracle;

public class TeacherDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static final int TEACHER_AUTH=2;
	
	public void insertTeacher(String teacherId, String teacherName,
			String teacherTel, String teacherEmail, String teacherAddr,
			String teacherPw) {
		String sql="insert into Staf values (?, ?, ?, ?, ?, ?, ?)";
		String sql1="insert into Member values (?, ?, ?)";
		boolean isIdValid=true;
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, teacherId);
			pstmt.setString(2, teacherPw);
			pstmt.setInt(3, TEACHER_AUTH);
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
			pstmt.setString(1, teacherId);
			pstmt.setString(2, teacherName);
			pstmt.setString(3, teacherTel);
			pstmt.setString(4, teacherEmail);
			pstmt.setString(5, teacherAddr);
			pstmt.setString(6, teacherPw);
			pstmt.setString(7, "teacher");
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
		
	} //insertTeacher
	
	public TeacherDto selectTeacher(String teacherId) {
		TeacherDto bean = new TeacherDto();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from Staf where Staf_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bean.setTeacherId(rs.getString("Staf_Id"));
				bean.setTeacherName(rs.getString("Staf_Name"));
				bean.setTeacherTel(rs.getString("Staf_Tel"));
				bean.setTeacherEmail(rs.getString("Staf_Email"));
				bean.setTeacherAddr(rs.getString("Staf_Addr"));
				bean.setTeacherPw(rs.getString("Staf_Pw"));
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
	} //selectTeacher
	
	public ArrayList<TeacherDto> selectTeacherList(){
		ArrayList<TeacherDto> list = new ArrayList<TeacherDto>();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from Staf where Staf_DepCode=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "teacher");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				TeacherDto bean = new TeacherDto();
				bean.setTeacherId(rs.getString("Staf_Id"));
				bean.setTeacherName(rs.getString("Staf_Name"));
				bean.setTeacherTel(rs.getString("Staf_Tel"));
				bean.setTeacherEmail(rs.getString("Staf_Email"));
				bean.setTeacherAddr(rs.getString("Staf_Addr"));
				bean.setTeacherPw(rs.getString("Staf_Pw"));
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
	} //selectTeacherList
	
	public ArrayList<TeacherDto> selectFreeTeacherList(){
		ArrayList<TeacherDto> list = new ArrayList<TeacherDto>();
		String sql="select Staf_Id, Staf_Name, Staf_Tel, Staf_Email, Staf_Addr, Staf_Pw from "
				+ "Staf left outer join Lect on Staf_Id=Lect_StafId from Staf where "
				+ "Staf_DepCode=? and Lect_Code is null";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "teacher");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				TeacherDto bean = new TeacherDto();
				bean.setTeacherId(rs.getString("Staf_Id"));
				bean.setTeacherName(rs.getString("Staf_Name"));
				bean.setTeacherTel(rs.getString("Staf_Tel"));
				bean.setTeacherEmail(rs.getString("Staf_Email"));
				bean.setTeacherAddr(rs.getString("Staf_Addr"));
				bean.setTeacherPw(rs.getString("Staf_Pw"));
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
	} //selectFreeTeacherList
	
	public void updateTeacher(String teacherId, String teacherTel,
			String teacherEmail, String teacherAddr, String teacherPw) {
		String sql="update Staf set Staf_Tel=?, Staf_Email=?, Staf_Addr=?, Staf_Pw=? where Staf_Id=? and Staf_DepCode=?";
		String sql1="update Member set Member_Pw=? where Member_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherTel);
			pstmt.setString(2, teacherEmail);
			pstmt.setString(3, teacherAddr);
			pstmt.setString(4, teacherPw);
			pstmt.setString(5, teacherId);
			pstmt.setString(6, "teacher");
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
			pstmt.setString(1, teacherPw);
			pstmt.setString(2, teacherId);
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
		} //try-catch-finally1
	} //updateTeacher
	
}
