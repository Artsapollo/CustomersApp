package springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.entity.Customer;
import springboot.service.CustomersService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping("/customer")
public class CustomersController {

    private static final Logger LOG = LogManager.getLogger(CustomersController.class);

    @Autowired
    private CustomersService customersService;

    @GetMapping
    @ResponseBody
    public Set<Customer> getAllCustomers(){
        LOG.info("Getting all customers");
        Set<Customer> result = customersService.getAllCustomers();
        LOG.info("Successfully got all customers");
        return result;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Customer getCustomerById (@PathVariable("id")BigDecimal id){
        LOG.info("getCustomerById started, id={}", id);
        Customer customer = customersService.findCustomerById(id);
        LOG.info("getCustomerById done");
        return customer;
    }

    @PostMapping
    public void addCustomer(@Valid @RequestBody Customer customerRequest) {
        LOG.info("Adding Customer, customerRequest={}", customerRequest);
        customersService.insertCustomer(customerRequest);
        LOG.info("Customer added");
    }

    @PutMapping("/{id}")
        public void updateCustomerById(@PathVariable("id") BigDecimal id, @RequestParam("company") String company){
        LOG.info("Updating Customer, id={}, company={}", id, company);
        Customer customer = customersService.findCustomerById(id);
        if(Objects.isNull(customer)){
            LOG.warn("Can't update not existing customer");
        } else {
            customer.setCompany(company);
            customersService.updateCustomer(customer);
        }
        LOG.info("Customer updated");
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") BigDecimal id){
        LOG.info("Deleting Customer By Id, id={}", id);
        customersService.deleteCustomer(id);
        LOG.info("Customer deleted");
    }
}
