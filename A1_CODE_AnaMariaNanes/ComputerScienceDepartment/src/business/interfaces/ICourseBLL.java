package business.interfaces;

import java.util.List;

import persistance.entities.Course;
import persistance.entities.Student;

public interface ICourseBLL {
      	
	public Course findById(int id) throws Exception ;
	public Course findByName(String name) throws Exception ;
	public Course findByTeacherID(int teacherID) throws Exception;
	public List<Course> findAll();
	public int insert(Course course);
	public void update(Course course);
	public void delete(int courseID);
}
