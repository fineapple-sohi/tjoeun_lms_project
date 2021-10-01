package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.tjoeun.util.MyOracle;

public class StuDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static final int STU_AUTH=1;
	
	public void insertStu(String stuId, String stuName, String stuTel, 
			String stuEmail, String stuAddr, String stuPw, 
			String stuLectCode) {
		String sql="insert into Stu values (?, ?, ?, ?, ?, ?, ?)";
		String sql1="insert into Eval values (Eval_Seq.nextval,1,?,null,?)";
		String sql2="insert into Eval values (Eval_Seq.nextval,2,?,null,?)";
		String sql3="insert into Eval values (Eval_Seq.nextval,3,?,null,?)";
		String sql4="insert into Eval values (Eval_Seq.nextval,4,?,null,?)";
		String sql5="insert into Member values (?, ?, ?)";
		boolean isIdValid=true;
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql5);
			pstmt.setString(1, stuId);
			pstmt.setString(2, stuPw);
			pstmt.setInt(3, STU_AUTH);
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
		} //try-catch-finally5
		if(!isIdValid) return;
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			pstmt.setString(2, stuName);
			pstmt.setString(3, stuTel);
			pstmt.setString(4, stuEmail);
			pstmt.setString(5, stuAddr);
			pstmt.setString(6, stuPw);
			pstmt.setString(7, stuLectCode);
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
			pstmt.setString(1, stuId);
			pstmt.setString(2, stuLectCode);
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
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, stuId);
			pstmt.setString(2, stuLectCode);
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
		} //try-catch-finally2
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql3);
			pstmt.setString(1, stuId);
			pstmt.setString(2, stuLectCode);
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
		} //try-catch-finally3
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql4);
			pstmt.setString(1, stuId);
			pstmt.setString(2, stuLectCode);
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
		} //try-catch-finally4
	} //insertStu
	
	public StuDto selectStu(String stuId) {
		StuDto bean = new StuDto();
		String sql="select Stu_Id, Stu_Name, Stu_Tel, Stu_Email, Stu_Addr, Stu_Pw, Stu_LectCode from Stu where Stu_Id=?";
		String stuLectCode="";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bean.setStuId(rs.getString("Stu_Id"));
				bean.setStuName(rs.getString("Stu_Name"));
				bean.setStuTel(rs.getString("Stu_Tel"));
				bean.setStuEmail(rs.getString("Stu_Email"));
				bean.setStuAddr(rs.getString("Stu_Addr"));
				bean.setStuPw(rs.getString("Stu_Pw"));
				//bean.setStuLect(new LectDao().selectLect(rs.getString("Stu_LectCode")));
				stuLectCode=rs.getString("Stu_LectCode");
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
		
		bean.setStuLect(new LectDao().selectLect(stuLectCode));
		return bean;
	} //selectStu
	
	public ArrayList<StuDto> selectStuList(){
		ArrayList<StuDto> list = new ArrayList<StuDto>();
		String sql="select Stu_Id, Stu_Name, Stu_Tel, Stu_Email, Stu_Addr, Stu_Pw, Stu_LectCode from Stu";
		ArrayList<String> stuLectCodeList = new ArrayList<String>();
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StuDto bean = new StuDto();
				bean.setStuId(rs.getString("Stu_Id"));
				bean.setStuName(rs.getString("Stu_Name"));
				bean.setStuEmail(rs.getString("Stu_Email"));
				bean.setStuAddr(rs.getString("Stu_Addr"));
				bean.setStuPw(rs.getString("Stu_Pw"));
				//bean.setStuLect(new LectDao().selectLect(rs.getString("Stu_LectCode")));
				stuLectCodeList.add(rs.getString("Stu_LectCode"));
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
		
		for(int i=0;i<list.size();i++) {
			String stuLectCode=stuLectCodeList.get(i);
			list.get(i).setStuLect(new LectDao().selectLect(stuLectCode));
		} //i loop (a loop for setting stuLect)
		return list;
	} //selectStuList
	
	public ArrayList<StuDto> selectStuList(String stuLectCode){
		ArrayList<StuDto> list = new ArrayList<StuDto>();
		String sql="select Stu_Id, Stu_Name, Stu_Tel, Stu_Email, Stu_Addr, Stu_Pw, Stu_LectCode from Stu where Stu_LectCode=?";
		ArrayList<String> stuLectCodeList = new ArrayList<String>();
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stuLectCode);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StuDto bean = new StuDto();
				bean.setStuId(rs.getString("Stu_Id"));
				bean.setStuName(rs.getString("Stu_Name"));
				bean.setStuEmail(rs.getString("Stu_Email"));
				bean.setStuAddr(rs.getString("Stu_Addr"));
				bean.setStuPw(rs.getString("Stu_Pw"));
				//bean.setStuLect(new LectDao().selectLect(rs.getString("Stu_LectCode")));
				stuLectCodeList.add(rs.getString("Stu_LectCode"));
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
		
		for(int i=0;i<list.size();i++) {
			String stuLectCode2=stuLectCodeList.get(i);
			list.get(i).setStuLect(new LectDao().selectLect(stuLectCode2));
		} //i loop (a loop for setting stuLect)
		return list;
	} //selectStuList
	
	public void updateStu(String stuId, String stuTel, String stuEmail,
			String stuAddr, String stuPw) {
		String sql="update Stu set Stu_Tel=?, Stu_Email=?, Stu_Addr=?, Stu_Pw=? where Stu_Id=?";
		String sql1="update Member set Member_Pw=? where Member_Id=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stuTel);
			pstmt.setString(2, stuEmail);
			pstmt.setString(3, stuAddr);
			pstmt.setString(4, stuPw);
			pstmt.setString(5, stuId);
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
			pstmt.setString(1, stuPw);
			pstmt.setString(2, stuId);
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
	} //updateStu
	
}
