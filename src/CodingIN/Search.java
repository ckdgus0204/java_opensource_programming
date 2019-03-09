package CodingIN;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Component;
import javax.swing.DropMode;
import java.awt.Dimension;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Search extends JFrame {

	JPanel contentPane;
	JTextField textField;
	JButton btnNewButton;
	handler handle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Search frame = new Search();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Search() {
		handle = new handler();
		setBounds(new Rectangle(0, 0, 300, 900));

		setBounds(100, 100, 633, 247);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 300, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setSize(900, 300);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setVisible(true);

		btnNewButton = new JButton("Search");

		btnNewButton.setVisible(true);
		btnNewButton.setBounds(459, 157, 88, 36);
		contentPane.add(btnNewButton);
		textField.setBounds(83, 157, 362, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		btnNewButton.setOpaque(true);

		btnNewButton.setForeground(new Color(0x550000FF, true));

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 620, 222);
		ImageIcon imggoogle = new ImageIcon("C:\\Users\\user\\Desktop\\Google.JPG");

		Image originImg = imggoogle.getImage();

		// ����� Image�� ũ�⸦ �����Ͽ� ���ο� Image��ü ����

		Image changedImg = originImg.getScaledInstance(620, 200, Image.SCALE_SMOOTH);

		// ���ο� Image�� ImageIcon��ü�� ����

		ImageIcon Icon = new ImageIcon(changedImg);

		label.setIcon(Icon);
		contentPane.add(label);
		btnNewButton.addActionListener(handle);
	}

	class handler implements ActionListener {
		// must implement method
		// This is triggered whenever the user clicks the login button
		public void actionPerformed(ActionEvent ae) {
			// checks if the button clicked
			if (ae.getSource() == btnNewButton) {
				String a = "";
				a = textField.getText();
				try {
					Google_Search(a);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // if

		}// method

	}// inner class

	public void Google_Search(String search) throws IOException {
		// ����â ���� �� �˻�
		String G_Search = "";

		G_Search = URLEncoder.encode(search, "UTF-8");
		G_Search = "https://www.google.co.kr/search?q=" + G_Search;
		try {
			Desktop.getDesktop().browse(new URI(G_Search));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
