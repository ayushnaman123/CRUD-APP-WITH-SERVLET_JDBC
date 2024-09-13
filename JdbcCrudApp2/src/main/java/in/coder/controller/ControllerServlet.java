package in.coder.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.coder.dto.Student;
import in.coder.service.IStudentService;
import in.coder.servicefactory.StudentServiceFactory;


@WebServlet(urlPatterns="/controller/*", loadOnStartup = 10)
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		IStudentService studentService = StudentServiceFactory.getStudentService();
		
		System.out.println("Request URI:: "+request.getRequestURI());
		System.out.println("Path Info:: "+request.getPathInfo());
		
		if(request.getRequestURI().endsWith("addform")) {
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");
			
			Student student = new Student();
			
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));;
			student.setAddress(saddress);
			
			String status = studentService.addStudent(student);
			RequestDispatcher requestDispatcher = null;
			if(status.equals("success")) {
				requestDispatcher = request.getRequestDispatcher("../regSuccess.html");
				requestDispatcher.forward(request, response);
			}else {
				requestDispatcher = request.getRequestDispatcher("../regFailure.html");
				requestDispatcher.forward(request, response);
			}
			
		}
		
		if(request.getRequestURI().endsWith("searchform")) {
			String sid = request.getParameter("sid");
			
			Student student = studentService.serachStudent(Integer.parseInt(sid));
			
			PrintWriter out = response.getWriter();
			
			if(student!=null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<h1 style='color:green; text-align:center;'>STUDENT RECORD </h1>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>ID</th><td>"+student.getSid()+"</td></tr>");
				out.println("<tr><th>NAME</th><td>"+student.getSname()+"</td></tr>");
				out.println("<tr><th>AGE</th><td>"+student.getSage()+"</td></tr>");
				out.println("<tr><th>ADDRESS</th><td>"+student.getAddress()+"</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			}else {
				out.println("<h1 style='color:red; text-align:center;'>RECORD NOT FOUND FOR THE GIVEN ID :"+sid+"</h1>");
			}
			
			out.close();
		}
		
		if(request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");
			Student student = studentService.serachStudent(Integer.parseInt(sid));
			
			PrintWriter out = response.getWriter();
			if(student!=null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>"+student.getSid()+"</td></tr>");
				out.println("<input type='hidden' name='sid' value='"+student.getSid()+"'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='"+student.getSname()+"'/></td></tr>"); 
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='"+student.getSage()+"'/></td></tr>"); 
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='"+student.getAddress()+"'/></td></tr>"); 
				out.println("<tr><th></th><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("<body>");
			}else {
				out.println("<body>");
				out.println("<h1 style='color:red; text-align:center;'>RECORD NOT FOUND FOR THE GIVEN ID: "+sid+"</h1>");
				out.println("<body>");
			}
			out.close();
		}
		
		if(request.getRequestURI().endsWith("updateRecord")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");
			
			Student student = new Student();
			student.setSid(Integer.parseInt(sid));
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setAddress(saddress);
			
			String status = studentService.updateStudent(student);
			RequestDispatcher requestDisp = null;
			if(status.equals("success")) {
				requestDisp = request.getRequestDispatcher("../../updateSuccess.html");
				requestDisp.forward(request, response);
				
			}else {
				requestDisp = request.getRequestDispatcher("../../updateFailure.html");
				requestDisp.forward(request, response);
			}
			
			
			
		}
		
		if(request.getRequestURI().endsWith("deleteform")) {
			String sid = request.getParameter("sid");
			
			String status = studentService.deleteStudent(Integer.parseInt(sid));
			RequestDispatcher requestDispatcher = null;
			if(status.equals("success")) {
				 requestDispatcher = request.getRequestDispatcher("../deleteSuccess.html");
				 requestDispatcher.forward(request, response);
			}else if(status.equals("failure")) {
				requestDispatcher = request.getRequestDispatcher("../deleteFailure.html");
				requestDispatcher.forward(request, response);
			}else {
				requestDispatcher = request.getRequestDispatcher("../deleteNotFound.html");
				requestDispatcher.forward(request, response);
			}
		}
		
	}

}
