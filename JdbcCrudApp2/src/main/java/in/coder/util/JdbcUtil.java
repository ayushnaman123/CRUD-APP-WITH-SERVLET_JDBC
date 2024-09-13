package in.coder.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
    
	private JdbcUtil() {
	}
    
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Physical Connection
	public static Connection getJdbcConnection() throws SQLException, IOException{
		String file = "D:\\ineuron\\PracticeServlet\\JdbcCrudApp2\\src\\main\\java\\in\\coder\\properties\\Application.properties";
		
		HikariConfig config = new HikariConfig(file);
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}
	
	//Logical Connection
	@SuppressWarnings("unused")
	private static Connection getPhysicalConnection() throws SQLException, IOException {
		File file = new File("D:\\ineuron\\PracticeServlet\\JdbcCrudApp2\\src\\main\\java\\in\\coder\\properties\\Application.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fis);
		
		Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
				properties.getProperty("password"));
		return connection;
	}
}
