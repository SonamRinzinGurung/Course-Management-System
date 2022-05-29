package moduleAndCourse;

//importing necessary classes
import courseManagementSystem.CheckDatabase;
import courseManagementSystem.MySqlConnection;

//importing necessary Java library classes
import java.sql.PreparedStatement;		
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;



/**
 * This class contains all the methods used to manipulate modules in the database of Course Management System.<br>
 * This class inherits the abstract class: CheckDatabase.
 * 
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 */
public class Module extends CheckDatabase{

	
	/**
	 * Checks if module ID is in database.<br>
	 * This implemented method is inherited from the abstract class: CheckDatabase
	 * 
	 * @param id Module ID of module to be checked in database
	 * @return Boolean value True or False
	 * @exception SQLException
	 */
	@Override
	public boolean checkInDatabase(int id) {
		
		//creating query with parameter
		String sqlQuery = "SELECT module_id, module_name FROM module WHERE module_id = ?";
		try {
			
			//establishing connection and creating Prepared Statement
			preSt =MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting value of parameter
			preSt.setInt(1, id);
			
			//storing the result acquired after executing query
			ResultSet rSet = preSt.executeQuery();
			
		
			if(rSet.next()) { //if data corresponding to the id exists
				
				return true;
			}
			
			//catching exceptions
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return false if the id doesn't exist in database
		return false;
	}


	
	/**
	 * Inserts the data passed in the parameter into the module table of database.
	 * 
	 * @param moduleId	ID of new module 
	 * @param modName	Name of new module
	 * @param courseId	ID of course where module is to be added
	 * @param level		level of study where module is to be added
	 * @exception 		SQLException
	 */
	public void insertModuleIntoDB(int moduleId, String modName, int courseId, int level) {
		
		//creating query
		String sqlQuery = "INSERT INTO module (module_id, module_name, course_id, level) VALUES (?,?,?,?)";
		try {
			
			//creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting the values using the parameter of the function
			preSt.setInt(1, moduleId);
			preSt.setString(2, modName);
			preSt.setInt(3, courseId);
			preSt.setInt(4, level);
			
			//executing the statement
			preSt.executeUpdate();
			preSt.close(); //closing
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "MySQL Error");
		}
	}
	
	
	
	/**
	 * Checks if module is in the given Course
	 * 
	 * @param courseId ID of course where module is to be checked
	 * @return Boolean Value True or False representing if module is in course or not
	 * @exception SQLException
	 */
	public boolean checkModuleDBCourseID(int courseId) {
		
		//creating query
		String sqlQuery = "SELECT * FROM module WHERE course_id = ?";
		try {
			
			//creating prepared statement
			preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting the value of id
			preSt.setInt(1, courseId);
			
			//storing result using ResultSet
			ResultSet rSet = preSt.executeQuery();
			
			if(rSet.next()) { //if data corresponding to the id exists
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false; //returns false if id doesn't exists
	}

}
