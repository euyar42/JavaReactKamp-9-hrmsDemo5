package com.kodlamaio.hrmsDemo5.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmsDemo5.business.abstracts.SchoolService;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo5.entities.concretes.School;

@RestController
@RequestMapping("/api/schools")
public class SchoolsController {
	
	private SchoolService schoolService;
	
	@Autowired
	public SchoolsController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.schoolService.getAll());
	}
	
	@GetMapping("/getallorderbyenddatedesc")
	public ResponseEntity<?> getAllOrderByEndDateDesc() {
		return ResponseEntity.ok(this.schoolService.getAllOrderByEndDateDesc());
	}
	
	@GetMapping("/getbyenddateisnull")
	public ResponseEntity<?> getByEndDateIsNull() {
		return ResponseEntity.ok(this.schoolService.getByEndDateIsNull());
	}
	
	@GetMapping("/getbyenddateisnotnullorderbyenddatedesc")
	public ResponseEntity<?> getByEndDateIsNotNullOrderByEndDateDesc() {
		return ResponseEntity.ok(this.schoolService.getByEndDateIsNotNullOrderByEndDateDesc());
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody School school) {
		return ResponseEntity.ok(this.schoolService.add(school));
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>("Validation Errors", validationErrors);
	}
	
}