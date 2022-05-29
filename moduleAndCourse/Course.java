package moduleAndCourse;

//importing CheckDatabase class
import courseManagementSystem.CheckDatabase;
import courseManagementSystem.MySqlConnection;	

//importing necessary Java library classes
import java.sql.PreparedStatement;	
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**This class contains the methods used to manipulate course in the database of Course Management System.<br>
 * This class inherits the abstract class: CheckDatabase.
 * 
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 */
public class Course extends CheckDatabase{

	
	/**Checks if Course ID is in database.<br>
	 * This implemented method is inherited from the abstract class: CheckDatabase
	 * @param id Course ID
	 * @return True or False
	 * @exception SQLException
	 * 
	 */
	
	@Override
	public boolean checkInDatabase(int id) {
		
		//creating query
		String sqlQuery = "SELECT course_id,course_name from course where course_id = ?";
		try {
			
			//creating prepared statement and establishing connection
			preSt= MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//assigning the value to the unknown (?) using setter method of PreparedStatement
			preSt.setInt(1, id); // (1) is the index of the parameter i.e course_id (first index is 1)
			
			//ResultSet stores the result of the executed query
			ResultSet rSet = preSt.executeQuery();
			
			//checks if the id is in the database
			if(rSet.next()){
				
				return true;
			}
			
		//catching exceptions
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;  //returns false if id not found in database
	}
	
	
	
	/**
	 * Inserts data into course table of database.
	 * 
	 * @param id Course ID of new course
	 * @param courseName Name of new course to insert
	 * @exception SQLException
	 */
	public void insertCourseIntoDB(int id, String courseName) {
		
		//creating parameterized query
		String sqlQuery = "INSERT INTO course (course_id,course_name) values (?,?)";
		try {
			
			//creating prepared statement and setting the values of the unkown parameters in the query
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			preSt.setInt(1, id);
			preSt.setString(2, courseName);
			
			//executing the statement
			preSt.executeUpdate();
			
			//closing the prepared statement
			preSt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "MySQL Error");   //show error message on the frame
		}
	}
	
	
	
	/**
	 * Changes the status of the course in database (Active or Inactive)
	 * @param id ID of the course whose status is to be changed
	 * @param status Status of the course either Active of Inactive
	 * @exception SQLException
	 */
	public void changeCourseStatusDB(int id, String status) {
		
		//creating query
		String sqlQuery = "UPDATE course SET status = ? WHERE course_id=?";
		try {
			
			//creating a prepared statement
			PreparedStatement preSt  = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting values
			preSt.setString(1, status);
			preSt.setInt(2, id);
			
			//executing the statement
			preSt.executeUpdate();
			preSt.close(); //closing
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "MySQL Error");
		}
	}


	/**
	 * Deletes the course from the database
	 * 
	 * @param id ID of the course to be deleted
	 * @exception SQLException
	 */
	public void deleteCourseFromDatabase(int id) {
		
		//query
		String sqlQuery = "DELETE FROM course where course_id=?";
		try {
			
			//establishing connection
			//passing the query
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery); 
			
			preSt.setInt(1, id);
			preSt.executeUpdate();
			preSt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "MySQL Error");
		}
	}


}
