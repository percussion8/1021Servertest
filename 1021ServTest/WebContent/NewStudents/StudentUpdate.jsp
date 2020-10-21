<%@ page import = "vo.Score" %>
<%@ page import = "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="student" scope='request' class='vo.Score' />
<!-- 폼 데이터 처리에 용이하도록 Score클래스를 자바빈즈로 추가 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생 업데이트</title>
</head>
<body>
	<h1>학생정보</h1>
<form action="supdate" method="post"> <!-- 저장버튼을 누르면 update서블릿에 post방식으로 전달하도록 설정 -->
번호 : <input type="text" name ="no" value="<%=student.getNo()%>" readonly><br>
<!-- 번호는 조회가능하되 바꾸지 못하도록 readonly 설정 -->
이름 : <input type="text" name="name" value="<%=student.getName()%>"><br>
국어 : <input type="text" name="kor" id="kor" value="<%=student.getKor()%>"><br>
영어 : <input type="text" name="eng" id="eng" value="<%=student.getEng()%>"><br>
수학 : <input type="text" name="math" id="math" value="<%=student.getMath()%>"><br>
<input type="submit" value="저장">
<input type="button" value="취소 " onclick="location.href='StudentListServ'">
<!-- 작성을 취소하면 학생목록으로 돌아가도록 설정 -->

</form>

</body>
</html>