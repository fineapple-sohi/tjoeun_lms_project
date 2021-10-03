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
import kr.co.tjoeun.model.StuDao;
import kr.co.tjoeun.model.SalesDao;
import kr.co.tjoeun.model.TeacherDto;
import kr.co.tjoeun.model.TeacherDao;
import java.util.ArrayList;

public class LmsPersonalUpdateController extends HttpServlet {
	
	/*
	정보수정 버튼을 누르면 doGet이 작동된다.
	doGet은 정보 수정 페이지를 포워드한다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");

		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/personalInfo/personalUpdate.jsp");
		rd.forward(req, resp);
	}
	
	/*
	정보 수정 페이지에서 수정 버튼을 클릭하면 doPost가 작동된다.
	입력된 값이 조건을 충족하면 doPost는 정보를 수정한다.
	입력된 값이 조건을 충족하지 않으면 doPost는 정보를 수정하지 않는다.
	양쪽 다 정보 수정 페이지를 포워드한다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		String depCode=req.getParameter("depCode");
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String addr=req.getParameter("addr");
		
		
		//입력값 validation
		//비밀번호는 로마자, 숫자로 4-16자 \\w{4,16}
		//전화번호는 최대 20자 .{1,20}
		//이메일은 최대 36자 .{1,36}
		//주소는 최대 60자 .{1,60}
		boolean isInputValid=true;
		String invalidItemName="";
		String pattern="\\w{4,16}";
		isInputValid=java.util.regex.Pattern.matches(pattern, pw);
		if(isInputValid) {
			pattern=".{1,20}";
			isInputValid=java.util.regex.Pattern.matches(pattern, tel);
		} else {
			invalidItemName="".equals(invalidItemName)?"pw":invalidItemName;
		}
		
		if(isInputValid) {
			pattern=".{1,36}";
			isInputValid=java.util.regex.Pattern.matches(pattern, email);
		} else {
			invalidItemName="".equals(invalidItemName)?"tel":invalidItemName;
		}
		
		if(isInputValid) {
			pattern=".{1,60}";
			isInputValid=java.util.regex.Pattern.matches(pattern, addr);
		} else {
			invalidItemName="".equals(invalidItemName)?"email":invalidItemName;
		}
		
		if(isInputValid) { //만약 입력값이 조건을 충족한다면-
			if("stu".equals(depCode)) {
				new StuDao().updateStu(id, tel, email, addr, pw);
			} else if("admin".equals(depCode)) {
				new AdminDao().updateAdmin(id, tel, email, addr, pw);
			} else if("sales".equals(depCode)) {
				new SalesDao().updateSales(id, tel, email, addr, pw);
			} else if("teacher".equals(depCode)) {
				new TeacherDao().updateTeacher(id, tel, email, addr, pw);
			}
			invalidItemName="updateFinished";
		} else { //만약 입력값이 조건을 충족하지 않는다면-
			invalidItemName="".equals(invalidItemName)?"addr":invalidItemName;
			req.setAttribute("pw", pw);
			req.setAttribute("tel", tel);
			req.setAttribute("email", email);
			req.setAttribute("addr", addr);
		}
		req.setAttribute("invalidItemName", invalidItemName);
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/personalInfo/personalUpdate.jsp");
		rd.forward(req, resp);
	}
		
		
		
}

