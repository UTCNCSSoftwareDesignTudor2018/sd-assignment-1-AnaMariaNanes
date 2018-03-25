package business.classes;

import java.util.List;

import business.interfaces.IStudentBLL;
import persistance.dao.classes.StudentDao;
import persistance.dao.interfaces.IStudentDao;
import persistance.entities.Student;

public class StudentBLL implements IStudentBLL {
	
	private IStudentDao studentDao;
	
	public StudentBLL()
	{
		this.studentDao = new StudentDao();
	}
	
	// find student by studentID 	
	public Student findById(int id) throws Exception {
		
		Student student = studentDao.findById(id);
		if (student == null) { 
			throw new Exception("The student with id = " + id + " was not found!");
		}
		return student;
	}
	
    // get student by username	
	 public Student findByUsername(String username) throws Exception {
		
		Student student = studentDao.findByUsername(username);
		if (student == null) {
			throw new Exception("The student with username = " + username + " was not found!");
		}
		return student;
	}
    
    // find student by account
	 public Student findByAccount(String username, String password) throws Exception {
		
		Student student = (Student)((StudentDao)studentDao).findByAccount(username,password);
		if (student == null) {
			throw new Exception("The student account with username = " + username + " was not found!");
		}
		return student;
	}
	
	// get all the students	
	public List<Student> findAll()
	{
		return studentDao.findAll();
	}
	
	// insert student	
	public int insert(Student student) {		
		return studentDao.insert(student);	
	}
	
	//update student
	public void update(Student student)
	{
		studentDao.update(student);
	}
	
	//delete student
	public void delete(int studentID) 
	{
		Student student = studentDao.findById(studentID);
		studentDao.delete(studentID);
	}


}
