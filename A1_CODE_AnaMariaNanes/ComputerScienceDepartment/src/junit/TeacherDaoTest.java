package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import persistance.dao.classes.TeacherDao;
import persistance.dao.interfaces.ITeacherDao;
import persistance.entities.Teacher;

class TeacherDaoTest {

	@Test
	void test() {
		ITeacherDao teacherDao = new TeacherDao();
		
		// get by id
		Teacher teacher1 = teacherDao.findById(1);
		assertEquals(teacher1.getName(),"Popescu Mircea ");
		
		// insert
		Teacher newTeacher = new Teacher("Cornea Diana","cornea.cs","45678");
		List<Teacher> allTeachers_init = teacherDao.findAll();
		teacherDao.insert(newTeacher);
		List<Teacher> allTeachers_final = teacherDao.findAll(); 
		assertEquals(allTeachers_init.size()+1,allTeachers_final.size());
		
		// find all
		List<Teacher> allTeachers = teacherDao.findAll();
		assertEquals(allTeachers.size(),11);
		
		//update
		Teacher teacherToUpdate =  teacherDao.findById(1);
		teacherToUpdate.setPassword("99088");
		teacherDao.update(teacherToUpdate);
		Teacher teacherUpdated =  teacherDao.findById(1);
		assertEquals(teacherUpdated.getPassword(),"99088");
		
	
		
	}
}
		