package com.sk.spring.service;

import java.util.List;

import com.sk.spring.entity.Customer;

public interface ICustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);
}
