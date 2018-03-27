package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import business.classes.TeacherBLL;
import business.interfaces.ITeacherBLL;
import business.validators.TeacherValidator;
import persistance.entities.Teacher;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField textField_3;

    private ITeacherBLL teacherBLL;
	
	public TeacherRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Teacher Registration");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		teacherBLL = new TeacherBLL();
		
		JLabel lblIntroduceTheRequired = new JLabel("Introduce the required data:");
		lblIntroduceTheRequired.setBounds(25, 23, 175, 16);
		contentPane.add(lblIntroduceTheRequired);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(35, 52, 67, 16);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(34, 85, 68, 16);
		contentPane.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(35, 114, 67, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(35, 143, 67, 16);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(116, 52, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 82, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(114, 111, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JPasswordField();
		textField_3.setBounds(114, 140, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText() + " " + textField_1.getText();
				String username = textField_2.getText();
				char[] pass = textField_3.getPassword();
				String password = String.valueOf(pass);
							
				Teacher newTeacher = new Teacher(name,username,password);
				TeacherValidator teacherValidator = new TeacherValidator();
				String message = teacherValidator.validateTeacher(newTeacher);
				if(message.equals("correct"))
				{
					teacherBLL.insert(newTeacher);
					
					JOptionPane.showMessageDialog(null, 
	                        "Account created.", 
	                        "Registration Status", 
	                        JOptionPane.INFORMATION_MESSAGE);
					
					HomePage frame = new HomePage();
					frame.setVisible(true);
					setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(null, 
	                         message, 
	                        "Registration Status", 
	                        JOptionPane.ERROR_MESSAGE);
				}
				

			}
		});
		btnRegister.setBounds(70, 189, 97, 25);
		contentPane.add(btnRegister);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				HomePage frame = new HomePage();
				frame.setVisible(true);
			}
		});
		btnBack.setBounds(298, 215, 97, 25);
		contentPane.add(btnBack);
	}

}
