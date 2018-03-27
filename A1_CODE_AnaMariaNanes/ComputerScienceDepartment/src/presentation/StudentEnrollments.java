package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.classes.*;
import business.interfaces.*;
import persistance.entities.*;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentEnrollments extends JFrame {

	private JPanel contentPane;
	private Student studentAccount;
	private IStudentBLL studentBLL;
	private ICourseBLL courseBLL;
	private ITeacherBLL teacherBLL;
	private IEnrollmentBLL enrollmentBLL;
	private IGradeBLL gradeBLL;
	private JList list;


	public StudentEnrollments(Student studentAccount ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 650, 500);
		setTitle("Student Account: " + studentAccount.getName() + " COURSES");
		
		this.studentAccount =  studentAccount;
		this.studentBLL = new StudentBLL();
		this.courseBLL = new CourseBLL();
		this.teacherBLL = new TeacherBLL();
		this.enrollmentBLL = new EnrollmentBLL();
		this.gradeBLL = new GradeBLL();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseEnrollments = new JLabel("Course enrollments:");
		lblCourseEnrollments.setBounds(36, 26, 133, 28);
		contentPane.add(lblCourseEnrollments);
		
		JLabel lblCourseDetails = new JLabel("Course details:");
		lblCourseDetails.setBounds(312, 13, 107, 16);
		contentPane.add(lblCourseDetails);
		
		JLabel lblCourseGrades = new JLabel("Course Grades:");
		lblCourseGrades.setBounds(312, 212, 94, 16);
		contentPane.add(lblCourseGrades);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(312, 42, 240, 157);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(312, 241, 240, 107);
		contentPane.add(textPane_1);
		
		
		list = new JList();
		List<Course> allCourses = studentBLL.findStudentCourses(studentAccount.getStudentID());
		List<String> courseNames = new ArrayList<String>();
		for(Course c : allCourses)
		{  courseNames.add(c.getName()); }
		
		//create the JList model
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(String st: courseNames)
		{
			listModel.addElement(st);
		}
		
		// create the list
		list = new JList<>(listModel);
		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				JList theList = (JList) e.getSource();
				if(e.getClickCount() == 2) 
				{
					 int index = theList.locationToIndex(e.getPoint());
					 String courseName = courseNames.get(index);
					 try {
						Course chosenCourse = courseBLL.findByName(courseName);
						Teacher teacher = teacherBLL.findById(chosenCourse.getTeacherID());
						String teacherName = teacher.getName();
						int credits = chosenCourse.getCredits();
						String room = chosenCourse.getRoom();
						
						Enrollment enrollment = enrollmentBLL.findByCourseAndStudent(studentAccount.getStudentID(), chosenCourse.getCourseID());						
												
						String line1 = "Course:   " + courseName + "\n";
						String line2 = "Teacher:   " + teacherName + "\n";
						String line3 = "Credits:   " + credits + "\n";
						String line4 = "Room:   " + room + "\n";
						String line5 = "Start date:   " + enrollment.getStartDate() +  "\n";
						String line6 = "End date:   " + enrollment.getEndDate() +  "\n";
						String  content = line1 + line2 + line3 + line4 + line5 + line6;
						textPane.setEditable(false);
						textPane.setText(content);
						
						float examGrade = enrollment.getExamGrade();
						List<Grade> allGrades = gradeBLL.findByEnrollmentId(enrollment.getEnrollmentID());
						
						//create String of grades
						String theGrades = "";
						for(Grade gr : allGrades)
						{
							theGrades = theGrades + gr.getGrade() +", ";
						}
						
						String line2_1 = "Course:   " + courseName + "\n";
						String line2_2;
						if(!theGrades.equals("")) {
						line2_2 = "Grades:   " + theGrades + "\n";
						}
						else
						{
							line2_2 = "Grades:   " + "No grades yet." + "\n";
						}
						String line2_3 = "Final Exam Grade:   " + examGrade +  "\n";
						String content2 = line2_1 + line2_2 + line2_3;
						textPane_1.setEditable(false);
						textPane_1.setText(content2);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		list.setBounds(36, 67, 200, 190);
		contentPane.add(list);
		
		JButton btnRemoveEnrollment = new JButton("Remove Enrollment");
		btnRemoveEnrollment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedCourse = list.getSelectedValue();
				String courseName = (String) selectedCourse;
				
				try {
					Course toRemoveCourse = courseBLL.findByName(courseName);
					Enrollment enroll= enrollmentBLL.findByCourseAndStudent(studentAccount.getStudentID(), toRemoveCourse.getCourseID());
					enrollmentBLL.delete(enroll.getEnrollmentID());
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemoveEnrollment.setBounds(36, 292, 170, 36);
		contentPane.add(btnRemoveEnrollment);
		
		JButton btnNewEnrollment = new JButton("New Enrollment");
		btnNewEnrollment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentNewEnrollment frame = new StudentNewEnrollment(studentAccount);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewEnrollment.setBounds(312, 361, 170, 36);
		contentPane.add(btnNewEnrollment);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StudentAccount frame = new StudentAccount(studentAccount);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(489, 404, 97, 25);
		contentPane.add(btnBack);
		
	}
}
