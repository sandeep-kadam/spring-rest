package com.sk.springmvc.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.springmvc.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	
	List<Student> studentList ;
	
	//to load the student data only once
	@PostConstruct
	public void loadData() {

		studentList = new ArrayList<Student>();
		
		studentList.add(new Student("sandeep", "kadam"));
		studentList.add(new Student("shrikant", "kadam"));
		studentList.add(new Student("vijay", "shrimangle"));
		studentList.add(new Student("john", "smith"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return studentList;
	}
	
	
	//using path variables like student details by id
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable int studentId){
		System.out.println("StudentId :: "+studentId);
		System.out.println("StudentList size :: " + studentList.size());
		if((studentList.size() < studentId) || (studentId < 0) ) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		return studentList.get(studentId);
	}
	
	
	
	//add an exception handler using @ExceptionHandler
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception){
		
		StudentErrorResponse error = new StudentErrorResponse();
	
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleGenericExcpetion(Exception exception){
		
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
