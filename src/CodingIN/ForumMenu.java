package CodingIN;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ForumMenu extends JFrame {
   List T_list;

   String[][] content;
   DefaultTableModel model ;
   String search_tab;
   Object[] header= {"번호","이름","제목","언어"};
   private JPanel contentPane;
   private JTextField textField;
   private JTable table = new JTable();
   private Database db;
   Table tb;
   private JTable table_1;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MainMenu frame = new MainMenu();
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
   public ForumMenu() {
      db = new Database();
      //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 800, 530);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JSeparator separator = new JSeparator();
      separator.setBounds(250, 12, 300, 44);
      contentPane.add(separator);

      JButton btnNewButton = new JButton("메인");
      btnNewButton.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
            MainMenu mainmenu = new MainMenu();
            Dimension frameSize = mainmenu.getSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mainmenu.setLocation((screenSize.width - frameSize.width) / 2,
                  (screenSize.height - frameSize.height) / 2);
            mainmenu.setVisible(true);
         }
      });
      btnNewButton.setBounds(250, 12, 100, 44);
      contentPane.add(btnNewButton);
      JScrollPane jScrollPane = new JScrollPane(table);
      JButton btnNewButton_1 = new JButton("게시판");
      btnNewButton_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      btnNewButton_1.setBounds(350, 12, 100, 44);
      contentPane.add(btnNewButton_1);

      JButton button = new JButton("검색");
      button.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
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

      JButton btnNewButton_2 = new JButton("글쓰기");
      btnNewButton_2.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            WritingScene writing = new WritingScene();
            Dimension frameSize = writing.getSize();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            writing.setLocation((screenSize.width - frameSize.width) / 2,
                  (screenSize.height - frameSize.height) / 2);
            writing.setVisible(true);
         }
      });
      btnNewButton_2.setBounds(663, 436, 105, 27);
      contentPane.add(btnNewButton_2);
      
      JComboBox comboBox = new JComboBox();
      comboBox.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"제목", "작성자", "언어"}));
      comboBox.setMaximumRowCount(3);
      comboBox.setBounds(134, 436, 80, 27);
      
      contentPane.add(comboBox);
      
      textField = new JTextField();
      textField.setBounds(220, 436, 330, 27);
      contentPane.add(textField);
      textField.setColumns(10);
      
      JButton btnNewButton_3 = new JButton("Go");
      btnNewButton_3.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      btnNewButton_3.setBounds(556, 436, 60, 27);
      contentPane.add(btnNewButton_3);
      
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String search_ = textField.getText();         
            search_tab=comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
            Table[] search_all=db.e_search(search_tab,search_);
            
            model.fireTableDataChanged();
            
            displaylist(search_all);
         }
      });
      
      Table[] list_all=db.select();
      displaylist(list_all);
      
   }
   public void displaylist(Table[] list_all) {
   
      content = new String[list_all.length][];
      
      for (int i = 0; i < list_all.length; i++) {
         content[i]=new String[4];
         content[i][0]=String.valueOf(list_all[i].Number);
         content[i][1]=list_all[i].ID;
         content[i][2]=list_all[i].Title;
         content[i][3]=list_all[i].Language;
      }
      model =new DefaultTableModel(content,header) {
         @Override
         public boolean isCellEditable(int row,int col) {
            return false;
         }
      };
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()>1) {
               int row = table.getSelectedRow();
               int numb =Integer.parseInt((String) table.getValueAt(row, 0));
               try {
                  ReadingScene reads = new ReadingScene(numb);
                  Dimension frameSize = reads.getSize();
                  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                  reads.setLocation((screenSize.width - frameSize.width) / 2,
                        (screenSize.height - frameSize.height) / 2);
                  reads.setVisible(true);
               } catch (ClassNotFoundException e1) {
               // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            }
         }
      });

      
      table.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      table.setModel(model);
      table.getColumnModel().getColumn(0).setResizable(false);
      table.getColumnModel().getColumn(0).setPreferredWidth(59);
      table.getColumnModel().getColumn(0).setMinWidth(30);
      table.getColumnModel().getColumn(1).setResizable(false);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setResizable(false);
      table.getColumnModel().getColumn(2).setPreferredWidth(530);
      table.getColumnModel().getColumn(2).setMinWidth(30);
      table.getColumnModel().getColumn(3).setResizable(false);
      table.setBounds(14, 98, 754, 320);
      
      contentPane.add(table);
      
      table_1 = new JTable();
      table_1.setShowHorizontalLines(false);
      table_1.setModel(new DefaultTableModel(
         new Object[][] {
            {"    \uBC88\uD638", "  \uC791\uC131\uC790", "                                               \uC81C\uBAA9 ", "     \uC5B8\uC5B4"},
         },
         new String[] {
            "New column", "New column", "New column", "New column"
         }
      ) {
         boolean[] columnEditables = new boolean[] {
            false, false, false, false
         };
         public boolean isCellEditable(int row, int column) {
            return columnEditables[column];
         }
      });
      table_1.getColumnModel().getColumn(0).setResizable(false);
      table_1.getColumnModel().getColumn(0).setPreferredWidth(59);
      table_1.getColumnModel().getColumn(0).setMinWidth(30);
      table_1.getColumnModel().getColumn(1).setResizable(false);
      table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
      table_1.getColumnModel().getColumn(2).setResizable(false);
      table_1.getColumnModel().getColumn(2).setPreferredWidth(530);
      table_1.getColumnModel().getColumn(2).setMinWidth(30);
      table_1.getColumnModel().getColumn(3).setResizable(false);
      table_1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
      table_1.setBackground(new Color(192, 192, 192));
      table_1.setBounds(14, 81, 754, 17);
      contentPane.add(table_1);
   
      
   }
}
