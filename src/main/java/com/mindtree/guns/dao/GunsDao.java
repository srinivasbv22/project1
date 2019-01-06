package com.mindtree.guns.dao;

import java.util.List;

import com.mindtree.guns.entity.Guns;

public interface GunsDao {
	public String add(Guns gun);
	public String delete(String name);
	public Guns getById(String name);
	public List<Guns> getAll();

}
