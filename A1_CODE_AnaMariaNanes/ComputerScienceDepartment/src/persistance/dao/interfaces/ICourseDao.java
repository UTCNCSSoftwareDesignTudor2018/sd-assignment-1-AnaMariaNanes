package persistance.dao.interfaces;

import java.util.List;

import persistance.entities.Course;

public interface ICourseDao {
	
	public Course findById(int courseID);
	public Course findByName(String name);
	public Course findByTeacherID(int teacherID);
	public List<Course> findAll();
	public int insert(Course course);
	public void update(Course course);
	public void delete(int courseID);

}
