package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.classes.CourseBLL;
import business.interfaces.ICourseBLL;
import persistance.entities.Course;
import persistance.entities.Enrollment;
import persistance.entities.Student;
import persistance.entities.Teacher;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentReport extends JFrame {

	private JPanel contentPane;
	private List<Enrollment> finished_courses;
	private List<Enrollment> enrolled_courses;
	private Student student;
	private Teacher teacher;
	private JTextField textField;
	private JLabel lblFinishedCourses;
	private JLabel lblEnrolledCourses;
	private ICourseBLL courseBLL;
	private JList list;
	private JList list_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblTotal;
	private JTextField textField_3;

	public StudentReport(Student student, Teacher teacher,List<Enrollment> finished_courses,List<Enrollment> enrolled_courses) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 454);
		setTitle("Student Report");
		
		this.finished_courses =	finished_courses;
		this.enrolled_courses =  enrolled_courses;
		this.student = student;
		this.teacher = teacher;
		this.courseBLL = new CourseBLL();
			
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudent = new JLabel("Student: ");
		lblStudent.setBounds(30, 13, 56, 16);
		contentPane.add(lblStudent);
		
		textField = new JTextField();
		textField.setBounds(90, 10, 156, 22);
		contentPane.add(textField);
		textField.setText(student.getName());
		textField.setEditable(false);
		textField.setColumns(10);
		
			
		lblFinishedCourses = new JLabel("Finished Courses");
		lblFinishedCourses.setBounds(65, 76, 117, 16);
		contentPane.add(lblFinishedCourses);
		
		lblEnrolledCourses = new JLabel("Enrolled Courses");
		lblEnrolledCourses.setBounds(359, 76, 107, 16);
		contentPane.add(lblEnrolledCourses);
			
		// list of enrolled courses:
		list = new JList();
		List<String> enrolled_records =  new ArrayList<String>();
		for(Enrollment en: enrolled_courses)
		{
			String courseName;
			try {
				courseName = courseBLL.findById(en.getCourseID()).getName();
				String record = courseName + "    " + en.getExamGrade();
				enrolled_records.add(record);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
			
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(String st: enrolled_records)
		{
			listModel.addElement(st);
		}
		
		list = new JList<>(listModel);
		list.setBounds(30, 114, 261, 230);
		contentPane.add(list);
		
		
		// list of finised courses:
		list_1 = new JList();
		List<String> finished_records =  new ArrayList<String>();
		for(Enrollment en: finished_courses)
		{
			String courseName;
			try {
				courseName = courseBLL.findById(en.getCourseID()).getName();
				String record = courseName + "    " + en.getExamGrade();
				finished_records.add(record);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
					
		DefaultListModel<String> listModel_1 = new DefaultListModel<>();
		for(String st: finished_records)
		{
			listModel_1.addElement(st);
		}
				
		// create the list
		list_1 = new JList<>(listModel_1);
		list_1.setBounds(327, 114, 261, 230);
		contentPane.add(list_1);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TeacherAccountView frame = new TeacherAccountView(teacher);
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(457, 369, 97, 25);
		contentPane.add(btnBack);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 73, 48, 22);
		textField_1.setEditable(false);
		textField_1.setText(String.valueOf(enrolled_courses.size()));
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		textField_2.setText(String.valueOf(finished_courses.size()));
		textField_2.setBounds(467, 73, 48, 22);
		contentPane.add(textField_2);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(30, 373, 56, 16);
		contentPane.add(lblTotal);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		textField_3.setText(String.valueOf(enrolled_courses.size()  + finished_courses.size() ));
		textField_3.setBounds(77, 370, 48, 22);
		contentPane.add(textField_3);
	}
}
