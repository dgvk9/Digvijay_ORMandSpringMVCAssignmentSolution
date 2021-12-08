package com.greatlearning.crm.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.crm.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	private SessionFactory sessionfactory;
	private Session session;
	
	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionfactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
	}
	
	@Transactional
	@Override
	public List<Customer> listAll() {
		Transaction transaction = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();
		transaction.commit();

		return customers;
	}

	@Override
	public void save(Customer customer) {
		
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(customer);
		transaction.commit();
	}

	@Override
	public void deleteById(int id) {
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class,id);
		session.delete(customer);
		transaction.commit();
		
	}

	@Override
	public Customer findById(int id) {
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		transaction.commit();
		return customer;
	}

}
