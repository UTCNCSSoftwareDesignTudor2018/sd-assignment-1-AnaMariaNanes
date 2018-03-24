package persistance.entities;

public class Course {

		private int studentID;
		private String name;
		private int teacherID;
		private int credits;
		private String room;
		
		public Course() {}
		
		public Course(int studentID,String name,int teacherID,int credits,String room)
		{
			this.studentID = studentID;
			this.name = name;
			this.teacherID = teacherID;
			this.credits = credits;
			this.room = room;
		}

		public int getStudentID() {
			return studentID;
		}

		public void setStudentID(int studentID) {
			this.studentID = studentID;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getTeacherID() {
			return teacherID;
		}

		public void setTeacherID(int teacherID) {
			this.teacherID = teacherID;
		}

		public int getCredits() {
			return credits;
		}

		public void setCredits(int credits) {
			this.credits = credits;
		}

		public String getRoom() {
			return room;
		}

		public void setRoom(String room) {
			this.room = room;
		}
		
		
		
}
