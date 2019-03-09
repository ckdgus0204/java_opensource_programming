package CodingIN;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;




public class Database {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	static int In=21;
	Database() {
		try {

			// MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
			// ALSO SET THE CLASSPATH
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// ip:username,password
	// return boolean
	public Boolean checkLogin(String ID, String Pwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			pst = con.prepareStatement("select * from client_table where ID=? and Pwd=?");
			pst.setString(1, ID); // this replaces the 1st "?" in the query for username
			pst.setString(2, Pwd); // this replaces the 2st "?" in the query for password
			// executes the prepared statement
			rs = pst.executeQuery();
			if (rs.next()) {
				// TRUE iff the query founds any corresponding data
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error while validating" + e);
			return false;
		}
	}
	public void update(int Number, String Title, String ID, String Language) {	//업데이트

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			String query = "update new_table set Number=?,ID=?,Title=?, where Language = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Number);
			pst.setString(2, Title);
			pst.setString(3, ID);
			pst.setString(4, Language);


					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void regist(String Id, String Pwd, String Name, String Birth, String Email) {	//회원가입

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			String query = "insert into client_table(Id, Pwd, Name, Birth, Email) values (?,?,?,?,?)";// ㅇㅇ 맴버2
			pst = con.prepareStatement(query);
			pst.setString(1, Id);
			pst.setString(2, Pwd);
			pst.setString(3, Name);
			pst.setString(4, Birth);
			pst.setString(5, Email);

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    public void Error_register(String Title, String ID, String Language, String Error_Image,String Description) throws ClassNotFoundException {//게시물업로드
       
        
    	String query = "insert into new_table( Number,Title, ID, Language, Error_Image,Description) values (?,?,?,?,?,?)";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			pst = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try  {
        	++In;
			// read the file
            File E_Image = new File(Error_Image+".png");
            FileInputStream E_input = new FileInputStream(E_Image);
 
			pst.setInt(1, In);
			pst.setString(2, Title);
			pst.setString(3, ID);
			pst.setString(4, Language);
			pst.setBinaryStream(5, E_input);
			pst.setString(6, Description);
			
            pst.executeUpdate();
            }
         catch(Exception e) {
        	 
         }
        
        JOptionPane.showMessageDialog(null, "success!!","업로드 성공",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void reply_register(int Number,String Title, String ID, String Error_Image,String Description) throws ClassNotFoundException {//게시물업로드
       
        
    	String query = "insert into reply_table( Number_,Title_, ID_, Error_Image_,Description_) values (?,?,?,?,?)";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			pst = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try  {

			// read the file
            File E_Image = new File(Error_Image+Number+".png");
            FileInputStream E_input = new FileInputStream(E_Image);
 
			pst.setInt(1, Number);
			pst.setString(2, Title);
			pst.setString(3, ID);
			pst.setBinaryStream(4, E_input);
			pst.setString(5, Description);
			
            pst.executeUpdate();
            }
         catch(Exception e) {
        	 
         }
        
        JOptionPane.showMessageDialog(null, "success!!","업로드 성공",
                JOptionPane.INFORMATION_MESSAGE);

    }

	public Table[] select() {	//
		Table[] list=null;
		int i = 0;
		try {		//숫자새서넘겨주기	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");
			String query1= "select count(*) as cnt from new_table";//갯수
			String query = "select * from new_table";//테이블에서 가져오는데
			pst = con.prepareStatement(query1);
			rs = pst.executeQuery();	//갯수
			while (rs.next()) {
				i=rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		try {	//갯수만큼 배열할당해서 만들어주기
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");
			String query = "select * from new_table";//테이블에서 가져오는데
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();	//갯수
			list = new Table[i];
			
			pst = con.prepareStatement(query);
			rs= pst.executeQuery();
			int k=0;
			while(rs.next()) {
				
				Table dto = new Table();
				dto.setNumber(rs.getInt("Number"));
				dto.setTitle(rs.getString("Title"));
				dto.setID(rs.getString("ID"));
				dto.setLanguage(rs.getString("Language"));

				list[k]= dto;
				k++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public Table[] e_search(String search_tab,String search) {	//검색 검색탭,검색키워드
		Table[] tb=null;
		if(search_tab.equals("제목")) {
			search_tab="Title";
		}
		else if(search_tab.equals("작성자")) {
			search_tab="ID";
		}
		else if(search_tab.equals("언어")) {
			search_tab="Language";
		}
		int k = 0;
		try {		//숫자새서넘겨주기	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");
			String query1= "select count(*) as cnt from new_table where "+search_tab+" like '%"+search+"%'";//갯수
			String query = "select * from new_table";//테이블에서 가져오는데
			pst = con.prepareStatement(query1);
			rs = pst.executeQuery();	//갯수
			while (rs.next()) {
				k=rs.getInt("cnt");
			}
			if(k==0)
				JOptionPane.showMessageDialog(null, "TRY AGAIN!!","검색 결과없음",
                        JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			String query = "select * from new_table where "+search_tab+" like '%"+search+"%'";
			pst = con.prepareStatement(query);
			
			rs = pst.executeQuery();
			if(rs==null)
				JOptionPane.showMessageDialog(null, "TRY AGAIN!!","검색 결과없음",
                        JOptionPane.ERROR_MESSAGE);
			tb = new Table[k];
			int i=0;
			while(rs.next()) {
				tb[i]=new Table();
				tb[i].setNumber(rs.getInt("Number"));
				tb[i].setTitle(rs.getString("Title"));
				tb[i].setID(rs.getString("ID"));
				tb[i].setLanguage(rs.getString("Language"));
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return tb;
	}
    public Table Read_Error(int Number, String filename) throws ClassNotFoundException {//오류 가져오기
        // update sql
        String SQL = "select * from new_table where Number=?";
        Table tb = new Table();
        try  {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

        	pst = con.prepareStatement(SQL);
            pst.setInt(1, Number);

            rs = pst.executeQuery();
            
   

            // write binary stream into file
            
 
            while (rs.next()) {
            	tb.setTitle(rs.getString("Title"));
                tb.setID(rs.getString("ID"));
                tb.setLanguage(rs.getString("Language"));
                tb.setDescription(rs.getString("Description"));
                
                File file = new File(filename);
                FileOutputStream output = new FileOutputStream(file);
                InputStream input = rs.getBinaryStream("Error_Image");
                byte[] buffer = new byte[100];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                output.close();
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            
        } catch(NullPointerException e){
        	
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
		return tb;
 
    }

    public Table reply_Error(int Number, String filename) throws ClassNotFoundException {//오류 가져오기
        // update sql
        String SQL = "select * from reply_table where Number_=?";
        Table tb = new Table();
        try  {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

        	pst = con.prepareStatement(SQL);
            pst.setInt(1, Number);

            rs = pst.executeQuery();
            
   

            // write binary stream into file
            
 
            while (rs.next()) {
            	tb.setNumber(rs.getInt("Number_"));
            	tb.setTitle(rs.getString("Title_"));
                tb.setID(rs.getString("ID_"));
         
                tb.setDescription(rs.getString("Description_"));
                
                File file = new File(filename);
                FileOutputStream output = new FileOutputStream(file);
                InputStream input = rs.getBinaryStream("Error_Image_");
                byte[] buffer = new byte[100];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                output.close();
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            
        } catch(NullPointerException e){
        	
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
		return tb;
 
    }
	public boolean isExist(String Title) {	//존재여부
		boolean result = false;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myimages?serverTimezone=UTC&useSSL=false", "root", "!ntbt0927");

			String query = "select * from new_table where Title = ?";
			pst = con.prepareStatement(query);
			pst.setString(2, Title);
			rs = pst.executeQuery();
			while(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}