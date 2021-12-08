package com.greatlearning.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.crm.entity.Customer;
import com.greatlearning.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@RequestMapping("/list")
	public String getCustomerList(Model model){
		List<Customer> customers = customerservice.listAll();
		model.addAttribute("customers", customers);
		return "list-Customers";
	}
	
	@RequestMapping("/showFormForAdd")
	public String addCustomer(Model model){
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-Form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int id, Model model) {
		Customer customer = new Customer();	
		customer = customerservice.findById(id);
		model.addAttribute("customer", customer);
		return "customer-Form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, 
			@RequestParam("firstName") String first, 
			@RequestParam("lastName") String last, 
			@RequestParam("email") String email) {
		
			System.out.println(email);
			Customer customer;
		
			if(id!=0) {
				customer = customerservice.findById(id);
				customer.setFirstName(first);
				customer.setLastName(last);
				customer.setEmail(email);
			} else {
			customer = new Customer(first, last, email );
		}
			customerservice.save(customer);
			return "redirect:/customer/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerservice.deleteById(id);
		return "redirect:/customer/list";
		
	}
	
	

}
