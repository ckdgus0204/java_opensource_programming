package CodingIN;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReplyWritingScene extends JFrame {
	Database db=new Database();
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField title_text;
	private JFileChooser filec;
	String filename="C:\\Users\\user\\Desktop\\error";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReplyWritingScene frame = new ReplyWritingScene(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param parent 
	 */
	public ReplyWritingScene(int parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(SystemColor.activeCaptionBorder);
		lblNewLabel.setBounds(50, 25, 100, 35);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		lblNewLabel_1.setBackground(SystemColor.activeCaptionBorder);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(50, 63, 100, 280);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("No File Selected");
		lblNewLabel_2.setBounds(165, 356, 580, 27);
		contentPane.add(lblNewLabel_2);
		
		JTextArea des_text = new JTextArea();
		des_text.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		des_text.setBounds(151, 63, 580, 280);
		contentPane.add(des_text);
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String title;
				
				String des;
				String e_image;
				des=des_text.getText();
				title=title_text.getText();
				
				if(filec==null)
					e_image=filename;
				else
					e_image=filec.getSelectedFile().getAbsolutePath();
				
				try {
					db.reply_register( parent,title, LoginMenu.user_id,e_image , des);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnNewButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		btnNewButton.setBounds(631, 398, 100, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(517, 398, 100, 27);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("파일 업로드");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filec = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int i = filec.showSaveDialog(null);
	            if (i == JFileChooser.APPROVE_OPTION) 
	            { 
	                // set the label to the path of the selected file 
	            	lblNewLabel_2.setText(filec.getSelectedFile().getAbsolutePath()); 
	            } 
			}
		});
		btnNewButton_2.setFont(new Font("함초롬돋움", Font.PLAIN, 12));
		btnNewButton_2.setBounds(50, 355, 100, 27);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("캡쳐");
		btnNewButton_3.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Screen_Capture sc = new Screen_Capture();
				sc.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(50, 398, 100, 27);
		contentPane.add(btnNewButton_3);
		
		title_text = new JTextField();
		title_text.setFont(new Font("함초롬돋움", Font.PLAIN, 18));
		title_text.setBounds(151, 25, 580, 35);
		contentPane.add(title_text);
		title_text.setColumns(10);
	}
}
