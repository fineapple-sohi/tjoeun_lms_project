package kr.co.tjoeun.model; 

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import kr.co.tjoeun.util.MyOracle;

 

public class AttendDao {
   private Connection conn=null;
   private PreparedStatement pstmt=null;
   private ResultSet rs=null;
   private static final int IN=0; //입실
   private static final int OUT=1; //퇴실
   private static final double LUNCH_BEGIN_TIME_HOUR=13.0; //점심 시작 시각
   private static final double LUNCH_END_TIME_HOUR=14.0; //점심 끝 시각
 
   public void sendSignal(String attendStuId, int inOrOutSignal) {
      int result=0;
      // idx, nalja, id, lectcode, begintime,0(입실체크여부-강사승인여부),퇴실시간(null-blank라서 테이블명세에서는 notnull), 0(퇴실체크여부-강사승인여부),
      String sql0="select count(*) from Attend where Attend_StuId=? and trunc(Attend_Nalja)=trunc(sysdate)";
      String sql1="insert into Attend values (Attend_Seq.nextval,sysdate,?,?,sysdate,0,null,0)"; // default 0 0,0
      String sql2="update Attend set Attend_EndTime=sysdate where Attend_StuId=? and trunc(Attend_Nalja)=trunc(sysdate) and Attend_EndTime is null";
      String lectCode="";
      lectCode=new kr.co.tjoeun.model.StuDao().selectStu(attendStuId).getStuLect().getLectCode();
      // 학생이->교사한테 보내는 함수(0 입실, 1 퇴실)
      if(inOrOutSignal==IN) { //수강생이 입실 신호를 보냈을 시 처리
    	  int numRows=0;
     	  try { //첫 번째 데이터베이스 연동시 해당 수강생의 해당 날짜를 갖는 레코드가 생성되었는지 체크한다.
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql0);
			pstmt.setString(1, attendStuId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				numRows=rs.getInt(1);
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
     	  } //try-catch-finally 0 (첫 번째 데이터베이스 연동시 해당 수강생의 해당 날짜를 갖는 레코드가 생성되었는지 체크한다.)
     	  if(numRows!=0) return; //이미 해당 수강생의 해당 날짜를 갖는 레코드가 생성되었으므로 INSERT 쿼리문(sql1)은 실행할 필요가 없다.
     	  
     	  try { //두 번째 데이터베이스 연동시 해당 수강생의 해당 날짜를 갖는 레코드를 생성한다.
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, attendStuId);
			pstmt.setString(2, lectCode);
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
     	  } //try-catch-finally 1 (두 번째 데이터베이스 연동시 해당 수강생의 해당 날짜를 갖는 레코드를 생성한다.)
      } //수강생이 입실 신호를 보냈을 시 처리
      
