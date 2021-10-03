package kr.co.tjoeun.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.tjoeun.model.EvalDao;
import kr.co.tjoeun.model.EvalDto;
import kr.co.tjoeun.model.StuDao;
import kr.co.tjoeun.model.StuDto;

public class AddScoreController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String path = getInitParameter("path");
		String stuId = req.getParameter("stuId");
		String stuName = new StuDao().selectStu(stuId).getStuName();
		String lectName = new StuDao().selectStu(stuId).getStuLect().getLectName();
		
		req.setAttribute("stuName", stuName);
		req.setAttribute("stuId", stuId);
		req.setAttribute("lectName", lectName);
		req.setAttribute("path", path);
		
		ArrayList<EvalDto> list = new EvalDao().selectEvalListStu(stuId);
		req.setAttribute("list", list);
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/eval/addScore.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		ServletConfig config = getServletConfig();
		String path = config.getInitParameter("path");
		int evalScore1 = Integer.parseInt(req.getParameter("evalScore1")); //2,3,4 필요
		int evalScore2 = Integer.parseInt(req.getParameter("evalScore2")); //2,3,4 필요
		int evalScore3 = Integer.parseInt(req.getParameter("evalScore3")); //2,3,4 필요
		int evalScore4 = Integer.parseInt(req.getParameter("evalScore4")); //2,3,4 필요
		String stuId=req.getParameter("stuId");
		
		new EvalDao().updateEval(1, stuId, evalScore1); //2,3,4
		new EvalDao().updateEval(2, stuId, evalScore2); //2,3,4
		new EvalDao().updateEval(3, stuId, evalScore3); //2,3,4
		new EvalDao().updateEval(4, stuId, evalScore4); //2,3,4
		
		req.setAttribute("path", path);
		
		req.setAttribute("stuTit","성적관리");
		String lectCode=new StuDao().selectStu(stuId).getStuLect().getLectCode();
		ArrayList<StuDto> sList=new StuDao().selectStuList(lectCode);
		req.setAttribute("stuList", sList);
		
		RequestDispatcher rd = req.getRequestDispatcher(path+"lms/eval/scoreManage.jsp");
		rd.forward(req, resp);
		
	}
}
