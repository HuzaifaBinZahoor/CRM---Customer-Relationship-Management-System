package com.huzaifabinzahoor.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huzaifabinzahoor.springdemo.dao.CustomerDAO;
import com.huzaifabinzahoor.springdemo.entity.Customer;
import com.huzaifabinzahoor.springdemo.service.CustomerService;
import com.huzaifabinzahoor.springdemo.utility.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Inject the Customer dao
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get customer from dao
		List<Customer> theCustomers = customerService.getCustomers();
		// add those customers to spring MVC model
		theModel.addAttribute("customer", theCustomers);

		return "list-customer";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			return "customer-form";
		} else {
			customerService.saveCustomer(theCustomer);
			return "redirect:/customer/list";
		}

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		// get the customer from service
		Customer theCustomer = customerService.getCustomer(theId);

		// set the customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);

		// send over to our form

		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		// delete the customer
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}

	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

		// add the customers to the model
		theModel.addAttribute("customer", theCustomers);
		return "list-customer";
	}

	@GetMapping("/listBySorting")
	public String listBySorting(Model theModel, @RequestParam(required = false) String sort) {

		// get customers from the service
		List<Customer> theCustomers = null;

		// check for sort field
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.listBySorting(theSortField);
		} else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.listBySorting(SortUtils.LAST_NAME);
		}

		// add the customers to the model
		theModel.addAttribute("customer", theCustomers);

		return "list-customer";
	}

}
