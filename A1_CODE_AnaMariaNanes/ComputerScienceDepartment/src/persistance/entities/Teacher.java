package persistance.entities;

public class Teacher {
       
	private int teacherID;
	private String name;
	private String username;
	private String password;
	
	public Teacher() {}

	public Teacher(int teacherID, String name, String username, String password) {
		super();
		this.teacherID = teacherID;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
