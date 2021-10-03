package kr.co.tjoeun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.tjoeun.model.BbsDao;
import kr.co.tjoeun.model.BbsDto;

public class LmsBbsViewController extends HttpServlet {
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

		HttpSession session = req.getSession();
		
		req.setAttribute("path", path);
		RequestDispatcher rd = null;
		if(bbsTable.equals("notice")) {
			if(req.getServletPath().indexOf(".lms") > 0) {
				rd = req.getRequestDispatcher(path+"lms/bbs/bbsView.jsp");
			} else if (req.getServletPath().indexOf(".do") > 0) {
				rd = req.getRequestDispatcher(path+"community/boardView.jsp");
			}
		} else if(bbsTable.equals("quest")) {
			try {
				if(session.getAttribute("success").equals(true)) {
					if(req.getServletPath().indexOf(".lms") > 0) {
						rd = req.getRequestDispatcher(path+"lms/bbs/bbsView.jsp");
					} else if (req.getServletPath().indexOf(".do") > 0) {
						rd = req.getRequestDispatcher(path+"community/boardView.jsp");
					}	
				}
			} catch (NullPointerException e) {
				rd = req.getRequestDispatcher(path+"member/login.jsp");
			}			
		}
		

		
		
		

		rd.forward(req, resp);
	}
}
