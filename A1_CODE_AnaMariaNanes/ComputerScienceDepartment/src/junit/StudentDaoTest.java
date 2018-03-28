package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import persistance.dao.classes.StudentDao;
import persistance.dao.interfaces.IStudentDao;
import persistance.entities.Student;

class StudentDaoTest {

	@Test
	void test() {
		IStudentDao studentDao= new StudentDao();
		
		// get by id
		Student student1 = studentDao.findById(1);
		assertEquals(student1.getName(),"Belu Madalina");
		
		// find all
		List<Student> allStud = studentDao.findAll();
		assertEquals(allStud.size(),18);
		
		//update
		Student studentToUpdate= studentDao.findById(1);
		studentToUpdate.setAddress("Brasov");
		studentDao.update(studentToUpdate);
		Student studentUpdated= studentDao.findById(1);
		assertEquals(studentUpdated.getAddress(),studentToUpdate.getAddress());
		
		// insert
		Student newStud = new Student("Delcea Ramona","1144122336","1909111111230","Sibiu","30990","ramona_d","12123");
		List<Student> allStud_init = studentDao.findAll();
		studentDao.insert(newStud);
		List<Student> allStud_final = studentDao.findAll(); 
		assertEquals(allStud_init.size()+1,allStud_final.size());
		
		
	}
	

	
	

}
