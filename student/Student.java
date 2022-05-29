package student;

//importing necessary classes from within the project
import courseManagementSystem.CheckDatabase;
import courseManagementSystem.MySqlConnection;

//importing necessary SQL classes
import java.sql.PreparedStatement;	
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * This class contains the methods used to manipulate student in the database of Course Management System.<br>
 * This class inherits the abstract class: CheckDatabase.
 * 
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 */
public class Student extends CheckDatabase{

	
	
	/**Checks if Student ID is in database.<br>
	 * This implemented method is inherited from the abstract class: CheckDatabase
	 * @param id Student ID
	 * @return True or False signifying if student id is in database or not
	 * @exception SQLException
	 * 
	 */
	@Override
	public boolean checkInDatabase(int id) {
		
		//creating query
		String sqlQuery = "SELECT student_id, student_name FROM student WHERE student_id=?";
		try {
			
			//initializing prepared statement
			preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			preSt.setInt(1, id); //setting value to the parameters
			
			ResultSet rSet = preSt.executeQuery(); //executing the statement and storing the result
			
			//iterating through the result
			if(rSet.next()) {
				
				return true; //if data found, return true
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; //returns false if data not found
	}
	
	
	
	/**
	 * Checks if student is enrolled in the specified module.
	 * 
	 * @param studId	ID of the student 
	 * @param moduleId	ID of the module where student is being checked
	 * @return			True or false based on whether the student is in the module or not
	 * 
	 * @exception		SQLException
	 */
	public boolean checkStudentInMod(int studId, int moduleId ) {
		
		//creating query
		String sqlQuery = "SELECT * FROM student WHERE student_id = ? AND module_id = ?";
		try {
			
			//creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting values
			preSt.setInt(1, studId);
			preSt.setInt(2, moduleId);
			
			ResultSet rSet = preSt.executeQuery(); //executing
			
			if(rSet.next()){
				//if result set have data
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;  //if data corresponding to the query not found in database
	}
}
