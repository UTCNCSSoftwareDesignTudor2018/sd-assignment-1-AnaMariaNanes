package persistance.entities;

public class Student {
	
	private int studentID;
	private String name;
	private String cardID;
	private String cnp;
	private String address;
	private String groupID;
	private String username;
	private String password;
	
	public Student() {}

	public Student(int studentID,String name, String cardID, String cnp, String address, String groupID, String username,
			String password) {
		
		this.studentID =  studentID;
		this.name = name;
		this.cardID = cardID;
		this.cnp = cnp;
		this.address = address;
		this.groupID = groupID;
		this.username = username;
		this.password = password;
	}
	
	public Student(String name, String cardID, String cnp, String address, String groupID, String username,
			String password) {
		
		this.name = name;
		this.cardID = cardID;
		this.cnp = cnp;
		this.address = address;
		this.groupID = groupID;
		this.username = username;
		this.password = password;
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

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroup(String groupID) {
		this.groupID = groupID;
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
