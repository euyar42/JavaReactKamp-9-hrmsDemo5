package com.kodlamaio.hrmsDemo5.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmsDemo5.business.abstracts.TechnologyKnowledgeService;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.DataResult;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.Result;
import com.kodlamaio.hrmsDemo5.core.utilities.result.concretes.SuccessDataResult;
import com.kodlamaio.hrmsDemo5.dataAccess.abstracts.TechnologyKnowledgeDao;
import com.kodlamaio.hrmsDemo5.entities.concretes.TechnologyKnowledge;

@Service
public class TechnologyKnowledgeManager implements TechnologyKnowledgeService {
	
	private TechnologyKnowledgeDao technologyKnowledgeDao;
	
	@Autowired
	public TechnologyKnowledgeManager(TechnologyKnowledgeDao technologyKnowledgeDao) {
		this.technologyKnowledgeDao = technologyKnowledgeDao;
	}

	@Override
	public DataResult<List<TechnologyKnowledge>> getAll() {
		return new SuccessDataResult<List<TechnologyKnowledge>>("Technology Knowledges listed succesfully.", this.technologyKnowledgeDao.findAll());
	}

	@Override
	public DataResult<TechnologyKnowledge> get(int id) {
		return new SuccessDataResult<TechnologyKnowledge>("Technology Knowledge got succesfully.", this.technologyKnowledgeDao.findById(id).get());
	}

	@Override
	public Result add(TechnologyKnowledge schoolDegree) {
		this.technologyKnowledgeDao.save(schoolDegree);
		return new SuccessDataResult<TechnologyKnowledge>("Technology Knowledge added succesfully.");
	}

	@Override
	public Result delete(int id) {
		this.technologyKnowledgeDao.deleteById(id);
		return new SuccessDataResult<TechnologyKnowledge>("Technology Knowledge deleted succesfully.");
	}

	@Override
	public Result update(TechnologyKnowledge schoolDegree) {
		this.technologyKnowledgeDao.save(schoolDegree);
		return new SuccessDataResult<TechnologyKnowledge>("Technology Knowledge updated succesfully.");
	}

	
}
