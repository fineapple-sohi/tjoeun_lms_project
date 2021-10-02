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

public class LmsStafListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		ArrayList<AdminDto> adminList = new AdminDao().selectAdminList();
		req.setAttribute("adminList", adminList);
		ArrayList<SalesDto> salesList = new SalesDao().selectSalesList();
		req.setAttribute("salesList", salesList);
		ArrayList<TeacherDto> teacherList = new TeacherDao().selectTeacherList();
		req.setAttribute("teacherList",teacherList);
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/stafManage/stafList.jsp");
		rd.forward(req, resp);
	}
}
