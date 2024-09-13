package in.coder.servicefactory;

import in.coder.service.IStudentService;
import in.coder.service.StudentServiceImpl;

public class StudentServiceFactory {
	private StudentServiceFactory() {
	};

	private static IStudentService studentService = null;

	public static IStudentService getStudentService() {
		if (studentService == null)
			studentService = new StudentServiceImpl();

		return studentService;
	}
}
