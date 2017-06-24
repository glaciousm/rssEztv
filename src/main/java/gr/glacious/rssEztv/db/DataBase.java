package gr.glacious.rssEztv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	
	public static void main(String[] args) {
		try {
			System.out.println(readFromDB());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<String> readFromDB() throws ClassNotFoundException, SQLException{
		
		List<String> list = new ArrayList<>();
		
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eztv", "root", "") ;
		try {
		Statement stmt = conn.createStatement() ;
		String query = "select name from favorites ;" ;
		ResultSet rs = stmt.executeQuery(query) ;
		
		while(rs.next()){

			list.add(rs.getString(1));
            }
    }
        catch (SQLException e){
             System.out.println(e.getMessage());
         }
	
	return list;
	
	}
	

}
