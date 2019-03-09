package CodingIN;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.CompoundBorder;

public class SignIn extends JFrame {

	JPanel contentPane;
	JTextField ID;
	JTextField Pwd;
	JTextField Name;
	JTextField Birth;
	JTextField Email;
	handler R_handle;
	JButton riturn;
	JButton legist;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SignIn() {
		
		R_handle = new handler();
		setBackground(Color.WHITE);
		setBounds(100, 100, 346, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ID = new JTextField();
		ID.setBounds(90, 13, 230, 32);
		contentPane.add(ID);
		ID.setColumns(10);

		JLabel label = new JLabel("\uC544\uC774\uB514");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		label.setBounds(16, 10, 66, 32);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		label_1.setBounds(0, 65, 82, 32);
		contentPane.add(label_1);

		Pwd = new JTextField();
		Pwd.setColumns(10);
		Pwd.setBounds(90, 68, 230, 32);
		contentPane.add(Pwd);

		JLabel label_2 = new JLabel("\uC774\uB984");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		label_2.setBounds(16, 122, 66, 32);
		contentPane.add(label_2);

		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(90, 125, 230, 32);
		contentPane.add(Name);

		riturn = new JButton("\uC774\uC804");
		riturn.setBorder(null);
		riturn.setBackground(SystemColor.menu);
		riturn.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\controls-editor-return.png"));
		riturn.setBounds(28, 324, 66, 46);
		contentPane.add(riturn);

		legist = new JButton("\uD68C\uC6D0\uAC00\uC785");
		legist.setBorder(null);
		legist.setForeground(Color.CYAN);
		legist.setBackground(SystemColor.menu);
		legist.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\web-browsers-broswer-checked.png"));
		legist.setBounds(248, 319, 72, 56);
		contentPane.add(legist);

		Birth = new JTextField();
		Birth.setColumns(10);
		Birth.setBounds(90, 186, 230, 32);
		contentPane.add(Birth);

		JLabel label_3 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		label_3.setBounds(10, 183, 72, 32);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\uC774\uBA54\uC77C");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		label_4.setBounds(10, 236, 72, 32);
		contentPane.add(label_4);

		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(90, 239, 230, 32);
		contentPane.add(Email);
		riturn.addActionListener(R_handle);
		legist.addActionListener(R_handle);
	}

	class handler implements ActionListener {
		// must implement method
		// This is triggered whenever the user clicks the login button
		public void actionPerformed(ActionEvent ae) {
			// checks if the button clicked
			if (ae.getSource() == legist) {

				String R_ID = ID.getText();
				String R_Pwd = Pwd.getText();
				String R_Name = Name.getText();
				String R_Birth = Birth.getText();
				String R_Email = Email.getText();

				Database dao = new Database();
				dao.regist(R_ID, R_Pwd, R_Name, R_Birth, R_Email);
				JOptionPane.showMessageDialog(null, "반갑습니다!", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} // if
			else if (ae.getSource() == riturn) {

				dispose();
			}
		}// method

	}// inner class

}
