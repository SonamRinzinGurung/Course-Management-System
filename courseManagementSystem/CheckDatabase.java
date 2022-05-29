package courseManagementSystem;

//importing necessary library class
import java.sql.PreparedStatement;

/**
 * Abstract class used to check information in the database.
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 */
public abstract class CheckDatabase {
	
	protected PreparedStatement preSt;
	
	/**
	 * Checks if the the given id is in the database
	 * @param id specified ID
	 * @return True or False
	 */
	abstract public boolean checkInDatabase(int id);  //abstract method used by classes that inherit this class

}
