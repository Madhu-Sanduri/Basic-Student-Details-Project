package com.example.demoForJPA.Controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoForJPA.Controller.repo.StudentRepo;
import com.example.demoForJPA.Entity.Student;

@RestController
public class StudentController {
	@Autowired
	StudentRepo studentRepo;
	@PostMapping("/api/students") //Create
	public ResponseEntity<List<Student>> saveStudent(@RequestBody List<Student> student) {
		
		return new ResponseEntity<>(studentRepo.saveAll(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/students") //Read
	public ResponseEntity<List<Student>> getStudentData(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("api/students/{id}") //Read By id
	
	public ResponseEntity<Student> getStudentDataId(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(student.get(),HttpStatus.NOT_FOUND);
		
	}
	
	
	@PutMapping("/api/students/{id}")  //Update	
	public ResponseEntity<Student> updateStudentData(@PathVariable long id, @RequestBody Student student) {
		Optional<Student> s = studentRepo.findById(id);
		if(studentRepo.findById(id).isPresent()) {
			return new ResponseEntity<>(studentRepo.save(student) ,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/api/students/{id}") //Delete By id
	public ResponseEntity<Void> deleteStudentData(@PathVariable long id) {
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
