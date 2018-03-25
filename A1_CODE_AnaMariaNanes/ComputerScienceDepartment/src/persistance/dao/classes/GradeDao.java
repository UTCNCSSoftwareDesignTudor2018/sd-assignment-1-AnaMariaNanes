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

import persistance.dao.interfaces.IGradeDao;
import persistance.entities.Grade;
import persistence.connection.ConnectionFactory;

public class GradeDao implements IGradeDao{

	protected static final Logger LOGGER = Logger.getLogger(GradeDao.class.getName());
	
	private static final String insertStatementString = "INSERT INTO enrollment_grades (enrollmentID,grade)" + " VALUES (?,?)";
	private final static String updateStatementString = "UPDATE enrollment_grades SET enrollmentID = ?, grade = ?" + " WHERE id = ?";
	private final static String findStatementString = "SELECT * FROM enrollment_grades where id = ?";
	private final static String findStatementStringByEnrollmentId = "SELECT * FROM enrollment_grades where enrollmentID = ?";
	private final static String findAllStatementString = "SELECT * FROM enrollment_grades";
	private final static String deleteStatementString = "DELETE FROM enrollment_grades where id = ?";

	// find grade by ID
	public Grade findById(int id) {
		Grade toReturn = null; 

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			rs.next();

			int enrollmentID = rs.getInt("enrollmentID"); 
			float grade = rs.getFloat("grade");
			
			toReturn = new Grade(id,enrollmentID,grade);
			
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"GradeDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}


	// find all grades
	public List<Grade> findAll() {
		List<Grade> allGrades = new ArrayList<Grade>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
        ResultSet rs = null;
		
		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			
			while(rs.next())
			{   
				int id = rs.getInt("id");
				int enrollmentID = rs.getInt("enrollmentID");
				float grade = rs.getFloat("grade");
				
				Grade oneGrade = new Grade(id,enrollmentID,grade);
			    allGrades.add(oneGrade);
			}		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"GradeDao:findAll" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
     
		return allGrades;
	}
	

	// find all grades by enrollmentID
	public List<Grade> findByEnrollmentId(int enrollmentID) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		List<Grade> allGrades = new ArrayList<Grade>();
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementStringByEnrollmentId);
			findStatement.setLong(1, enrollmentID);
			rs = findStatement.executeQuery();
			
			while(rs.next())
			{ 

			int id = rs.getInt("id");
		    float grade = rs.getFloat("grade");
			
			Grade oneGrade = new Grade(id,enrollmentID,grade);
			allGrades.add(oneGrade);
			
			}
			
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"GradeDao:findByEnrollmentId " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return allGrades;
	}


	// insert grade
	public int insert(Grade grade) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			
			insertStatement.setInt(1,grade.getEnrollmentID());
			insertStatement.setFloat(2, grade.getGrade());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GradeDao:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}


	//update grade
	public void update(Grade grade) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		int id = grade.getId();
		
		try {
			
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);		
			updateStatement.setInt(1,grade.getEnrollmentID());
			updateStatement.setFloat(2,grade.getGrade());
			updateStatement.setInt(3,grade.getId());
			updateStatement.executeUpdate();

		}
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "GradeDao:update " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(updateStatement);
		  ConnectionFactory.close(dbConnection);
	    }	
		
	}

    // delete grade
	public void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		 
		 PreparedStatement deleteStatement = null;
		 
		 try
		 {
			 deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			 deleteStatement.setInt(1,id);
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
