package in.coder.daofactory;

import in.coder.persistence.IStudentDao;
import in.coder.persistence.StudentDaoImpl;

public class StudentDaoFactory {
	private static IStudentDao studentDao = null;

	private StudentDaoFactory() {
	};

	public static IStudentDao getStudentDao() {
		if (studentDao == null)
			studentDao = new StudentDaoImpl();

		return studentDao;
	}

}
