package ru.achernyavskiy0n.springboot.repository.customer;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.model.customer.Customer;

import java.util.List;

import static ru.achernyavskiy0n.springboot.util.Util.unproxy;

/**
 * 31.07.2020
 *
 * @author a.chernyavskiy0n
 */
@Repository
@Transactional(readOnly = true)
public class DataJpaCustomerRepository implements CustomerRepository {

    private final CrudCustomerRepository crudCustomerRepository;

    public DataJpaCustomerRepository(CrudCustomerRepository crudCustomerRepository) {
        this.crudCustomerRepository = crudCustomerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return unproxy(crudCustomerRepository.findAll());
    }

    @Override
    public Customer findById(Integer customerId) {
        return unproxy(crudCustomerRepository.getOne(customerId));
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return unproxy(crudCustomerRepository.save(customer));
    }

    @Override
    public boolean delete(Integer customerId) {
        return crudCustomerRepository.delete(customerId) != 0;
    }
}
