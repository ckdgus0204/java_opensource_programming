/*
 * 메인 화면
 */

package CodingIN;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu mainmenu = new MainMenu();
					mainmenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(250, 12, 300, 44);
		contentPane.add(separator);

		JButton btnNewButton = new JButton("메인");
		btnNewButton.setBounds(250, 12, 100, 44);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("게시판");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ForumMenu forummenu = new ForumMenu();
				Dimension frameSize = forummenu.getSize();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				forummenu.setLocation((screenSize.width - frameSize.width) / 2,
						(screenSize.height - frameSize.height) / 2);
				forummenu.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(350, 12, 100, 44);
		contentPane.add(btnNewButton_1);
		
		JButton button = new JButton("검색");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search = new Search();
				Dimension frameSize = search.getSize();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				search.setLocation((screenSize.width - frameSize.width) / 2,
						(screenSize.height - frameSize.height) / 2);
				search.setVisible(true);
			}
		});
		button.setBounds(450, 12, 100, 44);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("코");
		lblNewLabel.setFont(new Font("문체부 궁체 정자체", Font.BOLD, 90));
		lblNewLabel.setBounds(350, 70, 100, 100);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("딩");
		label.setFont(new Font("문체부 궁체 정자체", Font.BOLD, 90));
		label.setBounds(350, 182, 100, 100);
		contentPane.add(label);

		JLabel label_1 = new JLabel("인");
		label_1.setFont(new Font("문체부 궁체 정자체", Font.BOLD, 90));
		label_1.setBounds(350, 294, 100, 100);
		contentPane.add(label_1);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\김창현1.jpg"));
		lblNewLabel_1.setBounds(50, 100, 200, 300);
		contentPane.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\박동운.jpg"));
		label_2.setBounds(550, 100, 200, 300);
		contentPane.add(label_2);
	}
}
