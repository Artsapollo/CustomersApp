package springboot.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.entity.Customer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CustomersDaoImpl implements CustomersDao {

    private Session sessionObj;

    private SessionFactory sessionFactoryObj;

    private static final Logger LOG = LogManager.getLogger(CustomersDaoImpl.class);

    @Autowired
    public CustomersDaoImpl(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactoryObj = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Set<Customer> getAllCustomers() {
        Set<Customer> customers = new HashSet<>();
        try {
            sessionObj = sessionFactoryObj.openSession();
            customers = new HashSet<>(sessionObj.createQuery("from Customer", Customer.class).list());
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(BigDecimal id) {
        Customer customer = null;
        try{
            sessionObj = sessionFactoryObj.openSession();
            customer = sessionObj.get(Customer.class, id);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
        return customer;
    }

    @Override
    public void insertCustomer(Customer customer) {
        try{
            sessionObj = sessionFactoryObj.openSession();
            sessionObj.beginTransaction();

            sessionObj.save(customer);

            sessionObj.getTransaction().commit();
            LOG.info("Customer was successfully added");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                LOG.info(".......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try{
            sessionObj = sessionFactoryObj.openSession();
            sessionObj.beginTransaction();
            sessionObj.update(customer);
            sessionObj.getTransaction().commit();
            LOG.info("Customer was successfully updated", customer.getCustNum());
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                LOG.info(".......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    @Override
    public void deleteCustomer(BigDecimal id) {
        try{
            sessionObj = sessionFactoryObj.openSession();
            sessionObj.beginTransaction();
            sessionObj.delete(sessionObj.get(Customer.class, id));
            sessionObj.getTransaction().commit();
            LOG.info("Customer was successfully deleted", id);
        }  catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                LOG.info(".......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}