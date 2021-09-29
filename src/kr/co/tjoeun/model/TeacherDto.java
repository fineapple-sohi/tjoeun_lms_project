package kr.co.tjoeun.model;

public class TeacherDto {
	private String teacherId;
	private String teacherName;
	private String teacherTel;
	private String teacherEmail;
	private String teacherAddr;
	private String teacherPw;
	
	public TeacherDto() {}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherTel() {
		return teacherTel;
	}

	public void setTeacherTel(String teacherTel) {
		this.teacherTel = teacherTel;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherAddr() {
		return teacherAddr;
	}

	public void setTeacherAddr(String teacherAddr) {
		this.teacherAddr = teacherAddr;
	}

	public String getTeacherPw() {
		return teacherPw;
	}

	public void setTeacherPw(String teacherPw) {
		this.teacherPw = teacherPw;
	}

}
