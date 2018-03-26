package business.classes;

import java.util.ArrayList;
import java.util.List;

import business.interfaces.IStudentBLL;
import persistance.dao.classes.*;
import persistance.dao.interfaces.*;
import persistance.entities.*;

public class StudentBLL implements IStudentBLL {
	
	private IStudentDao studentDao;
	private IEnrollmentDao enrollmentDao;
	private ICourseDao courseDao;
	
	
	public StudentBLL()
	{
		this.studentDao = new StudentDao();
		this.courseDao = new CourseDao();
		this.enrollmentDao =  new EnrollmentDao();
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
		
		Student student = studentDao.findByAccount(username,password);
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

	// get the courses of a student
	public List<Course> findStudentCourses(int studentID) {
		List<Course> allCourses = new ArrayList<Course>();
		
		List<Enrollment> allEnrollments = enrollmentDao.findByStudentId(studentID);
		for(Enrollment e : allEnrollments)
		{
			Course course = courseDao.findById(e.getCourseID());
			allCourses.add(course);
		}
		
		return allCourses;
	}
}
