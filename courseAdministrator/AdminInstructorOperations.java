package courseAdministrator;

//importing necessary classes from the project
import instructor.Instructor;
import moduleAndCourse.Module;
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
 * This class creates a menu for operations on instructor that the course administrator can utilize.
 * 
 * @author Sonam Rinzin Gurung
 * @author 2059524
 *
 */
public class AdminInstructorOperations implements ActionListener{

	// declaring instance variables
	private JFrame f1;
	private JTextField addInsName, addInsId,addModId,addEmail,addAddress,assignInsId,assignModId;
	private JTextField editID,editName,editEmail,editAddress;
	private JButton addInstButton,assignInstButton,editButton,backButton,exitButton;
	private int i,j=0;
	
	
	
	/**
	 * This constructor of the class creates a menu for operations on instructor.<br>
	 * First displays a list of module ID and instructor ID in specified course and level.<br>
	 * Operations: Add new instructor, assign instructor to modules
	 * 
	 * @param courseID ID of course where instructor is teaching
	 * @param level Level of study where instructor is teaching
	 * 
	 * @exception SQLException
	 */
	public  AdminInstructorOperations(int courseID,int level) {
		
		//declaring labels
		JLabel head1,nameLabel,insIdLabel,modIdLabel,emaiLabel,addressLabel,head2,assignInsIdLabel,assignModIdLabel;
		JLabel editHead, editIDLabel,editNameLabel,editEmaiLabel,editAddressLabel;
		
		JTable table;
		String column[] = {"Module ID","Instructor ID"}; //column of the table
		String row[][] = new String[10][2]; //declaring rows of the table
		
		f1 = new JFrame("Instructor Operation"); //creating the frame
		
		JPanel jPanel= new JPanel() ; //creating a panel to store different elements
		f1.setBounds(200,5,900,675); //setting bounds(x-axis,y-axis,width, height)
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //safely exit the frame when it is closed
		
		//creating query
		String sqlQuery = "SELECT module_id, instructor_id from module where course_id=? and level =?";
		try {
			
			//establishing connection and creating prepared statement
			PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sqlQuery);
			
			//setting the parameter values
			preSt.setInt(1, courseID);
			preSt.setInt(2, level);
			
			//storing the result after execution
			ResultSet rSet = preSt.executeQuery();
			
			//iterating through the result
			while(rSet.next()) {
				row[i][j++] = rSet.getString(1); //module id
				row[i][j++] = rSet.getString(2); //instructor id
				i++;
				j=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//creating table with row and column 
		table = new JTable(row,column);
		table.setDefaultEditor(Object.class, null); // prevents the content of the table from being edited by the user
		
		//create a scroll pane
		JScrollPane sp = new JScrollPane();
		sp.setBounds(50, 10, 400, 140); //setting bounds
		sp.setViewportView(table); //inserting the table into the scroll pane
		
		jPanel.add(sp); //adding the scroll pane into the panel
		jPanel.setLayout(null);
		
		//creating label
		head1 = new JLabel("Add New Instructor");
		head1.setFont(new Font("arial black",Font.BOLD,20)); //setting font
		head1.setBounds(20,170,240,20); //setting bounds (x-axis,y-axis,width,height)
		f1.add(head1); //inserting into the frame
		
		//creating label
		nameLabel = new JLabel("Enter Instructor Name");
		nameLabel.setBounds(20,210,150,20);
		f1.add(nameLabel);
		
		//creating text field for the instructor name
		addInsName = new JTextField();
		addInsName.setBounds(170,210,175,25);
		f1.add(addInsName);
		
		//label for instructor id
		insIdLabel = new JLabel("Enter Instructor ID");
		insIdLabel.setBounds(20,255,150,20);
		f1.add(insIdLabel);
		
		//text area to enter the instructor id
		addInsId = new JTextField();
		addInsId.setBounds(170,255,75,25);
		f1.add(addInsId);
		
		//label
		modIdLabel = new JLabel("Enter Module ID");
		modIdLabel.setBounds(20,300,150,20);
		f1.add(modIdLabel);
		
		//text field to enter module id
		addModId = new JTextField();
		addModId.setBounds(170,300,75,25);
		f1.add(addModId);
		
		//label for email
		emaiLabel = new JLabel("Enter Email");
		emaiLabel.setBounds(20,345,150,20);
		f1.add(emaiLabel);
		
		//text field to enter the email
		addEmail = new JTextField();
		addEmail.setBounds(170,345,175,25);
		f1.add(addEmail);
		
		//label for the address
		addressLabel = new JLabel("Enter Address");
		addressLabel.setBounds(20,375,150,20);
		f1.add(addressLabel);
		
		//text field to enter the address
		addAddress = new JTextField();
		addAddress.setBounds(170,375,175,25);
		f1.add(addAddress);
		
		//button to add the instructor
		addInstButton = new JButton("Add Instructor");
		addInstButton.setBounds(20,405,120,30);
		f1.add(addInstButton);
		
		
		//heading label of edit instructor
		editHead = new JLabel("Edit Instructor");
		editHead.setFont(new Font("arial black",Font.BOLD,20));
		editHead.setBounds(365,170,240,20);
		f1.add(editHead);
		
		//instructor id label
		editIDLabel = new JLabel("Enter ID of the Instructor to be edited");
		editIDLabel.setBounds(365,210,220,20);
		f1.add(editIDLabel);
		
		//text field to enter the instructor id
		editID = new JTextField();
		editID.setBounds(595,210,75,25);
		f1.add(editID);
		
		//label to enter the new instructor name
		editNameLabel = new JLabel("Enter renamed Instructor name");
		editNameLabel.setBounds(365,255,200,25);
		f1.add(editNameLabel);
		
		//text field to enter the new name
		editName = new JTextField();
		editName.setBounds(575,255,150,20);
		f1.add(editName);
		
		//email label
		editEmaiLabel = new JLabel("Enter Email");
		editEmaiLabel.setBounds(365,300,150,20);
		f1.add(editEmaiLabel);
		
		//new email text field
		editEmail = new JTextField();
		editEmail.setBounds(515,300,175,25);
		f1.add(editEmail);
		
		//new address label
		editAddressLabel = new JLabel("Enter Address");
		editAddressLabel.setBounds(365,345,150,20);
		f1.add(editAddressLabel);
		
		//new address text field
		editAddress = new JTextField();
		editAddress.setBounds(515,345,175,25);
		f1.add(editAddress);
		
		//button to edit the instructor detail
		editButton = new JButton("Edit Instructor");
		editButton.setBounds(365,405,120,30);
		f1.add(editButton);
		
		//label to addign instructor to module
		head2 = new JLabel("Assign Instructor to Module");
		head2.setFont(new Font("arial black",Font.BOLD,20));
		head2.setBounds(20,445,380,25);
		f1.add(head2);
		
		//label instructor id
		assignInsIdLabel = new JLabel("Enter Instructor ID");
		assignInsIdLabel.setBounds(20,480,150,20);
		f1.add(assignInsIdLabel);
		
		//text field to enter instructor id
		assignInsId = new JTextField();
		assignInsId.setBounds(170,480,75,25);
		f1.add(assignInsId);
		
		//module id label
		assignModIdLabel = new JLabel("Enter Module ID");
		assignModIdLabel.setBounds(20,510,150,20);
		f1.add(assignModIdLabel);
		
		//module id text field
		assignModId = new JTextField();
		assignModId.setBounds(170,510,75,25);
		f1.add(assignModId);
		
		//button to assign instructor to module
		assignInstButton = new JButton("Assign Instructor");
		assignInstButton.setBounds(20,540,140,30);
		f1.add(assignInstButton);
		
		//back button
		backButton = new JButton("Back");
		backButton.setBounds(320,600,85,35);
		f1.add(backButton);
		
		// exit button
		exitButton = new JButton("Exit");
		exitButton.setBounds(490,600,85,35);
		f1.add(exitButton);
		
		//adding action listener to all the buttons in the frame to assign task to each button
		addInstButton.addActionListener(this);
		editButton.addActionListener(this);
		assignInstButton.addActionListener(this);
		backButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		//inserting the panel 
		f1.add(jPanel);
		
		//displaying the frame
		f1.setVisible(true);
	}
	
	
	
	//this method is overridden from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//creating objects of Instructor and Module classes
		Instructor i = new Instructor();
		Module m = new Module();
		
		//if the add instructor button pressed
		if(e.getSource()==addInstButton) {
			try {
				
				String instructorName = addInsName.getText(); //setting the instructor name from the text field
				int instructorId = Integer.parseInt(addInsId.getText()); //setting the instructor id from the text field
				int moduleId = Integer.parseInt(addModId.getText()); //setting the module id from the text field
				String insEmail = addEmail.getText(); //setting the instructor email from the text field
				String insAddress = addAddress.getText(); //setting the instructor address from the text field
				
				//checking the module id in the database
				if(m.checkInDatabase(moduleId)== true) {
					
					//checking if instructor is teaching the specified module
					if(i.checkInstructorInModule(instructorId, moduleId)==false) {
						
						//creating query to insert new instructor into the database
						String sqlQuery = "INSERT INTO instructor (instructor_id, instructor_name, instructor_email, instructor_address, module_id) values (?, ?, ?, ?,?)";
						try {
							
							//establishing connection and creating prepared statement
							PreparedStatement preSt  = MySqlConnection.connect().prepareStatement(sqlQuery);
							
							//setting the parameter values. The index points the unknown value in query. It uses 1 index as start 
							preSt.setInt(1, instructorId);
							preSt.setString(2, instructorName);
							preSt.setString(3, insEmail);
							preSt.setString(4, insAddress);
							preSt.setInt(5, moduleId);
							
							//executing the statement
							preSt.executeUpdate();
							preSt.close(); //closing the prepared statement
							
							
							JOptionPane.showMessageDialog(null, "The instructor was successfully added"); //successful execution
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "MySQL Error");
							e1.printStackTrace();
						}
						
					}else{
						
						//if the instructor is already teaching the module
						JOptionPane.showMessageDialog(null, "Specified Instructor is already teaching in this Module");
					
					}
					
					i.updateInstructorInModule(instructorId,moduleId); //updates the instructor assigned to the module
					
					JOptionPane.showMessageDialog(null, "The instructor was successfully updated on Module");

				}else {
					
					//if the module id is invalid
					JOptionPane.showMessageDialog(null, "Module ID does not exists");
				}
				
			}
			//catching exceptions
			catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
		}
		
