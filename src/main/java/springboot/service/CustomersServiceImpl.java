package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dao.CustomersDao;


import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.Customer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
public class CustomersServiceImpl implements CustomersService {

    private static final Logger LOG = LogManager.getLogger(CustomersService.class);

    @Autowired
    private CustomersDao customersDao;

    @Override
    public Set<Customer> getAllCustomers() {
        LOG.debug("getAllCustomers");
        HashSet<Customer> result = new HashSet<>(customersDao.getAllCustomers());
        LOG.debug("getAllCustomers return {} records", result.size());
        return result;
    }

    @Override
    public Customer findCustomerById(BigDecimal id) {
        LOG.debug("findCustomerById, id={}", id);
        Customer result = customersDao.findCustomerById(id);
        LOG.debug("findCustomersById, result={}", result);
        return result;
    }

    @Override
    public void insertCustomer(Customer customer) {
        LOG.debug("insertCustomer, customer={}", customer);
        customersDao.insertCustomer(customer);
        LOG.debug("Customer inserted");
    }

    @Override
    public void updateCustomer(Customer customer) {
        LOG.debug("updateCustomer, customer={}", customer);
        customersDao.updateCustomer(customer);
        LOG.debug("Customer updated");
    }

    @Override
    public void deleteCustomer(BigDecimal id) {
        LOG.debug("deleteCustomer, id={}", id);
        customersDao.deleteCustomer(id);
        LOG.debug("Customer deleted");
    }
}