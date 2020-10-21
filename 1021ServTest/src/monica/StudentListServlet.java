package monica;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Score;

@SuppressWarnings("serial")
@WebServlet("/StudentListServ")
public class StudentListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Connection conn = null; //DB를 연결시킬 Connection객체
		Statement stmt = null; //DB에 명령어를 전달 할 Statement객체
		ResultSet rs = null; //DB엣 가져온 결과값(해시태그)을 가지고있을 Resultset객체
		String dbURL = "jdbc:mysql://localhost:3306/greendb?serverTimezone=Asia/Seoul"; //DB서버주소
		   String dbId = "root"; //접속아이디
		   String dbPw = "1234"; //접속비밀번호
		
		// DB와 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //jdbc드라이버 유무 확인, 확인 안될경우 예외처리
			conn = DriverManager.getConnection(dbURL, dbId, dbPw); //DB연결
			System.out.println("DB 접속 성공 " + conn);
			 
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select sno, sname, kor, eng, math, total, avg, grade "
					+ "from student " + "order by sno asc"); //student테이블의 레코드를 가져올 쿼리작성
			response.setContentType("text/html; charset=utf-8");
			
			ArrayList<Score> students = new ArrayList<Score>();
			while (rs.next()) { // DB로부터 가져온 레코드 ResultSet에서 하나씩 가져옴
				students.add(new Score() //레코드별로 Score객체 생성, 값(학생이름, 과목별점수, 학점)저장
						.setNo(rs.getInt("sno"))
						.setName(rs.getString("sname"))
						.setKor(rs.getInt("kor"))
						.setEng(rs.getInt("eng"))
						.setMath(rs.getInt("math"))
						.setTotal(rs.getInt("total"))
						.setAvg(rs.getFloat("avg"))
						.setGrade(rs.getString("grade"))
						);
			}
			request.setAttribute("students", students); //서블릿컨텍스트에  가져온 값을 저장한 ArrayList객체 저장
			RequestDispatcher rd = request.getRequestDispatcher(
					"/NewStudents/StudentList.jsp"
					); //요청정보를 양도할 주소를 설정
			rd.include(request, response); //StudentList.jsp가 요청을 양도받아 실행한 결과값을 현재 서블릿페이지에 포함하여 출력
			
			
			
		} catch (Exception e) {
			throw new ServletException(); // 예외를 던짐
		} finally {
			try {
				if (rs != null) //Resultset객체가 존재할때
					rs.close(); // 닫는다
			} catch (Exception e) {
			}
			try {
				if (stmt != null) //Statement객체가 존재할때
					stmt.close(); // 닫는다
			} catch (Exception e) {
			}
			try {
				if (conn != null) //Connection객체가 존재할때
					conn.close(); // 닫는다
			} catch (Exception e) {
			}

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
