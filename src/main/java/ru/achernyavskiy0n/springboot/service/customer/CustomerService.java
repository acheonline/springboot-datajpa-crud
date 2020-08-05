package ru.achernyavskiy0n.springboot.service.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.model.customer.Customer;
import ru.achernyavskiy0n.springboot.repository.customer.CustomerRepository;

import java.util.List;

/**
 * 01.08.2020
 *
 * @author a.chernyavskiy0n
 */
@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Cacheable("customers")
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

    @CacheEvict(value = "customers", allEntries = true)
    @Transactional
    public Customer save(Customer customer) {
        log.debug("Save customer " + customer);
        return repository.save(customer);
    }

    @Transactional
    public void delete(Integer customerId) {
        log.debug("Delete id " + customerId);
        repository.delete(customerId);
    }
}
