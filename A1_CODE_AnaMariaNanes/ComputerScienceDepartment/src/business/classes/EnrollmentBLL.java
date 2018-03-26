package business.classes;

import java.util.List;

import business.interfaces.IEnrollmentBLL;
import persistance.dao.classes.EnrollmentDao;
import persistance.dao.interfaces.IEnrollmentDao;
import persistance.entities.Enrollment;
import persistance.entities.Student;

public class EnrollmentBLL implements IEnrollmentBLL{

     private IEnrollmentDao enrollmentDao;
     
     public EnrollmentBLL()
     {
    	 this.enrollmentDao = new EnrollmentDao();
     }
	
	
	// find enrollment by enrollmentID
     public Enrollment findById(int id) throws Exception {
		Enrollment enrollment = enrollmentDao.findById(id);
		if (enrollment == null) { 
			throw new Exception("The enrollment with id = " + id + " was not found!");
		}
		return enrollment;
	}

    // find enrollment by studentID
	public List<Enrollment> findByStudentId(int studentID) throws Exception {
		return enrollmentDao.findByStudentId(studentID);
	}


	// find enrollment by courseID
	public List<Enrollment> findByCourseId(int courseID) throws Exception {
		return enrollmentDao.findByCourseId(courseID);
	}

    // find all enrollments
	public List<Enrollment> findAll() {
		return enrollmentDao.findAll();
	}


	public int insert(Enrollment enrollment) {
		return enrollmentDao.insert(enrollment);	
	}


	public void update(Enrollment enrollment) {
		enrollmentDao.update(enrollment);		
	}


	public void delete(int enrollmentID) {
		Enrollment enrollment = enrollmentDao.findById(enrollmentID);
		enrollmentDao.delete(enrollmentID);
		
	}

    // find enrollment by courseID and studentID
	public Enrollment findByCourseAndStudent(int studentID, int courseID){
		Enrollment chosenEnroll =  new Enrollment();
		List<Enrollment> enrollOfStud = enrollmentDao.findByStudentId(studentID);
		for(Enrollment en : enrollOfStud)
		{
			if(en.getCourseID() == courseID) {
				chosenEnroll = en;
			}
		}
		
		return chosenEnroll;
	}

}
