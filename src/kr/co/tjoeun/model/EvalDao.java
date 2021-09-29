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
		String sql = "select * from Eval where eval_stuId=?";
		ArrayList<EvalDto> selectEvalListStu = new ArrayList<EvalDto>();
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EvalDto bean = new EvalDto();
				bean.setEvalOrderNum(rs.getInt("evalOrderNum"));
				bean.setEvalStu(rs.getString("evalStu"));
				bean.setEvalScore(rs.getInt("evalScore"));
				bean.setEvalLectCode(rs.getString("evalLectCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectEvalListStu;
	}
	
	public ArrayList<EvalDto> selectEvalListLect(String lectCode){
		String sql = "select * from Eval where eval_LectCode=?";
		ArrayList<EvalDto> selectEvalListLect = new ArrayList<EvalDto>();
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lectCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EvalDto bean = new EvalDto();
				bean.setEvalOrderNum(rs.getInt("evalOrderNum"));
				bean.setEvalStu(rs.getString("evalStu"));
				bean.setEvalScore(rs.getInt("evalScore"));
				bean.setEvalLectCode(rs.getString("evalLectCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectEvalListLect;
	}
	
	public void updateEval(int evalOrderNum, String stuId, int evalScore) {
		String sql="update Eval set Eval_Score=? where Eval_OrderNum=? and Eval_Stu=?";
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









