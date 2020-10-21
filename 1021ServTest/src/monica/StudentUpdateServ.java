package monica;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Score;

@SuppressWarnings("serial")
@WebServlet({ "/StudentUpdateServ", "/supdate" })
public class StudentUpdateServ extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String dbURL = "jdbc:mysql://localhost:3306/greendb?serverTimezone=Asia/Seoul";
		String dbId = "root";
		String dbPw = "1234";
		
		// DB와 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbId, dbPw);
			System.out.println("DB 접속 성공 " + conn);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select sno, sname, kor, eng, math, total, avg, grade from student where sno="
							+ request.getParameter("sno"));
				//전달받은 번호에 해당하는 데이터베이스의 레코드를 쿼리문을 통해 받아온다.
			if (rs.next()) { //받아온 레코드가 존재할 때 Score객체를 생성하고 각 데이터를 저장한다
					request.setAttribute("student", new Score()
						.setNo(rs.getInt("sno"))
						.setName(rs.getString("sname"))
						.setKor(rs.getInt("kor"))
						.setEng(rs.getInt("eng"))
						.setMath(rs.getInt("math"))
						.setTotal(rs.getInt("total"))
						.setAvg(rs.getFloat("avg"))
						.setGrade(rs.getString("grade"))
						);
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다."); //번호에 해당하는 레코드값을 받지 못할경우 에러메세지 출력
			}
			RequestDispatcher rd = request.getRequestDispatcher(
					"/NewStudents/StudentUpdate.jsp"
					); //요청정보를 양도할 주소를 StudentUpdate.jsp로 성정
			rd.forward(request, response); //설정한 주소로 요청정보, 반환정보 전달
			
			
			
			
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //Update jsp문서에서 전달한 값의 인코딩 설정
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
			stmt = conn.prepareStatement("update student set sname=?, kor=?, eng=?, math=?, total=?,"
					+ "avg=?, grade=? where sno=?");
			//해당 번호에 수정된 값들(jsp로부터 전달받고 현재 서블릿페이지에서 연산처리 한)을 저장하기위한  쿼리문 작성
			stmt.setString(1, request.getParameter("name"));
			stmt.setInt(2, Integer.parseInt(request.getParameter("kor")));
			stmt.setInt(3, Integer.parseInt(request.getParameter("eng")));
			stmt.setInt(4, Integer.parseInt(request.getParameter("math")));
			stmt.setInt(5, total);
			stmt.setFloat(6, avg);
			stmt.setString(7, grade);
			stmt.setString(8, request.getParameter("no"));
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
