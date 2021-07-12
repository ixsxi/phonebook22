package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("컨트롤러");
		
		//파라미터 action 값을 읽어온다
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			//리스트업무
			System.out.println("[리스트]");
			
			//리스트
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();

			System.out.println("contoller=========================");
			System.out.println(personList);
			
			
			//데이터 넣기 --request 어트리뷰트에 데이터를 넣어준다
			request.setAttribute("pList", personList);
			
			/*
			request.setAttribute("age", 45);
			request.setAttribute("name", "황일영");
			*/
			
			
			//html작업 ---> jsp에게 시킨다 ==>forword 한다   포워드
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			
			
		}else if("wform".equals(action)) {
			System.out.println("[글쓰기폼]");
			
			// writeFrom.jsp 포워드   --> 데이터X
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
		}else if("insert".equals(action)) {
			System.out.println("[저장]");
			
			//dao --> 저장
			//파라미터를 꺼낸다 name, hp, company,
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo 로 묶어준다
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//dao personInsert(vo)
			PhoneDao phoneDao = new PhoneDao();
			int count = phoneDao.personInsert(personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook22/pbc?action=list");
			
			//수정
		}else if("update".equals(action)) {
		
			System.out.println("update!!!");
			
		int id =Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String hp = request.getParameter("hp");
		String company = request.getParameter("company");
		
		PersonVo personvo = new PersonVo(id,name,hp,company);
		
		PhoneDao phonedao = new PhoneDao();
		phonedao.personUpdate(personvo);

		response.sendRedirect("/phonebook22/pbc?action=list");
		
		//수정 폼
		}else if("updf".equals(action)) {
			System.out.println("수정폼");
			
			// writeFrom.jsp 포워드   --> 데이터X
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
			rd.forward(request, response);
			
		}else if( "del".equals(action)){
			PhoneDao phoneDao = new PhoneDao();
			int personId =Integer.parseInt(request.getParameter("id"));
			
			phoneDao.personDelete(personId);
			
			
			
			response.sendRedirect("/phonebook22/pbc?action=list");
		}
		
		
		
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}