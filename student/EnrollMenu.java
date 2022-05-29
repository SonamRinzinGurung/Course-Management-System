package student;

//importing all the necessary classes from within the project
import courseManagementSystem.MySqlConnection;
import moduleAndCourse.Course;
import moduleAndCourse.Module;

//importing all the necessary Java Library classes
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
 * This class creates multiple JFrames to enroll new student into the system.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class EnrollMenu implements ActionListener{
	
	//declaring instance variables
	private int studentID;
	private String studentName;
	private String studentEmail;
	private int courseID;
	private int lvl;
	
	private JFrame f1;
	private JFrame f2;
	private JFrame f3;
	
	
	private JButton b1,backButton;
	private JTextField stuId,stuName,stuEmail;
	
	private JTextField courseId,level;
	private JButton b2,backButton2;
	private int i,j=0;
	
	private JButton b3;
	private JTextField modId1,modId2,modId3,modId4;
	private int k,l=0;
	
	
	
	/**
	 * This Constructor creates a JFrame that takes in the name and id of new student and then opens the next frame. 
	 */
	public EnrollMenu() {
		
		//declaring labels
		JLabel l1,l2,l3,emaiLabel;
		
		f1 = new JFrame("Enroll Student"); //creating JFrame
		f1.setLayout(null);
		f1.setBounds(400,90,450,300); //setting the bounds of the frame (x-axis,y-axis,width,height)
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when it is closed
		
		//label for heading
		l3 = new JLabel("New Student Registration");
		l3.setBounds(80,20,300,30);
		f1.add(l3);
		
		//label for student id
		l1 = new JLabel("Student ID");
		l1.setBounds(30,50,100,30);
		f1.add(l1);
		
		//label for student name
		l2 = new JLabel("Student Name: ");
		l2.setBounds(30, 100, 100, 30);
		f1.add(l2);
		
		//label for email
		emaiLabel = new JLabel("Student Email: ");
		emaiLabel.setBounds(30, 150, 100, 30);
		f1.add(emaiLabel);
		
		//text field to enter student id
		stuId = new JTextField();
		stuId.setBounds(130, 50, 150, 30);
		f1.add(stuId);
		
		//text field to enter student name
		stuName = new JTextField();
		stuName.setBounds(130, 100, 150, 30);
		f1.add(stuName);
		
		//text field to enter email
		stuEmail = new JTextField();
		stuEmail.setBounds(130, 150, 150, 30);
		f1.add(stuEmail);
		
		//creating button to enter
		b1 = new JButton("Enter");
		b1.setBounds(30, 190, 90, 30);
		f1.add(b1);
		
		//creating back button
		backButton = new JButton("Back");
		backButton.setBounds(320, 190, 90, 30);
		f1.add(backButton);
		
		//adding action listener to the buttons to add functionality
		backButton.addActionListener(this);
		b1.addActionListener(this);
		
		//displaying the frame
		f1.setVisible(true);
		
	}

	
	
		/**
		 * This method creates a new JFrame that takes in the course and level of the student enrolling.
		 * @exception SQLException
		 */
		private void courseIdAndLevel() {
			
			JTable table;
			JLabel l4,l5;
			String x[]= {"Course ID","Course Name"}; //column of the table
			String y[][] = new String[3][2]; //row of the table
			
			//creating the frame
			f2 = new JFrame("Enroll Student");
			f2.setBounds(250,30,800,500); //setting bounds to the frame
			f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exiting the frame when it is closed
			
			//creating query
			String sqlQuery = "SELECT course_id, course_name from course where status=? ";
				try {
					
				//creating prepared statement
				PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
				preSt.setString(1, "Active"); //setting the value of the status
				
				ResultSet rSet = preSt.executeQuery(); //executing the prepared statement and storing the result
				
				//iterating through the result
				while(rSet.next()) {
					
					y[i][j++] = rSet.getString(1); //course id
					y[i][j++] = rSet.getString(2); //course name
					i++;
					j=0;
				}
				}catch(SQLException exp) {
					exp.printStackTrace();
				}
			
				table = new JTable(y,x); //creating table and adding the row and column data
				
				table.setDefaultEditor(Object.class, null); //prevent the user from editing the contents of the table
				
				JScrollPane sp = new JScrollPane(table); //creating a scroll pane
			
				//label for course id
				l4 = new JLabel("Course ID");
				l4.setBounds(5, 150,100, 30);
				f2.add(l4);
				
				//label for level
				l5 = new JLabel("Level [4|5|6]");
				l5.setBounds(5, 200, 100, 30);
				f2.add(l5);
				
				//creating text field to enter course id
				courseId = new JTextField();
				courseId.setBounds(100, 150, 150, 30);
				f2.add(courseId);
				
				//creating text field to enter level
				level = new JTextField();
				level.setBounds(100, 200, 150, 30);
				f2.add(level);
				
				//creating button to enter
				b2 = new JButton("Enter");
				b2.setBounds(5, 300, 75, 50);
				f2.add(b2);
				
				//creating a back button
				backButton2 = new JButton("Back");
				backButton2.setBounds(300, 300, 75, 50);
				f2.add(backButton2);
				
				//adding action listeners to the buttons to add functionality to them
				b2.addActionListener(this);
				backButton2.addActionListener(this);
				
				f2.add(sp); //adding the scroll pane to the frame
 				
				f2.setVisible(true); //displaying the frame
		}
		
		
		/**
		 * This method creates a JFrame for level six students to select any four modules to enroll into.
		 * 
		 * @exception SQLException 
		 */
		private void selectModuleForLvl6() {
			
			JLabel l6,l7,l8,l9,headLabel; //declaring labels
			
			JTable moduleTable;  //declaring table
			String column[]= {"Module ID","Module Name"}; //column of the table
			String row[][] = new String [8][2];  //row of the table
			
			
			f3 = new JFrame("Enroll Student"); //creating the frame
			f3.setBounds(250,30,800,580); //setting the frame bounds
			f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when closed
			
			//creating a query
			String sqlQuery = "SELECT module_id, module_name FROM module WHERE course_id=? AND level=?";
				try {
					
				//establishing connection and creating prepared statement
				PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
				
				//setting the values of course id and level
				preSt.setInt(1, courseID);
				preSt.setInt(2, lvl);
				
				ResultSet rSet = preSt.executeQuery(); //executing the prepared statement and storing the result
				
				//iterating through the result set
				while(rSet.next()) {
					
					row[k][l++] = rSet.getString(1); //module id
					row[k][l++] = rSet.getString(2); //module name
					k++;
					l=0;
				}
				}catch(SQLException exp) {
					exp.printStackTrace();
				}
			 
				moduleTable = new JTable(row,column); //creating table with row and column data
				moduleTable.setDefaultEditor(Object.class, null); //prevents the user from editing the contents of the table
				
				JScrollPane sp2 = new JScrollPane(moduleTable); //creating the scroll pane and inserting the table into it
				
				//label for module id
				headLabel = new JLabel("Enter ID of any four Modules");
				headLabel.setFont(new Font("Arial Black",Font.BOLD,15));
				headLabel.setBounds(120, 200,400, 30);
				f3.add(headLabel);
				
				//label for module num1
				l6 = new JLabel("Module ID 1");
				l6.setBounds(5, 250,100, 30);
				f3.add(l6);
				
				//label for module num2
				l7 = new JLabel("Module ID 2");
				l7.setBounds(5, 300, 100, 30);
				f3.add(l7);
				
				//label for module num3
				l8 = new JLabel("Module ID 3");
				l8.setBounds(270, 250,100, 30);
				f3.add(l8);
				
				//label for module num4
				l9 = new JLabel("Module ID 4");
				l9.setBounds(270, 300, 100, 30);
				f3.add(l9);
				
				//text field to enter first module id
				modId1 = new JTextField();
				modId1.setBounds(100, 250, 150, 30);
				f3.add(modId1);
				
				//text field to enter second module id
				modId2 = new JTextField();
				modId2.setBounds(100, 300, 150, 30);
				f3.add(modId2);
				
				//text field to enter third module id
				modId3 = new JTextField();
				modId3.setBounds(365, 250, 150, 30);
				f3.add(modId3);
				
				//text field to enter fourth module id
				modId4 = new JTextField();
				modId4.setBounds(365, 300, 150, 30);
				f3.add(modId4);
				
				//creating button to enter
				b3 = new JButton("Enter");
				b3.setBounds(15, 380, 75, 50);
				f3.add(b3);
				
				//adding action listener to the enter button
				b3.addActionListener(this);
				
				
				f3.add(sp2); //inserting the scroll pane into the frame
				
				f3.setVisible(true); //displaying the frame
		}

		
		
	//this function is overridden from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//creating objects of classes
		Student s = new Student();
		Module m = new Module();
		Course c = new Course();
		
		//if the action event occurred on button b1
		if(e.getSource()==b1) {
			try {
			
			//setting the values of variables using the input data from the respective text fields
			String studentIDString = stuId.getText();
			studentID = Integer.parseInt(studentIDString);
			studentName = stuName.getText();
			studentEmail = stuEmail.getText();
			
			
			if(s.checkInDatabase(studentID)==false) {
				//if the student id doesn't exists in the database
				
				courseIdAndLevel(); //call the function that opens the frame to choose the course and level
				
				f1.setVisible(false); //closing the current frame
				
			}else {
				//if the student id already in the database
				JOptionPane.showMessageDialog(null, "Student ID already exists");
			}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		//if back button pressed
		else if (e.getSource()==backButton) {
			
			new StudentMenu(); //new instance of StudentMenu class to go back
			
			f1.setVisible(false); //closing the current frame
		}
		
		//if the action event occurred on button b2
		else if(e.getSource()==b2) {
			try {
			
			//setting the values of variables using the input data from the respective text fields	
			courseID = Integer.parseInt(courseId.getText());
			lvl = Integer.parseInt(level.getText());
			
			
			if(c.checkInDatabase(courseID)==true) {
				//if the course id exists in database
				
				if(m.checkModuleDBCourseID(courseID)==true) {
					//if module in the specified course
					
					if(lvl==4 || lvl==5 || lvl==6) { //if the entered level is 4 or 5 or 6
						
					if (lvl == 4 || lvl==5) { //for level 4 and 5 only
						
						//creating query
						String sqlQuery  = "SELECT module_id FROM module WHERE course_id=? AND level=?";
						try {
						
						//creating prepared statement
						PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
						
						preSt.setInt(1, courseID); //setting course id
						preSt.setInt(2, lvl); //setting level
						
						ResultSet rSet = preSt.executeQuery(); //executing the query and storing the result
						
						//iterating through the result
						while(rSet.next()) {
							
							//query
							String sqlQuery2 = "INSERT INTO student (student_id,student_name,student_email,module_id) VALUES (?,?,?,?)";
							try {
								
								PreparedStatement preSt2 = MySqlConnection.connect().prepareStatement(sqlQuery2);
								
								//setting the parameter values in the query
								preSt2.setInt(1, studentID);
								preSt2.setString(2, studentName);
								preSt2.setString(3, studentEmail);
								preSt2.setInt(4,Integer.parseInt(rSet.getString(1)));
								
								preSt2.executeUpdate(); //executing the prepared statement
								preSt2.close(); //closing the prepared statement
								
							} catch (SQLException se) {
								se.printStackTrace();
								JOptionPane.showMessageDialog(null, "SQL Error");
							}
						}
						
						JOptionPane.showMessageDialog(null, "Student was enrolled successfully"); //successful enrollment message
						
						new StudentMenu(); //new instance of the StudentMenu to go back to the menu
						 
						f2.setVisible(false); //closing the current frame
						
						}catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "SQL Error");
							ex.printStackTrace();
						}
					}
					
					if(lvl==6) { //if level 6 entered by user
						
						selectModuleForLvl6();  //opens the frame that lets the user select  4 modules to choose
						
						f2.setVisible(false); //closes the current frame
					}
					
					
					}else {
						//if user entered level other than 4,5,6
						JOptionPane.showMessageDialog(null, "Enter Level 4 or 5 or 6 only");
					}
					
				}else {
					//if the course does not contain module
					JOptionPane.showMessageDialog(null, "This Course has no modules");
				}
			}else {
				//if the course id is invalid
				JOptionPane.showMessageDialog(null, "Course ID does not exists");
			}
			
			}catch (Exception excep) {
				excep.printStackTrace();
			}
		}
		
		//if back button pressed
		else if(e.getSource()==backButton2) {
			
			new EnrollMenu(); //crating new instance of the EnrollMenu to go back to the menu
			
			f2.setVisible(false); //close the current frame
		}
		
		//if the action event occurred on button b3
		else if(e.getSource()==b3) {
			
			//setting the values of variables using the input data from the respective text fields
			int modChoice1= Integer.parseInt(modId1.getText());
			int modChoice2  = Integer.parseInt(modId2.getText());
			int modChoice3= Integer.parseInt(modId3.getText());
			int modChoice4  = Integer.parseInt(modId4.getText());
			
			
			try {
				//executing the function only if all the chosen module IDs are valid
				if(m.checkInDatabase(modChoice1)==true) {
					if(m.checkInDatabase(modChoice2)==true) {
						if(m.checkInDatabase(modChoice3)==true) {
							if(m.checkInDatabase(modChoice4)==true) {
								
								int[] moduleChoosen = {modChoice1,modChoice2,modChoice3,modChoice4} ; //storing the module IDs into a list
								
								//iterating through the chosen module id 
								for(int i =0;i<4;i++) {
									
									//creating query to insert data into student table in database
									String sqlQuery2 = "INSERT INTO student (student_id,student_name,student_email,module_id) values (?,?,?,?)";
									try {
										
										//establishing connection and creating prepared statement
										PreparedStatement preSt2 = MySqlConnection.connect().prepareStatement(sqlQuery2);
										
										//setting the values of unknown parameters in the query
										preSt2.setInt(1, studentID);
										preSt2.setString(2,studentName);
										preSt2.setString(3, studentEmail);
										preSt2.setInt(4, moduleChoosen[i]); //setting the module id of each choice with each iteration of this loop
										
										preSt2.executeUpdate(); //executing the prepared statement
										preSt2.close(); //closing the prepared statement
										
									} catch (SQLException sqlE) {
										sqlE.printStackTrace();
										JOptionPane.showMessageDialog(null, "SQL Error Occured!!");
									}
								}
								
								//successfully enrolled message
								JOptionPane.showMessageDialog(null, "Student was successfully enrolled into level 6");
								
								new StudentMenu(); //creating the new instance of the StudentMenu to open the menu
								
								f3.setVisible(false); //closing the current frame
				
				}else {
						
					   //if the fourth module id is invalid
					   JOptionPane.showMessageDialog(null, "Module ID of fourth choice does not exists.");
					  }	
				}else {
					   //if the third module id is invalid
					   JOptionPane.showMessageDialog(null, "Module ID of third choice does not exists.");
				}
				}else {
					//if the second module id is invalid
					JOptionPane.showMessageDialog(null, "Module ID of second choice does not exists.");
				}
					
				}else {
					//if the first module id is invalid
					JOptionPane.showMessageDialog(null, "Module ID of first choice does not exists.");
				}
			} catch (Exception e2) { //handling exceptions
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured!!");
			}
		}
		
	}
	
}
