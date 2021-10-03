package kr.co.tjoeun.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.tjoeun.model.LectDto;
import kr.co.tjoeun.model.StuDao;
import kr.co.tjoeun.model.StuDto;

public class ScoreManageController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		
		String stuTable = req.getParameter("stu_table");
		String stuTit = "기본";
		if(stuTable.equals("search")) {
			stuTit = "수강생 조회";
		} else if(stuTable.equals("attend")) {
			stuTit = "출결관리";
		} else if(stuTable.equals("score")) {
			stuTit = "성적관리";
		}
		req.setAttribute("stuTit", stuTit);
		String teacherId=req.getParameter("teacherId");
		String lectCode="";
		ArrayList<LectDto> lList = new kr.co.tjoeun.model.LectDao().selectLectList();
		for(LectDto bean:lList) {
			if(bean.getLectTeacher().getTeacherId().equals(teacherId)) {
				lectCode=bean.getLectCode();
				break;
			}
		}
		StuDao dao = new StuDao();
		ArrayList<StuDto> sList = dao.selectStuList(lectCode);
		req.setAttribute("stuList", sList);
		req.setAttribute("path", path);
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/eval/scoreManage.jsp");
		rd.forward(req, resp);
	}
}
