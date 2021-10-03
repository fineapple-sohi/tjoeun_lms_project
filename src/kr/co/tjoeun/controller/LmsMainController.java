package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LmsMainController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		HttpSession session = req.getSession();

		req.setAttribute("path", path);
		RequestDispatcher rd = null;
		try {
			if(session.getAttribute("success").equals(true)) {
				rd = req.getRequestDispatcher(path+"lms/main.jsp");
			}
		} catch (NullPointerException e) {
			rd = req.getRequestDispatcher(path+"member/login.jsp");
		}

		rd.forward(req, resp);
	}
}
