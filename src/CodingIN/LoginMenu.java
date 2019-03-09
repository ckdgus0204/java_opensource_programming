/*
 * 로그인 화면
 */


package CodingIN;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.DropMode;

public class LoginMenu extends JFrame {

	JPanel contentPane;
	JLabel lblNewLabel_1;
	JLabel lblPassword;
	JLabel lblNewLabel_2;
	JPasswordField passwordField;
	JTextField textField ;
	JButton LogIn ;
	JButton SignIn ;
	handler handle;
	Database db;
	static String user_id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
					Dimension frameSize = frame.getSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
					frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginMenu() {
		db=new Database();
        handle =new handler();

        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Picture");
		lblNewLabel.setIcon(new ImageIcon("dog.jpg"));
		lblNewLabel.setBounds(131, 12, 172, 184);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(141, 216, 162, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 263, 162, 35);
		contentPane.add(passwordField);
		
/*		String ID = new String(textField.getText()); // 아이디
		String PW = new String(passwordField.getPassword());// 패스워드
*/		 
		lblNewLabel_1 = new JLabel("ID : ");
		lblNewLabel_1.setFont(new Font("Bradley Hand ITC", Font.BOLD, 23));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(52, 216, 87, 35);
		contentPane.add(lblNewLabel_1);
	 	
		lblPassword = new JLabel("PASSWORD : ");
		lblPassword.setFont(new Font("Bradley Hand ITC", Font.BOLD, 19));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(0, 263, 139, 35);
		contentPane.add(lblPassword);
		
		LogIn = new JButton("Log In");
		LogIn.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		LogIn.setBounds(226, 310, 77, 31);
		contentPane.add(LogIn);
		
		SignIn = new JButton("Sign In");
		SignIn.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		SignIn.setBounds(141, 310, 77, 31);
		contentPane.add(SignIn);
		
		lblNewLabel_2 = new JLabel("HELLO?");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(25, 95, 92, 35);
		contentPane.add(lblNewLabel_2);
		
        LogIn.addActionListener(handle);
        SignIn.addActionListener(handle);
	}
	//an inner class .You can also write as a separate class
    class handler implements ActionListener
    {
        //must implement method
        //This is triggered whenever the user clicks the login button
        public void actionPerformed(ActionEvent ae)
        {
            //checks if the button clicked
            if(ae.getSource()==LogIn)
            {
                char[] temp_pwd=passwordField.getPassword();
                String pwd=null;
                pwd=String.copyValueOf(temp_pwd);
                System.out.println("Username,Pwd:"+textField.getText()+","+pwd);
 
                //The entered username and password are sent via "checkLogin()" which return boolean
                if(db.checkLogin(textField.getText(), pwd))
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "WELCOME!!","로그인 성공",
                                        JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    MainMenu mainmenu = new MainMenu();
                    Dimension frameSize = mainmenu.getSize();
    				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    				mainmenu.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                    mainmenu.setVisible(true);
                    user_id=textField.getText();
                }
                else
                {
                    //a pop-up box
                    JOptionPane.showMessageDialog(null, "TRY AGAIN!!","로그인 실패",
                                        JOptionPane.ERROR_MESSAGE);
                }
            }//if
            else if(ae.getSource()==SignIn) {
            	SignIn frame = new SignIn();
        		Dimension frameSize = frame.getSize();
        		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        		frame.setLocation((screenSize.width - frameSize.width) / 2,
        				(screenSize.height - frameSize.height) / 2);
            	frame.setVisible(true);
            	
            }
        }//method
    }//inner class
}
