package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path=config.getInitParameter("path");	// init시점에 읽어와서 설정하는것. 중간에 값 바꿀수X
		
		req.setAttribute("path", path);
		RequestDispatcher rd=req.getRequestDispatcher(path+"index.jsp");
		rd.forward(req, resp);
	}
}
