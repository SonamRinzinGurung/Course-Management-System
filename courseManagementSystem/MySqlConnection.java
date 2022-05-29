package courseManagementSystem;

//importing necessary SQL classes
import java.sql.Connection;					
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;


/**
 * This class establishes a connection between the Java application and MySQL database.<br>
 * If the database is not found, then a new database is created along with some dummy data.
 * @author Sonam Rinzin Gurung
 * @author ID: 2059524
 */
public class MySqlConnection {
	
	private static String dbName = "courseManagementSystem_2059524";  //name of database
	
	//username and password to access MySQL
	private static String username = "root";
	private static String password = "";
	
	//initializing URL
	private final static String url = "jdbc:mysql://localhost/"+dbName;
	
	//declaring a static Connection variable to establish connection between Java and database
    private static Connection connection;
    
    
    /**
     * This static method returns a connection with the MySQL database.<br>
     * If the database is not found in the system, then the database is created automatically along with the required tables and dummy data.
     * @return Connection 
     * @exception SQLException
     */
    public static Connection connect() {
    	
    	//if there is no connection
    	if (connection == null) {
    		try {
    			
    		//creating a connection to the database using the DriverManager class
    			
    		connection= DriverManager.getConnection(url,username,password);
   		
    		
    	// catching exceptions such as database not found
    	}catch(SQLException e) {
    		
				try {
					
					//creating connection
					connection = DriverManager.getConnection("jdbc:mysql://localhost", username, password);
		            
					//creating a statement
					Statement command = connection.createStatement();
		            
					//creating a SQL query to create new database
					String sql = "Create Database "+dbName;
					
					//passing the query to execute the statement
		            command.execute(sql);
				
				
		            
		        //calling all the functions that creates tables in the database
		            
				createLoginTable();     //create login table
				createCourseTable();    //create course table
				createModuleTable(); 	//create modules table
				createInstructorTable();//create instructor table
				createStudentTable();  	//create student table
				
				
				//calling the function that inserts dummy data into the database tables
				insertDataInDB();
				
				//catching exceptions
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
    		}
    	}
    	return connection;  //returning a connection
    }
    
    
    
    /**
     * Creates a login table in the database.
     * 
     * @exception SQLSyntaxErrorException
     * @exception SQLException
     */
    private static void createLoginTable() {
        try{
            
        	//creating connection
            connection = DriverManager.getConnection(url, username, password);
            
            //creating a statement
            Statement createTable = connection.createStatement();
            
            //creating a SQL query to create table
            String sql = "CREATE TABLE login "
            		+ "( admin_name varchar(20) DEFAULT NULL, "
            		+ "admin_username varchar(15) DEFAULT NULL, "
            		+ "admin_password varchar(15) DEFAULT NULL)";
            
            //executing the query
            createTable.executeUpdate(sql);
            
            //catching exceptions
            }catch (SQLSyntaxErrorException se){
            System.out.println("Login table already exists!!");
            }catch(SQLException se){
                se.printStackTrace();
            }
    }
    
    
    
    /**
     * Creates a course table in the database.
     * 
     * @exception SQLSyntaxErrorException
     * @exception SQLException
     */
    private static void createCourseTable(){
        try{
           
        	//creating connection
            connection = DriverManager.getConnection(url, username, password);
            
            //creating statement
            Statement createTable = connection.createStatement();
            
            //SQL query to create table
            String sql = "CREATE TABLE course "
            		+ "( course_id int(11) NOT NULL,"
            		+ "  course_name varchar(255) DEFAULT NULL,"
            		+ "  status varchar(10) DEFAULT 'Active',"
            		+ "  PRIMARY KEY (course_id))";
          
            //executing the statement
            createTable.executeUpdate(sql);
            
         //catching exceptions
        }catch (SQLSyntaxErrorException se){
        	System.out.println("Course table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    

    /**
     * Creates a module table in the database.
     * 
     * @exception SQLSyntaxErrorException
     * @exception SQLException
     */
    private static void createModuleTable(){
        try{
            
        	//creating connection
            connection = DriverManager.getConnection(url, username, password);
            
            //creating statement
            Statement createTable = connection.createStatement();
            
            //creating SQL query
            String sql = "CREATE TABLE module ("
            		+ "  module_id int(11) NOT NULL,"
            		+ "  course_id int(11) DEFAULT NULL,"
            		+ "  level int(11) DEFAULT NULL,"
            		+ "  module_name varchar(255) DEFAULT NULL,"
            		+ "  instructor_id int(11) DEFAULT NULL,"
            		+ "  PRIMARY KEY (module_id),"
            		+ "  KEY course_id (course_id),"
            		+ "  CONSTRAINT module_ibfk_1 FOREIGN KEY (course_id) REFERENCES course (course_id) ON DELETE CASCADE"
            		+ ")";
            
            //passing the query to execute the statement
            createTable.executeUpdate(sql);
            
        //catching exceptions
        }catch (SQLSyntaxErrorException se){
            System.out.println("Module table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    
    
    /**
     * Creates an instructor table in the database.
     * 
     * @exception SQLSyntaxErrorException
     * @exception SQLException
     */
    private static void createInstructorTable(){
        try{
           
        	//creating connection
            connection = DriverManager.getConnection(url, username, password);
            
            //creating statement
            Statement createTable = connection.createStatement();
            
            //creating SQL query
            String sql = "CREATE TABLE instructor ("
            		+ "  instructor_id int(11) DEFAULT NULL,"
            		+ "  instructor_name varchar(255) DEFAULT NULL,"
            		+ "  instructor_email varchar(255) DEFAULT NULL,"
            		+ "  instructor_address varchar(255) DEFAULT NULL,"
            		+ "  module_id int(11) DEFAULT NULL,"
            		+ "  KEY module_id (module_id),"
            		+ "  CONSTRAINT instructor_ibfk_1 FOREIGN KEY (module_id) REFERENCES module (module_id) ON DELETE CASCADE"
            		+ ")";
            
            //passing the query to execute the statement
            createTable.executeUpdate(sql);
            
        //catching exceptions    
        }catch (SQLSyntaxErrorException se){
            
        	System.out.println("Instructor table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    
    
    /**
     * Creates a student table in the database.
     * 
     * @exception SQLSyntaxErrorException
     * @exception SQLException
     */
    private static void createStudentTable(){
        try{
           
        	//creating connection
            connection = DriverManager.getConnection(url, username, password);
            
            //creating statement
            Statement createTable = connection.createStatement();
            
            //creating query
            String sql = "CREATE TABLE student ("
            		+ "  student_id int(11) DEFAULT NULL,"
            		+ "  student_name varchar(255) DEFAULT NULL,"
            		+ "  student_email varchar(255) DEFAULT NULL,"
            		+ "  module_id int(11) DEFAULT NULL,"
            		+ "  marks int(11) DEFAULT 0"
            		+ ")";
            
            //passing the query to execute the statement
            createTable.executeUpdate(sql);
            
        //catching exceptions
        }catch (SQLSyntaxErrorException se){
            
        	System.out.println("Student table already exists!!");
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    
    
    /**
     * This method inserts dummy data into the created tables of the database.
     * 
     * @exception SQLSyntaxErrorException
     * @exception SQLException
     */
    private static void insertDataInDB(){
        try{
           
        	
        	//creating connection
            connection = DriverManager.getConnection(url, username, password);
            
            //creating statement
            Statement createTable = connection.createStatement();
            
            //creating query to insert data into login table
            String loginInsert = "insert into login values('Course Administrator','admin','admin'),"
            		+ "('Instructor','instructor','instructor'),"
            		+ "('Student','student','student')";
            
            //creating query to insert data into course table
            String courseInsert = "insert into course values"
            		+ "(2022001,'BSc (Hons) Computer Science','Active'),"
            		+ "(2022002,'BSc (Hons) International Business Management','Active')";
            
            //creating query to insert data into module table
            String moduleInsert = "INSERT INTO module VALUES"
            		+ "(400124,2022001,4,'Academic Skills and Team Based Learning',801),"
            		+ "(400125,2022001,4,'Introductory Programming and Problem Solving',802),"
            		+ "(400126,2022001,4,'Fundamentals of Computing',803),"
            		+ "(400127,2022001,4,'Embedded System Programming',804),"
            		+ "(400128,2022001,4,'Internet Software Architecture',805),"
            		+ "(400129,2022001,4,'Computational Mathematics',806),"
            		+ "(400130,2022001,4,'Calculus and Linear Regression',801),"
            		+ "(400131,2022001,4,'Introduction to Robotics and IoT',802),"
            		+ "(500134,2022001,5,'Concepts and Technologies of AI',803),"
            		+ "(500135,2022001,5,'Object-Oriented Design and Programming',804),"
            		+ "(500136,2022001,5,'Numerical Methods and Concurrency',805),"
            		+ "(500137,2022001,5,'Distributed and Cloud System Programming',806),"
            		+ "(500138,2022001,5,'Collaborative Development',801),"
            		+ "(500139,2022001,5,'Human Computer Interaction',802),"
            		+ "(500140,2022001,5,'Applied Data Science',803),"
            		+ "(500141,2022001,5,'Software Engineering',804),"
            		+ "(600144,2022001,6,'Complex System',805),"
            		+ "(600145,2022001,6,'High Performance Computing',806),"
            		+ "(600146,2022001,6,'Project and Professionalism',801),"
            		+ "(600147,2022001,6,'Artificial Intelligence and Machine Learning',802),"
            		+ "(600148,2022001,6,'Big Data',803),"
            		+ "(600149,2022001,6,'Computer Vision',804),"
            		+ "(400224,2022002,4,'21st Century Management',901),"
            		+ "(400225,2022002,4,'Preparing for Success at University',902),"
            		+ "(400226,2022002,4,'Principles of Business',903),"
            		+ "(400227,2022002,4,'Project Based Learning',904),"
            		+ "(400228,2022002,4,'The Responsible Business',905),"
            		+ "(400229,2022002,4,'The Sustainable Business',906),"
            		+ "(400230,2022002,4,'The Innovative Business',901),"
            		+ "(400231,2022002,4,'The Digital Business',902),"
            		+ "(500234,2022002,5,'Contemporary Issues in International Business',903),"
            		+ "(500235,2022002,5,'The International HR Professional',904),"
            		+ "(500236,2022002,5,'Operation and Project Planning',905),"
            		+ "(500237,2022002,5,'Managing Finance and Accounts',906),"
            		+ "(500238,2022002,5,'The Strategic Business',901),"
            		+ "(500239,2022002,5,'Global Context for Multinational Enterprises',902),"
            		+ "(500240,2022002,5,'The Professional Project',903),"
            		+ "(500241,2022002,5,'The Marketing Consultant',904),"
            		+ "(600244,2022002,6,'International Marketing and Sales in the Digital Era',905),"
            		+ "(600245,2022002,6,'Economics of Multinational Business',906),"
            		+ "(600246,2022002,6,'Learning through Work',901),"
            		+ "(600247,2022002,6,'Developing Breakthrough Leadership Skills',902),"
            		+ "(600248,2022002,6,'Creating a Winning Business',903),"
            		+ "(600249,2022002,6,'Corporate Social Responsibility and Sustainability',904)";
            
            
            //creating query to insert data into instructor table
            String instructorInsert = "INSERT INTO instructor VALUES"
            		+ "(801,'Dinesh Kumar','dinsh@gmail.com','Boudha',400124),"
            		+ "(802,'Deepson Sharma','deepson1@gmail.com','Kirtipur',400125),"
            		+ "(803,'Biraj Dulal','birajdl@gmail.com','Naxal',400126),"
            		+ "(804,'Sajjan Raj','sajraj@gmail.com','Chabahil',400127),"
            		+ "(805,'Bishal Khadka','bishal201@gmail.com','Kalopul',400128),"
            		+ "(806,'Siman Giri','simangi@gmail.com','Kapan',400129),"
            		+ "(801,'Dinesh Kumar','dinsh@gmail.com','Boudha',400130),"
            		+ "(802,'Deepson Sharma','deepson1@gmail.com','Kirtipur',400131),"
            		+ "(803,'Biraj Dulal','birajdl@gmail.com','Naxal',500134),"
            		+ "(804,'Sajjan Raj','sajraj@gmail.com','Chabahil',500135),"
            		+ "(805,'Bishal Khadka','bishal201@gmail.com','Kalopul',500136),"
            		+ "(806,'Siman Giri','simangi@gmail.com','Kapan',500137),"
            		+ "(801,'Dinesh Kumar','dinsh@gmail.com','Boudha',500138),"
            		+ "(802,'Deepson Sharma','deepson1@gmail.com','Kirtipur',500139),"
            		+ "(803,'Biraj Dulal','birajdl@gmail.com','Naxal',500140),"
            		+ "(804,'Sajjan Raj','sajraj@gmail.com','Chabahil',500141),"
            		+ "(805,'Bishal Khadka','bishal201@gmail.com','Kalopul',600144),"
            		+ "(806,'Siman Giri','simangi@gmail.com','Kapan',600145),"
            		+ "(801,'Dinesh Kumar','dinsh@gmail.com','Boudha',600146),"
            		+ "(802,'Deepson Sharma','deepson1@gmail.com','Kirtipur',600147),"
            		+ "(803,'Biraj Dulal','birajdl@gmail.com','Naxal',600148),"
            		+ "(804,'Sajjan Raj','sajraj@gmail.com','Chabahil',600149),"
            		+ "(901,'Gagan Thapa','gagant14@gmail.com','Gongabu',400224),"
            		+ "(902,'Shikar Niraula','niraulashikhar@gmail.com','Tokha',400225),"
            		+ "(903,'Asish Acharya','aasish401@gmail.com','Lazimpath',400226),"
            		+ "(904,'Bibek Sigdel','bbksigdel@gmail.com','Gokarna',400227),"
            		+ "(905,'Gauri Gurung','gaurigurung@gmail.com','Sakhu',400228),"
            		+ "(906,'Rajesh Dakal','rajdakal@gmail.com','Gausala',400229),"
            		+ "(901,'Gagan Thapa','gagant14@gmail.com','Gongabu',400230),"
            		+ "(902,'Shikar Niraula','niraulashikhar@gmail.com','Tokha',400231),"
            		+ "(903,'Asish Acharya','aasish401@gmail.com','Lazimpath',500234),"
            		+ "(904,'Bibek Sigdel','bbksigdel@gmail.com','Gokarna',500235),"
            		+ "(905,'Gauri Gurung','gaurigurung@gmail.com','Sakhu',500236),"
            		+ "(906,'Rajesh Dakal','rajdakal@gmail.com','Gausala',500237),"
            		+ "(901,'Gagan Thapa','gagant14@gmail.com','Gongabu',500238),"
            		+ "(902,'Shikar Niraula','niraulashikhar@gmail.com','Tokha',500239),"
            		+ "(903,'Asish Acharya','aasish401@gmail.com','Lazimpath',500240),"
            		+ "(904,'Bibek Sigdel','bbksigdel@gmail.com','Gokarna',500241),"
            		+ "(905,'Gauri Gurung','gaurigurung@gmail.com','Sakhu',600244),"
            		+ "(906,'Rajesh Dakal','rajdakal@gmail.com','Gausala',600245),"
            		+ "(901,'Gagan Thapa','gagant14@gmail.com','Gongabu',600246),"
            		+ "(902,'Shikar Niraula','niraulashikhar@gmail.com','Tokha',600247),"
            		+ "(903,'Asish Acharya','aasish401@gmail.com','Lazimpath',600248),"
            		+ "(904,'Bibek Sigdel','bbksigdel@gmail.com','Gokarna',600249)";
            
            
            //creating query to insert data into student table
            String studentInsert = "INSERT INTO student VALUES"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400124,50),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400125,60),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400126,40),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400127,57),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400128,71),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400129,53),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',400130,68),"
            		+ "(501,'Sonam Rinzin','sonam2@gmail.com',4001231,75),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400124,80),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400125,70),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400126,80),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400127,77),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400128,71),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400129,83),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',400130,68),"
            		+ "(502,'Sujan Bhandari','sujanban@gmail.com',4001231,75),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500134,60),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500135,65),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500136,70),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500137,80),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500138,50),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500139,56),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',50040,8),"
            		+ "(503,'Damche Sandup','sandupdamche@gmail.com',500141,70),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500134,50),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500135,80),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500136,70),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500137,78),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500138,74),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500139,60),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500140,73),"
            		+ "(504,'Nima Tamang','ntmg@gmail.com',500141,60),"
            		+ "(505,'Urgen Dorje','urgen@gmail.com',600144,68),"
            		+ "(505,'Urgen Dorje','urgen@gmail.com',600145,58),"
            		+ "(505,'Urgen Dorje','urgen@gmail.com',600146,88),"
            		+ "(505,'Urgen Dorje','urgen@gmail.com',600147,69),"
            		+ "(506,'Gyatso Lama','gyatso33@gmail.com',600146,85),"
            		+ "(506,'Gyatso Lama','gyatso33@gmail.com',600147,56),"
            		+ "(506,'Gyatso Lama','gyatso33@gmail.com',600148,66),"
            		+ "(506,'Gyatso Lama','gyatso33@gmail.com',600149,59),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400224,60),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400225,64),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400226,50),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400227,69),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400228,70),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400229,80),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400230,66),"
            		+ "(601,'Astha Gurung','asthagrg@gmail.com',400231,63),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400224,80),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400225,70),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400226,86),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400227,85),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400228,87),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400229,70),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400230,90),"
            		+ "(602,'Karma Tsedup','tsedupk@gmail.com',400231,80),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500234,60),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500235,50),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500236,68),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500237,67),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500238,80),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500239,66),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500240,51),"
            		+ "(603,'Kelsang Lhundup','klundup@gmail.com',500241,79),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500234,70),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500235,60),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500236,70),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500237,80),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500238,50),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500239,76),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500240,71),"
            		+ "(604,'Gyaltsen Lama','gyaltlama@gmail.com',500241,56),"
            		+ "(605,'Angesh Basnet','angbasnet@gamil.com',600244,63),"
            		+ "(605,'Angesh Basnet','angbasnet@gamil.com',600246,60),"
            		+ "(605,'Angesh Basnet','angbasnet@gamil.com',600247,70),"
            		+ "(605,'Angesh Basnet','angbasnet@gamil.com',600249,90),"
            		+ "(606,'Sanam Timilsena','sanamt@gmail.com',600245,71),"
            		+ "(606,'Sanam Timilsena','sanamt@gmail.com',600246,78),"
            		+ "(606,'Sanam Timilsena','sanamt@gmail.com',600248,76),"
            		+ "(606,'Sanam Timilsena','sanamt@gmail.com',600249,68)";
            		
            
            //executing all the statements to insert data into the database tables
            createTable.executeUpdate(loginInsert);
            createTable.executeUpdate(courseInsert);
            createTable.executeUpdate(moduleInsert);
            createTable.executeUpdate(instructorInsert);
            createTable.executeUpdate(studentInsert);
            
            
        //catching exceptions
        }catch (SQLSyntaxErrorException se){
            
        	se.printStackTrace();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
}
