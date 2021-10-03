package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import kr.co.tjoeun.util.MyOracle;

public class EvalDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList<EvalDto> selectEvalListStu(String stuId){
		String sql = "select * from Eval where eval_stuId=? order by eval_ordernum";
		ArrayList<EvalDto> selectEvalListStu = new ArrayList<EvalDto>();
		ArrayList<String> idList = new ArrayList<String>();
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EvalDto bean = new EvalDto();
				bean.setEvalOrderNum(rs.getInt("eval_OrderNum"));
				idList.add(rs.getString("Eval_StuId"));
				bean.setEvalScore(rs.getInt("eval_Score"));
				bean.setEvalLectCode(rs.getString("eval_LectCode"));
				selectEvalListStu.add(bean);
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
		}
		for(int i=0;i<selectEvalListStu.size();i++) {
			String stuId2=idList.get(i);
			selectEvalListStu.get(i).setEvalStu(new StuDao().selectStu(stuId2));
		}
		return selectEvalListStu;
	}
	
	public ArrayList<EvalDto> selectEvalListLect(String lectCode){
		String sql = "select * from Eval where eval_LectCode=?";
		ArrayList<EvalDto> selectEvalListLect = new ArrayList<EvalDto>();
		ArrayList<String> idList = new ArrayList<String>();
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lectCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EvalDto bean = new EvalDto();
				bean.setEvalOrderNum(rs.getInt("eval_OrderNum"));
				idList.add(rs.getString("eval_StuId"));
				bean.setEvalScore(rs.getInt("eval_Score"));
				bean.setEvalLectCode(rs.getString("eval_LectCode"));
				selectEvalListLect.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<selectEvalListLect.size();i++) {
			String stuId2=idList.get(i);
			selectEvalListLect.get(i).setEvalStu(new StuDao().selectStu(stuId2));
		}
		return selectEvalListLect;
	}
	
	public void updateEval(int evalOrderNum, String stuId, int evalScore) {
		String sql="update Eval set Eval_Score=? where Eval_OrderNum=? and Eval_StuId=?";
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, evalScore);
			pstmt.setInt(2, evalOrderNum);
			pstmt.setString(3, stuId);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}









