package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		System.out.println("로그아웃");
		
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"member/logout.jsp");
		rd.forward(req, resp);
	}
}
