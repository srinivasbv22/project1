package com.mindtree.guns.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.guns.dao.GunsDao;
import com.mindtree.guns.entity.Guns;
import com.mindtree.guns.service.GunsService;

@Component
@Repository
public class GunsServiceImpl implements GunsService {

	@Autowired
	private GunsDao gunsDao;
	
	@Transactional
	public String add(Guns gun) {
		return gunsDao.add(gun);
	}
	
	@Transactional(readOnly = false)
	public String delete(String name) {
		return gunsDao.delete(name);
	}

	public Guns getById(String name) {
		return gunsDao.getById(name);
	}

	public List<Guns> getAll() {
		return gunsDao.getAll();
	}

}
