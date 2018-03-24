package persistance.entities;

import java.sql.Date;

public class Enrollment {
    
	private int enrollmentID;
	private int studentID;
	private int courseID;
	private float examGrade;
	private Date startDate;
	private Date endDate;
	
	public Enrollment() {}

	public Enrollment(int enrollmentID, int studentID, int courseID, float examGrade, Date startDate, Date endDate) {
		super();
		this.enrollmentID = enrollmentID;
		this.studentID = studentID;
		this.courseID = courseID;
		this.examGrade = examGrade;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
		
}
