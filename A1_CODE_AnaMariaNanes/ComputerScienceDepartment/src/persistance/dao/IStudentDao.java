package persistance.dao;

import java.util.List;

import persistance.entities.Student;

public interface IStudentDao {
	
	public Student findById(int studentID);
	public Student findByUsername(String username) ;
	public Student findByAccount(String username, String password) ;
	public List<Student> findAll();
	public int insert(Student student);
	public void update(int studentID,Student student);
	public void delete(int studentID);
}
