package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.tjoeun.model.BbsDao;
import kr.co.tjoeun.model.BbsDto;

public class LmsBbsEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		String bbsTable = req.getParameter("bbs_table");
		String bbsTit = "기본";
		if(bbsTable.equals("notice")) {
			bbsTit = "공지사항";
		} else if(bbsTable.equals("quest")) {
			bbsTit = "문의하기";
		}
		req.setAttribute("bbsTit", bbsTit);
		req.setAttribute("bbsTable", bbsTable);
		
		int bbsIdx = Integer.parseInt(req.getParameter("idx"));
		BbsDao bbsDao = BbsDao.getInstance();
		BbsDto bean = bbsDao.selectOneBbs(bbsTable, bbsIdx);
		req.setAttribute("bean", bean);
		
		req.setAttribute("path", path);
		RequestDispatcher rd = null;
		if(req.getServletPath().indexOf(".lms") > 0) {
			rd = req.getRequestDispatcher(path+"lms/bbs/bbsEdit.jsp");
		} else if (req.getServletPath().indexOf(".do") > 0) {
			rd = req.getRequestDispatcher(path+"community/boardEdit.jsp");
		}	
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		req.setAttribute("path", path);
		
		String bbsTable = req.getParameter("bbs_table");
		String bbsSub = req.getParameter("subject");
		String bbsCont = req.getParameter("content");
		int bbsIdx = Integer.parseInt(req.getParameter("idx"));
		
		BbsDao bbsDao = BbsDao.getInstance();
		bbsDao.editBbs(bbsTable, bbsSub, bbsCont, bbsIdx);
		
		if(req.getServletPath().indexOf(".lms") > 0) {
			resp.sendRedirect(path+"lms/bbs/bbsView.lms?bbs_table="+bbsTable+"&idx="+bbsIdx);
		} else if (req.getServletPath().indexOf(".do") > 0) {
			resp.sendRedirect(path+"community/boardView.do?bbs_table="+bbsTable+"&idx="+bbsIdx);
		}	
		
		
		
	}
	
	
	
}
