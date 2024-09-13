package in.coder.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.coder.dto.Student;
import in.coder.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao{

	private Connection connection=null;
	private PreparedStatement prepareStatement=null;
	private ResultSet resultSet=null;

	@Override
	public String addStudent(Student student) {
		String sqlInsertQuery = "Insert into student(`name`,`age`,`address`) values(?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
				prepareStatement = connection.prepareStatement(sqlInsertQuery);
			
			if(prepareStatement != null) {
				prepareStatement.setString(1, student.getSname());
				prepareStatement.setInt(2, student.getSage());
				prepareStatement.setString(3, student.getAddress());
				
				int rowsAffected = prepareStatement.executeUpdate();
				
				if(rowsAffected==1)
					return "success";
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String sqlSelectQuery = "Select id, name, age, address from student where id = ?";
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
				prepareStatement = connection.prepareStatement(sqlSelectQuery);
			
			if(prepareStatement!=null) {
				prepareStatement.setInt(1, sid);
				resultSet = prepareStatement.executeQuery();
			}
			
			if(resultSet!=null) {
				if(resultSet.next()) {
					student = new Student();
					
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setAddress(resultSet.getString(4));
					
					return student;
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		String sqlUpdateQuery = "Update student set name=?, age=?, address=? where id=?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				prepareStatement = connection.prepareStatement(sqlUpdateQuery);
			
			if(prepareStatement!=null) {
				prepareStatement.setString(1, student.getSname());
				prepareStatement.setInt(2, student.getSage());
				prepareStatement.setString(3, student.getAddress());
				prepareStatement.setInt(4, student.getSid());
				
				int rowsAffected = prepareStatement.executeUpdate();
				
				if(rowsAffected==1)
					return "success";
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "Delete from student where id = ?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
				prepareStatement = connection.prepareStatement(sqlDeleteQuery);
			
			if(prepareStatement!=null) {
				prepareStatement.setInt(1, sid);
				int rowsAffected = prepareStatement.executeUpdate();
				
				if(rowsAffected==1)
					return "success";
				else
					return "not found";
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}

}
