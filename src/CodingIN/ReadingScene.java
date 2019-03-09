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
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReadingScene extends JFrame {
	
	private JPanel contentPane;
	Database db=new Database();
	String filename="C:\\Users\\user\\Desktop\\read";
	private JTextField text_title;
	private JTextField text_lang;
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
					ReadingScene frame = new ReadingScene(1);
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
	public ReadingScene(int numb) throws ClassNotFoundException {
		Table tb =new Table();
		tb=db.Read_Error(numb, filename+numb+".png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel error_image = new JLabel("");
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
		Img= tk.getImage(filename+numb+".png");
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
		
		JLabel label_1 = new JLabel("언어");
		label_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(153, 84, 60, 30);
		contentPane.add(label_1);
		
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
		
		text_lang = new JTextField();
		text_lang.setEditable(false);
		text_lang.setBounds(153, 124, 80, 30);
		text_lang.setText(tb.getLanguage());
		contentPane.add(text_lang);
		text_lang.setColumns(10);
		
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
	
	
		
		JButton button_1 = new JButton("답글달기");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int parent=Integer.parseInt(text_numb.getText());
				dispose();
				ReplyWritingScene RWS=new ReplyWritingScene(parent);
				Dimension frameSize = RWS.getSize();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				RWS.setLocation((screenSize.width - frameSize.width) / 2,
						(screenSize.height - frameSize.height) / 2);
				RWS.setVisible(true);
			}
		});
		button_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		button_1.setBounds(551, 414, 100, 27);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("답글보기");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int parent=Integer.parseInt(text_numb.getText());
				
				ReplyReadingScene RWS;
				try {
					RWS = new ReplyReadingScene(parent);
					Dimension frameSize = RWS.getSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					RWS.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					RWS.setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button_2.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		button_2.setBounds(439, 414, 100, 27);
		contentPane.add(button_2);
	}
}
