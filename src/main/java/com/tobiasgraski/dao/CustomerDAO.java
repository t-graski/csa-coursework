package com.tobiasgraski.dao;

import com.tobiasgraski.exceptions.AuthorNotFoundException;
import com.tobiasgraski.exceptions.CustomerNotFoundException;
import com.tobiasgraski.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static final List<Customer> customers = new ArrayList<>();
    private static int currentId = 1;

    public List<Customer> findAll() {
        return customers;
    }

    public Customer findById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElseThrow(() -> new CustomerNotFoundException("No customer with Id " + id));
    }

    public Customer create(Customer customerDTO) {
        var customer = new Customer(getNextId(), customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(), customerDTO.getPassword());
        customers.add(customer);
        return customer;
    }

    public Customer update(int id, Customer customerDTO) {
        var customer = findById(id);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }

    public void delete(int id) {
        var success = customers.removeIf(c -> c.getId() == id);
        if (!success) throw new AuthorNotFoundException("No author with Id " + id);
    }

    private int getNextId() {
        return currentId++;
    }
}
