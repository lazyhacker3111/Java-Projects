package PhoneBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Db_Main implements DB_vars{
	ArrayList<Phone> contacts = new ArrayList<Phone>();
	 
     Db_interface din = new Db_interface();

	private Connection con; // Initialized 
	private Statement st; // Initialized 
	
	public Db_Main() {
		try { //her try catch is auto implemented
			Class.forName(driver);
			con = DriverManager.getConnection(url,uname,upass);
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


	   
	   
	   public boolean insertProduct(Phone p) {
		   
			boolean success = true;
			String sql = "insert into contacts (name,number) values('"+p.getName()+"', '"+p.getNumber()+"')";
			
		    try {
				st.executeUpdate(sql);	
		    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				success=false;
				Alert a =new Alert(AlertType.ERROR);
				a.setContentText("Cannot be added");
				a.showAndWait();
				
			}   
			return success;
		}
	
	   public boolean deleteProduct(String name) {
			boolean success = true;
			String sql = "delete from contacts where name = '"+name+"'";
			try {
				int res = st.executeUpdate(sql);
				if(res ==0) {
					success = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				success = false;
				e.printStackTrace();
			}
			return success;
			
		}

	   
	   public  ArrayList<Phone> searchProduct(String name) {
			ArrayList<Phone>list= new ArrayList<Phone>();
			String sql = "select * from contacts where name = '"+name+"'";
			try {
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					
					
					Phone p = new Phone(rs.getInt(3),
							rs.getString(2));
					
					list.add(p);	
				}	 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
			}
	   public ArrayList<Phone> getAllData() {
	   ArrayList<Phone> all = new ArrayList<Phone>();
		String sql = "SELECT * FROM CONTACTS";
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Phone p = new Phone(rs.getInt(3),rs.getString(2));
				
				all.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	   
	  	
}
}
