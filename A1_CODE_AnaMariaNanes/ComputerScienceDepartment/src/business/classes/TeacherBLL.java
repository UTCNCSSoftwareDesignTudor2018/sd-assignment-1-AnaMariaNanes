package business.classes;

import java.util.List;

import business.interfaces.ITeacherBLL;
import persistance.dao.classes.StudentDao;
import persistance.dao.classes.TeacherDao;
import persistance.dao.interfaces.ITeacherDao;
import persistance.entities.Student;
import persistance.entities.Teacher;

public class TeacherBLL implements ITeacherBLL {

	private ITeacherDao teacherDao;
	
	public TeacherBLL()
	{
		this.teacherDao = new TeacherDao();
	}
	
	// find teacher by teacherID
	public Teacher findById(int id) throws Exception {
		
		Teacher teacher = teacherDao.findById(id);
		if (teacher == null) { 
			throw new Exception("The teacher with id = " + id + " was not found!");
		}
		return teacher;
	}
	
	// find student by account
     public Teacher findByAccount(String username, String password) throws Exception {
			
			Teacher teacher = teacherDao.findByAccount(username,password);
			if (teacher == null) {
				throw new Exception("The teacher account with username = " + username + " was not found!");
			}
			return teacher;
		}

 	// find teacher by username
     public Teacher findByUsername(String username) throws Exception {
    		Teacher teacher = teacherDao.findByUsername(username);
    		if (teacher == null) {
    			throw new Exception("The teacher account with username = " + username + " was not found!");
    		}
    		return teacher;
	}
     
     //find all teachers
	public List<Teacher> findAll() {
		return teacherDao.findAll();
	}

	// insert teacher
	public int insert(Teacher teacher) {
		return teacherDao.insert(teacher);	
	}

	//update teacher
	public void update(Teacher teacher) {
		teacherDao.update(teacher);
		
	}
   
	//delete teacher
	public void delete(int teacherID) {
		Teacher teacher = teacherDao.findById(teacherID);
		teacherDao.delete(teacherID);
	}

}
