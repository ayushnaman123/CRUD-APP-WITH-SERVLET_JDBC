package in.coder.service;

import in.coder.dto.Student;

public interface IStudentService {
	public String addStudent(Student student);
	public Student serachStudent(Integer sid);
	public String updateStudent(Student student);
	public String deleteStudent(Integer sid);
	
}
