package kr.co.tjoeun.model;

import java.sql.Date;

public class LectDto {
	private String lectCode;
	private String lectName;
	private TeacherDto lectTeacher;
	private int lectTotalNumStus;
	private Date lectBeginDate;
	private Date lectEndDate;
	private int lectNumNaljas;
	private int lectBeginTime;
	private int lectEndTime;
	private String lectThumbName;
	
	public LectDto() {}

	public String getLectCode() {
		return lectCode;
	}

	public void setLectCode(String lectCode) {
		this.lectCode = lectCode;
	}

	public String getLectName() {
		return lectName;
	}

	public void setLectName(String lectName) {
		this.lectName = lectName;
	}

	public TeacherDto getLectTeacher() {
		return lectTeacher;
	}

	public void setLectTeacher(TeacherDto lectTeacher) {
		this.lectTeacher = lectTeacher;
	}

	public int getLectTotalNumStus() {
		return lectTotalNumStus;
	}

	public void setLectTotalNumStus(int lectTotalNumStus) {
		this.lectTotalNumStus = lectTotalNumStus;
	}

	public Date getLectBeginDate() {
		return lectBeginDate;
	}

	public void setLectBeginDate(Date lectBeginDate) {
		this.lectBeginDate = lectBeginDate;
	}

	public Date getLectEndDate() {
		return lectEndDate;
	}

	public void setLectEndDate(Date lectEndDate) {
		this.lectEndDate = lectEndDate;
	}

	public int getLectNumNaljas() {
		return lectNumNaljas;
	}

	public void setLectNumNaljas(int lectNumNaljas) {
		this.lectNumNaljas = lectNumNaljas;
	}

	public int getLectBeginTime() {
		return lectBeginTime;
	}

	public void setLectBeginTime(int lectBeginTime) {
		this.lectBeginTime = lectBeginTime;
	}

	public int getLectEndTime() {
		return lectEndTime;
	}

	public void setLectEndTime(int lectEndTime) {
		this.lectEndTime = lectEndTime;
	}

	public String getLectThumbName() {
		return lectThumbName;
	}

	public void setLectThumbName(String lectThumbName) {
		this.lectThumbName = lectThumbName;
	}

}
