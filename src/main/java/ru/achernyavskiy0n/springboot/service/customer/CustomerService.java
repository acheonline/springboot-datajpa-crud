package ru.achernyavskiy0n.springboot.service.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.model.customer.Customer;
import ru.achernyavskiy0n.springboot.repository.customer.CustomerRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 01.08.2020
 *
 * @author a.chernyavskiy0n
 */
@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository repository;

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        log.info("getAll");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findById(Integer customerId) {
        log.debug("findById id " + customerId);
        return repository.findById(customerId);
    }

    @Transactional
    public Customer save(@NotNull Customer customer) {
        log.debug("Save customer " + customer);
        return repository.save(customer);
    }

    @Transactional
    public void delete(Integer customerId) {
        log.debug("Delete id " + customerId);
        repository.delete(customerId);
    }
}
