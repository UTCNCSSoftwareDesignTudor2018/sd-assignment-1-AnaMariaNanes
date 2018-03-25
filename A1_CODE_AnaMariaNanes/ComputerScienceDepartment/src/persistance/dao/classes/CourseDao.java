package persistance.dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistance.dao.interfaces.ICourseDao;
import persistance.entities.Course;
import persistence.connection.ConnectionFactory;

public class CourseDao implements ICourseDao {
	
	protected static final Logger LOGGER = Logger.getLogger(CourseDao.class.getName());
	
	private static final String insertStatementString = "INSERT INTO courses (name,teacherID,credits,room)" 
	+ " VALUES (?,?,?,?)";
	private final static String updateStatementString = "UPDATE courses SET name=?, teacherID=?, credits=?, room=? " + " WHERE courseID=?";
	private final static String findStatementString = "SELECT * FROM courses where courseID = ?";
	private final static String findAllStatementString = "SELECT * FROM courses";
	private final static String deleteStatementString = "DELETE FROM courses where courseID = ?";
	
	//find course by courseID
	
	public Course findById(int courseID) {
			Course toReturn = null; 

			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			
			ResultSet rs = null;
			
			try {
				findStatement = dbConnection.prepareStatement(findStatementString);
				findStatement.setLong(1, courseID);
				rs = findStatement.executeQuery();
				rs.next();

				String name = rs.getString("name");
				int teacherID= rs.getInt("teacherID");
				int credits = rs.getInt("credits");
				String room = rs.getString("room");
				
				toReturn = new Course(courseID,name,teacherID,credits,room);
				
			} catch (SQLException e) { 
				LOGGER.log(Level.WARNING,"CourseDAO:findById " + e.getMessage());
				return null;
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(findStatement);
				ConnectionFactory.close(dbConnection);
			}
			return toReturn;
		}
		
	// find all courses	
	public List<Course> findAll()
		{
			List<Course> allCourses = new ArrayList<Course>();
			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement findAllStatement = null;
	        ResultSet rs = null;
			
			try {
				findAllStatement = dbConnection.prepareStatement(findAllStatementString);
				rs = findAllStatement.executeQuery();
				
				while(rs.next())
				{   
					int courseID = rs.getInt("courseID");
					String name = rs.getString("name");
					int teacherID = rs.getInt("teacherID");
					int credits = rs.getInt("credits");
					String room = rs.getString("room");
				    
				    Course oneCourse = new Course(courseID,name,teacherID,credits,room);
				    allCourses.add(oneCourse);
				}		
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,"CourseDao:findAll " + e.getMessage());
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(findAllStatement);
				ConnectionFactory.close(dbConnection);
			}
	     
			return allCourses;
		}
		
		// insert course
	public int insert(Course course) {
			Connection dbConnection = ConnectionFactory.getConnection();

			PreparedStatement insertStatement = null;
			int insertedId = -1;
			
			try {
				insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
				
				insertStatement.setString(1,course.getName());
				insertStatement.setInt(2, course.getTeacherID());
				insertStatement.setInt(3,course.getCredits());
				insertStatement.setString(4,course.getRoom());
				insertStatement.executeUpdate();
				
				ResultSet rs = insertStatement.getGeneratedKeys();
				if (rs.next()) {
					insertedId = rs.getInt(1);
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "CourseDao:insert " + e.getMessage());
			} finally {
				ConnectionFactory.close(insertStatement);
				ConnectionFactory.close(dbConnection);
			}
			return insertedId;
		}
		
		// update a course
		public void update(Course course)
		{
			Connection dbConnection = ConnectionFactory.getConnection();

			PreparedStatement updateStatement = null;
			
			int courseID = course.getCourseID();
			
			try {
				
				updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);				
				updateStatement.setString(1,course.getName());
				updateStatement.setInt(2,course.getTeacherID());
				updateStatement.setInt(3,course.getCredits());
				updateStatement.setString(4,course.getRoom());
				updateStatement.setInt(5,courseID);			
				updateStatement.executeUpdate();

			}
			 catch(SQLException e)
			 {
				 LOGGER.log(Level.WARNING, "CourseDao:update " + e.getMessage());
		     } finally {
			  ConnectionFactory.close(updateStatement);
			  ConnectionFactory.close(dbConnection);
		    }	
		}
		
		// delete course
		public void delete(int courseID){
			 Connection dbConnection = ConnectionFactory.getConnection();
			 
			 PreparedStatement deleteStatement = null;
			 
			 try
			 {
				 deleteStatement = dbConnection.prepareStatement(deleteStatementString);
				 deleteStatement.setInt(1,courseID);
				 deleteStatement.executeUpdate();
			 }
			 catch(SQLException e)
			 {
				 LOGGER.log(Level.WARNING, "CourseDao:delete " + e.getMessage());
		     } finally {
			  ConnectionFactory.close(deleteStatement);
			  ConnectionFactory.close(dbConnection);
		    }
		}

}
