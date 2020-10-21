package vo;

public class Score {
	//맴버변수 선언
	protected int no;
	protected String name;
	protected int kor, eng, math, total;
	protected float avg;
	protected String grade;
	
	//번호
	public int getNo() {
		return no;
	}
	public Score setNo(int no) {
		this.no = no;
		return this; //현재 Score객체를 반환함으로서 연달아 메서드호출 가능하도록 구현
	}
	//이름
	public String getName() {
		return name;
	}
	public Score setName(String name) {
		this.name = name;
		return this;
	}
	//국어
	public int getKor() {
		return kor;
	}
	public Score setKor(int kor) {
		this.kor = kor;
		return this;
	}
	//영어
	public int getEng() {
		return eng;
	}
	public Score setEng(int eng) {
		this.eng = eng;
		return this;
	}
	//수학
	public int getMath() {
		return math;
	}
	public Score setMath(int math) {
		this.math = math;
		return this;
	}
	//총점
	public int getTotal() {
		return total;
	}
	public Score setTotal(int total) {
		this.total = total;
		return this;
	}
	//평균
	public float getAvg() {
		return avg;
	}
	public Score setAvg(float avg) {
		this.avg = avg;
		return this;
	}
	//학점
	public String getGrade() {
		return grade;
	}
	public Score setGrade(String grade) {
		this.grade = grade;
		return this;
	}

	
	
}
