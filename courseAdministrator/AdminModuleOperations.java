package courseAdministrator;

//importing necessary classes from within the project
import moduleAndCourse.Module;
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
 * This class creates the menu for the operations on module that the course administrator can utilize.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class AdminModuleOperations implements ActionListener{
	
	//declaring instance variables
	private int courseID,level;
	private JFrame f1;
	private JTextField addCourseId,addModuleName,addModId,addLevel, editCourseId,editLevel;
	private JButton addModule,enterButton,backButton,exitButton;
	private int i,j=0;
	
	private JFrame f2;
	private JTextField editModuleId,editModuleName;
	private JButton editModuleButton, backButton2,exitButton2;
	private int k,l=0;
	
	
	
	/**
	 * This constructor creates the JFrame that contains the operations on module.<br>
	 * The operations are: add new module and edit module.<br>
	 * Displays course id, course name, course status.
	 * 
	 * @exception SQLException
	 */
	public AdminModuleOperations() {
		
		//declaring labels
		JLabel h1,h2,add1,add2,add3,add4,edit1,edit2;
		
		JTable table;
		String column[] = {"Course ID","Course Name"}; //column of the table
		String row[][] = new String[6][2];  //row of the table
		
		f1 = new JFrame("Module Operations"); //creating new frame
		
		JPanel jPanel= new JPanel() ; //creating a new panel
		
		f1.setBounds(200,10,700,660); //setting bounds of the frame
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when closed
		
		//creating query
		String sqlQuery = "SELECT course_id, course_name from course where status=? ";
		try {
			
			//establishing connection and creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			preSt.setString(1, "Active"); //setting the parameter
			
			//executing the statement and storing in result set
			ResultSet rSet = preSt.executeQuery();
			
			//iterating through the result
			while(rSet.next()) {
				
				row[i][j++]= rSet.getString(1); //course id
				row[i][j++] = rSet.getString(2); //course name
				
				i++;
				j=0;
			}
			
			//SQL exception handling
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table = new JTable(row,column); //creating a table with row and column data
		table.setDefaultEditor(Object.class, null); // prevents the content of the table from being edited by the user
		
		//creating a scroll pane. Scroll pane creates a frame that has scroll function
		JScrollPane sp = new JScrollPane();
		sp.setBounds(4, 10, 650, 120); //setting bounds (x-axis,y-axis,width,height)
		sp.setViewportView(table); //inserting the table into the scroll pane
		
		//adding the scroll pane into the panel
		jPanel.add(sp);
		jPanel.setLayout(null);
		
		//label for add module
		h1 = new JLabel("Add Module");
		h1.setFont(new Font("arial black",Font.BOLD,20)); //setting font
		h1.setBounds(20,150,170,20); //setting bounds (x-axis,y-axis,width,height)
		f1.add(h1); //adding into the frame
		
		//label for course id
		add1= new JLabel("Enter Course ID");
		add1.setBounds(20,190,100,20);
		f1.add(add1);
		
		//text field for course id
		addCourseId = new JTextField();
		addCourseId.setBounds(150,190,75,25);
		f1.add(addCourseId);
		
		//label got module name
		add2= new JLabel("Enter Module Name");
		add2.setBounds(20,225,150,20);
		f1.add(add2);
		
		//text field to enter module name
		addModuleName = new JTextField();
		addModuleName.setBounds(150,225,175,25);
		f1.add(addModuleName);
		
		//label for module id
		add3= new JLabel("Enter Module ID");
		add3.setBounds(20,260,100,20);
		f1.add(add3);
		
		//text field to enter module id
		addModId = new JTextField();
		addModId.setBounds(150,260,75,25);
		f1.add(addModId);
		
		//label for level
		add4= new JLabel("Enter Level 4|5|6");
		add4.setBounds(20,295,100,20);
		f1.add(add4);
		
		//text field to enter level
		addLevel = new JTextField();
		addLevel.setBounds(150,295,75,25);
		f1.add(addLevel);
		
		//creating button for the add module
		addModule = new JButton("Add Module");
		addModule.setBounds(20,325,110,40);
		f1.add(addModule);
		
		//label for edit module 
		h2 = new JLabel("Edit Module");
		h2.setFont(new Font("arial black",Font.BOLD,20));
		h2.setBounds(20,395,170,20);
		f1.add(h2);
		
		//label for course id
		edit1 = new JLabel("Enter Course ID");
		edit1.setBounds(20,435,100,20);
		f1.add(edit1);
		
		//text field to enter course id
		editCourseId = new JTextField();
		editCourseId.setBounds(150,435,75,25);
		f1.add(editCourseId);
		
		//label for level
		edit2 = new JLabel("Enter Level 4|5|6");
		edit2.setBounds(20,470,100,20);
		f1.add(edit2);
		
		//text field to enter level
		editLevel = new JTextField();
		editLevel.setBounds(150,470,75,25);
		f1.add(editLevel);
		
		//creating an enter button
		enterButton= new JButton("Enter");
		enterButton.setBounds(20,505,110,40);
		f1.add(enterButton);
		
		//back button
		backButton = new JButton("Back");
		backButton.setBounds(20,565,85,35);
		f1.add(backButton);
		
		//exit button
		exitButton = new JButton("Exit");
		exitButton.setBounds(170,565,85,35);
		f1.add(exitButton);
		
		//adding action listener to all the buttons in the frame to assign task to each button
		addModule.addActionListener(this);
		enterButton.addActionListener(this);
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		//inserting the panel into the frame
		f1.add(jPanel);
		
		//displaying the frame
		f1.setVisible(true);
	}
	
	
	
	/**
	 * This method creates a new JFrame that edits the module name of pre-existing modules.<br>
	 * Displays the detail of module in specified course and level.
	 * 
	 * @exception SQLException
	 */
	private void editModule() {
		
		//declaring labels
		JLabel editLabel1,editLabel2,editLabel3;
		
		JTable modTable;
		String col[] = {"Module ID","Module Name"}; //column of table
		String data[][] = new String[15][2]; //row of table
		
		f2 = new JFrame("Edit Module"); //creating new frame
		
		JPanel jPanel= new JPanel() ; //creating a panel
		
		f2.setBounds(200,10,700,550); //setting bounds of the frame
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //safely exit the frame when closed
		
		// creating query
		String sqlQuery = "SELECT module_id, module_name from module where course_id=? and level =?";
		try {
			
			//establishing connection and creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting the values of unknown parameter in the query
			preSt.setInt(1, courseID);
			preSt.setInt(2, level);
			
			//executing the statement and storing the result
			ResultSet rSet = preSt.executeQuery();
			
			//iterating through the result
			while(rSet.next()) {
				data[k][l++] = rSet.getString(1); //module id
				data[k][l++] = rSet.getString(2); //module name
				k++;
				l=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//creating table
		modTable = new JTable(data,col);
		modTable.setDefaultEditor(Object.class, null); // prevents the content of the table from being edited by the user
		
		//creating a scrol pane
		JScrollPane sp = new JScrollPane();
		sp.setBounds(5, 10, 650, 140);
		sp.setViewportView(modTable); //adding the table into the scroll pane
		
		//inserting the scroll pane into the panel
		jPanel.add(sp);
		jPanel.setLayout(null);
		
		//label heading
		editLabel1 = new JLabel("Edit Module Name");
		editLabel1.setFont(new Font("arial black",Font.BOLD,20));
		editLabel1.setBounds(20,170,240,20);
		f2.add(editLabel1);
		
		//label for module id
		editLabel2 = new JLabel("Enter Module ID");
		editLabel2.setFont(new Font("arial",Font.BOLD,15));
		editLabel2.setBounds(20,210,150,20);
		f2.add(editLabel2);
		
		//text field to enter module id
		editModuleId = new JTextField();
		editModuleId.setBounds(170,210,75,25);
		f2.add(editModuleId);
		
		//label for module name
		editLabel3 = new JLabel("New Module Name");
		editLabel3.setFont(new Font("arial",Font.BOLD,15));
		editLabel3.setBounds(20,255,150,20);
		f2.add(editLabel3);
		
		//text field to enter new module name
		editModuleName = new JTextField();
		editModuleName.setBounds(170,255,175,25);
		f2.add(editModuleName);
		
		//button to edit the module
		editModuleButton = new JButton("Edit Module");
		editModuleButton.setBounds(20,290,110,40);
		f2.add(editModuleButton);
		
		//back button
		backButton2 = new JButton("Back");
		backButton2.setBounds(20,380,85,35);
		f2.add(backButton2);
		
		//exit button
		exitButton2 = new JButton("Exit");
		exitButton2.setBounds(150,380,85,35);
		f2.add(exitButton2);
		
		//adding action listener to all the button to add functionality to them
		editModuleButton.addActionListener(this);
		backButton2.addActionListener(this);
		exitButton2.addActionListener(this);
		
		//inserting the panel into the frame
		f2.add(jPanel);
		
		//displaying the frame
		f2.setVisible(true);
	}
	


	//this method is overridden from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//creating objects of Course and Module classes
		Course course = new Course();
		Module module = new Module();
		
		//if the button pressed is addModule
		if(e.getSource()==addModule) {
			try {
				
				//setting the values of the variables by data from the text fields
				int courseID = Integer.parseInt(addCourseId.getText());
				String moduleName = addModuleName.getText();
				int moduleID = Integer.parseInt(addModId.getText());
				int level = Integer.parseInt(addLevel.getText());
				
				//check if course id in databse
				if(course.checkInDatabase(courseID)==true) {
					
					//check if module id in database
					if(module.checkInDatabase(moduleID)==false) {
						
						//check if level entered is valid
						if(level == 4 || level ==5 || level==6) {
							
							//inserting the module into the database
						module.insertModuleIntoDB(moduleID, moduleName, courseID, level);
						
						JOptionPane.showMessageDialog(null, "Module was successfully added"); //successful execution message
						
						f1.setVisible(false); //closing the current frame
						
						new AdminModuleOperations(); //creating instance of current class to refresh the frame
						
					}else {
						//if incorrect level entered
						JOptionPane.showMessageDialog(null, "Level does not exists");
					}
					}else {
						//if module id already exits before
						JOptionPane.showMessageDialog(null, "Module ID already exists");
					}
				}else {
					//if course id is invalid
					JOptionPane.showMessageDialog(null, "Course ID does not exists");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
		}
		
		//if the enter button pressed
		else if(e.getSource()==enterButton) {
			try {
				
				//setting the data from text field into variables
				courseID = Integer.parseInt(editCourseId.getText());
				level = Integer.parseInt(editLevel.getText());
				
				if(course.checkInDatabase(courseID)==true) {
					//if course id in database
					if(level == 4 || level == 5 || level ==6) {
						//if level is valid
						
						editModule(); //Call the function to open the edit module frame
						
						f1.setVisible(false); //close the current frame
					
					}else {
						//if incorrect level entered
						JOptionPane.showMessageDialog(null, "Enter Valid Level");
					}
				}else {
					//if course id does not exists
					JOptionPane.showMessageDialog(null, "Course ID does not exists");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error Occured");
			}
			
		}
		
		//if back button pressed
		else if(e.getSource()==backButton) {
			
			//creating new instance of CourseAdministratorMenu class to go back to menu
			new CourseAdministratorMenu();
			
			//close the current frame
			f1.setVisible(false);
		}
		
		//if exit button pressed
		else if(e.getSource()==exitButton) {
			System.exit(0); //terminate the program
		}
		
		//if edit module button pressed
		else if(e.getSource()==editModuleButton) {
			try {
				
				//setting the variables
				int moduleId = Integer.parseInt(editModuleId.getText());
				String moduleName = editModuleName.getText();
				
				//checking the module id in database
				if(module.checkInDatabase(moduleId)==true) {
					
					//creating query to update module name
					String sqlQuery = "UPDATE module SET module_name = ? WHERE module_id=? AND level =?";
					try {
						
						//creating prepared statement
						PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
						
						//setting the parameters
						preSt.setString(1, moduleName);
						preSt.setInt(2, moduleId);
						preSt.setInt(3, level);
						
						preSt.executeUpdate(); //executing the update query
						preSt.close(); //closing the prepared statement
						
						JOptionPane.showMessageDialog(null, "Module was successfully updated");
						f2.setVisible(false);
						editModule();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "MySQL Error");
					}
				}else {
					//if module id not valid
					JOptionPane.showMessageDialog(null, "Module ID does not exists");
				
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
		}
		
		//if back button pressed in edit module frame
		else if(e.getSource()==backButton2) {
			
			//new instance of AdminModuleOperations
			new AdminModuleOperations();
			
			//close current frame
			f2.setVisible(false);
		}
		
		//if exit button pressed 
		else if(e.getSource()==exitButton2) {
			//terminate the program
			System.exit(0);
		}
		
	}
	

}
