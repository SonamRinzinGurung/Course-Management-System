package student;

//importing necessary classes from within the project
import courseManagementSystem.MySqlConnection;
import courseManagementSystem.LoginMenu;

//importing necessary Java Library Classes
import java.awt.Font;				
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



/**
 * This class creates the Student menu. The menu has two operations, 
 * enrolling a new student and displaying instructors of the student.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class StudentMenu implements ActionListener{
	
	//declaring instance variables
	private JFrame f1;
	private JButton enrollButton, viewButton,logoutButton,exitButton;
	private JTextField stuId;
	private JLabel label;
	
	private JFrame f2;
	private JButton backButton,exitButton2;
	private int i=0,j=0;
	
	
	
	/**
	 * This constructor of the class create a JFrame for the student menu. The menu has two operations, 
	 * enroll new student and display instructors of student
	 */
	public StudentMenu() {
		
		//new frame
		f1 = new JFrame("Student Menu");
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when it is closed
		
		//creating button to enroll student
		enrollButton = new JButton("New Student Enroll");
	    enrollButton.setBounds(15,30,300,100); //setting bounds of the button (x-axis, y-axis, width , height)
	    enrollButton.setFont(new Font("monospaced",Font.BOLD,20)); //setting font
	    f1.add(enrollButton); //adding the button to the frame
	    
	    //label for student id
	    label = new JLabel("To view Instructors | Enter Student ID");
	    label.setBounds(15, 200, 370, 100); //setting bounds
	    label.setFont(new Font("Arial",Font.BOLD,18));
	    f1.add(label);
	    
	    //text field to enter student id
	    stuId = new JTextField();
	    stuId.setBounds(420,230,100,50);
	    f1.add(stuId);
	    
	    //creating enter button to view instructors
	    viewButton = new JButton("Enter");
	    viewButton.setBounds(550,230,70,55);
	    f1.add(viewButton);
	    
	    //creating log out button
		logoutButton = new JButton("Log Out");
	    logoutButton.setBounds(15,400,150,75);
	    logoutButton.setFont(new Font("monospaced",Font.BOLD,20));
	    f1.add(logoutButton);
	    
	    //exit button
		exitButton = new JButton("Exit");
	    exitButton.setBounds(250,400,150,75);
	    exitButton.setFont(new Font("monospaced",Font.BOLD,20));
	    f1.add(exitButton);
	    
	    //adding action listener to add functionality to all the buttons
	    enrollButton.addActionListener(this);
	    viewButton.addActionListener(this);
	    logoutButton.addActionListener(this);
	    exitButton.addActionListener(this);
		
		f1.setBounds(220,45,800,550); //setting bounds to of the frame
		f1.setLayout(null);
		f1.setVisible(true); //displaying the frame
		
	}
	
	
	
	/**
	 * Creates a new JFrame to display the instructors teaching a specified student.
	 * @exception SQLException
	 * @exception Exception
	 */
	private void displayInstructors(){
		
		JTable table; //declaring JTable
		
		String x[]={"Instructor ID","Instructor Name","Module ID","Instructor Email","Instructor Address"}; //column of table
		String y[][] = new String[10][5]; //row of the table
		
		f2 = new JFrame("Instructors Display"); //creating the frame
		f2.setBounds(350,100,850,400); //setting bounds of the frame
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when it is closed
		
		//creating query
		String sqlQuery = "SELECT module_id FROM student WHERE student_id=?";
		try {
			//creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
				preSt.setInt(1, Integer.parseInt(stuId.getText())); //setting the value of student id
			
				ResultSet rSet = preSt.executeQuery(); //executing the statement and storing the result
			
				//iterating through the result set
				while(rSet.next()) {
					
					//creating query
					String sql = "SELECT instructor_id, instructor_name, module_id, instructor_email,instructor_address FROM instructor where module_id= "+ rSet.getString(1);
					PreparedStatement preSt2 = MySqlConnection.connect().prepareStatement(sql);
					ResultSet rs = preSt2.executeQuery(); //executing prepared statement
					try {
						while(rs.next()) { //iterating the result set
							
							y[i][j++]= rs.getString(1);  //instructor id
							y[i][j++]  = rs.getString(2); //instructor name
							y[i][j++]  = rs.getString(3); //module id
							y[i][j++]   = rs.getString(4); //instructor email
							y[i][j++] = rs.getString(5); //address
							i++;
							j=0;
						
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
			
		//creating table with the row and column values
		table = new JTable(y,x);
		table.setDefaultEditor(Object.class, null); //preventing the user from editing the contents of the table
		
		JScrollPane sp = new JScrollPane(table); //creating a scroll pane and inserting the table into it
		sp.setBounds(4, 10, 700, 120); //setting bounds
		
		//creating a back button
		backButton = new JButton("Back");
		backButton.setBounds(15 ,250 ,75 ,45 ); //setting bounds of the button
		f2.add(backButton);
		backButton.addActionListener(this); //adding action listener to the button
		
		//creating exit button
		exitButton2 = new JButton("Exit");
		exitButton2.setBounds(400 ,250 ,75 ,45 );
		f2.add(exitButton2);
		exitButton2.addActionListener(this); //adding action listener
		
		f2.add(sp); //inserting the scroll pane into the frame
		f2.setVisible(true); //displaying the frame
	}	
	
	
	
	//this function is overridden from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the enroll button pressed
		if(e.getSource()==enrollButton) {
			
			//creating new instance of the EnrollMenu class to open the enroll menu
			new EnrollMenu();
			
			f1.setVisible(false); //closing the current frame
		}
		
		//if viewButton pressed
		else if (e.getSource()==viewButton) {
			
			try {
				//creating new object of the Student class
				Student student = new Student();
				
				if(student.checkInDatabase(Integer.parseInt(stuId.getText()))==true) {
					//if student id found in the database
					displayInstructors(); //calling the display instructor function that contains the frame that displays the instructors
			
					f1.setVisible(false); //closing the current frame
			
				}else {
					//if student id is not found in database
					JOptionPane.showMessageDialog(null, "Invalid Student ID");
				}
			
					}catch(Exception ex) {
						ex.printStackTrace();
					}
		}
		
		//if logout button pressed
		else if (e.getSource()==logoutButton) {
			
			//creating new instance of the LoginMenu 
			new LoginMenu();
			
			f1.setVisible(false); //closing the current frame
		}
		
		//if exit button pressed
		else if(e.getSource()==exitButton){
			System.exit(0); //terminate the program
		}
		
		// back button pressed in view instructor frame
		else if(e.getSource()==backButton) {
			
			//create new instance of StudentMenu class
			new StudentMenu();
			
			f2.setVisible(false); //closing the current frame
			
		}
		//if exit button pressed in view instructor frame
		else if(e.getSource()==exitButton2) {
			System.exit(0); //terminate the program
		}
		
	}

}
