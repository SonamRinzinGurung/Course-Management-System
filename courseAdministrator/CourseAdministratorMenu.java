package courseAdministrator;


//importing necessary classes from different packages of the project
import moduleAndCourse.Course;
import student.Student;
import courseManagementSystem.MySqlConnection;
import courseManagementSystem.LoginMenu;

//importing necessary Java Library classes
import java.awt.BorderLayout;	
import java.awt.Font;	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



/**
 * This class creates the menu for Course Administrator.
 * It connects with the operations on Course, Module , Instructor and Report Slip.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class CourseAdministratorMenu implements ActionListener{

	//declaring necessary instance variables
	private String stdName;
	private JFrame menuFrame ; 
	private JButton courseButton,module,inst,report,logOut,exitButton;
	private JTextField stdId,level;
	private JFrame reportFrame;
	private JTextArea textArea;
	private JPanel panel;
	private JButton backButton;
	private JComboBox<String> cb;
	private ArrayList<String> array;

	
	/**
	 * Getter method for student name
	 * 
	 * @return Name of the student as string
	 */
	private String getStdName() {
		return stdName;
	}
	
	
	/**
	 * This constructor of the class create the menu that contains operations 
	 * on course, module, instructor and display the report of student.
	 */
	public CourseAdministratorMenu() {
		
		//declaring JLabels
		JLabel heading,l1,l2,l3,l4,courseIDLabel,levelLabel;
		
		//creating new instance of JFrame 
		menuFrame = new JFrame("Administrator Menu");
		
		menuFrame.setBounds(220,40,800,600); //(x-axis,y-axis,width,height)
		menuFrame.setLayout(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //close safely when exited
		
		//heading label
		heading = new JLabel("Administrator Menu");
		heading.setFont(new Font("monospaced",Font.BOLD,20)); //setting font of heading
		heading.setBounds(250,5,350,20); //setting bounds (x-axis,y-axis,width,height)
		menuFrame.add(heading);  //adding to the frame
		
		//creating course heading
		l1 = new JLabel("Work On Courses");
		l1.setFont(new Font("monospaced",Font.BOLD,18)); //setting font
		l1.setBounds(30,50,170,20);
		menuFrame.add(l1);
		
		//creating course button
		courseButton = new JButton("Course");
		courseButton.setBounds(205,50,150,50); //setting size and position
		menuFrame.add(courseButton); //adding the button to the frame
		
		//creating module heading
		l2 = new JLabel("Work On Modules");
		l2.setFont(new Font("monospaces",Font.BOLD,18));
		l2.setBounds(30,110,170,20);
		menuFrame.add(l2);
		
		//creating module button
		module  = new JButton("Module");
		module.setBounds(25,150,150,50);
		menuFrame.add(module);
		

		//creating instructor heading
		l3 = new JLabel("Work On Instructors | Enter Course ID and Level to view details");
		l3.setFont(new Font("monospaces",Font.BOLD,18));
		l3.setBounds(30,220,560,20);
		menuFrame.add(l3);
		
		//creating label for course id
		courseIDLabel = new JLabel("Enter Course ID");
		courseIDLabel.setBounds(30,240,100,75);
		menuFrame.add(courseIDLabel);
		
		
		//getting course id from the database for the JCombo Box drop down menu
		try {
			String sqlQuery = "SELECT course_id from course";
			//establishing connection and creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			 array = new ArrayList<>();
			
			//storing the result after execution
			ResultSet rSet = preSt.executeQuery();
			
			//iterating through the result
			while(rSet.next()) {
				array.add(rSet.getString(1));  //adding each data into the ArrayList
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String[] courseArray = array.toArray(new String[array.size()]);  //converting the ArrayList into a String list
		
		//creating combo box for course id
		cb = new JComboBox<String>(courseArray);
		cb.setBounds(140,270,80,20);
		menuFrame.add(cb);
		
		//creating label to enter level
		levelLabel = new JLabel("Enter Level");
		levelLabel.setBounds(230,240,100,75);
		menuFrame.add(levelLabel);
		
		//creating text box for level
		level = new JTextField();
		level.setBounds(310,270,80,20);
		menuFrame.add(level);
		
		//creating enter button
		inst  = new JButton("Enter");
		inst.setBounds(435,260,75,35);
		menuFrame.add(inst);
		
		
		//creating label for report
		l4 = new JLabel("Generate Report | Enter Student ID");
		l4.setFont(new Font("monospaces",Font.BOLD,18));
		l4.setBounds(30,350,400,20);
		menuFrame.add(l4);
		
		//creating text box for student id
		stdId = new JTextField();
		stdId.setBounds(35,380,75,35);
		menuFrame.add(stdId);
		
		//creating button to show report
		report  = new JButton("Show Report");
		report.setBounds(120,380,150,50);
		menuFrame.add(report);
		
		//creating button to log out
		logOut  = new JButton("LogOut");
		logOut.setBounds(30,500,85,35);
		menuFrame.add(logOut);
		
		//creating an exit button
		exitButton  = new JButton("Exit");
		exitButton.setBounds(300,500,85,35);
		menuFrame.add(exitButton);
		
		//adding actionListener to the buttons to perform certain task when pressed
		courseButton.addActionListener(this);
		module.addActionListener(this);
		inst.addActionListener(this);
		report.addActionListener(this);
		logOut.addActionListener(this);
		exitButton.addActionListener(this);
	
		
		menuFrame.setVisible(true);  //display the frame
	}

	
	
	/**
	 * This private method creates a new JFrame that displays the report of the specified student.
	 * 
	 * @param studentID ID of the student whose report is to be displayed
	 * 
	 * @exception SQLException
	 * @exception Exception
	 * 
	 */
	private void displayReport(int studentID) {
		
		//creating new frame to display the report
		reportFrame = new JFrame("Student Report");
		
		reportFrame.setBounds(250,45,620,600); //setting the position and size of the frame
		reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit the frame when closed
		reportFrame.setLayout(new BorderLayout()); //border layout used
		reportFrame.setVisible(true); //display the frame
		
		panel = new JPanel();  //creating a new panel
		
		//creating a text area to insert the report
		textArea = new JTextArea(50,15);
		textArea.setEditable(false); //doesn't allow the user to edit the text
		
		//creating a back button
		backButton = new JButton("Back");
		backButton.setBounds(5,600,75,35);
		backButton.addActionListener(this);  //adding action listener to the back button
		

		panel.add(backButton);	//adding the back button to the panel
		panel.add(textArea);	//adding the text area to the panel
		
		//adding the whole panel to the report frame
		reportFrame.add(panel,"North");
		
		
		
		try {
			
				//creating a query to select information of the student
				String sqlQuery = "SELECT student_id, student_name, module_id, marks FROM student WHERE student_id=?";
				try {
					//creating prepared statement and establishing connection
					PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
					preSt.setInt(1, studentID); //setting the student id
					
					ResultSet rSet = preSt.executeQuery(); //executing the query and storing the result
					
					//creating a new array list
					ArrayList<String> marksList = new ArrayList<>();
					
					//adding text to the text area
					textArea.setText("\n\n\n\t             Student Marks Report\n\n");
				
					//initializing with zero
					int totalMarks = 0;  //total marks obtained
					int passCount = 0;   //how many modules passed
					int failCount = 0;   //how many modules failed
					
					//while result set have next row
					while(rSet.next()) {
						
						//setting the student name (2 is the index of student name in database table and query)
						this.stdName = rSet.getString(2);
						
						//getString(4) retrieves the marks of the student
						if(Integer.parseInt(rSet.getString(4))>39) { //if marks greater than 49
							
							passCount++;  //student passed the module
							
							//inserting the details into the array list of marks
							marksList.add("Module ID: "+ rSet.getString(3) + "     " + "Marks: "+rSet.getString(4)+"\t"+"  Result: Pass\n");
							
						}else { //if student scored less than 39
							
							//adding the back button to the panel
							marksList.add("Module ID: "+ rSet.getString(3) + "     " + "Marks: "+rSet.getString(4)+"\t"+"  Result: Fail\n");
							
							failCount++; //student failed
							
						}
						
						totalMarks = totalMarks + Integer.parseInt(rSet.getString(4)); //calculating the total marks obtained by the student
						
					}
					
					//adding details to the report
					textArea.append("\n\n Student Name: "+getStdName());  //student name
					textArea.append("\n\n Student ID: "+studentID);       //student id
					textArea.append("\n\n\n______________________________________________________\n\n\n");
					
					//Iterator is used to loop through the data in the collection, in this case ArrayList. It retrieves the elements in the collection one by one
					Iterator<String> itrt = marksList.iterator(); //using iterator
					
					//while the array have next element
					while(itrt.hasNext()) { 
						
						//appending the selected element in the ArrayList into the text area of the JFrame
						textArea.append(itrt.next());
						
					}
					
					textArea.append("\nTotal Marks Obtained = "+totalMarks);  //displaying the total marks obtained in the frame
					
					
					textArea.append("\n\n______________________________________________________\n\n");
					
					//if the student has more passed modules than failed ones
					if(passCount>=failCount) {
						
						//student can move to next level
						textArea.append("Congratulations!! You are eligible to move to the next level\n\n");
						
						//if student has failed more modules
					}else {
						
						//student can't move to next level
						textArea.append("Sorry, You are not eligible to move to the next level\n\n");
						
					}
					
				//catching SQL exceptions
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		//catching exceptions
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Occured");
		}
		
	}
	
	

	//This function is overridden from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the action event occurred on course button / if course button pressed
		if(e.getSource()==courseButton) {
			
			//create new instance of AdminCourseOperations class and instantiating it
			new AdminCourseOperations();
			
			//close the current frame
			menuFrame.setVisible(false);
		}
		
		//if module button pressed
		else if(e.getSource()==module) {
			
			//create new instance of AdminModuleOperations class and instantiating it
			new AdminModuleOperations();
			
			//close the current frame
			menuFrame.setVisible(false);
		}
		
		//if the instructor button pressed
		else if(e.getSource()==inst) {
			
			//creating an object of Course class
			Course c= new Course();
			
			try {
				
				//setting the values of course id and level
				int courseID = Integer.parseInt(cb.getItemAt(cb.getSelectedIndex()));
				int level  = Integer.parseInt(this.level.getText());
				
				//if the course id exists in the database
				if(c.checkInDatabase(courseID)==true) {

					if(level==4 || level==5 || level==6) {
						
				//create new instance of AdminInstructorOperations class and instantiating it	
				new AdminInstructorOperations(courseID,level);
				
				//closing the current frame
				menuFrame.setVisible(false);
				
					}else { //if level entered is invalid
						JOptionPane.showMessageDialog(null, "Invalid Level"); //prompt error message
					}
					
				}else { //if the course id is invalid
					JOptionPane.showMessageDialog(null, "Course ID does not exists"); //prompt error message
				}
				
			//catching exceptions
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error Occured");
				e2.printStackTrace();
			}
		}
		
		//if the report button pressed
		else if(e.getSource()==report) {
			
			try {
				//creating an object of the Student class
				Student s = new Student();
				
				//setting the value of student id from the text field. Parsing to integer because the text field takes data in string format.
				int studentID = Integer.parseInt(stdId.getText());
				
				//checking if student id is valid
				if(s.checkInDatabase(studentID)==true) {
					
					//calling the function to display the report of the specific student
					displayReport(studentID);
					
					menuFrame.setVisible(false); //closing the current frame
				
				}else { //invalid student id
					JOptionPane.showMessageDialog(null, "Student ID does not exists");
				}
				
			//catching exceptions
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error Occured");
				e2.printStackTrace();
			}
			
			
		}
		//if the back button is pressed
		else if(e.getSource()==backButton) {
			
			//creating new instance of CourseAdministratorMenu class . Going back to the menu
			new CourseAdministratorMenu();
			
			//closing the current frame
			reportFrame.setVisible(false);
		}
		
		//if the logout button pressed
		else if(e.getSource()==logOut) {
			
			//creating new instance of the LoginMenu class. Going back to the login menu
			new LoginMenu();
			
			//closing the current frame
			menuFrame.setVisible(false);
		}
		
		//if the exit button pressed
		else if(e.getSource()==exitButton) {
			System.exit(0); //terminate the program
		}
	}
	
}
