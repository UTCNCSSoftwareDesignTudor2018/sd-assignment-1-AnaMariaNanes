package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.classes.*;
import business.interfaces.*;
import persistance.entities.*;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentNewEnrollment extends JFrame {

	private JPanel contentPane;
	private Student studentAccount;
	private ICourseBLL courseBLL;
	private IStudentBLL studentBLL;
	private IEnrollmentBLL enrollmentBLL;
	private JList list;
	private JTextField textField;
	private JTextField textField_1;


	public StudentNewEnrollment(Student studentAccount) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 650, 500);
		setTitle("Student: " + studentAccount.getName() + "  New Enrollment.");
		
		this.studentAccount = studentAccount;
		this.studentBLL = new StudentBLL();
		this.courseBLL = new CourseBLL();
		this.enrollmentBLL = new EnrollmentBLL();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list= new JList();
		List<Course> allCoursesOfStudent = studentBLL.findStudentCourses(studentAccount.getStudentID());
		List<Course> allCourses = courseBLL.findAll();
		List<Course> remainingCourses= new ArrayList<Course>();
		for(Course allC : allCourses) {
			int ok =1;
			for(Course studC : allCoursesOfStudent) {
				if(allC.getCourseID() == studC.getCourseID()) {
					ok=0;
				}
			}
			if(ok==1) {
				remainingCourses.add(allC);
			}
		}
		
		List<String> courseNames = new ArrayList<String>();
		for(Course c : remainingCourses)
		{  courseNames.add(c.getName()); }
		
		//create the JList model
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(String st: courseNames)
			{
				listModel.addElement(st);
			}
				
		// create the list
		list = new JList<>(listModel);
		
		list.setBounds(27, 69, 271, 261);
		contentPane.add(list);		
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(339, 69, 76, 25);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(339, 109, 76, 25);
		contentPane.add(lblEndDate);
		
		textField = new JTextField();
		textField.setBounds(417, 67, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(417, 110, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblChooseFromThe = new JLabel("Choose from the above courses:");
		lblChooseFromThe.setBounds(35, 18, 205, 25);
		contentPane.add(lblChooseFromThe);
		
		JButton btnMakeEnrollment = new JButton("Make enrollment");
		btnMakeEnrollment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String startDate = textField.getText();
				String endDate = textField_1.getText();
				
				Object selectedCourse = list.getSelectedValue();
				String courseName = (String) selectedCourse;
				try {
					Course selCourse = courseBLL.findByName(courseName);
					Enrollment newEnrollment = new Enrollment(studentAccount.getStudentID(),selCourse.getCourseID(),0,startDate,endDate);
					enrollmentBLL.insert(newEnrollment);
					
					JOptionPane.showMessageDialog(null, 
	                        "The enrollment was performed.", 
	                        "Enrollment Status", 
	                        JOptionPane.INFORMATION_MESSAGE);
					
					setVisible(false);
					StudentEnrollments frame = new StudentEnrollments(studentAccount);
					frame.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnMakeEnrollment.setBounds(339, 189, 147, 51);
		contentPane.add(btnMakeEnrollment);
		
	
	}
}
