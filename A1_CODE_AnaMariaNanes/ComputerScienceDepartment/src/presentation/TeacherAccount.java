package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.xml.stream.events.StartElement;

import business.classes.*;
import business.interfaces.*;
import business.validators.EnrollmentValidator;
import business.validators.TeacherValidator;
import persistance.entities.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class TeacherAccount extends JFrame {

	private JPanel contentPane;
	private Teacher teacherAccount;
	private ITeacherBLL teacherBLL;
	private ICourseBLL courseBLL;
	private IStudentBLL studentBLL;
	private IEnrollmentBLL enrollmentBLL;
	private IGradeBLL gradeBLL;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JList list;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public TeacherAccount(Teacher teacherAccount) {
		
		this.teacherAccount =  teacherAccount;
		this.teacherBLL = new TeacherBLL();
		this.courseBLL = new CourseBLL();
		this.studentBLL = new StudentBLL();
		this.enrollmentBLL = new EnrollmentBLL();
		this.gradeBLL = new GradeBLL();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 700, 850, 700);
		setTitle("Teacher account: " + this.teacherAccount.getName());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeacherid = new JLabel("TeacherID: ");
		lblTeacherid.setBounds(17, 25, 77, 21);
		contentPane.add(lblTeacherid);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(17, 88, 77, 16);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(17, 59, 56, 16);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(17, 117, 77, 26);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(84, 24, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
	    textField.setEditable(false);
	    textField.setText(String.valueOf(teacherAccount.getTeacherID()));
		
		textField_1 = new JTextField();
		textField_1.setBounds(68, 53, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(teacherAccount.getName());
		
		textField_2 = new JTextField();
		textField_2.setBounds(84, 85, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(teacherAccount.getUsername());
		
		textField_3 = new JPasswordField();
		textField_3.setBounds(84, 117, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(teacherAccount.getPassword());
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int teacherID = Integer.valueOf(textField.getText());
				String name = textField_1.getText();
			    String username = textField_2.getText();
			    char[] pass = textField_3.getPassword();
			    String password = String.valueOf(pass);
			    
			    Teacher newTeacher = new Teacher(teacherID,name,username,password);
			    
			    TeacherValidator teacherValidator = new TeacherValidator();
				String message = teacherValidator.validateUpdateTeacher(newTeacher);
				if(message.equals("correct"))
				{
					teacherBLL.update(newTeacher);
					
					JOptionPane.showMessageDialog(null, 
	                        "Account updated.", 
	                        "Update Status", 
	                        JOptionPane.INFORMATION_MESSAGE);
					
					setTitle("Teacher account: " + name);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, 
	                         message, 
	                        "Update Status", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnUpdate.setBounds(51, 166, 97, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = teacherAccount.getTeacherID();
				teacherBLL.delete(id);
				JOptionPane.showMessageDialog(null, 
                        "The account has been deleted", 
                        "Account deletion", 
                        JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				HomePage frame = new HomePage();
				frame.setVisible(true);
				
			}
		});
		btnDeleteAccount.setBounds(35, 414, 128, 26);
		contentPane.add(btnDeleteAccount);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				HomePage frame = new HomePage();
				frame.setVisible(true);
			}
		});
		btnLogOut.setBounds(35, 376, 128, 25);
		contentPane.add(btnLogOut);
		
		
		
		JLabel lblCourse = new JLabel("Course: ");
		lblCourse.setBounds(259, 27, 56, 16);
		contentPane.add(lblCourse);
	
		JLabel lblStudents = new JLabel("Students: ");
		lblStudents.setBounds(403, 54, 77, 21);
		contentPane.add(lblStudents);
		
		JLabel lblExamGrade = new JLabel("Exam Grade:");
		lblExamGrade.setBounds(226, 446, 89, 26);
		contentPane.add(lblExamGrade);
		
		JButton btnAddExamGrade = new JButton("Add exam grade");
		btnAddExamGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Object selectedStudent = list.getSelectedValue();
				String studentName = (String) selectedStudent;
				
				if(selectedStudent == null)
				{		
					JOptionPane.showMessageDialog(null, 
	                        "No student chosen", 
	                        "Student Exam Grading", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try
					{
						float newGrade = Float.valueOf(textField_5.getText());
						Student selStudent;
						Course selCourse;
						try {
							selStudent = studentBLL.findByName(studentName);
							selCourse = courseBLL.findByTeacherID(teacherAccount.getTeacherID());
		                    Enrollment enrol = enrollmentBLL.findByCourseAndStudent(selStudent.getStudentID(), selCourse.getCourseID());
		                    enrol.setExamGrade(newGrade);
		                    enrollmentBLL.update(enrol);
		                    
		                    JOptionPane.showMessageDialog(null, 
			                        "Exam Grade updated.", 
			                        "Exam Grade", 
			                        JOptionPane.INFORMATION_MESSAGE);
		                    
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					catch(java.lang.NumberFormatException exp )
					{
						JOptionPane.showMessageDialog(null, 
		                        "Wrong grade format", 
		                        "Student Exam Grading", 
		                        JOptionPane.ERROR_MESSAGE);
					}			
				}			
			}
		});
		btnAddExamGrade.setBounds(336, 494, 144, 26);
		contentPane.add(btnAddExamGrade);
		
		textField_4 = new JTextField();
		textField_4.setBounds(320, 24, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_4.setEditable(false);

		Course theCourse;
		try {
			theCourse = courseBLL.findByTeacherID(teacherAccount.getTeacherID());

			textField_4.setText(theCourse.getName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		contentPane.add(textField_4);
		
		
		// populate the JList
		list = new JList();
		
		List<Student> allStudents = studentBLL.findEnrolledStudents(teacherAccount.getTeacherID());
		List<String> studentNames = new ArrayList<String>();
		for(Student st: allStudents)
		{  studentNames.add(st.getName()); }
		
		//create the JList model
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(String st: studentNames)
		{
			listModel.addElement(st);
		} 
		
		// create the list
		list = new JList<>(listModel);
		list.setBounds(248, 84, 403, 243);
		contentPane.add(list);
		
		textField_5 = new JTextField();
		textField_5.setBounds(330, 448, 116, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(13);
		
		JButton btnDisplayExamGrade = new JButton("Display Exam Grade");
		btnDisplayExamGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedStudent = list.getSelectedValue();
				String studentName = (String) selectedStudent;
				
				if(selectedStudent == null)
				{		
					JOptionPane.showMessageDialog(null, 
	                        "No student chosen", 
	                        "Student Exam Grade", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				
				Student selStudent;
				Course selCourse;
				try {
					selStudent = studentBLL.findByName(studentName);
					selCourse = courseBLL.findByTeacherID(teacherAccount.getTeacherID());
                    Enrollment enrol = enrollmentBLL.findByCourseAndStudent(selStudent.getStudentID(), selCourse.getCourseID());
                    Float grade = enrol.getExamGrade();
                    if(grade > 0)
                      textField_5.setText(String.valueOf(grade));
                    else textField_5.setText("Not grade.");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		btnDisplayExamGrade.setBounds(458, 447, 170, 25);
		contentPane.add(btnDisplayExamGrade);
		
		JButton btnGenerateStudentReport = new JButton("GENERATE STUDENT REPORT");
		btnGenerateStudentReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String startPeriod = textField_6.getText();
				String endPeriod = textField_7.getText();
			
				Object selectedStudent = list.getSelectedValue();
				String studentName = (String) selectedStudent;
				
				EnrollmentValidator enrollmentValidator = new EnrollmentValidator();
				
				if(selectedStudent == null)
				{		
					JOptionPane.showMessageDialog(null, 
	                        "No student chosen", 
	                        "Student Report", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				else
				{
                      String message = enrollmentValidator.validateDates(startPeriod, endPeriod);
					  if(message.equals("correct"))
					  {

							Student selStudent;
							Course selCourse;
							try {
								selStudent = studentBLL.findByName(studentName);
								selCourse = courseBLL.findByTeacherID(teacherAccount.getTeacherID());
			                    List<Enrollment> enrol = enrollmentBLL.findByStudentId(selStudent.getStudentID());
			                    List<Enrollment> finished_courses = new ArrayList<>();
			                    List<Enrollment> enrolled_courses = new ArrayList<>();
			                    for(Enrollment en : enrol)
			                    {
			                    	if(en.checkPeriod(startPeriod, endPeriod).equals("FINISHED"))
			                    			finished_courses.add(en);
			                    	if(en.checkPeriod(startPeriod, endPeriod).equals("ENROLLED"))
			                    	        enrolled_courses.add(en);
			                    }
			                    
			                    setVisible(false);
			                    StudentReport frame = new StudentReport(selStudent,teacherAccount,finished_courses,enrolled_courses);
								frame.setVisible(true);
			                   
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					  }
					  else
					  {
						   JOptionPane.showMessageDialog(null, 
			                        message, 
			                        "Student Report", 
			                        JOptionPane.ERROR_MESSAGE);
					  }
            
				}
			}
			
		});
		btnGenerateStudentReport.setBounds(320, 340, 245, 25);
		contentPane.add(btnGenerateStudentReport);
		
		textField_6 = new JTextField();
		textField_6.setBounds(333, 377, 116, 22);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(474, 377, 116, 22);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblPeriod = new JLabel("Period:");
		lblPeriod.setBounds(258, 380, 56, 16);
		contentPane.add(lblPeriod);
		
		JButton btnViewGrades = new JButton("View Grades");
		btnViewGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int teacherID = teacherAccount.getTeacherID();
				
				Object selectedStudent = list.getSelectedValue();
				String studentName = (String) selectedStudent;
				if(selectedStudent == null)
				{		
					JOptionPane.showMessageDialog(null, 
	                        "No student chosen", 
	                        "Student Exam Grades", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				Student selStudent;
				Course selCourse;
				try {
					selStudent = studentBLL.findByName(studentName);
					selCourse = courseBLL.findByTeacherID(teacherAccount.getTeacherID());
                    Enrollment enrol = enrollmentBLL.findByCourseAndStudent(selStudent.getStudentID(), selCourse.getCourseID());
                    int enrollmentID = enrol.getEnrollmentID();
                    List<Grade> allGrades = gradeBLL.findByEnrollmentId(enrollmentID);
                    String theGrades = "";
                    for(Grade gr: allGrades) {
                    	if(theGrades.equals(""))
                    		theGrades = theGrades + gr.getGrade();
                    	else
                    	   theGrades = theGrades + ", " + gr.getGrade();
                    }
                    textField_8.setText(theGrades);
                    
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		btnViewGrades.setBounds(226, 545, 110, 25);
		contentPane.add(btnViewGrades);
		
		textField_8 = new JTextField();
		textField_8.setBounds(360, 546, 268, 22);
		contentPane.add(textField_8);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		
		
		textField_9 = new JTextField();
		textField_9.setBounds(364, 588, 116, 22);
		contentPane.add(textField_9);
		textField_9.setColumns(10);		
		
		JButton btnInsertGrade = new JButton("Insert Grade");
		btnInsertGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int teacherID = teacherAccount.getTeacherID();
				
				Object selectedStudent = list.getSelectedValue();
				String studentName = (String) selectedStudent;
				
				if(selectedStudent == null)
				{		
					JOptionPane.showMessageDialog(null, 
	                        "No student chosen", 
	                        "Student Report", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				
				Student selStudent;
				Course selCourse;
				
				try
				{
					float grade = Float.valueOf(textField_9.getText());
					if(grade <4 || grade>10)
					{
						JOptionPane.showMessageDialog(null, 
		                        "Wrong grade format", 
		                        "Student Grade", 
		                        JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try {
							selStudent = studentBLL.findByName(studentName);
							selCourse = courseBLL.findByTeacherID(teacherAccount.getTeacherID());
		                    Enrollment enrol = enrollmentBLL.findByCourseAndStudent(selStudent.getStudentID(), selCourse.getCourseID());
		                    int enrollmentID = enrol.getEnrollmentID();
		                   
		                    Grade newGrade = new Grade(enrollmentID,grade);
		                    gradeBLL.insert(newGrade);
		                    
		                	JOptionPane.showMessageDialog(null, 
			                        "The student`s grade was added.", 
			                        "Grade received", 
			                        JOptionPane.INFORMATION_MESSAGE);
		                	textField_8.setText(textField_8.getText() + "," + grade);
		                	textField_9.setText("");
		                         
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
					
				}
				catch(java.lang.NumberFormatException exp )
				{
					JOptionPane.showMessageDialog(null, 
	                        "Wrong grade format", 
	                        "Student Grade", 
	                        JOptionPane.ERROR_MESSAGE);
				}	
				}
			}
		});
		btnInsertGrade.setBounds(226, 587, 110, 25);
		contentPane.add(btnInsertGrade);

		
	}
}
