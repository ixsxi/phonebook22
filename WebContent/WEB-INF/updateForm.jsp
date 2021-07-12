<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import= "com.javaex.vo.PersonVo" %>
  <%@ page import = "com.javaex.dao.PhoneDao" %>
    <%
    PhoneDao phoneDao = new PhoneDao();
	//id추출
	String id = request.getParameter("id");
	int personId = Integer.parseInt(id);
	System.out.println(personId);
	
	
	//dao 에서 한사람(id)의 정보 가져오기
	PersonVo personVo = phoneDao.getPerson(personId);
	System.out.println(personVo.toString());
	
 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>전화번호 수정폼</h1>
	<p>
		전화번호를 수정하고싶으니? <br>
		아래 항목을 기입하고 "수정" 버튼을 누르려무나
	</p>
	<!-- 
	http://localhost:8088/phonebook1/insert.jsp?name=황일영&hp=010-2222-2222&company=02-2222-2222
	 -->
	<form action="/phonebook22/pbc" method="get">
		이름(name): <input type="text" name="name" value="<%=personVo.getName()%>"> <br>
		핸드폰(hp): <input type="text" name="hp" value="<%=personVo.getHp()%>"> <br>
		회사(company): <input type="text" name="company" value="<%=personVo.getCompany()%>"> <br>
		번호(id): <input type="hidden" name="id" value="<%=personVo.getPersonId() %>">
		<input type="text" name="action" value="update"> <br>
		<button type="submit">수정</button>
	</form>



</body>
</html>