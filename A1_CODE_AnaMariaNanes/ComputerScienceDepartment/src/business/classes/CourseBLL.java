package business.classes;

import java.util.List;

import business.interfaces.ICourseBLL;
import persistance.dao.classes.CourseDao;
import persistance.dao.interfaces.ICourseDao;
import persistance.entities.Course;
import persistance.entities.Student;

public class CourseBLL implements ICourseBLL{
	
	private ICourseDao courseDao;
	
	public CourseBLL()
	{
		courseDao = new CourseDao();
	}
	
	// find course by courseID
	public Course findById(int id) throws Exception {
		
		Course course = courseDao.findById(id);
		if (course == null) { 
			throw new Exception("The course with id = " + id + " was not found!");
		}
		return course;
	}
	
	// find course by name
	public Course findByName(String name) throws Exception{	
		Course course = courseDao.findByName(name);
		if (course == null) { 
			throw new Exception("The course with name = " + name + " was not found!");
		}
		return course;
	}
	
	// find by teacherID
	public Course findByTeacherID(int teacherID) throws Exception{
		Course course = courseDao.findByTeacherID(teacherID);
		if (course == null) { 
			throw new Exception("The course with teacherID = " + teacherID + " was not found!");
		}
		return course;
	}
	
	// get all the courses	
	public List<Course> findAll()
	{
		return courseDao.findAll();
	}
		
	
	// insert course
	public int insert(Course course) 
	{		
		return courseDao.insert(course);	
	}
	
	//update course
	public void update(Course course)
	{
		courseDao.update(course);
	}
	
	//delete course 
	public void delete(int courseID)
	{
		Course course = courseDao.findById(courseID);
		courseDao.delete(courseID);
	}

	
}
