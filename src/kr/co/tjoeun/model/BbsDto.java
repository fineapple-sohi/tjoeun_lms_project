package kr.co.tjoeun.model;

import java.sql.Date;

public class BbsDto {
	private int bbsIdx;
	private String bbsTable, bbsMemberId, bbsSub, bbsCont, ansCont;
	private Date bbsNalja, ansNalja;
	
	public int getBbsIdx() {
		return bbsIdx;
	}
	public String getBbsTable() {
		return bbsTable;
	}
	public String getBbsMemberId() {
		return bbsMemberId;
	}
	public String getBbsSub() {
		return bbsSub;
	}
	public String getBbsCont() {
		return bbsCont;
	}
	public String getAnsCont() {
		return ansCont;
	}
	public Date getBbsNalja() {
		return bbsNalja;
	}
	public Date getAnsNalja() {
		return ansNalja;
	}
	public void setBbsIdx(int bbsIdx) {
		this.bbsIdx = bbsIdx;
	}
	public void setBbsTable(String bbsTable) {
		this.bbsTable = bbsTable;
	}
	public void setBbsMemberId(String bbsMemberId) {
		this.bbsMemberId = bbsMemberId;
	}
	public void setBbsSub(String bbsSub) {
		this.bbsSub = bbsSub;
	}
	public void setBbsCont(String bbsCont) {
		this.bbsCont = bbsCont;
	}
	public void setAnsCont(String ansCont) {
		this.ansCont = ansCont;
	}
	public void setBbsNalja(Date bbsNalja) {
		this.bbsNalja = bbsNalja;
	}
	public void setAnsNalja(Date ansNalja) {
		this.ansNalja = ansNalja;
	}
	
	
	
	
	
}
