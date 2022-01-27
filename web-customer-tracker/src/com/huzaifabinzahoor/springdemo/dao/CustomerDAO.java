package com.huzaifabinzahoor.springdemo.dao;

import java.util.List;

import com.huzaifabinzahoor.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);

	public List<Customer> listBySorting(int theSortField);

}
