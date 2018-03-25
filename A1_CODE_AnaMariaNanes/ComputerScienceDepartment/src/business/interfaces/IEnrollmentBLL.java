package business.interfaces;

import java.util.List;

import persistance.entities.Enrollment;


public interface IEnrollmentBLL {
	
	public Enrollment findById (int id) throws Exception ;
    public List<Enrollment> findByStudentId (int studentID) throws Exception ;
    public List<Enrollment> findByCourseId (int courseID) throws Exception ;
    public List<Enrollment> findAll();
    public int insert(Enrollment enrollment);
    public void update(Enrollment enrollment);
    public void delete(int enrollmentID);

}
