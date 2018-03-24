package business;

import java.util.List;

import persistance.entities.Student;

public interface IStudentBLL {
	
	public Student findById(int id);
    public Student findByUsername(String username);
    public Student findByAccount(String username, String password);
    public List<Student> findAll();
    public int insert(Student student);
    public void update(int studentID, Student student);
    public void delete(int studentID);
    
}
