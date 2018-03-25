package business.interfaces;

import java.util.List;

import persistance.entities.Grade;


public interface IGradeBLL {
	
	public Grade findById(int id) throws Exception ;
    public List<Grade> findAll();
    public List<Grade> findByEnrollmentId(int enrollmentID);
    public int insert(Grade grade);
    public void update(Grade grade);
    public void delete(int id);

}
