package instructor;

//importing necessary classes from within the project
import student.Student;	
import courseManagementSystem.MySqlConnection;
import courseManagementSystem.LoginMenu;

//importing necessary Java Library Classes
import java.awt.FlowLayout;
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
 * This class creates a menu for instructor operations.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class InstructorMenu implements ActionListener{
	
	//declaring instance variables
	private int instructorId;
	private JFrame f1;
	private JTextField insId;
	private JButton viewMod,viewStd,addMarks,logOut,exitButton;
	
	private JFrame modFrame;
	private JButton backButton,exitButton2;
	private int i=0,j=0;
	
	private JFrame stdFrame;
	private int k,l=0;
	private JButton backButton2,exitButton3;
	
	private JFrame marksFrame;
	private JTextField stdId,modId,mark;
	private JButton enterMark,backButton3;
	private int m,n=0;
	
	
	
	/**
	 * This constructor of the class creates a JFrame for the operations of instructor.<br>
	 *  The operations are view modules, view students and add marks to student.
	 *  
	 */
	public InstructorMenu() {
		
		JLabel label,heading; //declaring labels
		
		f1 = new JFrame("Instructor Menu"); //creating new frame
		f1.setBounds(200,30,800,500); //setting bounds (x-axis, y-axis, width, height)
		f1.setLayout(null);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when closed to save memory
		
		//creating heading label
		heading = new JLabel("Enter Instructor ID, then choose any of the three operations");
		heading.setFont(new Font("Arial Black",Font.BOLD,18)); //setting font
		heading.setBounds(65,15,670,100); //setting bounds (x-axis, y-axis, width, height)
		f1.add(heading); //adding the label to the frame
		
		//label for instructor id
		label = new JLabel("Enter Instructor ID");
		label.setFont(new Font("Arial",Font.BOLD,20));
		label.setBounds(65,115,370,100);
		f1.add(label);

		//creating text field to enter instructor id
		insId = new JTextField();
		insId.setBounds(260,145,100,40);
		f1.add(insId);
		
		//creating button to view modules
		viewMod = new JButton("View Modules");
		viewMod.setBounds(45,235,150,75);
		f1.add(viewMod);
		
		//creating button to view students
		viewStd = new JButton("View Students");
		viewStd.setBounds(300,235,150,75);
		f1.add(viewStd);
		
		//creating button to add marks
		addMarks = new JButton("Add Marks");
		addMarks.setBounds(550,235,150,75);
		f1.add(addMarks);
		
		//creating a log out button
		logOut = new JButton("LogOut");
		logOut.setBounds(45,370,85,45);
		f1.add(logOut);
		
		//creating a exit button
		exitButton = new JButton("Exit");
		exitButton.setBounds(200,370,85,45);
		f1.add(exitButton);
		
		//adding action listener to all the buttons to add functionality
		viewMod.addActionListener(this);
		viewStd.addActionListener(this);
		addMarks.addActionListener(this);
		logOut.addActionListener(this);
		exitButton.addActionListener(this);
		
		f1.setVisible(true); //displaying the frame
	}
	
	
	
	/**
	 * This method creates a JFrame that displays the list of modules the instructor is teaching.
	 * 
	 *  @exception SQLException
	 */
	private void viewModules() {
		
		//declaring a table;
		JTable modTable;
		 
		String x[]={"Module ID","Module Name"};  //column of the table
		String y[][] = new String[6][2]; //row of the table
		
		modFrame = new JFrame("Instructor Modules"); //creating a new frame
		modFrame.setBounds(200,50,700,350); //setting bounds
		modFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when it is closed
		
		//creating query
		String sqlQuery = "SELECT module_id, module_name FROM module WHERE instructor_id =?";
		try {
			
			//establishing connection and creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			preSt.setInt(1, instructorId); //setting the value of the parameter
			
			ResultSet rSet  = preSt.executeQuery(); //executing the prepared statement and storing the result
			
			//iterating through the result set
			while(rSet.next()) {
				
				y[i][j++] = rSet.getString(1); //module id
				y[i][j++] = rSet.getString(2); //module name
				i++;
				j=0;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//creating table with y and x as row and column data
		modTable = new JTable(y,x);
		modTable.setDefaultEditor(Object.class, null); //preventing the user from editing the content of the table
		
		JScrollPane sp = new JScrollPane(modTable); //creating a scroll pane and adding the table to it
		
		//creating a back button
		backButton = new JButton("Back");
		backButton.setBounds(15 ,150 ,75 ,45 ); //setting bounds of the button
		modFrame.add(backButton); //adding the button to the frame
		backButton.addActionListener(this); //adding action listener to the button to add functionality to it
		
		//creating an exit button
		exitButton2 = new JButton("Exit");
		exitButton2.setBounds(400 ,150 ,75 ,45 );
		modFrame.add(exitButton2);
		exitButton2.addActionListener(this); //adding action listener to the button to add functionality to it
		
		
		modFrame.add(sp); //adding the scroll pane into the frame
		
		modFrame.setVisible(true); //displaying the frame
		
	}

	
	
	/**
	 * This method creates a JFrame that displays all the students that the instructor is teaching.
	 * 
	 * @exception SQLException
	 */
	private void viewStudents() {
		
		//declaring a table
		JTable stdTable;
		
		String column[]= {"Student ID","Student Name","Student Email","Module ID"}; //columns of the table
		String row[][] = new String[40][4]; //rows of the table
		
		stdFrame = new JFrame("Instructor Students"); //creating new frame to view the students
		
		stdFrame.setBounds(200,50,700,550); //setting the bounds of the frame
		stdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when it is closed
		
		//creating query
		String sqlQuery = "SELECT module_id FROM instructor WHERE instructor_id=?";
		try {
			
			//creating prepared statement
			PreparedStatement preSt  = MySqlConnection.connect().prepareStatement(sqlQuery);
			preSt.setInt(1, instructorId); //setting the values of the parameters
			
			ResultSet rSet = preSt.executeQuery(); //executing the prepared statement and storing the result
			
			//iterating through the result
			while(rSet.next()) {
				try {
					
					//creating query
					String sqlQuery2 = "SELECT student_id, student_name, student_email, module_id FROM student WHERE module_id ="+ rSet.getString(1);
					
					PreparedStatement preSt2 = MySqlConnection.connect().prepareStatement(sqlQuery2); //creating prepared statement
					
					ResultSet rsStudent = preSt2.executeQuery(); //executing the prepared statement and storing the result
					
					//iterating through the result set
					while(rsStudent.next()) {
						
						row[k][l++] = rsStudent.getString(1); //student id
						row[k][l++] = rsStudent.getString(2); //student name
						row[k][l++] = rsStudent.getString(3); //student email
						row[k][l++] = rsStudent.getString(4); //module id
						k++;
						l=0;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//creating table and inserting the data of row and column
		stdTable = new JTable(row,column);
		stdTable.setDefaultEditor(Object.class, null); //prevents the user from editing the contents of the table
		
		JScrollPane sp2 = new JScrollPane(stdTable); //creating a scroll pane and inserting the table into it
		
		//creating a back button
		backButton2 = new JButton("Back");
		stdFrame.add(backButton2);
		backButton2.addActionListener(this); //adding action listener to add functionality
		
		//creating an exut button
		exitButton3 = new JButton("Exit");
		stdFrame.add(exitButton3);
		exitButton3.addActionListener(this); //adding action listener to add functionality
		
		
		stdFrame.setLayout(new FlowLayout() ); //setting layout of the frame as FlowLayout
		stdFrame.add(sp2); //adding the scroll pane into the frame
		
		stdFrame.setVisible(true); //displaying the frame
		
	}
		
	
	
		/**
		 * This method creates a JFrame that displays the detail of the instructor's students 
		 * and adds marks to specified student.
		 * 
		 * @exception SQLException
		 */
		private void addMarks() {
			
			JTable marksTable;
			JLabel l1,l2,l3;
			String top[]= {"Student ID","Student Name","Module ID"}; //column of the table
			String data[][] = new String[40][3];  //row of the table 
			
			marksFrame = new JFrame("Add Marks"); //creating new frame 
			marksFrame.setBounds(200,50,600,600); //setting bounds of the frame
			marksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when closed 
			
			JPanel contentPane= new JPanel(); //creating panel to store the elements
		
			//creating query
			String sqlQuery = "SELECT module_id FROM instructor WHERE instructor_id=?";
			try {
				
				//creating prepared statement
				PreparedStatement preSt  = MySqlConnection.connect().prepareStatement(sqlQuery);
				preSt.setInt(1, instructorId);  //setting the value of the unknown parameters
				
				ResultSet rSet = preSt.executeQuery(); //executing the statement and storing it
				
				//iterating through the result set
				while(rSet.next()) {
					try {
						//creating query
						String sqlQuery2 = "SELECT student_id, student_name, module_id FROM student WHERE module_id ="+ rSet.getString(1);
						PreparedStatement preSt2 = MySqlConnection.connect().prepareStatement(sqlQuery2);
						ResultSet rsStudent = preSt2.executeQuery();
						
						while(rsStudent.next()) {
							data[m][n++] = rsStudent.getString(1); //student id
							data[m][n++] = rsStudent.getString(2); //student name
							data[m][n++] = rsStudent.getString(3); //module id
							m++;
							n=0;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//creating a table and inserting the value of rows and columns
			marksTable = new JTable(data,top);
			marksTable.setDefaultEditor(Object.class, null); //prevents the user from editing the contents of the table
			
			JScrollPane sp2 = new JScrollPane(); //creating a scroll pane
			sp2.setBounds(50, 10, 400, 200); //setting bounds
			sp2.setViewportView(marksTable); // display the table in the scroll pane
			
			contentPane.add(sp2); //adding the scroll pane into the panel
			contentPane.setLayout(null);
			
			//label to enter student id
			l1 = new JLabel("Enter Student ID");
			l1.setBounds(20,250,100,50);
			marksFrame.add(l1);
			
			//text field to enter the student id
			stdId = new JTextField();
			stdId.setBounds(130,260,100,30);
			marksFrame.add(stdId);
			
			//label for module id
			l2 = new JLabel("Enter Module ID");
			l2.setBounds(20,320,100,50);
			marksFrame.add(l2);
			
			//text field to enter module id
			modId = new JTextField();
			modId.setBounds(130,330,100,30);
			marksFrame.add(modId);
			
			//label for marks
			l3 = new JLabel("Enter the mark obtained by student in the mentioned module");
			l3.setBounds(20,390,500,50);
			marksFrame.add(l3);
			
			//text field to enter marks
			mark = new JTextField();
			mark.setBounds(20,450,100,30);
			marksFrame.add(mark);
			
			//creating button to enter
			enterMark = new JButton("Enter");
			enterMark.setBounds(140,450,75,35);
			marksFrame.add(enterMark);
			enterMark.addActionListener(this); //adding action listener to the button

			//creating a back button 
			backButton3 = new JButton("Back");
			backButton3.setBounds(20,520,75,30);
			marksFrame.add(backButton3);
			backButton3.addActionListener(this); //adding action listener to the button
		
			//inserting the panel to the frame
			marksFrame.add(contentPane);
			
			marksFrame.setVisible(true); //displaying the frame
		
		}
		
		

		/**
		 * This method checks if instructor is teaching a specified student with the help of a 
		 * method checkInstructorInModule() from Instructor class.
		 * 
		 * @param stId ID of the student 
		 * @return True or False based on whether the instructor is teaching the specified student or not.
		 */
		private boolean checkInstructorWithStudent(int stId) {
			
			//creating an object of the instructor
			Instructor instructor = new Instructor();
			
			//creating query
			String sqlQuery = "SELECT module_id FROM student WHERE student_id = ?";
			try {
				
				//creating prepared statement
				PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
				preSt.setInt(1, stId); //setting value
				
				ResultSet rSet  = preSt.executeQuery(); //result set stores the result of the executed statement
				
				//iterating thorough the result set
				while(rSet.next()) {
					
					//if the instructor is teaching in the specified module
					if(instructor.checkInstructorInModule(instructorId,Integer.parseInt(rSet.getString(1)))==true){
						return true; //true if instructor teaching in module
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;  //return false if instructor is not teaching in module
		}
		
		
		
	//method overridden from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//creating new object of Instructor class
		Instructor i = new Instructor();
		
		//if the source of the ActionEvent is viewMod button
		//if viewMod button pressed
		if(e.getSource()==viewMod) {
			
			//setting the instructor id from text field
			instructorId = Integer.parseInt(insId.getText());
			
			
			if(i.checkInDatabase(instructorId)==true) {
				//if the instructor id found in database
				
			viewModules(); //calling the viewModules function to open the frame that displays the modules
			
			//closing the current frame
			f1.setVisible(false);
			
			}else {
				//if instructor id not found in database
				JOptionPane.showMessageDialog(null, "Instructor does not exists");
			}
		}
		
		//if viewStd button pressed
		else if(e.getSource()==viewStd) {
			
			instructorId = Integer.parseInt(insId.getText());
			
			if(i.checkInDatabase(instructorId)==true) {
				//if the instructor id found in database
				
			viewStudents(); //calling the viewStudents function to open the frame that displays the students
			
			f1.setVisible(false); //close the current frame
			
			}else {
				//if instructor id not found in database
				JOptionPane.showMessageDialog(null, "Instructor does not exists");
			}
		}
		
		//if addMarks button pressed
		else if(e.getSource()==addMarks) {
			
			instructorId = Integer.parseInt(insId.getText()); //using getText to retrieve the data from the text field
			
			if(i.checkInDatabase(instructorId)==true) {
				//if the instructor id found in database
				
			addMarks(); //calling the function that contains the frame to add marks
			
			f1.setVisible(false); //closing the current frame
			
			}else {
				//if instructor id not found in database
				JOptionPane.showMessageDialog(null, "Instructor does not exists");
			}
		}
		
		//if logOut button pressed
		else if(e.getSource()==logOut) {
			//creating new instance of the LoginMenu class
			new LoginMenu();
			
			f1.setVisible(false); //closing the current frame
		}
		
		//if exit button pressed
		else if(e.getSource()==exitButton) {
			System.exit(0); //terminating the program
		}
		
		//if back button pressed
		else if(e.getSource() ==backButton) {
			new InstructorMenu(); //creating new instance of InstructorMenu class to go back to the menu
			
			modFrame.setVisible(false); //closing the current frame
		}
		
		//if exit button pressed
		else if(e.getSource()==exitButton2) {
			System.exit(0); //terminating the program
		}
		
		//if back button pressed
		else if(e.getSource()==backButton2) {
			
			//open new frame and close the current frame
			new InstructorMenu();
			stdFrame.setVisible(false);
		}
		
		
		else if(e.getSource()==exitButton3) {
			System.exit(0); //terminating the program if exit button pressed
		}
		
		//if enter marks button pressed
		else if(e.getSource()==enterMark) {
			
			try {
				
				//setting the values of variables from the input coming from the text field
				int studentId = Integer.parseInt(stdId.getText());
				int moduleId = Integer.parseInt(modId.getText());
				int stdMarks = Integer.parseInt(mark.getText());
				
				Student stu = new Student(); //new object of Student class
				
				if(stu.checkStudentInMod(studentId, moduleId)==true) {
					//if student is studying in module
					if(checkInstructorWithStudent(studentId)==true) {
						//if instructor is teaching specific student
						if(stu.checkInDatabase(studentId)==true) {
							//if the student id is in database
							
							//create query
							String sqlQuery = "UPDATE student SET marks=? WHERE student_id=? AND module_id=?";
							try {
								//creating prepared statement
								PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
								
								//setting values to the parameters in query
								preSt.setInt(1, stdMarks);
								preSt.setInt(2, studentId);
								preSt.setInt(3, moduleId);
								
								preSt.executeUpdate(); //executing the prepared statement
								preSt.close(); //closing
								
								JOptionPane.showMessageDialog(null, "Marks was successfully added to the Student's record"); //successful execution message
								 
								marksFrame.setVisible(false); //closing the current frame
								
								new InstructorMenu(); //creating new instance of InstructorMenu to reopen the frame
								
								
							} catch (SQLException sqlEx) {
								JOptionPane.showMessageDialog(null, "MySql Database Error!!");
								sqlEx.printStackTrace();
							}
						}else {
								//if student id not found in database
								JOptionPane.showMessageDialog(null, "Student ID does not exists");
						}
					}else {
						//if instructor not teaching specified student
						JOptionPane.showMessageDialog(null, "You dont teach this student");
					}
				}else {
					//if the student is not studying in the student module
					JOptionPane.showMessageDialog(null, "Student is not studying in the entered module");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occured");
				ex.printStackTrace();
			}
		}
		
		
		else if(e.getSource()==backButton3) {
			new InstructorMenu();  //opening the menu
			marksFrame.setVisible(false); //closing the current frame
		}
	}

}
