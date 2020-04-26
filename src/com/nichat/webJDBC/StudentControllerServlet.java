package com.nichat.webJDBC;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StudentDBUtil studentDBUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
    @Override
	public void init() throws ServletException {
		super.init();
		
		//create our student db util and pass in the conn pool
		
		try {
			studentDBUtil = new StudentDBUtil(dataSource);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
				listStudents(request, response);
			}
			// route to the appropriate method
			else if(theCommand.equals("ADD")) {
				addStudent(request, response);
			}
			else if(theCommand.equals("LOAD")) {
				loadStudent(request, response);
			}
			else if(theCommand.equals("DELETE")) {
				deleteStudent(request, response);				
			}
			else if(theCommand.equals("UPDATE")) {
				updateStudent(request, response);				
			}
			//default to listStudents
			else{
				listStudents(request, response);
			}
			
			
//			switch (theCommand) {
//			
//			case "LIST":
//				listStudents(request, response);
//				break;
//				
//			case "ADD":
//				addStudent(request, response);
//				break;
//				
//			case "LOAD":
//				loadStudent(request, response);
//				break;
//				
//			case "UPDATE":
//				updateStudent(request, response);
//				break;
//			
//			case "DELETE":
//				deleteStudent(request, response);
//				break;
//				
//			default:
//				listStudents(request, response);
//			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//read student id from data
		String theStudentId = request.getParameter("studentId");
		
		//get student from database
		Student theStudent = studentDBUtil.getStudent(theStudentId);
		
		//place student in request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		//send to jsp page -updateStudent.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateStudent.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		try{
			//read student id from data
			int theStudentId = Integer.parseInt(request.getParameter("studentId"));
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
					
			//get student from database
			Student theStudent = new Student(theStudentId, firstName, lastName, email);
			
			//perfrom update
			studentDBUtil.updateStudent(theStudent);
			
			listStudents(request,response);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try{
			//read student id from data
			int theStudentId = Integer.parseInt(request.getParameter("studentId"));
			
			//delete student from db
			studentDBUtil.deleteStudent(theStudentId);
			
			//send them back to list students page
			listStudents(request,response);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");		
		
		// create a new student object
		Student theStudent = new Student(firstName, lastName, email);
		
		// add the student to the database
		studentDBUtil.addStudent(theStudent);
				
		// send back to main page (the student list)
		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//get students from DB util
		try {
			List<Student> students = studentDBUtil.getStudents();
			//add students to request
			request.setAttribute("STUDENT_LIST", students);
			
			//send to jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/studentList.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
