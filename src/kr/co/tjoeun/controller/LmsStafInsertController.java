package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.tjoeun.model.AdminDto;
import kr.co.tjoeun.model.AdminDao;
import kr.co.tjoeun.model.SalesDto;
import kr.co.tjoeun.model.SalesDao;
import kr.co.tjoeun.model.TeacherDto;
import kr.co.tjoeun.model.TeacherDao;
import java.util.ArrayList;

public class LmsStafInsertController extends HttpServlet {
	
	/*
	네비게이션에서 직원 등록 아이템을 누르면 doGet이 작동한다.
	doGet은 직원 등록페이지를 포워드한다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/stafManage/stafInsert.jsp");
		rd.forward(req, resp);
	}
	
	/*
	직원 상세 페이지에서 등록 버튼을 클릭하면 doPost가 작동된다.
	입력된 값이 조건을 충족하면 doPost는 직원 리스트 페이지를 포워드한다.
	입력된 값이 조건을 충족하지 않으면 doPost는 직원 상세 페이지를 포워드한다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		String stafName = req.getParameter("stafName");
		String stafDepCode = req.getParameter("stafDepCode");
		String stafId = req.getParameter("stafId");
		String stafPw = req.getParameter("stafPw");
		String stafTel = req.getParameter("stafTel");
		String stafEmail = req.getParameter("stafEmail");
		String stafAddr = req.getParameter("stafAddr");
		
		//입력값 validation
		//비밀번호는 로마자, 숫자로 4-16자 \\w{4,16}
		//전화번호는 최대 20자 .{1,20}
		//이메일은 최대 36자 .{1,36}
		//주소는 최대 60자 .{1,60}
		boolean isInputValid=true;
		String invalidItemName="";
		String pattern=".{1,5}";
		isInputValid=java.util.regex.Pattern.matches(pattern, stafName);
		
		if(isInputValid) {
			pattern="\\w{4,16}";
			isInputValid=java.util.regex.Pattern.matches(pattern, stafId);
		} else {
			invalidItemName="".equals(invalidItemName)?"stafName":invalidItemName;
		}
		
		if(isInputValid) {
			pattern="\\w{4,16}";
			isInputValid=java.util.regex.Pattern.matches(pattern, stafPw);
		} else {
			invalidItemName="".equals(invalidItemName)?"stafId":invalidItemName;
		}
		
		if(isInputValid) {
			pattern=".{1,20}";
			isInputValid=java.util.regex.Pattern.matches(pattern, stafTel);
		} else {
			invalidItemName="".equals(invalidItemName)?"stafPw":invalidItemName;
		}
		
		if(isInputValid) {
			pattern=".{1,36}";
			isInputValid=java.util.regex.Pattern.matches(pattern, stafEmail);
		} else {
			invalidItemName="".equals(invalidItemName)?"stafTel":invalidItemName;
		}
		
		if(isInputValid) {
			pattern=".{1,60}";
			isInputValid=java.util.regex.Pattern.matches(pattern, stafAddr);
		} else {
			invalidItemName="".equals(invalidItemName)?"stafEmail":invalidItemName;
		}
		
		if(!new AdminDao().isIdValid(stafId)) {
			isInputValid=false;
			invalidItemName="stafId";
		}
		
		if(isInputValid) { //만약 입력값이 조건을 충족한다면-
			if("admin".equals(stafDepCode)) { //행정팀 등록
				new AdminDao().insertAdmin(stafId, stafName, stafTel, stafEmail, stafAddr, stafPw);
			} else if("sales".equals(stafDepCode)) { //영업팀 등록
				new SalesDao().insertSales(stafId, stafName, stafTel, stafEmail, stafAddr, stafPw);
			} else if("teacher".equals(stafDepCode)) { //강사팀 등록
				new TeacherDao().insertTeacher(stafId, stafName, stafTel, stafEmail, stafAddr, stafPw);
			}
			ArrayList<AdminDto> adminList = new AdminDao().selectAdminList();
			req.setAttribute("adminList", adminList);
			ArrayList<SalesDto> salesList = new SalesDao().selectSalesList();
			req.setAttribute("salesList", salesList);
			ArrayList<TeacherDto> teacherList = new TeacherDao().selectTeacherList();
			req.setAttribute("teacherList",teacherList);
			req.setAttribute("path", path);
			RequestDispatcher rd = req.getRequestDispatcher(path+"lms/stafManage/stafList.jsp");
			rd.forward(req, resp);
		} else { //만약 입력값이 조건을 충족하지 않는다면-
			invalidItemName="".equals(invalidItemName)?"stafAddr":invalidItemName;
			req.setAttribute("stafName", stafName);
			req.setAttribute("stafDepCode", stafDepCode);
			req.setAttribute("stafId", stafId);
			req.setAttribute("stafPw", stafPw);
			req.setAttribute("stafTel", stafTel);
			req.setAttribute("stafEmail", stafEmail);
			req.setAttribute("stafAddr", stafAddr);
			req.setAttribute("invalidItemName", invalidItemName);
			req.setAttribute("path", path);
			RequestDispatcher rd = req.getRequestDispatcher(path+"lms/stafManage/stafInsert.jsp");
			rd.forward(req, resp);
		}
		
		
		
	}
	
}
