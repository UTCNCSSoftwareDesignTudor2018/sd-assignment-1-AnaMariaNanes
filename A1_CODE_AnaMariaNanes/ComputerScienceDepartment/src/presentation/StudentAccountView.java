package presentation;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.classes.StudentBLL;
import business.interfaces.*;
import persistance.entities.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentAccountView extends JFrame {

	private JPanel contentPane;
	private Student studentAccount;
	private IStudentBLL studentBLL;

	public StudentAccountView(Student studentAccount) {
		
		this.studentAccount = studentAccount;
		this.studentBLL = new StudentBLL();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 650, 500);
		setTitle("Student account: "+this.studentAccount.getName() );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewStudentProfile = new JButton("View Student Profile");
		btnViewStudentProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentProfileInfoView frame = new StudentProfileInfoView(studentAccount);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnViewStudentProfile.setBounds(54, 74, 214, 203);
		contentPane.add(btnViewStudentProfile);
		
		JButton btnEnrollments = new JButton("Enrollments");
		btnEnrollments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentEnrollmentsView frame = new StudentEnrollmentsView(studentAccount);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnEnrollments.setBounds(338, 74, 232, 203);
		contentPane.add(btnEnrollments);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentBLL.delete(studentAccount.getStudentID());
				
				JOptionPane.showMessageDialog(null, 
                        "Student account deleted.", 
                        "Delete status.", 
                        JOptionPane.INFORMATION_MESSAGE);
				
				setVisible(false);
				HomePage frame = new HomePage();
				frame.setVisible(true);
			}
		});
		btnDeleteAccount.setBounds(440, 329, 135, 25);
		contentPane.add(btnDeleteAccount);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				HomePage frame = new HomePage();
				frame.setVisible(true);
			}
		});
		btnLogOut.setBounds(473, 398, 97, 25);
		contentPane.add(btnLogOut);
	}

}
