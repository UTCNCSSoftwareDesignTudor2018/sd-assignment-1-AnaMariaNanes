package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import business.classes.*;
import business.interfaces.*;
import persistance.entities.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	
	private IStudentBLL studentBLL;
	private ITeacherBLL teacherBLL;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 650, 500);
		setTitle("Computer Science Department Page");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		studentBLL = new StudentBLL();
		teacherBLL = new TeacherBLL();
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Computer Science Department!");
		lblWelcomeToThe.setBounds(170, 34, 288, 16);
		contentPane.add(lblWelcomeToThe);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(50, 104, 80, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 133, 78, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(152, 101, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(152, 130, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				char[] pass= textField_1.getPassword();
				String password = String.valueOf(pass);
				
				// try to find student account
				try {
					Student student = studentBLL.findByUsername(username);
					if(student.getPassword().equals(password))
					{
						StudentAccountView frame = new StudentAccountView(student);
						frame.setVisible(true);
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, 
		                        "Incorrect password for student account.", 
		                        "Log in error", 
		                        JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					
					// try to find teacher account
					try {
						Teacher teacher = teacherBLL.findByUsername(username);
						if(teacher.getPassword().equals(password))
						{
						TeacherAccountView frame = new TeacherAccountView(teacher);
						frame.setVisible(true);
						setVisible(false);
						}
						else
						{
							JOptionPane.showMessageDialog(null, 
			                        "Incorrect password for teacher account.", 
			                        "Log in error", 
			                        JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, 
		                        "No account exists for provided username.", 
		                        "Log in error", 
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
		
			}
		});
		btnLogIn.setBounds(119, 176, 97, 25);
		contentPane.add(btnLogIn);
		
		JButton btnCreateStudentAccount = new JButton("Create Student Account");
		btnCreateStudentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRegistrationView frame = new StudentRegistrationView();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnCreateStudentAccount.setBounds(50, 273, 178, 25);
		contentPane.add(btnCreateStudentAccount);
		
		JButton btnCreateTeacherAccount = new JButton("Create Teacher Account");
		btnCreateTeacherAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherRegistrationView frame = new TeacherRegistrationView();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnCreateTeacherAccount.setBounds(264, 273, 178, 25);
		contentPane.add(btnCreateTeacherAccount);
	}

}
