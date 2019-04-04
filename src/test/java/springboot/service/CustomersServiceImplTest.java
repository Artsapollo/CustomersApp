package springboot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import springboot.dao.CustomersDaoImpl;
import springboot.entity.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomersServiceImplTest {
    @Spy
    @InjectMocks
    private CustomersService customersService = new CustomersServiceImpl();
    @Mock
    private CustomersDaoImpl customersDaoImpl;

    private Customer customer1 = new Customer();
    private Customer customer2 = new Customer();


    @Test
    public void testGetAllCustomers() {
        Set<Customer> customer = new HashSet<>(Arrays.asList(new Customer[]{customer1, customer2}));
        doReturn(customer).when(customersDaoImpl).getAllCustomers();
        Set<Customer> result = customersService.getAllCustomers();
        assertTrue(customer.containsAll(result) && result.containsAll(customer));
    }

    @Test
    public void testFindCustomerById() {
        doReturn(customer1).when(customersDaoImpl).findCustomerById(any());
        Customer result = customersService.findCustomerById(BigDecimal.TEN);
        assertEquals(customer1, result);
    }

    @Test
    public void testInsertCustomer() {
        doNothing().when(customersDaoImpl).insertCustomer(any());
        customersService.insertCustomer(customer1);
        verify(customersDaoImpl, times(1)).insertCustomer(any());
    }

    @Test
    public void testUpdateCustomer() {
        doNothing().when(customersDaoImpl).updateCustomer(any());
        customersService.updateCustomer(customer1);
        verify(customersDaoImpl, times(1)).updateCustomer(any());
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customersDaoImpl).deleteCustomer(any());
        customersService.deleteCustomer(customer1.getCustNum());
        verify(customersDaoImpl, times(1)).deleteCustomer(any());
    }
}