package business.interfaces;

import java.util.List;

import persistance.entities.Student;

public interface IStudentBLL {
	
	public Student findById(int id) throws Exception ;
    public Student findByUsername(String username) throws Exception ;
    public Student findByAccount(String username, String password) throws Exception ;
    public List<Student> findAll();
    public int insert(Student student);
    public void update(Student student);
    public void delete(int studentID);
    
}
