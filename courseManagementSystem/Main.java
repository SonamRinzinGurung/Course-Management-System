package courseManagementSystem;


/*
 * This is the main entry point of the Course Management System program.
 * 
 *  Login Configuration for different types of users:
 *  	
 * Course Administrator --> username: admin    		 password: admin
 * Instructor 			--> username: instructor     password: instructor  
 * Student				--> username: student		 password: student
 */



/**
 * This is the main class of this program. The program should be run from this class.
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 *
 */
public class Main {

	/**
	 * This is the main method of this class. Here the program is initiated.
	 * @param args
	 */
public static void main(String[] args) {
	try {
		
		//creating a instance of the class LoginMenu to start the program
		new LoginMenu();
		
		
		//catching any exceptions
		} catch (Exception e) {
			
		e.printStackTrace();
		
		}
	}

}
