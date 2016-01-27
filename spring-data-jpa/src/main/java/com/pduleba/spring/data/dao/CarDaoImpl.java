package com.pduleba.spring.data.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.jpa.model.CarModel;

@Repository
@Transactional
public class CarDaoImpl extends HibernateDaoSupport implements CarDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);
	
	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void create(CarModel car) {
		getHibernateTemplate().save(car);
	}

	@Override
	public CarModel read(long carId) {
		return getHibernateTemplate().get(CarModel.class, carId);
	}

	@Override
	public void update(CarModel car) {
		HibernateTemplate template = getHibernateTemplate();
		template.update(template.contains(car) ? car : template.merge(car));
	}

	@Override
	public void delete(CarModel car) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(template.contains(car) ? car : template.merge(car));
	}
}
