package com.sk.spring.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sk.spring.entity.Customer;

@Service
public class CustomerServiceRestClientImpl implements ICustomerService {


	private RestTemplate restTemplate;

	@Value("${crm.rest.url}")
	private String crmRestUrl;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	public CustomerServiceRestClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		logger.info("  loaded property value :: "+crmRestUrl);
	}

	@Override
	public List<Customer> getCustomers() {

		logger.info("inside getCustomers: Calling REST API #"+crmRestUrl);
		
		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(crmRestUrl, HttpMethod.GET,
								null,new ParameterizedTypeReference<List<Customer>>() {
		});
		
		//get the list from response
		List<Customer> customers = responseEntity.getBody();
		
		logger.info("Customer List ## "+customers);
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		logger.info("inside saveCustomer: Calling REST API #"+crmRestUrl);
		
		int customerId = customer.getId();
		
		if(customerId == 0) {
			//insert new customer
			restTemplate.postForEntity(crmRestUrl, customer, String.class);
			logger.info("Customer SAVED successfully");
		} else {
			//update existing customer
			restTemplate.put(crmRestUrl, customer);
			logger.info("Customer UPDATED successfully");
		}
		
		
	}

	@Override
	public Customer getCustomer(int customerId) {

		logger.info("inside getCustomer: Calling REST API #"+crmRestUrl);
		
		Customer customer = restTemplate.getForObject(crmRestUrl + "/" + customerId, Customer.class);
		
		logger.info("Customer Info ## "+customer);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {

		logger.info("inside deleteCustomer: Calling REST API #"+crmRestUrl);
		
		Customer customer = restTemplate.getForObject(crmRestUrl + "/" + customerId, Customer.class);
		
		if(customer == null) {
			logger.info("Customer DOES NOT EXISTS");
		} else {
			restTemplate.delete(crmRestUrl + "/" + customerId);
			
			logger.info("Customer DELETED Successfully!");	
		}
		
		
	}
	

}
