package com.mindtree.guns.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mindtree.guns.dao.GunsDao;
import com.mindtree.guns.entity.Guns;
@Repository
public class GunsDaoImpl implements GunsDao  {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public String add(Guns gun){
		hibernateTemplate.save(gun);
		return "Success";
	}

	public String delete(String name) {
		hibernateTemplate.delete(getById(name));
		return null;
	}

	public Guns getById(String name) {
		return hibernateTemplate.get(Guns.class,name);
	}

	public List<Guns> getAll() {
		return hibernateTemplate.loadAll(Guns.class);
	}

}
