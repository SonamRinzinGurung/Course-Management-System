package instructor;

//importing necessary classes from within the project
import courseManagementSystem.CheckDatabase;
import courseManagementSystem.MySqlConnection;

//importing necessary Java Library Classes
import java.sql.PreparedStatement;	
import java.sql.ResultSet;
import java.sql.SQLException;




/**
 * This class contains the methods used to manipulate Instructor in the database of Course Management System.<br>
 * This class inherits the abstract class: CheckDatabase.
 * 
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 */
public class Instructor extends CheckDatabase{
	
	//declaring instance variables
	private String insName;
	private int insId;
	private String email;
	private int moduleId;
	private String address;

	

	/**
	 * Getter method for Instructor Name
	 * @return A string that is the name of the instructor
	 */
	public String getInsName() {
		return insName;
	}
	
	
	/**
	 * Getter method for Instructor ID
	 * @return An integer that is the ID of the instructor
	 */
	public int getInsId() {
		return insId;
	}
	
	
	
	/**
	 * Setter method for Module ID. Sets the ID of the module in the variable moduleId.
	 * @param moduleId ID of the module to set in the instance variable 
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	
	
	/**Checks if Instructor ID is in database.<br>
	 * Also initializes the instance variables of the class using the private method initializeInstructor.<br>
	 * This implemented method is inherited from the abstract class: CheckDatabase
	 * @param id Student ID
	 * @return True or False signifying if Instructor id is in database or not
	 * @exception SQLException
	 * 
	 */
	@Override
	public boolean checkInDatabase(int id) {
		
		//creating SQL query
		String sqlQuery = "SELECT instructor_id, instructor_name, instructor_email,instructor_address, module_id FROM instructor WHERE instructor_id= ?";
		try {
			
			//establishing connection and creating prepared statement
			preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			preSt.setInt(1,id); //setting the value of the unknown parameter
			
			ResultSet rSet = preSt.executeQuery(); //executing the statement and storing the result
			
			//iterating through the result
			if(rSet.next()) {
				initializeInstructor(rSet); //calling the function that initializes the instance variables of this class
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; //returns false if the id not found in database
	}

	
	
	/**
	 * This method initializes the instance variables in this class.
	 * @param rSet ResultSet containing data related to instructor
	 * @exception SQLException
	 */
	private void initializeInstructor(ResultSet rSet) {
		try {
			
			//sets the value of the instance variables
			this.insId = rSet.getInt(1);
			this.insName = rSet.getString(2);
			this.email = rSet.getString(3);
			this.address = rSet.getString(4);
			this.moduleId = rSet.getInt(5);
			
		} 
		//handling SQL exceptions
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Update the instructor assigned in the module.
	 * 
	 * @param instId ID of the new assigned instructor
	 * @param modId ID of the module where instructor is to be assigned
	 * @exception SQLException
	 */
	public void updateInstructorInModule(int instId, int modId) {
		
		//creating query
		String sqlQuery = "UPDATE module SET instructor_id = ? WHERE module_id =?";
		try {
			
			//creating prepared statement and establishing connection
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting the values of parameters
			preSt.setInt(1, instId);
			preSt.setInt(2, modId);
			
			preSt.executeUpdate(); //executing the prepared statement
			preSt.close();	//closing the prepared statement
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Inserts a new instructor into the database. Gets the instructor data from the instance variables.
	 *  @exception SQLException
	 */
	public void insertInstructorDB() {
		
		//creating query
		String sqlQuery = "INSERT INTO instructor (instructor_id, instructor_name, instructor_email, instructor_address, module_id) values (?, ?, ?, ?, ?)";
		try {
			
			//prepared statement
			PreparedStatement preSt  = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting the values
			preSt.setInt(1, getInsId());
			preSt.setString(2, getInsName());
			preSt.setString(3, this.email);
			preSt.setString(4, this.address);
			preSt.setInt(5, this.moduleId);
			
			preSt.executeUpdate(); //executing the prepared statement
			preSt.close(); //closing
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Checks if instructor is teaching in specified module.
	 * @param insId ID of the instructor
	 * @param moduleId ID of the module where instructor is being checked
	 * @return True or False based on whether instructor is teaching in the module or not
	 * @exception SQLException
	 */
	public boolean checkInstructorInModule( int insId,int moduleId) {
		
		//creating query
		String sqlQuery = "SELECT * FROM instructor WHERE instructor_id=? and module_id=?";
		try {
			
			//creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting values
			preSt.setInt(1, insId);
			preSt.setInt(2, moduleId);
			
			//executing prepared statement and storing the result
			ResultSet rSet = preSt.executeQuery();
			
			//iterating through the result set
			if(rSet.next()) {
				return true; //true if data found
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;  //false, if data corresponding to the query not found
	}

}
