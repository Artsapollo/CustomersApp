package springboot.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import springboot.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:springboot/test.properties")
public class CustomersDaoImplTest {

    private static final Customer INSERT_CUSTOMER = new Customer(BigDecimal.valueOf(3333));
    private static final Customer UPDATE_CUSTOMER = new Customer(BigDecimal.valueOf(1111));
    private static final Customer DELETE_CUSTOMER = new Customer(BigDecimal.valueOf(3333));

    @Autowired
    private CustomersDaoImpl customersDaoImpl;

    @Autowired
    private DataSource dataSource;

    @Before
    public void initDb() {
        Resource initSchema = new ClassPathResource("springboot\\script\\schema.sql");
        Resource data = new ClassPathResource("springboot\\script\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }


    @Test
    public void testGetAllCustomers() {
        Set<Customer> customer = customersDaoImpl.getAllCustomers();
        assertTrue(customer.size() >= 2);
    }


    @Test
    public void testFindCustomerById() {
        Customer customer = customersDaoImpl.findCustomerById(UPDATE_CUSTOMER.getCustNum());
        assertNotNull(customer);
    }

    @Test
    public void testInsertCustomer() {
        customersDaoImpl.insertCustomer(INSERT_CUSTOMER);
    }

    @Test
    public void testUpdateCustomer() {
        UPDATE_CUSTOMER.setCompany("Bobo");
        UPDATE_CUSTOMER.setCreditLimit(BigDecimal.valueOf(55555));
        customersDaoImpl.updateCustomer(UPDATE_CUSTOMER);
    }

    @Test
    public void testDeleteCustomer() {
        customersDaoImpl.deleteCustomer(INSERT_CUSTOMER.getCustNum());
    }

}
