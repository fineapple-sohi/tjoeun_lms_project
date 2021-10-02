package kr.co.tjoeun.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.tjoeun.model.BbsDao;
import kr.co.tjoeun.model.BbsDto;

public class NoticeController extends HttpServlet {
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
		
		BbsDao bbsDao = BbsDao.getInstance();
		ArrayList<BbsDto> list = bbsDao.selectBbs(bbsTable);
		req.setAttribute("list", list);
		
		req.setAttribute("bbsTable", bbsTable);
		req.setAttribute("bbsTit", bbsTit);	
		
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"community/notice.jsp");
		rd.forward(req, resp);
		
		System.out.println(req.getServletPath());
	}
}
