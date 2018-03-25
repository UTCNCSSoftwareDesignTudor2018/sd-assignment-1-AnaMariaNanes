package persistance.dao.interfaces;

import java.util.List;

import persistance.entities.Grade;



public interface IGradeDao {
	
	public Grade findById(int id);
	public List<Grade> findAll();
	public List<Grade> findByEnrollmentId(int enrollmentID);
	public int insert(Grade grade);
	public void update(Grade grade);
	public void delete(int id);

}
