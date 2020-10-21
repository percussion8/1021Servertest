package monica;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet({ "/StudentAddServlet", "/sadd" })
public class StudentAddServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(
				"/NewStudents/StudentAdd.jsp"
				); //StudentAdd서블릿이 실행되면 자동으로 jsp파일로 전달되도록 주소 설정
		rd.forward(request, response); //설정된 주소로 요청,응답정보 전달
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //Add jsp문서에서 전달한 값의 인코딩 설정
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String dbURL = "jdbc:mysql://localhost:3306/greendb?serverTimezone=Asia/Seoul";
		String dbId = "root";
		String dbPw = "1234";
		
		// DB와 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbId, dbPw);
			System.out.println("DB 접속 성공 " + conn);
			int total = Integer.parseInt(request.getParameter("kor"))+Integer.parseInt(request.getParameter("eng"))
				+Integer.parseInt(request.getParameter("math")); //테이블의 total컬럼에 저장 될 값 연산
			float avg = (float)total/3; //테이블의 avg컬럼에 저장 될 값 연산
			String grade; //테이블의 grade컬럼에 저장 될 값 연산을 통해 문자열 설정
			switch((int)avg/10) {
			case 10: case 9: grade="A"; break;
			case 8: grade="B"; break;
			case 7: grade="C"; break;
			case 6: grade="D"; break;
			default: grade="F"; break;
			}
			
			stmt = conn.prepareStatement("insert into student(sname, kor, eng, math, total,"
					+ "avg, grade) values(?, ?, ?, ?, ?, ?, ?)");
			//새로운 레코드에 값들(jsp로부터 전달받고 현재 서블릿페이지에서 연산처리 한)을 저장하기위한  쿼리문 작성
			stmt.setString(1, request.getParameter("name"));
			stmt.setInt(2, Integer.parseInt(request.getParameter("kor")));
			stmt.setInt(3, Integer.parseInt(request.getParameter("eng")));
			stmt.setInt(4, Integer.parseInt(request.getParameter("math")));
			stmt.setInt(5, total);
			stmt.setFloat(6, avg);
			stmt.setString(7, grade);
			stmt.execute(); //쿼리문 실행
			
			response.sendRedirect("StudentListServ"); //쿼리문 실행 후 List서블릿으로 이동
			
			
			
		} catch (Exception e) {
			throw new ServletException(); // 예외를 던짐
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}

		}
	}

}
