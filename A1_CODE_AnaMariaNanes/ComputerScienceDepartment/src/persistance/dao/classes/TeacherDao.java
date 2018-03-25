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

import persistance.dao.interfaces.ITeacherDao;
import persistance.entities.Teacher;
import persistence.connection.ConnectionFactory;

public class TeacherDao implements ITeacherDao{
	
	
    protected static final Logger LOGGER = Logger.getLogger(TeacherDao.class.getName());
	
	private static final String insertStatementString = "INSERT INTO teachers (name,username,password)"  + " VALUES (?,?,?)";
	private final static String updateStatementString = "UPDATE teachers SET name=?, username=?, password=?" + " WHERE teacherID=?";
	private final static String findStatementString = "SELECT * FROM teachers where teacherID = ?";
	private final static String findAllStatementString = "SELECT * FROM teachers";
	private final static String deleteStatementString = "DELETE FROM teachers where teacherID = ?";
	
	
    // find teacher by teacherID
	public Teacher findById(int teacherID) {
		Teacher toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		
		ResultSet rs = null;
		
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, teacherID);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			toReturn = new Teacher(teacherID,name,username,password);
			
		} catch (SQLException e) { 
			LOGGER.log(Level.WARNING,"TeacherDAO:findById " + e.getMessage());
			return null;
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	//find all teachers
	public List<Teacher> findAll() {
		
		List<Teacher> allTeachers = new ArrayList<Teacher>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
        ResultSet rs = null;
		
		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			
			while(rs.next())
			{   
				int teacherID= rs.getInt("teacherID");
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
			    
			    Teacher oneTeacher = new Teacher(teacherID,name,username,password);
			    allTeachers.add(oneTeacher);
			}		
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"TeacherDao:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
     
		return allTeachers;
	}

	//insert teacher
	public int insert(Teacher teacher) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			
			insertStatement.setString(1,teacher.getName());
			insertStatement.setString(2,teacher.getUsername());
			insertStatement.setString(3,teacher.getPassword());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TeacherDao:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	//update teacher
	public void update(Teacher teacher) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		
		int teacherID = teacher.getTeacherID();		
		
		try {
			
			updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);				
			updateStatement.setString(1,teacher.getName());
			updateStatement.setString(2,teacher.getUsername());
			updateStatement.setString(3,teacher.getPassword());
			updateStatement.setInt(4,teacherID);			
			updateStatement.executeUpdate();

		}
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "TeacherDao:update " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(updateStatement);
		  ConnectionFactory.close(dbConnection);
	    }	
		
	}

	public void delete(int teacherID) {
		 Connection dbConnection = ConnectionFactory.getConnection();
		 
		 PreparedStatement deleteStatement = null;
		 
		 try
		 {
			 deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			 deleteStatement.setInt(1,teacherID);
			 deleteStatement.executeUpdate();
		 }
		 catch(SQLException e)
		 {
			 LOGGER.log(Level.WARNING, "TeacherDao:delete " + e.getMessage());
	     } finally {
		  ConnectionFactory.close(deleteStatement);
		  ConnectionFactory.close(dbConnection);
	    }
		
	}

}
