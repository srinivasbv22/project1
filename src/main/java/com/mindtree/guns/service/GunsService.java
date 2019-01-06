package com.mindtree.guns.service;

import java.util.List;

import com.mindtree.guns.entity.Guns;
import com.mindtree.guns.exception.DaoException;
import com.mindtree.guns.exception.ServiceException;

public interface GunsService {
	public String add(Guns gun);
	public String delete(String name);
	public Guns getById(String name);
	public List<Guns> getAll();
}
