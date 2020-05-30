package com.sk.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.spring.entity.Customer;
import com.sk.spring.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(Model model) {
		System.out.println("inside searchCustomer");
		
		List<Customer> customerList = customerService.getCustomers();

		model.addAttribute("customerList", customerList);
		
		return "list-customers";
	}
	
	
	@GetMapping("/list")
	public String listCustomer(Model model) {
		System.out.println("inside list customer");
		
		List<Customer> customerList = customerService.getCustomers();
		
		model.addAttribute("customerList", customerList);
		
		return "list-customers";
	}
	
	
	@GetMapping("/showCustomerAddForm")
	public String showCustomerAddForm(Model model) {
		
		//bind form data to spring model
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {

		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showCustomerUpdateForm")
	public String showCustomerUpdateForm(@RequestParam("customerId") int customerId, Model model) {
		
		Customer customer = customerService.getCustomer(customerId); 
		model.addAttribute("customer",customer);
		return "customer-form";
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {

		customerService.deleteCustomer(customerId);
		return "redirect:/customer/list";
	}
	
	
}
