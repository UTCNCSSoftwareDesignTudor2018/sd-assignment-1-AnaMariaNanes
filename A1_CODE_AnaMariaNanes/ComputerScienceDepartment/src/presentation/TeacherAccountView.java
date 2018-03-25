package presentation;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistance.entities.*;

public class TeacherAccountView extends JFrame {

	private JPanel contentPane;
	private Teacher teacherAccount;

	public TeacherAccountView(Teacher teacherAccount) {
		
		this.teacherAccount =  teacherAccount;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 650, 500);
		setTitle("Teacher account: " + this.teacherAccount.getName());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
