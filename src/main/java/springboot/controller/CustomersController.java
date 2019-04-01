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


@RestController
@RequestMapping("/customer")
public class CustomersController {

    private static final Logger LOG = LogManager.getLogger(CustomersController.class);

    @Autowired
    private CustomersService customersService;

    @GetMapping
    public @ResponseBody String test(){
        return "Hello bastard!";
    }


    @GetMapping("/{id}")
    public @ResponseBody Customer getCustomerById (@PathVariable("id")BigDecimal id){
        LOG.info("getCustomerById started, id={}", id);
        Customer result = customersService.findCustomerById(id);
        LOG.info("getCustomerById done");
        return result;
    }

    @PostMapping
    public void addCustomer(@Valid @RequestBody Customer customerRequest) {
        LOG.info("Adding Customer, customerRequest={}", customerRequest);
        customersService.insertCustomer(customerRequest);
        LOG.info("Customer added");
    }

    @PutMapping("/{id}")
        public void updateCustomerById(@PathVariable("id") BigDecimal id, @RequestParam("cust_num") BigDecimal cust_num){
        LOG.info("Updating Customer, id={}, cust_num={}", id, cust_num);
        Customer customer = customersService.findCustomerById(id);
        if(Objects.isNull(customer)){
            LOG.warn("Can't update not existing customer");
        } else {
            customer.setCustNum(cust_num);
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
