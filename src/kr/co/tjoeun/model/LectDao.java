package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.tjoeun.util.MyOracle;

public class LectDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	public void insertLect(String lectCode, String lectName,String lectTeacherId,
			int lectTotalNumStus, Date lectBeginDate, Date lectEndDate,
			int lectNumNaljas, int lectBeginTime, int lectEndTime, 
			String lectThumbName) {
		String sql="insert into Lect values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lectCode);
			pstmt.setString(2, lectName);
			pstmt.setString(3, lectTeacherId);
			pstmt.setInt(4, lectTotalNumStus);
			pstmt.setDate(5, lectBeginDate);
			pstmt.setDate(6, lectEndDate);
			pstmt.setInt(7, lectNumNaljas);
			pstmt.setInt(8, lectBeginTime);
			pstmt.setInt(9, lectEndTime);
			pstmt.setString(10, lectThumbName);
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
	} //insertLect
	
	public LectDto selectLect(String lectCode) {
		LectDto bean = new LectDto();
		String sql="select * from Lect where Lect_Code=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lectCode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bean.setLectCode(rs.getString("Lect_Code"));
				bean.setLectName(rs.getString("Lect_Name"));
				bean.setLectTeacher(new TeacherDao().selectTeacher(rs.getString("Lect_StafId")));
				bean.setLectTotalNumStus(rs.getInt("Lect_TotalNumStus"));
				bean.setLectBeginDate(rs.getDate("Lect_BeginDate"));
				bean.setLectEndDate(rs.getDate("Lect_EndDate"));
				bean.setLectNumNaljas(rs.getInt("Lect_NumNaljas"));
				bean.setLectBeginTime(rs.getInt("Lect_BeginTime"));
				bean.setLectEndTime(rs.getInt("Lect_EndTime"));
				bean.setLectThumbName(rs.getString("Lect_ThumbName"));
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
	} //selectLect
	
	public void updateLect(String lectCode, String lectName, String lectTeacherId,
			int lectNumStus, Date lectBeginDate, Date lectEndDate, int lectNumNaljas,
			int lectBeginTime, int lectEndTime, String lectThumbName) {
		String sql="update Lect set Lect_Name=?, Lect_StafId=?, "
				+ "Lect_TotalNumStus=?, Lect_BeginDate=?, Lect_EndDate=?, "
				+ "Lect_NumNaljas=?, Lect_BeginTime=?, Lect_EndTime=?, Lect_ThumbName=? "
				+ "where Lect_Code=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, lectName);
			pstmt.setString(2, lectTeacherId);
			pstmt.setInt(3, lectNumStus);
			pstmt.setDate(4, lectBeginDate);
			pstmt.setDate(5, lectEndDate);
			pstmt.setInt(6, lectNumNaljas);
			pstmt.setInt(7, lectBeginTime);
			pstmt.setInt(8, lectEndTime);
			pstmt.setString(9, lectThumbName);
			pstmt.setString(10, lectCode);
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
	} //updateLect

}
