package business.validators;

import java.util.List;

import business.classes.*;
import business.interfaces.*;
import persistance.entities.Student;

public class StudentValidator {
	
	private IStudentBLL studentBLL;
	
	public StudentValidator()
	{
		this.studentBLL =  new StudentBLL();
	}
	
	public String validateStudent(Student st)
	{
        String message = "correct";
		
        if(st.getName().equals("") || st.getName().equals("")  || (! isNameUnique(st.getName()))) 
        	return message = "Wrong name field format";
        if(st.getCardID() == null || st.getCardID().equals("")  || st.getCardID().length() != 10 || ( ! st.getCardID().matches("[0-9]+")) || (! isCardIDUnique(st.getCardID())))
        	return message = "Wrong cardID field format";
        if(st.getCnp()== null || st.getCnp().equals("")  || st.getCnp().length() != 13|| ( ! st.getCardID().matches("[0-9]+")) || (! isCnpUnique(st.getCnp()))) 
        	return message = "Wrong cnp field format";
        if(st.getAddress() == null || st.getAddress().equals("")  ) 
        	return message = "Wrong address field format";
        if(st.getGroupID() == null || st.getGroupID().equals("")  || st.getGroupID().length() != 5 || ( ! st.getCardID().matches("[0-9]+"))) 
        	return message = "Wrong groupID field format";
        if(st.getUsername() == null || st.getUsername().equals("")  || (! isUsernameUnique(st.getUsername())))
        	return message = "Wrong username field format";
        if(st.getPassword() == null || st.getPassword().equals("")  || st.getPassword().length() != 5 || ( ! st.getCardID().matches("[0-9]+"))) 
        	return message = "Wrong password field format";
    
		return message;
	}
	
	public String validateUpdateStudent(Student st)
	{
        String message = "correct";
		
        if(st.getName().equals("") || st.getName().equals("") ) 
        	return message = "Wrong name field format";
        if(st.getCardID() == null || st.getCardID().equals("")  || st.getCardID().length() != 10 || ( ! st.getCardID().matches("[0-9]+")) )
        	return message = "Wrong cardID field format";
        if(st.getCnp()== null  || st.getCnp().equals("") || st.getCnp().length() != 13|| ( ! st.getCardID().matches("[0-9]+")) ) 
        	return message = "Wrong cnp field format";
        if(st.getAddress() == null || st.getAddress().equals("") ) 
        	return message = "Wrong address field format";
        if(st.getGroupID() == null || st.getGroupID().equals("")  || st.getGroupID().length() != 5 || ( ! st.getCardID().matches("[0-9]+"))) 
        	return message = "Wrong groupID field format";
        if(st.getUsername() == null || st.getUsername().equals("") )
        	return message = "Wrong username field format";
        if(st.getPassword() == null || st.getPassword().equals("") || st.getPassword().length() != 5 || ( ! st.getCardID().matches("[0-9]+"))) 
        	return message = "Wrong password field format";
    
		return message;
	}
	
	private boolean isNameUnique(String name)
	{
		List<Student> allStud = studentBLL.findAll();
		for(Student st : allStud)
		{
			if(st.getName().equals(name))
				return false;
		}
		
		return true;
	}
	
	private boolean isUsernameUnique(String username)
	{
		List<Student> allStud = studentBLL.findAll();
		for(Student st : allStud)
		{
			if(st.getUsername().equals(username))
				return false;
		}
		
		return true;
	}
	
	private boolean isCardIDUnique(String cardID)
	{
		List<Student> allStud = studentBLL.findAll();
		for(Student st : allStud)
		{
			if(st.getCardID().equals(cardID))
				return false;
		}
		
		return true;
	}
	
	private boolean isCnpUnique(String cnp)
	{
		List<Student> allStud = studentBLL.findAll();
		for(Student st : allStud)
		{
			if(st.getCnp().equals(cnp))
				return false;
		}
		
		return true;
	}

}
