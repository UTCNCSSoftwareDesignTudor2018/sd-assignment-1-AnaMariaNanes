package persistance.entities;

public class Grade {
	
	private int id;
	private int enrollmentID;
	private float grade;
	
	public Grade() {}

	public Grade(int id, int enrollmentID, float grade) {
		super();
		this.id = id;
		this.enrollmentID = enrollmentID;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

}
