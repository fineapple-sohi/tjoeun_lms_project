package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.tjoeun.model.BbsDao;

public class LmsBbsDelController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		req.setAttribute("path", path);
		resp.sendRedirect(path + "lms/main.lms");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		int bbsIdx = Integer.parseInt(req.getParameter("idx"));
		String bbsTable = req.getParameter("bbsTable");
		
		BbsDao bbsDao = BbsDao.getInstance();
		bbsDao.delBbs(bbsTable, bbsIdx);
		
	}
}
