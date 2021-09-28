package kr.co.tjoeun.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.tjoeun.model.MemberDao;
import kr.co.tjoeun.util.MyOracle;

public class LoginController extends HttpServlet {
	private Connection conn;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"member/login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		req.setAttribute("path", path);
		
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		MemberDao mbDao = MemberDao.getInstance();
		int loginResult = mbDao.login(id, pw);
		
		if(loginResult == 1) {
			req.setAttribute("loginResult", loginResult);
			HttpSession session = req.getSession();
			session.setAttribute("success", true);
			session.setAttribute("sessionID", id);
//			session.setAttribute("userGroup", group);	행정팀, 영업팀, 강사팀, 학생, 일반회원 구분해서 아이디옆에 표기되어야함

//			RequestDispatcher rd = req.getRequestDispatcher(path+"index.jsp");
//			rd.forward(req, resp);
			resp.sendRedirect(path+"index.do");
			
			System.out.println("로그인성공");
			
		} else {
			req.setAttribute("loginResult", loginResult);
			RequestDispatcher rd = req.getRequestDispatcher(path+"member/login.jsp");
			rd.forward(req, resp);
			System.out.println("로그인실패");
		}

		
		
	}
	
}
