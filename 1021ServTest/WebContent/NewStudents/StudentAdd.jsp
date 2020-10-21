<%@page import="java.util.ArrayList"%>
<%@ page import = "vo.Score" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="student" scope="request" class="vo.Score" />
<!-- 폼 데이터 처리에 용이하도록 Score클래스를 자바빈즈로 추가 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생등록</title>
</head>
<body>
<h1>학생등록</h1>
<form action="sadd" method="post"> <!-- 가입버튼을 누르면 add서블릿에 post방식으로 전달하도록 설정 -->
	이름 : <input type="text" name="name"><br>
	국어 : <input type="text" name="kor"><br>
	영어 : <input type="text" name="eng"><br>
	수학 : <input type="text" name="math"><br>
	<input type="submit" value="가입">
	<input type="reset" value="취소">
</form>

</body>
</html>