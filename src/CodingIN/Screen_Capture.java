package CodingIN;

import java.awt.ActiveEvent;
//ĸó�ϴ� ȭ��
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Panel;

//import com.sun.image.codec.jpeg.*;

public class Screen_Capture extends JPanel implements Runnable, ActionListener {
	Image img = null;
	JButton btn_capture;
	Panel panel;
	Dimension di;
	static JFrame frame;
	// ������. UI ��ġ.
	public Screen_Capture() {

		createFrame();
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("capture")) {
			di = frame.getSize();
			System.out.println("������ ĸ���մϴ�..");
			this.capture(); // ����ĸó ��ư�� ������ ĸ��.
		}
	}

	private void drawImage(Image imga, int x, int y) {
		Graphics g = this.getGraphics();
		g.drawImage(img, 0, 0, x, y, panel);
		this.paint(g);
		this.repaint();
	}

	public void paint(Graphics g) {
		if (this.img != null) {
			g.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), panel);
		}
	}

	public void capture() {
		Robot robot;
		BufferedImage bufImage = null;
		try {
			robot = new Robot();
			panel.setVisible(true);
			
			
			panel.setPreferredSize(di);
			
			Point A = panel.getLocationOnScreen();// �г��� point �޾ƿ���
			int x = panel.getX();
			int y = panel.getY();

			int w = (int) di.getWidth(); // �г��� �ʺ�
			int h = (int) di.getHeight(); // �г��� ����
			Rectangle area = new Rectangle(A.x, A.y, x + w-10, y + h-55 );// ĸó�� ����
			System.out.println(x+y+w+h);
			panel.setVisible(false);
			bufImage = robot.createScreenCapture(area); // Robot Ŭ������ �̿��Ͽ� ��ũ�� ĸ��.

			// Graphics2D g2d = bufImage.createGraphics();

			img = bufImage.getScaledInstance(w, h - 20, Image.SCALE_DEFAULT);
			//drawImage(img, w, h);
			// this.repaint();
			//ImageIO.write(bufImage, ".jpg", new File(LoginMenu.user_id+"_capture.jpg"));
			ImageIO.write(bufImage, "PNG", new File("C:\\Users\\user\\Desktop\\error.PNG"));
			System.out.println("sdf");

			// saveJPEGfile("cap.jpg", bufImage); // JPG ���Ϸ� ��ȯ�Ͽ� ����.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static boolean saveJPEGfile(String filename, BufferedImage bi) {
	 * FileOutputStream out = null; try { out = new FileOutputStream(filename);
	 * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); JPEGEncodeParam
	 * param = encoder.getDefaultJPEGEncodeParam(bi); param.setQuality(1.0f, false);
	 * encoder.setJPEGEncodeParam(param);
	 * 
	 * encoder.encode(bi); out.close(); } catch (Exception ex) {
	 * System.out.println("Error saving JPEG : " + ex.getMessage()); return false; }
	 * return true; }
	 */

	public void run() {
		while (true) {
			this.setBackground(Color.RED);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			this.setBackground(Color.GREEN);

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}

	}

	public void createFrame() {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("Capture");
		frame.getContentPane().setBackground(Color.RED);
		/*Container cont = frame.getContentPane();
		cont.setLayout(new BorderLayout());*/

		//Screen_Capture mm = new Screen_Capture();
		// new Thread(mm).start();

		//cont.add(frame, BorderLayout.CENTER);
		frame.setUndecorated(true); // ���� �����ϰ�
		frame.setBackground(new Color(0, 0, 0, 1));// â�� �������ϰ� �����

		frame.setSize(400, 400);
		frame.setVisible(true);
		
		btn_capture = new JButton("capture");
		btn_capture.addActionListener(this);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(btn_capture, BorderLayout.SOUTH);
		
		panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		
		
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		btn_capture.setVisible(true);
	}

	public static void main(String... v) {
		
/*		JFrame.setDefaultLookAndFeelDecorated(true);
		final JFrame frame = new JFrame("Capture");
		Container cont = frame.getContentPane();
		cont.setLayout(new BorderLayout());

		Screen_Capture mm = new Screen_Capture();
		

		cont.add(mm, BorderLayout.CENTER);
		frame.setUndecorated(true); // ���� �����ϰ�
		frame.setBackground(new Color(0, 0, 0, 1));// â�� �������ϰ� �����

		frame.setSize(400, 400);
		frame.setVisible(true);*/

	}

}
