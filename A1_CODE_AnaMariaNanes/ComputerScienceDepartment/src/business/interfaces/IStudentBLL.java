package business.interfaces;

import java.util.List;

import persistance.entities.Course;
import persistance.entities.Student;

public interface IStudentBLL {
	
	public Student findById(int id) throws Exception ;
    public Student findByUsername(String username) throws Exception ;
    public Student findByAccount(String username, String password) throws Exception ;
	public Student findByName(String name) throws Exception;
    public List<Course> findStudentCourses(int studentID);
    public List<Student> findEnrolledStudents(int teacherID);
    public List<Student> findAll();
    public int insert(Student student);
    public void update(Student student);
    public void delete(int studentID);
    
}
