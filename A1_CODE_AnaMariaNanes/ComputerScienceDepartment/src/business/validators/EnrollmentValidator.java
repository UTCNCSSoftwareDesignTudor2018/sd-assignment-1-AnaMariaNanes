package business.validators;

import persistance.entities.Enrollment;

public class EnrollmentValidator {
    

	public EnrollmentValidator() {
	}
	
	public String validateEnrollment(Enrollment enrollment)
	{
		String message = "correct";
		
		if((! enrollment.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) || (! enrollment.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}"))) 
			return message =  "Wrong date format";
		
		String[] start_dates= enrollment.getStartDate().split("-");
		String[] end_dates = enrollment.getEndDate().split("-");
		
		int start_course = Integer.valueOf(start_dates[0] + start_dates[1] + start_dates[2]);
		int end_course = Integer.valueOf(end_dates[0] + end_dates[1] + end_dates[2]);
		
		if(start_course > end_course)
			return message = "End date not correct.";
		
		if(Integer.valueOf(start_dates[1]) <1 || Integer.valueOf( start_dates[1])>12 || Integer.valueOf(end_dates[1])  <1|| Integer.valueOf(end_dates[1]) >12 )
			return message = "Wrong month format";
		
		if(Integer.valueOf(start_dates[2]) <1 || Integer.valueOf( start_dates[2])>31 || Integer.valueOf(end_dates[2])  <1|| Integer.valueOf(end_dates[1]) >31 )
			return message = "Wrong day format";
		
		return message;
	}
	
	public String validateDates(String start_dates, String end_dates)
	{
		String message = "correct";
		
		if((! start_dates.matches("\\d{4}-\\d{2}-\\d{2}")) || (! end_dates.matches("\\d{4}-\\d{2}-\\d{2}"))) 
			return message =  "Wrong date format";
		
		String[] start= start_dates.split("-");
		String[] end = end_dates.split("-");
		
		int start_course = Integer.valueOf(start[0] + start[1] + start[2]);
		int end_course = Integer.valueOf(end[0] + end[1] + end[2]);
		
		if(start_course > end_course)
			return message = "End date not correct.";
		
		if(Integer.valueOf(start[1]) <1 || Integer.valueOf( start[1])>12 || Integer.valueOf(end[1])  <1|| Integer.valueOf(end[1]) >12 )
			return message = "Wrong month format";
		
		if(Integer.valueOf(start[2]) <1 || Integer.valueOf( start[2])>31 || Integer.valueOf(end[2])  <1|| Integer.valueOf(end[1]) >31 )
			return message = "Wrong day format";
		
		return message;
	}
	
	
}