		//if the edit button is pressed
		else if(e.getSource()==editButton) {
			try {
				
				//setting the values coming from the text field into variables
				int instID =  Integer.parseInt(editID.getText());
				String instName = editName.getText();
				String email = editEmail.getText();
				String address = editAddress.getText();
				
				//check instructor id in database
				if(i.checkInDatabase(instID)==true) {
					
					//creating query
					String sql = "UPDATE instructor SET instructor_name=? , instructor_email=? ,instructor_address=?  WHERE instructor_id=?";
					try {
						
						//creating prepared statement and establishing connection
						PreparedStatement preSt = MySqlConnection.connect().prepareStatement(sql);
						
						//setting the values of the parameter in query
						preSt.setString(1, instName);
						preSt.setString(2, email);
						preSt.setString(3, address);
						preSt.setInt(4, instID);
						preSt.executeUpdate();
						preSt.close();
						
						JOptionPane.showMessageDialog(null, "Instructor Details were successfully updated"); //successful execution
						
					} catch (SQLException e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "MySQL Error");
					}
				}else {
					//if instructor id invalid
					JOptionPane.showMessageDialog(null, "Instructor ID does not exists");
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
			
		}
		
		//if assign instructor button is pressed
		else if(e.getSource()==assignInstButton) {
			try {
				
				//setting the variable value of instructor id and module id
				int instID = Integer.parseInt(assignInsId.getText());
				int moduleID = Integer.parseInt(assignModId.getText());
				
				//check if instructor id is in database
				if(i.checkInDatabase(instID)==true) {
					
				//if module id is in database
				if(m.checkInDatabase(moduleID)== true) {
					
					//check if the instructor is teaching in the module
					if(i.checkInstructorInModule(instID, moduleID)==false) {
						//if instructor is not teaching in the module
						
						i.setModuleId(moduleID); //setting the module id
						
						i.insertInstructorDB(); //inserting the instructor into the database
						
					}else{
						//if instructor already teaching the module
						JOptionPane.showMessageDialog(null, "Specified Instructor is already teaching in this Module");
						
					}
					
					//updating the instructor assigned to the module
					i.updateInstructorInModule(instID,moduleID);
					
					JOptionPane.showMessageDialog(null, "Instructor was successfully assigned");
					
				}else {
					//if module id not found in database
					JOptionPane.showMessageDialog(null, "Module ID does not exists");
					
				}
				}else {
					//if instructor id not found in database
					JOptionPane.showMessageDialog(null, "Instructor ID does not exists");
					
				}
				
			} catch (Exception exp) {
				exp.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error Occured");
			}
		}
		
		//if the back button pressed
		else if(e.getSource()==backButton) {
			
			//creating new instance of CourseAdministratorMenu class to go back to the menu
			new CourseAdministratorMenu();
			f1.setVisible(false); //closing the current frame
		}
		
		//if exit button pressed
		else if(e.getSource()==exitButton){
			System.exit(0); //terminating the program
		}
	
	}
	
}
