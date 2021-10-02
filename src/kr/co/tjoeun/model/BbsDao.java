package kr.co.tjoeun.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.tjoeun.util.MyOracle;

public class BbsDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private static BbsDao bbsDao;
	
	public BbsDao() {
		
	}
	
	public static synchronized BbsDao getInstance() {
		if(bbsDao == null) {
			bbsDao = new BbsDao();
		}
		return bbsDao;
	}
	
	// 글쓰기
	public void insertBbs(String bbsTable, String bbsSub, String bbsCont, String bbsMemberId) {
		
		String sql = "default";
		if(bbsTable.equals("notice")) {
			sql = "insert into notice values (notice_seq.nextval, ?, ?, sysdate, ?)";
		} else if(bbsTable.equals("quest")) {
			sql = "insert into quest (quest_idx, quest_memberid, quest_sub, quest_nalja, quest_cont)"
					+ " values (quest_seq.nextval, ?, ?, sysdate, ?)";
		}
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbsMemberId);
			pstmt.setString(2, bbsSub);
			pstmt.setString(3, bbsCont);	
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// 목록보기
	public ArrayList<BbsDto> selectBbs(String bbsTable) {
		
		String sql = "default";
		ArrayList<BbsDto> list = new ArrayList<BbsDto>();
		
		if(bbsTable.equals("notice")) {
			sql = "select notice_idx, notice_sub, notice_cont, notice_nalja from notice order by notice_idx desc";
		} else if(bbsTable.equals("quest")) {
			sql = "select quest_idx, quest_sub, quest_cont, quest_nalja, quest_memberid from quest order by quest_idx desc";
		}
		
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			if(bbsTable.equals("notice")) {
				while(rs.next()) {
					BbsDto bean = new BbsDto();
					bean.setBbsIdx(rs.getInt("notice_idx"));
					bean.setBbsSub(rs.getString("notice_sub"));
					bean.setBbsCont(rs.getString("notice_cont"));
					bean.setBbsNalja(rs.getDate("notice_nalja"));
					bean.setBbsMemberId("THEJOEUN");
					list.add(bean);
				}
			} else if(bbsTable.equals("quest")) {
				while(rs.next()) {
					BbsDto bean = new BbsDto();
					bean.setBbsIdx(rs.getInt("quest_idx"));
					bean.setBbsSub(rs.getString("quest_sub"));
					bean.setBbsCont(rs.getString("quest_cont"));
					bean.setBbsNalja(rs.getDate("quest_nalja"));
					bean.setBbsMemberId(rs.getString("quest_memberid"));
					list.add(bean);
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return list;		
				
	}
	
	
	// 상세보기
	public BbsDto selectOneBbs(String bbsTable, int bbsIdx) {
		
		String sql = "default";
		BbsDto bean = new BbsDto();
		
		if(bbsTable.equals("notice")) {
			sql = "select notice_idx, notice_sub, notice_cont, notice_nalja from notice where notice_idx=?";
		} else if (bbsTable.equals("quest")) {
			sql = "select quest_idx, quest_sub, quest_cont, quest_nalja, quest_memberid from quest where quest_idx=?";
		}
		
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bbsIdx);
			rs = pstmt.executeQuery();
			
			if(bbsTable.equals("notice")) {
				if(rs.next()) {
					bean.setBbsIdx(rs.getInt("notice_idx"));
					bean.setBbsSub(rs.getString("notice_sub"));
					bean.setBbsCont(rs.getString("notice_cont"));
					bean.setBbsNalja(rs.getDate("notice_nalja"));
					bean.setBbsMemberId("THEJOEUN");
				}
			} else if (bbsTable.equals("quest")) {
				if(rs.next()) {
					bean.setBbsIdx(rs.getInt("quest_idx"));
					bean.setBbsSub(rs.getString("quest_sub"));
					bean.setBbsCont(rs.getString("quest_cont"));
					bean.setBbsNalja(rs.getDate("quest_nalja"));
					bean.setBbsMemberId(rs.getString("quest_memberid"));
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return bean;
			
	}
	
	
	// 수정하기
	public void editBbs(String bbsTable, String bbsSub, String bbsCont, int bbsIdx) {
		
		String sql="default";
		
		if(bbsTable.equals("notice")) {
			sql = "update notice set notice_sub=?, notice_cont=? where notice_idx=?";
		} else if(bbsTable.equals("quest")) {
			sql = "update quest set quest_sub=?, quest_cont=? where quest_idx=?";
		}
		
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bbsSub);
			pstmt.setString(2, bbsCont);
			pstmt.setInt(3, bbsIdx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	


}
