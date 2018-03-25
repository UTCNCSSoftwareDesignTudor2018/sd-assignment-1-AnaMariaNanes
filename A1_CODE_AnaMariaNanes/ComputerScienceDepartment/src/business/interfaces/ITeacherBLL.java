package business.interfaces;

import java.util.List;

import persistance.entities.Teacher;

public interface ITeacherBLL {

	public Teacher findById(int id) throws Exception ;
    public List<Teacher> findAll();
    public int insert(Teacher teacher);
    public void update(Teacher teacher);
    public void delete(int teacherID);

}
