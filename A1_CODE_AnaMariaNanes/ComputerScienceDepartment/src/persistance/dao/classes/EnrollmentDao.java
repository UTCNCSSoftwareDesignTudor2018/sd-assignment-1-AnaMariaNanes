package persistance.dao.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistance.dao.interfaces.IEnrollmentDao;
import persistance.entities.Enrollment;
import persistence.connection.ConnectionFactory;

public class EnrollmentDao implements IEnrollmentDao {
	
    protected static final Logger LOGGER = Logger.getLogger(EnrollmentDao.class.getName());
	
	private static final String insertStatementString = "INSERT INTO enrollments (studentID,courseID,examGrade,startDate,endDate)" 
	+ " VALUES (?,?,?,?,?)";
	private final static String updateStatementString = "UPDATE enrollments SET studentID=?, courseID=?, examGrade=?, startDate=?, endDate=?"
			+ " WHERE enrollmentID=?";
	private final static String findStatementString = "SELECT * FROM enrollments where enrollmentID = ?";
	private final static String findStatementStringByStudentId = "SELECT * FROM  enrollments where studentID = ?";
	private final static String findStatementStringByCourseId = "SELECT * FROM enrollments where courseID = ?";
	private final static String findAllStatementString = "SELECT * FROM enrollments";
	private final static String deleteStatementString = "DELETE FROM enrollments where enrollmentID = ?";


	// find enrollment by enrollmentId
	public Enrollment findById(int enrollmentID) {
		Enrollment toReturn = null; 

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, enrollmentID);
			rs = findStatement.executeQuery();
			rs.next();

			int studentID = rs.getInt("studentID");
			int courseID = rs.getInt("courseID");
			float examGrade = rs.getFloat("examGrade");
			String startDate = rs.getString("startDate");
			String endDate = rs.getString("endDate");
			
			toReturn = new Enrollment(enrollmentID,studentID,courseID,examGrade,startDate,endDate);
			
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"EnrollmentDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

    // find enrollment by studentID
	public List<Enrollment> findByStudentId(int studentID) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		List<Enrollment> allEnrollments = new ArrayList<Enrollment>();
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringByStudentId);
			findStatement.setLong(1, studentID);
			rs = findStatement.executeQuery();
			
			while(rs.next())
			{ 

			int enrollmentID = rs.getInt("enrollmentID");
			int courseID = rs.getInt("courseID");
			float examGrade = rs.getFloat("examGrade");
			String startDate = rs.getString("startDate");
			String endDate = rs.getString("endDate");
			
			Enrollment oneEnrollment = new Enrollment(enrollmentID,studentID,courseID,examGrade,startDate,endDate);
			allEnrollments.add(oneEnrollment);
			}
			
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"EnrollmentDAO:findByStudentId " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return allEnrollments;
	}

	//find enrollment by courseID
	public List<Enrollment> findByCourseId(int courseID) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		List<Enrollment> allEnrollments = new ArrayList<Enrollment>();
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringByCourseId);
			findStatement.setLong(1, courseID);
			rs = findStatement.executeQuery();
		
			
			while(rs.next())
			{

			int enrollmentID = rs.getInt("enrollmentID");
			int studentID = rs.getInt("studentID");
			float examGrade = rs.getFloat("examGrade");
			String startDate = rs.getString("startDate");
			String endDate = rs.getString("endDate");
			
			Enrollment oneEnrollment = new Enrollment(enrollmentID,studentID,courseID,examGrade,startDate,endDate);
			allEnrollments.add(oneEnrollment);
			
			}
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"EnrollmentDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return allEnrollments;
	}

    // find all enrollments
	public List<Enrollment> findAll() {
		
		List<Enrollment> allEnrollments = new ArrayList<Enrollment>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
        ResultSet rs = null;
		
		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			
			while(rs.next())
			{   
				int enrollmentID = rs.getInt("enrollmentID");
				int studentID = rs.getInt("studentID");
				int courseID = rs.getInt("courseID");
				float examGrade = rs.getFloat("examGrade");
				String startDate = rs.getString("startDate");
				String endDate = rs.getString("endDate");
				
				Enrollment oneEnrollment = new Enrollment(enrollmentID,studentID,courseID,examGrade,startDate,endDate);
			    allEnrollments.add(oneEnrollment);
			}		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"EnrollmentDao:findAll" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
     
		return allEnrollments;
	}

    // isnert enrollment
	public int insert(Enrollment enrollment) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			
			insertStatement.setInt(1,enrollment.getStudentID());
			insertStatement.setInt(2, enrollment.getCourseID());
			insertStatement.setFloat(3,enrollment.getExamGrade());
			insertStatement.setString(4,enrollment.getStartDate());
			insertStatement.setString(5,enrollment.getEndDate());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "EnrollmentDao:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	// update enrollment
	public void update(Enrollment enrollment) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		int enrollmentID = enrollment.getEnrollmentID();
		
		try {
			
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);		
			updateStatement.setInt(1,enrollment.getStudentID());
			updateStatement.setInt(2,enrollment.getCourseID());
			updateStatement.setFloat(3,enrollment.getExamGrade());
			updateStatement.setString(4,enrollment.getStartDate());
			updateStatement.setString(5,enrollment.getEndDate());
			updateStatement.setInt(6,enrollmentID);	
			updateStatement.executeUpdate();

		}
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "EnrollmentDao:update " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(updateStatement);
		  ConnectionFactory.close(dbConnection);
	    }	
		
	}

    // delete enrollment
	public void delete(int enrollmentID) {
		Connection dbConnection = ConnectionFactory.getConnection();
		 
		 PreparedStatement deleteStatement = null;
		 
		 try
		 {
			 deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			 deleteStatement.setInt(1,enrollmentID);
			 deleteStatement.executeUpdate();
		 }
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "EnrollmentDao:delete " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(deleteStatement);
		  ConnectionFactory.close(dbConnection);
	    }
	}

}
