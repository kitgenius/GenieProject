package com.genie.dao;

import java.io.Serializable;
import java.util.List;

/*author:Genie
 *date:2017年4月11日
**/
public interface GenericDao<T, PK extends Serializable> {
	T findById(PK id);

	List<T> findByExample(T entity);

	List<T> findAll();

	void persist(T entity);

	void attachDirty(T entity); //对象转为持久状态
	
	void attachClean(T entity); //对象转为瞬时状态

	void delete(T entity);
	
	T merge(T entity); //saveOrUpdate
}
