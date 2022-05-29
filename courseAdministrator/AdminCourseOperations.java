package courseAdministrator;

//importing necessary classes from different packages of the project 
import moduleAndCourse.Course;
import courseManagementSystem.MySqlConnection;

//importing necessary Java Library classes
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;




/**
 * This class creates the menu for the operations on course that the course administrator can utilize.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class AdminCourseOperations implements ActionListener{
	
	//declaring instance variables
	private JFrame f1;
	private JTextField newCourseId,newCourseName,renameCourseId,renamedCourse,courseId;
	private JButton addCourse,editCourse,deleteCourse,resumeCourse,cancelCourse,backButton,exitButton;
	private int courseID;
	
	int i,j=0;
	
	
	
	   /**
	    * This constructor creates the JFrame that contains all the operations on the course.<br>
	    * First displays detail about course id, course name and status of course.<br>
	    * The operations are: add course, edit course, cancel or resume course, delete course.
	    * 
	    * @exception SQLException
	    */
	   public AdminCourseOperations(){
		   
		//declaring labels
		JLabel h1,h2,h3,h4,in1,in2,in3,in4;
		
		JTable table; //declaring a JTabel
		
		String column[] = {"Course ID","Course Name","Course Status"}; //columns of the table
		String row[][] = new String[6][3];  //two dimension list for the row of the table
		
		//creating a new JFrame
		f1 = new JFrame("Course Operations");
		
		//creating a new panel
		JPanel jPanel= new JPanel() ;
		
		//setting bounds for the frame
		f1.setBounds(200,20,900,650); //(x-axis,y-axis,width,height)
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //exit the frame safely when closed
		
		//creating query
		String sqlQuery = "SELECT course_id, course_name, status from course";
		try {
			
			//creating a prepared statement and establishing connection
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//executing the statement and storing the result
			ResultSet rSet = preSt.executeQuery();
			
			//iterating the result set
			while(rSet.next()) {
				
				//storing the data in the list of row
				row[i][j++]= rSet.getString(1);  //course id
				row[i][j++] = rSet.getString(2);  //course name
				row[i][j++] = rSet.getString(3); //status
				i++;
				j=0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//creating a JTable and inserting the row and column for the table
		table = new JTable(row,column);
		
		table.setDefaultEditor(Object.class, null); // prevents the content of the table from being edited by the user
		
		//creating a new scroll pane. JScrollPane creates a frame with scrollable window
		JScrollPane sp = new JScrollPane();
		sp.setBounds(50, 10, 800, 120);  //setting bounds (x-axis, y-axis, width, height)
		sp.setViewportView(table);  //adding the table to the scroll pane
		 
		//adding the scroll pane to the panel
		jPanel.add(sp); 
		jPanel.setLayout(null);
		
		//creating add course label
		h1 = new JLabel("Add Course");
		h1.setFont(new Font("arial black",Font.BOLD,15));
		h1.setBounds(30,160,170,20);  //setting bounds (x-axis, y-axis, width, height)
		f1.add(h1);  //inserting into the frame
		
		//label for the course id
		in1 = new JLabel("Enter Course ID");
		in1.setBounds(30,185,100,20);
		f1.add(in1);
		
		//creating text box to enter course id
		newCourseId = new JTextField();
		newCourseId.setBounds(190,185,75,25);
		f1.add(newCourseId);
		
		//course name label
		in2 = new JLabel("Enter Course Name");
		in2.setBounds(30,220,130,20);
		f1.add(in2);
		
		//text box to enter course name
		newCourseName = new JTextField();
		newCourseName.setBounds(190,220,175,25);
		f1.add(newCourseName);
		
		//creating button to add the course
		addCourse = new JButton("Add Course");
		addCourse.setBounds(370,203,110,40);
		f1.add(addCourse);
		
		//edit course heading label
		h2 = new JLabel("Edit Course");
		h2.setFont(new Font("arial black",Font.BOLD,15));
		h2.setBounds(30,250,170,20);
		f1.add(h2);
		
		//label for course id
		in3 = new JLabel("Enter Course ID");
		in3.setBounds(30,275,100,20);
		f1.add(in3);
		
		//text box for the new course id
		renameCourseId = new JTextField();
		renameCourseId.setBounds(190,275,75,25);
		f1.add(renameCourseId);
		
		//new course name label
		in4 = new JLabel("Enter new Course Name");
		in4.setBounds(30,310,150,20);
		f1.add(in4);
		
		//text box for the new course name
		renamedCourse = new JTextField();
		renamedCourse.setBounds(190,310,175,25);
		f1.add(renamedCourse);
		
		//button to edit the course
		editCourse = new JButton("Edit Course");
		editCourse.setBounds(370,303,110,40);
		f1.add(editCourse);
		
		//course id heading
		h3 = new JLabel("Enter Course ID and select any one operation");
		h3.setFont(new Font("arial black",Font.BOLD,15));
		h3.setBounds(60,365,400,20);
		f1.add(h3);
		
		h4 = new JLabel("Enter Course ID");
		h4.setBounds(30,405,100,20);
		f1.add(h4);
		
		//text box for course id
		courseId = new JTextField();
		courseId.setBounds(140,405,75,25);
		f1.add(courseId);
		
		//button to resume the course
		resumeCourse = new JButton("Resume Course");
		resumeCourse.setBounds(30,455,140,40);
		f1.add(resumeCourse);
		
		//button to cancel the course
		cancelCourse = new JButton("Cancel Course");
		cancelCourse.setBounds(180,455,140,40);
		f1.add(cancelCourse);
		
		//button to delete the course
		deleteCourse = new JButton("Delete Course");
		deleteCourse.setBounds(330,455,140,40);
		f1.add(deleteCourse);
		
		//back button
		backButton = new JButton("Back");
		backButton.setBounds(30,540,85,35);
		f1.add(backButton);
		
		//exit button
		exitButton = new JButton("Exit");
		exitButton.setBounds(180,540,85,35);
		f1.add(exitButton);
		
		//adding action listener to all the buttons in the frame to assign task to each button
		addCourse.addActionListener(this);
		editCourse.addActionListener(this);
		resumeCourse.addActionListener(this);
		cancelCourse.addActionListener(this);
		deleteCourse.addActionListener(this);
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		//adding the panel to the frame
		f1.add(jPanel);
		
		//displaying the frame
		f1.setVisible(true);
	}
	

	//overridden function from the ActionListener interface 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//creating an object of the Course class
		Course course = new Course();
		
		//if the addCourse button pressed
		if (e.getSource()==addCourse) {
			
			try {
				
			int newCourseID = Integer.parseInt(newCourseId.getText()); //getting value from the text field in the frame
			String newCourseName = this.newCourseName.getText(); //course name
			
			//checking if the course id already in database
			if(course.checkInDatabase(newCourseID)==false) {
				
				//inserting the new course id and course name into the database
				course.insertCourseIntoDB(newCourseID, newCourseName); //using the function of Course class
				
				JOptionPane.showMessageDialog(null, "New Course was successfully added"); //success message
				
				f1.setVisible(false); //close the frame after successful course addition
				
				//reopen the current class to refresh the frame
				new AdminCourseOperations();
				
			}else {
				//if the course id already exists 
				JOptionPane.showMessageDialog(null, "Course ID already exists");
			}
				}
				
			//catching exceptions
				catch(Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error Occured");
				}
		}
		
		//if edit course button pressed
		else if(e.getSource()==editCourse) {
			
			try {
				//setting the values from the text field
				int editCourseID = Integer.parseInt(renameCourseId.getText());
				String editCourseName = renamedCourse.getText() ;
				
				//if course id is in database
				if(course.checkInDatabase(editCourseID)==true) {
					
					//creating the query to update the course
					String sqlQuery = "UPDATE course SET course_name=? WHERE course_id = ?";
					try {
						//establishing connection and creating a prepared statement
						PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
						
						//setting the parameters in the query
						preSt.setString(1, editCourseName);
						preSt.setInt(2, editCourseID);
						
						preSt.executeUpdate(); //executing the statement
						preSt.close(); //closing the prepared statement
						
						//prompting the successful execution
						JOptionPane.showMessageDialog(null, "Course was successfully updated");
						
						//closing the current frame
						f1.setVisible(false);
						
						//reopen the current class to refresh the frame
						new AdminCourseOperations();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "MySQL Error");
					}
				}else {
					//course id not found in database
					JOptionPane.showMessageDialog(null, "Course ID does not exists");
				}
				//catching exceptions
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
				
			}
		}
		
		//if resume course button pressed
		else if(e.getSource()==resumeCourse) {
			
			try {
				//setting the course id
				courseID = Integer.parseInt(courseId.getText());
				
				//checking the validity of course id
				if(course.checkInDatabase(courseID)==true) {
					
					//creating query to select the specific course in the database
					String sqlQuery = "SELECT status FROM course WHERE course_id = ?";
					try {
						
						//establishing connection and creating prepared statement
						PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
						preSt.setInt(1, courseID); //setting the course id in query
						
						//storing the result of the query execution
						ResultSet resultSet = preSt.executeQuery();
						
						//iterating through the result
						while(resultSet.next()) {
							
							//if course status is Active
							if(resultSet.getString(1).equals("Active")) {
								//course already resumed
								JOptionPane.showMessageDialog(null, "Course is already resumed");
								return;
							}
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "MySQL Error");
					}
					
					//if course not active than activate it in the database
					course.changeCourseStatusDB(courseID, "Active"); //using the function from Course class
					
					JOptionPane.showMessageDialog(null, "Course was successsfully resumed"); //successful execution
					
					f1.setVisible(false); //close the current frame
					
					new AdminCourseOperations(); //creating new instance of this class to reopen the frame
					
				}else {
					//if course id not found in database
					JOptionPane.showMessageDialog(null, "Course ID does not exits!!");
				
				}
				//catching exceptions
			} catch (Exception exp) {
				exp.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
		}
		
		//if the cancel course button pressed
		else if(e.getSource()==cancelCourse) {
			try {
				
				//setting the value of the course id
				courseID = Integer.parseInt(courseId.getText());
				
			    //if the course id exists in the database
				if(course.checkInDatabase(courseID)==true) {
					
					//creating query
					String sqlQuery2 = "SELECT status FROM course WHERE course_id = ?";
					try {
						
						//establishing connection and prepared statement
						PreparedStatement preSt2 = MySqlConnection.connect().prepareStatement(sqlQuery2);
						preSt2.setInt(1, courseID); //setting the course id in the query
						
						ResultSet rSet = preSt2.executeQuery(); //executing the prepared statement and storing the result
						
						//iterating through the result
						while(rSet.next()) {
							
							//if the course status already inactive
							if(rSet.getString(1).equals("Inactive")) {
								
								JOptionPane.showMessageDialog(null, "Course is already cancelled");
								return;
							}
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "MySQL Error");
					}
					
					//if course is active, change the status to inactive in the database
					course.changeCourseStatusDB(courseID,"Inactive");
					
					JOptionPane.showMessageDialog(null, "Course was successsfully cancelled");  //successful execution
					
					f1.setVisible(false); //closing the current frame
					
					new AdminCourseOperations(); //re instantiating the current class to reopen the frame and refresh
					
				}else {
					//if course id not found in database 
					JOptionPane.showMessageDialog(null, "Course ID does not exits");
					
				}
			//catching exceptions
			}catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured!!");
			}
		}
		
		//if delete course button pressed
		else if (e.getSource()==deleteCourse) {
			
			try {
				//setting the course id
				courseID = Integer.parseInt(courseId.getText());
				
				//checking course id in database
				if(course.checkInDatabase(courseID)==true) {
					
					//show a pop up confirmation window
					//user can chose yes or no to confirm the course deletion
					int result =JOptionPane.showConfirmDialog(f1,"Sure? You want to delete?", "Confirm Course Delete",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
					
					//if user selects Yes option
					if(result == JOptionPane.YES_OPTION) {
						
						//call the function from Course class to delete course from database
						course.deleteCourseFromDatabase(courseID);
						
						JOptionPane.showMessageDialog(null, "Course was successsfully deleted"); //successful execution
						
						//closing the current frame
						f1.setVisible(false);
						
						//re freshing the frame
						new AdminCourseOperations();
					}
					

				}else {
					//if course id not found in database
					JOptionPane.showMessageDialog(null, "Course ID does not exists");
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
		}
		
		//if back button pressed
		else if(e.getSource()==backButton) {
			
			f1.setVisible(false); //close the current frame
			
			new CourseAdministratorMenu(); //instantiate the CourseAdministratorMenu class to go back to the menu frame
		}
		
		//if exit button pressed 
		else if(e.getSource()==exitButton) {
			System.exit(0); //terminate the program
		}
	}
	
	
}
