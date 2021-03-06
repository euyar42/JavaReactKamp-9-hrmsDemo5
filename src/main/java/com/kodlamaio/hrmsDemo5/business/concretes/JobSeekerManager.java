package com.kodlamaio.hrmsDemo5.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo5.business.abstracts.JobSeekerService;
import com.kodlamaio.hrmsDemo5.core.adapters.abstracts.JobSeekerValidationService;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.ErrorDataResult;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.ErrorResult;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.SuccessResult;
import com.kodlamaio.hrmsDemo5.core.validators.emailRegex.abstracts.JobSeekerEmailRegexValidatorService;
import com.kodlamaio.hrmsDemo5.core.validators.emailVerify.abstracts.JobSeekerEmailVerifyService;
import com.kodlamaio.hrmsDemo5.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrmsDemo5.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	
	private JobSeekerDao jobSeekerDao;
	private JobSeekerEmailRegexValidatorService jobSeekerEmailRegexValidatorService;
	private JobSeekerValidationService jobSeekerValidationService;
	private JobSeekerEmailVerifyService jobSeekerEmailVerifyService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao,
			JobSeekerEmailRegexValidatorService jobSeekerEmailRegexValidatorService,
			JobSeekerValidationService jobSeekerValidationService, 
			JobSeekerEmailVerifyService jobSeekerEmailVerifyService) {
		this.jobSeekerDao = jobSeekerDao;
		this.jobSeekerEmailRegexValidatorService = jobSeekerEmailRegexValidatorService;
		this.jobSeekerValidationService = jobSeekerValidationService;
		this.jobSeekerEmailVerifyService = jobSeekerEmailVerifyService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>("Jobseekers listed successfully", this.jobSeekerDao.findAll());
	}

	@Override
	public DataResult<JobSeeker> get(int id) {
		if (this.jobSeekerDao.findById(id).orElse(null) != null ) {
			return new SuccessDataResult<JobSeeker>("The specified jobseeker was found successfully.", this.jobSeekerDao.findById(id).get());
		} else {
			return new ErrorDataResult<JobSeeker>("The specified jobseeker is not available.");
		}
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		if (!this.jobSeekerValidationService.isRealPerson(jobSeeker)) {
			return new ErrorResult("Invalid job seeker!");
		} else if (this.existsJobSeekerByNationalityId(jobSeeker.getNationalityId())) {
			return new ErrorResult("There is a jobseeker record with this identification number.");
		} else if (!this.jobSeekerEmailRegexValidatorService.isValidEmail(jobSeeker.getEmail())) {
			return new ErrorResult("Invalid email!");
		} else if (this.existsJobSeekerByEmail(jobSeeker.getEmail())) {
			return new ErrorResult("There is a jobseeker record with this email.");
		} else if (!this.jobSeekerEmailVerifyService.hasVerifyEmail(jobSeeker.getEmail())) {
			return new ErrorResult("Email not verified!");
		} else {
			this.jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Jobseeker added successfully.");
		}
	}

	@Override
	public Result delete(int id) {
		this.jobSeekerDao.deleteById(id);
		return new SuccessResult("Jobseeker deleted successfully.");
	}

	@Override
	public Result update(JobSeeker jobSeeker) {
		this.jobSeekerDao.save(jobSeeker);
		return new SuccessResult("Jobseeker updated successfully.");
	}

	@Override
	public boolean existsJobSeekerByNationalityId(String nationalityId) {
		return this.jobSeekerDao.existsJobSeekerByNationalityId(nationalityId);
	}

	@Override
	public boolean existsJobSeekerByEmail(String email) {
		return this.jobSeekerDao.existsJobSeekerByEmail(email);
	}

//	@Override
//	public boolean hasEmptyField(JobSeeker jobSeeker) {
//		if (jobSeeker.getFirstName().isEmpty() || jobSeeker.getLastName().isEmpty() 
//				|| jobSeeker.getDateOfBirth().toString().isEmpty() || jobSeeker.getEmail().isEmpty() 
//				|| jobSeeker.getNationalityId().isEmpty() || jobSeeker.getPassword().isEmpty() 
//				|| jobSeeker.getGender().isEmpty()) {
//			return false;
//		} else {
//			return true;
//		}
//	}

}
