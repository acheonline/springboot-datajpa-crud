package ru.achernyavskiy0n.springboot.service.customer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.model.customer.Customer;

import static ru.achernyavskiy0n.springboot.TestUtil.CustomerTestData.*;

/**
 * 31.07.2020
 *
 * @author a.chernyavskiy0n
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = "classpath:/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CustomerServiceTest {

    @Autowired
    CustomerService service;

    @Test
    public void findById() {
        Customer customer = service.findById(CUSTOMER_ID3);
        Assert.assertEquals(CUSTOMER_3, customer);
    }

    @Test
    public void save() throws Exception {
        Customer newCustomer = NEW_CUSTOMER;
        Customer created = service.save(newCustomer);
        int newId = created.getId();
        newCustomer.setId(newId);
        Assert.assertEquals(newCustomer, service.findById(NEW_CUSTOMER_ID));
    }

    @Test
    public void update() throws Exception {
        Customer updated = UPDATED_CUSTOMER;
        service.save(updated);
        Assert.assertEquals(updated, service.findById(CUSTOMER_ID1));
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(service.findAll(), CUSTOMERS);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        service.delete(CUSTOMER_ID1);
        Assert.assertEquals(service.findAll().size(), 2);
    }
}