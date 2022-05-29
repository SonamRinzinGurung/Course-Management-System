package courseManagementSystem;


//importing the necessary classes from different packages of this project
import courseAdministrator.CourseAdministratorMenu;			
import student.StudentMenu;
import instructor.InstructorMenu;

//importing necessary Java library classes
import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/*
 * Course Administrator --> username: admin    		 password: admin	
 * Instructor 			--> username: instructor     password: instructor  
 * Student				--> username: student		 password: student
 */




/**
 * This class creates the JFrame for the Login Menu. The program starts from this class.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class LoginMenu extends JFrame implements ActionListener{
	
	//declaring instance variables for the JFrame
	private JButton loginButton, cancelButton;
	private JLabel l1,l2;
	private JTextField userText;
	private JPasswordField password;

/**
 * This constructor of the class: LoginMenu creates a frame that allows the user to login as either admin, instructor or student. 
 */
public LoginMenu() {

	super("Login Menu");
	setLayout(null);  //layout is set to null
	setBounds(450,200,350,200);  //setting bounds (x-axis,y-axis,width,height)

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely close the JFrame when exited
	
	//creating a label for Username
	l1 = new JLabel("UserName "); //creating a new instance of the JLabel class
	l1.setBounds(30,20,100,30);   //bounds(x-axis,y-axis,width,height)
	add(l1);   //adding the label to the JFrame
	
	//creating a label for password
	l2 = new JLabel("Password: ");
	l2.setBounds(30, 70, 100, 30);
	add(l2);
	
	//creating a text field for the username
	userText = new JTextField();  //creating a new instance of the JTextField class
	userText.setBounds(110, 20, 150, 30);
	add(userText);  //adding the text field to the JFrame
	
	//creating a password field for the password
	password = new JPasswordField(); //creating a new instance of the JPasswordField class
	password.setBounds(110, 70, 150, 30);
	add(password);
	
	//creating a button for login
	loginButton = new JButton("Login");  //creating a new instance of the JButton class
	loginButton.setBounds(30, 120, 90, 20);
	add(loginButton);
	
	//creating a cancel button
	cancelButton = new JButton("Cancel");
	cancelButton.setBounds(180, 120, 90, 20);
	add(cancelButton);
	
	
	//adding action listeners to the buttons to perform some actions when pressed
	loginButton.addActionListener(this);
	cancelButton.addActionListener(this);
	
	//setting the visibility of frame to true, show the frame
	setVisible(true);
	
}



//This function is overridden from the ActionListener interface
@Override
public void actionPerformed(ActionEvent e) {
	
	// .getSource() returns the object on which the event initially occurred
	if(e.getSource()==loginButton) {  	//if login button pressed
	try {
		
		//initializing userName with the text entered by user in the userText textField
		String userName = userText.getText();
		
		//initializing passWord with the text entered by user in the password passwordField
		@SuppressWarnings("deprecation")
		String passWord = password.getText();
		
		//creating a query
		String sqlQuery="SELECT * FROM login WHERE admin_username='"+userName+"' AND admin_password='"+passWord+"'";
		
		//PreparedStatement allows us to write query in parameterized form
		PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
		
		//Result stores the result of the executed SQL query
		ResultSet rSet = preSt.executeQuery();
		
		//next() function of ResultSet moves the cursor to the next row
		if(rSet.next()) {
			
			//if username and password entered by user is equal to the one stored in the database
			if(rSet.getString(2).equals("admin") && rSet.getString(3).equals("admin")) { //result index starts from 1
				
					new CourseAdministratorMenu(); //creating new instance to instantiate this class
					
					setVisible(false);  //close the current frame	
			}
			
			//if username and password entered by user is equal to the one stored in the database
			else if(rSet.getString(2).equals("student") && rSet.getString(3).equals("student")) {
				
					new StudentMenu(); //creating new instance to instantiate this class
					
					setVisible(false);  //close the current frame			
			}
			
			//if username and password entered by user is equal to the one stored in the database
			else if(rSet.getString(2).equals("instructor") && rSet.getString(3).equals("instructor")) {
					
					new InstructorMenu();  //creating new instance to instantiate this class
					
					setVisible(false); //close the current frame
			}
			
		}else {
			
			//JOptionPane shows dialog messages in the JFrame
			JOptionPane.showMessageDialog(null, "Login Unsuccessful"); //unsuccessful login message
		}
		
		//catching exceptions
		} catch (Exception ex) {
		ex.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error Occured");
		}
	
	
	}
	
	//if cancel button pressed
	else if(e.getSource()==cancelButton) {
		System.exit(0); //exit the program
	}
	
}

}
