package kr.co.tjoeun.model;

public class SalesDto {
	private String salesId;
	private String salesName;
	private String salesTel;
	private String salesEmail;
	private String salesAddr;
	private String salesPw;
	
	public SalesDto() {}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getSalesTel() {
		return salesTel;
	}

	public void setSalesTel(String salesTel) {
		this.salesTel = salesTel;
	}

	public String getSalesEmail() {
		return salesEmail;
	}

	public void setSalesEmail(String salesEmail) {
		this.salesEmail = salesEmail;
	}

	public String getSalesAddr() {
		return salesAddr;
	}

	public void setSalesAddr(String salesAddr) {
		this.salesAddr = salesAddr;
	}

	public String getSalesPw() {
		return salesPw;
	}

	public void setSalesPw(String salesPw) {
		this.salesPw = salesPw;
	}

}
