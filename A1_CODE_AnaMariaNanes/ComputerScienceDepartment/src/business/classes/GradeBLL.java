package business.classes;

import java.util.List;

import business.interfaces.IGradeBLL;
import persistance.dao.classes.GradeDao;
import persistance.dao.interfaces.IGradeDao;
import persistance.entities.Enrollment;
import persistance.entities.Grade;
import persistance.entities.Teacher;

public class GradeBLL implements IGradeBLL {

    private IGradeDao gradeDao;
    
    public GradeBLL()
    {
    	this.gradeDao = new GradeDao();
    }
	
    // find grade by id
    public Grade findById(int id) throws Exception {
		Grade grade= gradeDao.findById(id);
		if (grade == null) { 
			throw new Exception("The grade with id = " + id + " was not found!");
		}
		return grade;
	}


	// find all grades
    public List<Grade> findAll() {
		return gradeDao.findAll();
	}


    // find by enrollmentID
	public List<Grade> findByEnrollmentId(int enrollmentID) {
		return gradeDao.findByEnrollmentId(enrollmentID);
	}


	//insert grade
	public int insert(Grade grade) {
		return gradeDao.insert(grade);
	}

	
	//update grade
	public void update(Grade grade) {
		gradeDao.update(grade);	
		
	}

    //delete grade
	public void delete(int id) {
		Grade grade = gradeDao.findById(id);
		gradeDao.delete(id);
	}

}
