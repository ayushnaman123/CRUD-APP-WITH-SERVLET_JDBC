package in.coder.service;

import in.coder.daofactory.StudentDaoFactory;
import in.coder.dto.Student;
import in.coder.persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao studentDao;

	@Override
	public String addStudent(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.addStudent(student);
	}

	@Override
	public Student serachStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(sid);
	}

}
