package com.greatlearning.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.crm.entity.Customer;

@Service
public interface CustomerService {
	
	public List<Customer> listAll();
	public void save(Customer customer);
	public void deleteById(int id);	
	public Customer findById(int id);


}
