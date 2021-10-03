package kr.co.tjoeun.model;

public class EvalDto {
	private int evalOrderNum;
	private StuDto evalStu;
	private int evalScore;
	private String evalLectCode;
	
	public EvalDto() {}
	
	public int getEvalOrderNum() {
		return evalOrderNum;
	}

	public void setEvalOrderNum(int evalOrderNum) {
		this.evalOrderNum = evalOrderNum;
	}

	public StuDto getEvalStu() {
		return evalStu;
	}

	public void setEvalStu(StuDto evalStu) {
		this.evalStu = evalStu;
	}

	public int getEvalScore() {
		return evalScore;
	}

	public void setEvalScore(int evalScore) {
		this.evalScore = evalScore;
	}

	public String getEvalLectCode() {
		return evalLectCode;
	}

	public void setEvalLectCode(String evalLectCode) {
		this.evalLectCode = evalLectCode;
	}
	
	
}
