package ru.achernyavskiy0n.springboot.repository.customer;

import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.model.customer.Customer;

import java.util.List;

/**
 * 01.08.2020
 *
 * @author a.chernyavskiy0n
 */
@Transactional(readOnly = true)
public interface CustomerRepository{
    List<Customer> findAll();

    Customer findById(Integer customerId);

    Customer save(Customer customer);

    boolean delete(Integer customerId);
}
