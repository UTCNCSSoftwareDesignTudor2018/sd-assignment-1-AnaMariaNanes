package business.interfaces;

import java.util.List;

import persistance.entities.Course;

public interface ICourseBLL {
      	
	public Course findById(int id) throws Exception ;
	public Course findByName(String name) throws Exception ;
	public List<Course> findAll();
	public int insert(Course course);
	public void update(Course course);
	public void delete(int courseID);
}
