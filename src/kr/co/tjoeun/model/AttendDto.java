package kr.co.tjoeun.model;

import java.sql.Date;

public class AttendDto {
	private Date attendNalja;
	private String attendStuId;
	private String attendLectCode;
	private Date attendBeginTime;
	private boolean isBeginChecked;
	private Date attendEndTime;
	private boolean isEndChecked;
	
	public AttendDto() {}

	public Date getAttendNalja() {
		return attendNalja;
	}

	public void setAttendNalja(Date attendNalja) {
		this.attendNalja = attendNalja;
	}

	public String getAttendStuId() {
		return attendStuId;
	}

	public void setAttendStuId(String attendStuId) {
		this.attendStuId = attendStuId;
	}

	public String getAttendLectCode() {
		return attendLectCode;
	}

	public void setAttendLectCode(String attendLectCode) {
		this.attendLectCode = attendLectCode;
	}

	public Date getAttendBeginTime() {
		return attendBeginTime;
	}

	public void setAttendBeginTime(Date attendBeginTime) {
		this.attendBeginTime = attendBeginTime;
	}

	public boolean isBeginChecked() {
		return isBeginChecked;
	}

	public void setBeginChecked(boolean isBeginChecked) {
		this.isBeginChecked = isBeginChecked;
	}

	public Date getAttendEndTime() {
		return attendEndTime;
	}

	public void setAttendEndTime(Date attendEndTime) {
		this.attendEndTime = attendEndTime;
	}

	public boolean isEndChecked() {
		return isEndChecked;
	}

	public void setEndChecked(boolean isEndChecked) {
		this.isEndChecked = isEndChecked;
	}

}
