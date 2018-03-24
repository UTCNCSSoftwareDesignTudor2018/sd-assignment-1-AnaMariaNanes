package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistance.entities.Student;
import persistence.connection.ConnectionFactory;

public class StudentDao implements IStudentDao
{
	
	protected static final Logger LOGGER = Logger.getLogger(StudentDao.class.getName());
	
	private static final String insertStatementString = "INSERT INTO students (name,cardID,cnp,address,groupID,username,password)" 
	+ " VALUES (?,?,?,?,?,?,?)";
	private final static String updateStatementString = "UPDATE students SET name=?, cardID=?, cnp=?, address=?, groupID=?, username=?, password=?"
			+ " WHERE studentID=?";
	private final static String findStatementString = "SELECT * FROM students where studentID = ?";
	private final static String findStatementStringByUsername = "SELECT * FROM students where username = ?";
	private final static String findStatementStringAccount = "SELECT * FROM students where username = ? AND password = ?";
	private final static String findAllStatementString = "SELECT * FROM students";
	private final static String deleteStatementString = "DELETE FROM students where studentID = ?";


	//find student by studentID
	
	public Student findById(int studentID) {
		Student toReturn = null; 

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, studentID);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String cardID = rs.getString("cardID");
			String cnp = rs.getString("cnp");
			String address = rs.getString("address");
			String groupID = rs.getString("groupID");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			toReturn = new Student(studentID,name,cardID,cnp,address,groupID,username,password);
			
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
			return null;
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	//find student by username
	
	 public Student findByUsername(String username) {
			Student toReturn = null;

			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			
			ResultSet rs = null;
			
			try {
				findStatement = dbConnection.prepareStatement(findStatementStringByUsername);
				findStatement.setString(1, username);
				rs = findStatement.executeQuery();
				rs.next();

				int studentID = rs.getInt("studentID");
				String name = rs.getString("name");
				String cardID = rs.getString("cardID");
				String cnp = rs.getString("cnp");
				String address = rs.getString("address");
				String groupID = rs.getString("groupID");
				String password = rs.getString("password");
				
				toReturn = new Student(studentID,name,cardID,cnp,address,groupID,username,password);
				
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"ClientDAO:findByUsername " + e.getMessage());
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(findStatement);
				ConnectionFactory.close(dbConnection);
			}
			return toReturn;
		}
		
		//find student by username and password (authentication)
		
		public Student findByAccount(String username, String password) {
			Student toReturn = null;

			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			
			ResultSet rs = null;
			
			try {
				findStatement = dbConnection.prepareStatement(findStatementStringAccount);
				findStatement.setString(1, username);
				findStatement.setString(2, password);
				rs = findStatement.executeQuery();
				rs.next();

				int studentID = rs.getInt("studentID");
				String name = rs.getString("name");
				String cardID = rs.getString("cardID");
				String cnp = rs.getString("cnp");
				String address = rs.getString("address");
				String groupID = rs.getString("groupID");
				
				toReturn = new Student(studentID,name,cardID,cnp,address,groupID,username,password);
				
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"ClientDAO:findByAccount " + e.getMessage());
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(findStatement);
				ConnectionFactory.close(dbConnection);
			}
			return toReturn;
		}
		
		
	
	// get all students
	
	public List<Student> findAll()
	{
		List<Student> allStudents = new ArrayList<Student>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
        ResultSet rs = null;
		
		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			
			while(rs.next())
			{   
				int studentID = rs.getInt("studentID");
				String name = rs.getString("name");
				String cardID = rs.getString("cardID");
				String cnp = rs.getString("cnp");
				String address = rs.getString("address");
				String groupID = rs.getString("groupID");
				String username = rs.getString("username");
				String password = rs.getString("password");
			    
			    
			    Student oneStudent = new Student(studentID,name,cardID,cnp,address,groupID,username,password);
			    allStudents.add(oneStudent);
			}		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"StudentDao:retrieveDataStudents " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
     
		return allStudents;
	}
	
	// insert student in the database
	
	public int insert(Student student) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			
			insertStatement.setString(1,student.getName());
			insertStatement.setString(2, student.getCardID());
			insertStatement.setString(3,student.getCnp());
			insertStatement.setString(4,student.getAddress());
			insertStatement.setString(5,student.getGroupID());
			insertStatement.setString(6,student.getUsername());
			insertStatement.setString(7,student.getPassword());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StudentDao:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	// update a student
	
	public void update(int studentID,Student student)
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		try {
			
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
			
			updateStatement.setString(1,student.getName());
			updateStatement.setString(2,student.getCardID());
			updateStatement.setString(3,student.getCnp());
			updateStatement.setString(4,student.getAddress());
			updateStatement.setString(5,student.getGroupID());
			updateStatement.setString(6,student.getUsername());
			updateStatement.setString(7,student.getPassword());
			updateStatement.setInt(8,studentID);
			
			updateStatement.executeUpdate();

		}
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "StudentDao:update " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(updateStatement);
		  ConnectionFactory.close(dbConnection);
	    }	
	}
	
	// delete student
	
	public void delete(int studentID){
		 Connection dbConnection = ConnectionFactory.getConnection();
		 
		 PreparedStatement deleteStatement = null;
		 
		 try
		 {
			 deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			 deleteStatement.setInt(1,studentID);
			 deleteStatement.executeUpdate();
		 }
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "StudentDao:delete " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(deleteStatement);
		  ConnectionFactory.close(dbConnection);
	    }
	}
	
	
}
