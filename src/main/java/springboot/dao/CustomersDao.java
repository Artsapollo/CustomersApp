package springboot.dao;

import springboot.entity.Customer;

import java.math.BigDecimal;
import java.util.Set;

public interface CustomersDao {

    Set<Customer> getAllCustomers();

    Customer findCustomerById(BigDecimal id);

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(BigDecimal id);
}
