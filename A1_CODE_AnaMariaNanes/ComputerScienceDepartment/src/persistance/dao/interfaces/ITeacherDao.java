package persistance.dao.interfaces;

import java.util.List;

import persistance.entities.Teacher;

public interface ITeacherDao {
	
	public Teacher findById(int teacherID);
	public List<Teacher> findAll();
	public int insert(Teacher teacher);
	public void update(Teacher teacher);
	public void delete(int teacherID);

}
