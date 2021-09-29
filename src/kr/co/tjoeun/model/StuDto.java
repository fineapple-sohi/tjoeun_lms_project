package kr.co.tjoeun.model;

public class StuDto {
	private String stuId;
	private String stuName;
	private String stuTel;
	private String stuEmail;
	private String stuAddr;
	private String stuPw;
	private LectDto stuLect;
	
	public StuDto() {}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuTel() {
		return stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuAddr() {
		return stuAddr;
	}

	public void setStuAddr(String stuAddr) {
		this.stuAddr = stuAddr;
	}

	public String getStuPw() {
		return stuPw;
	}

	public void setStuPw(String stuPw) {
		this.stuPw = stuPw;
	}

	public LectDto getStuLect() {
		return stuLect;
	}

	public void setStuLect(LectDto stuLect) {
		this.stuLect = stuLect;
	}
	
}
