package kr.co.tjoeun.model;

 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.tjoeun.util.MyOracle;

public class AttendDao {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static final int IN=0;
	private static final int OUT=1;

	public void sendSignal(String attendStuId, int attendIsBeginChecked) {

		int result=0;
		// idx, nalja, id, lectcode, begintime,0(입실체크여부-강사승인여부),퇴실시간(null-blank라서 테이블명세에서는 notnull), 0(퇴실체크여부-강사승인여부),
		String sql="insert into Attend values (Attend_Seq.nextval,sysdate,?,?,sysdate,0,null,0)";
		String sql2="update Attend set Attend_EndTime=? where Attend_StuId=? and Attend_Nalja=sysdate";
		String lectCode="";
		lectCode=new kr.co.tjoeun.model.StuDao().selectStu(attendStuId).getStuLect().getLectCode();
		// 학생이->교사한테 보내는 함수(0 입실, 1 퇴실)

		try {
			conn=MyOracle.getConnection();
			
			if(attendIsBeginChecked==IN) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, attendStuId);
				pstmt.setString(2,lectCode);
				result=pstmt.executeUpdate();

			}else {
				pstmt=conn.prepareStatement(sql2);
				pstmt.setInt(2, attendIsBeginChecked);
				result=pstmt.executeUpdate();
			}

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
	} //sendSignal

}