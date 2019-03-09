package CodingIN;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ReplyReadingScene extends JFrame {
	
	private JPanel contentPane;
	Database db=new Database();
	String filename="C:\\Users\\user\\Desktop\\replyread";
	private JTextField text_title;
	private JTextField text_ID;
	private JTextField text_numb;
	Image Img;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReplyReadingScene frame = new ReplyReadingScene(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param numb 
	 * @param numb 
	 * @throws ClassNotFoundException 
	 */
	public ReplyReadingScene(int numb) throws ClassNotFoundException {
		Table tb =new Table();
		tb=db.reply_Error(numb, filename+numb+".gif");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel error_image = new JLabel("");
		error_image.setBorder(new LineBorder(new Color(0, 0, 0)));
		error_image.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				error_image.setBounds(200, 20, 500, 300);
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				error_image.setBounds(551, 21, 219, 127);
			}
		});
		Toolkit tk = Toolkit.getDefaultToolkit();
		Img= tk.getImage(filename+numb+".gif");
		ImageIcon ic = new ImageIcon(Img);
		error_image.setIcon(ic);
		error_image.setBounds(551, 21, 219, 127);
		contentPane.add(error_image);
		
		JLabel lblNewLabel_1 = new JLabel("첨부파일");
		lblNewLabel_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(364, 84, 100, 100);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("번호");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(15, 12, 40, 30);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("글쓴이");
		label.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(15, 84, 60, 30);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("제목");
		label_2.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(102, 12, 100, 30);
		contentPane.add(label_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		textArea.setBounds(15, 164, 755, 240);
		textArea.setText(tb.getDescription());
		contentPane.add(textArea);
		
		JButton button = new JButton("뒤로");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		button.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		button.setBounds(670, 414, 100, 27);
		contentPane.add(button);
		
		
		
		
		text_title = new JTextField();
		text_title.setEditable(false);
		text_title.setBounds(102, 52, 365, 30);
		text_title.setText(tb.getTitle());
		contentPane.add(text_title);
		text_title.setColumns(10);
		
		text_ID = new JTextField();
		text_ID.setEditable(false);
		text_ID.setBounds(15, 124, 114, 30);
		text_ID.setText(tb.getID());
		contentPane.add(text_ID);
		text_ID.setColumns(10);
		
		text_numb = new JTextField();
		text_numb.setEditable(false);
		text_numb.setBounds(15, 52, 51, 30);
		String A = String.valueOf(numb);
		text_numb.setText(A);
		contentPane.add(text_numb);
		text_numb.setColumns(10);
	
		
		
		

	}
}
