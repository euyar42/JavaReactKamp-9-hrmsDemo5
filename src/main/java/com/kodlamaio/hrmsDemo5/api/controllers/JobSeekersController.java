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

import com.kodlamaio.hrmsDemo5.business.abstracts.JobSeekerService;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo5.entities.concretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekersController {
	
	private JobSeekerService jobSeekerService;
	
	@Autowired
	public JobSeekersController(JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.jobSeekerService.getAll());
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobSeeker jobSeeker) {
		return ResponseEntity.ok(this.jobSeekerService.add(jobSeeker));
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
