package com.kodlamaio.hrmsDemo5.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo5.business.abstracts.CityService;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo5.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrmsDemo5.entities.concretes.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>("Cities listed succesfully.", this.cityDao.findAll());
	}

}
