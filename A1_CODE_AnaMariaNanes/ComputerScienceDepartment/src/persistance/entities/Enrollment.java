package persistance.entities;

import java.sql.Date;

public class Enrollment {
    
	private int enrollmentID;
	private int studentID;
	private int courseID;
	private float examGrade;
	private String startDate;
	private String endDate;
	
	public Enrollment() {}

	public Enrollment(int enrollmentID, int studentID, int courseID, float examGrade, String startDate, String endDate) {

		this.enrollmentID = enrollmentID;
		this.studentID = studentID;
		this.courseID = courseID;
		this.examGrade = examGrade;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Enrollment(int studentID, int courseID, float examGrade, String startDate, String endDate) {

		this.studentID = studentID;
		this.courseID = courseID;
		this.examGrade = examGrade;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// return 1 
	public String checkPeriod(String start, String end)
	{
		String not_considered = "NO";
		String still_enrolled = "ENROLLED";
		String finished = "FINISHED";
		
		
		String[] start_dates1 = this.startDate.split("-");
		String[] start_dates2 = start.split("-");
		String[] end_dates1 = this.endDate.split("-");
		String[] end_dates2 = end.split("-");
		
		int start_course = Integer.valueOf(start_dates1[0] + start_dates1[1] + start_dates1[2]);
		int end_course = Integer.valueOf(end_dates1[0] + end_dates1[1] + end_dates1[2]);
		int start_period = Integer.valueOf(start_dates2[0] + start_dates2[1] + start_dates2[2]);
		int end_period = Integer.valueOf(end_dates2[0] + end_dates2[1] + end_dates2[2]);
		
		if(start_period <= start_course && end_period <= start_course)
			return not_considered;
		
		if(start_period <= start_course && end_period < end_course)
			return still_enrolled;
		
		if(start_period <= start_course && end_period >= end_course)
			return finished;
		
		if(start_period >= start_course && start_period <end_course && end_period <end_course)
			return still_enrolled;
		
		if(start_period >= end_course && end_period >end_course)
			return finished;
		
		return null;
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public float getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(float examGrade) {
		this.examGrade = examGrade;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
		
}
