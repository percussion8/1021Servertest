<%@ page import = "vo.Score" %> <!-- Score클래스 추가 -->
<%@ page import = "java.util.*" %> <!-- 자바의 util클래스 추가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생별 점수 목록</title>
</head>
<body>
	<h1>학생별 점수 목록</h1>
	<h2><a href="sadd">학생 추가</a><br></h2> <!-- 학생을 추가하는 서블릿으로 이동하는 링크 -->
	<%
	ArrayList<Score> students = (ArrayList<Score>)request.getAttribute("students");
	//List서블릿에서 저장한 레코드값의 집합 students를 형변환을 통해 받아옴
	for(Score student:students){ //ArrayList객체 students의 Score객체드를 모두 출력한다
	%>
	<%=student.getNo()%>,
	<a href='supdate?sno=<%=student.getNo()%>'> <%=student.getName()%></a>, 
	<!-- 학생의 이름을 누르면 업데이트페이지로 이동하도록 링크, 이동할때 get방식으로 번호를 전달 -->
	<%=student.getKor()%>,
	<%=student.getEng()%>,
	<%=student.getMath()%>,
	<%=student.getTotal()%>,
	<%=student.getAvg()%>,
	<%=student.getGrade()%><br>
	<%} %>

</body>
</html>