      else { //수강생이 퇴실 신호를 보냈을 시 처리
    	try { //퇴실 시 퇴실 시각을 갱신한다.
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, attendStuId);
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
		} //try-catch-finally 2 (퇴실 시 퇴실 시각을 갱신한다.)
      } //수강생이 퇴실 신호를 보냈을 시 처리
   } //sendSignal

   
   public void checkSignal(String attendStuId, int inOrOutSignal) {
      String sql1="update Attend set Attend_isBeginChecked=1 where Attend_StuId=? and trunc(Attend_Nalja)=trunc(sysdate)";
      String sql2="update Attend set Attend_endBeginChecked=1 where Attend_StuId=? and trunc(Attend_Nalja)=trunc(sysdate)";
 
      try {
         conn=MyOracle.getConnection();
         if(inOrOutSignal==IN) { //입실 신호 승인
            pstmt=conn.prepareStatement(sql1);
            pstmt.setString(1, attendStuId);
            pstmt.executeUpdate();
         }else { //퇴실 신호 승인
            pstmt=conn.prepareStatement(sql2);
            pstmt.setString(1, attendStuId);
            pstmt.executeUpdate();
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
   } //checkSignal
   
   public Date getDate(String dateMsg) {
	   String[] biggerChunkDate=dateMsg.split("\\s");
	   String[] formerSmallerChunkDate=biggerChunkDate[0].split("-"); //년-월-일
	   int year=Integer.parseInt(formerSmallerChunkDate[0]);
	   int month=Integer.parseInt(formerSmallerChunkDate[1]);
	   int date=Integer.parseInt(formerSmallerChunkDate[2]);
	   String[] laterSmallerChunkDate=biggerChunkDate[1].split(":"); //시:분:초
	   int hour=Integer.parseInt(laterSmallerChunkDate[0]);
	   int minute=Integer.parseInt(laterSmallerChunkDate[1]);
	   int second=(int)Float.parseFloat(laterSmallerChunkDate[2]);
	   Calendar cal=Calendar.getInstance();
	   cal.set(year, month-1, date, hour, minute, second);
	  return new Date(cal.getTimeInMillis());
   }
   
   public AttendDto selectAttend(Date attendNalja, String attendStuId) {
	   AttendDto bean = new AttendDto();
	   String sql="select Attend_Nalja, Attend_StuId, Attend_LectCode, Attend_BeginTime, Attend_IsBeginChecked, Attend_EndTime, Attend_IsEndChecked from Attend where trunc(?)=trunc(sysdate) and Attend_StuId=?";
	   
	   try {
		   conn=MyOracle.getConnection();
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setDate(1, attendNalja);
		   pstmt.setString(2, attendStuId);
		   rs=pstmt.executeQuery();
		   if(rs.next()) {
			   bean.setAttendNalja(rs.getDate("Attend_Nalja"));
			   bean.setAttendStuId(rs.getString("Attend_StuId"));
			   bean.setAttendLectCode(rs.getString("Attend_LectCode"));
			   String attendBeginTimeString=rs.getString("Attend_BeginTime");
			   bean.setAttendBeginTime(getDate(attendBeginTimeString));
			   bean.setBeginChecked(rs.getInt("Attend_IsBeginChecked")!=0);
			   String attendEndTimeString=rs.getString("Attend_EndTime");
			   bean.setAttendEndTime(getDate(attendEndTimeString));
			   bean.setEndChecked(rs.getInt("Attend_IsEndChecked")!=0);
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
   }
   
   public double getHour(String dateMsg) { //eg. 2021-10-13 20:20:00 -> 20.33333
	   String[] biggerChunkDate=dateMsg.split("\\s");
	   String[] laterSmallerChunkDate=biggerChunkDate[1].split(":"); //시:분:초
	   int hour=Integer.parseInt(laterSmallerChunkDate[0]);
	   int minute=Integer.parseInt(laterSmallerChunkDate[1]);
	   int second=(int)Float.parseFloat(laterSmallerChunkDate[2]);
	   return hour+(minute/60.0)+(second/3600.0);
   }
   
   public Calendar getCal(String dateMsg) {
	   String[] biggerChunkDate=dateMsg.split("\\s");
	   String[] laterSmallerChunkDate=biggerChunkDate[1].split(":"); //시:분:초
	   int hour=Integer.parseInt(laterSmallerChunkDate[0]);
	   int minute=Integer.parseInt(laterSmallerChunkDate[1]);
	   int second=(int)Float.parseFloat(laterSmallerChunkDate[2]);
	   Calendar cal=Calendar.getInstance();
	   cal.set(1970, 0, 1, hour, minute, second);
	  return cal;
   }
 
   public double getAttendRate(String attendStuId) {
	   String sql="select * from Attend where Attend_StuId=?";
	   int numRowsAll=0; //(해당 학생의) 전체 행의 개수
	   int numRowsWithNulls=0; //퇴실시각 혹은 강사의 승인이 없는 행의 개수
	   int numRowsBelowHalf=0; //50% 미만으로 참여한 행의 개수
	   int numRowsLate=0; //지각한 행의 개수
	   int numRowsEarly=0; //조퇴한 행의 개수
	   
	   String lectCode="";
	   double lectBeginTimeHour=-1.0;
	   double lectEndTimeHour=-1.0;
	   long lectBeginTimeMs=-1;
	   long lectEndTimeMs=-1;
	   
	   try {
		   conn=MyOracle.getConnection();
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1, attendStuId);
		   rs=pstmt.executeQuery();
		   while(rs.next()) {
			   if("".equals(lectCode)) {
				   lectCode=rs.getString("Attend_LectCode");
				   LectDto lect=new LectDao().selectLect(lectCode);
				   lectBeginTimeHour=lect.getLectBeginTime()/100+lect.getLectBeginTime()%100/60.0; //930 -> 9.5
				   lectEndTimeHour=lect.getLectEndTime()/100+lect.getLectEndTime()%100/60.0; //1830 -> 18.5	
				   
				   Calendar cal=Calendar.getInstance();
				   cal.set(1970,0,1,lect.getLectBeginTime()/100,lect.getLectBeginTime()%100);
				   lectBeginTimeMs=cal.getTimeInMillis();
				   cal.set(1970,0,1,lect.getLectEndTime()/100,lect.getLectEndTime()%100);
				   lectBeginTimeMs=cal.getTimeInMillis();
			   }
			   numRowsAll++; //행 개수 카운트
			   double attendBeginTimeHour=getHour(rs.getString("Attend_BeginTime"));
			   double attendEndTimeHour=getHour(rs.getString("Attend_EndTime"));
			   if(attendBeginTimeHour<=lectBeginTimeHour) attendBeginTimeHour=lectBeginTimeHour;
			   else if(LUNCH_BEGIN_TIME_HOUR<attendBeginTimeHour && attendBeginTimeHour<=LUNCH_END_TIME_HOUR) attendBeginTimeHour=LUNCH_END_TIME_HOUR;
			   if(attendEndTimeHour>=lectEndTimeHour) attendEndTimeHour=lectEndTimeHour;
			   else if(LUNCH_BEGIN_TIME_HOUR<=attendEndTimeHour && attendEndTimeHour<LUNCH_END_TIME_HOUR) attendEndTimeHour=LUNCH_BEGIN_TIME_HOUR;
			   
			   double t1=(lectEndTimeHour<=LUNCH_BEGIN_TIME_HOUR || lectBeginTimeHour>=LUNCH_END_TIME_HOUR)?lectEndTimeHour-lectBeginTimeHour:lectEndTimeHour-lectBeginTimeHour-1; //순수 수업 시간
			   double t2=(attendEndTimeHour<=LUNCH_BEGIN_TIME_HOUR || attendBeginTimeHour>=LUNCH_END_TIME_HOUR)?attendEndTimeHour-attendBeginTimeHour:attendEndTimeHour-attendBeginTimeHour-1; //순수 수업 참여 시간
			   
			   long attendBeginTimeMs=getCal(rs.getString("Attend_BeginTime")).getTimeInMillis();
			   long attendEndTimeMs=getCal(rs.getString("Attend_EndTime")).getTimeInMillis();
			   
			   
			   
			   
			   if(rs.getInt("Attend_IsBeginChecked")==0 || rs.getInt("Attend_IsEndChecked")==0 || rs.getDate("Attend_EndTime")==null) numRowsWithNulls++; //퇴실 신호가 없거나 강사 승인이 없는 행
			   else if(t2/t1<0.5) numRowsBelowHalf++; //50% 미만 참여 횟수
			   else {
				   if(attendBeginTimeMs-lectBeginTimeMs>600000) numRowsLate++; //지각한 횟수
				   if(lectEndTimeMs-attendEndTimeMs>600000) numRowsEarly++; //조퇴한 횟수
			   }
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
	   }
	   
	   int numLectNaljas = new LectDao().selectLect(lectCode).getLectNumNaljas();
	   return (numRowsAll-numRowsWithNulls-numRowsBelowHalf-(numRowsLate+numRowsEarly)/3)/(double)numLectNaljas;
   }
 
 
 
   
 
   
 
}