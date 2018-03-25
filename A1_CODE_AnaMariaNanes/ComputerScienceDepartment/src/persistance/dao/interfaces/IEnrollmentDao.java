package persistance.dao.interfaces;

import java.util.List;

import persistance.entities.Enrollment;


public interface IEnrollmentDao {

	public Enrollment findById(int enrollmentID);
	public List<Enrollment> findByStudentId(int studentID);
	public List<Enrollment> findByCourseId(int courseID);
	public List<Enrollment> findAll();
	public int insert(Enrollment enrollment);
	public void update(Enrollment enrollment);
	public void delete(int enrollmentID);
}
