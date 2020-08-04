package ru.achernyavskiy0n.springboot.service.customer;

import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.SpringBootStarterExample;
import ru.achernyavskiy0n.springboot.model.customer.Customer;
import ru.achernyavskiy0n.springboot.service.customer.CustomerService;

import java.util.Arrays;
import java.util.List;

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
        Customer customer = service.findById(100002);
        Customer expected = new Customer(100002, "God");
        Assert.assertEquals(expected, customer);
    }

    @Test
    public void save() throws Exception {
        Customer newCustomer = new Customer(100003, "Anton");
        Customer created = service.save(newCustomer);
        int newId = created.getId();
        newCustomer.setId(newId);
        Assert.assertEquals(newCustomer, service.findById(100003));
    }

    @Test
    public void update() throws Exception {
        Customer updated = new Customer(100000, "Anton");
        service.save(updated);
        Assert.assertEquals(updated, service.findById(100000));
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(
                service.findAll(),
                List.of(
                        new Customer(100000, "User"),
                        new Customer(100001, "Admin"),
                        new Customer(100002, "God")
                )
        );
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        service.delete(100000);
        Assert.assertEquals(service.findAll().size(), 2);
    }
}