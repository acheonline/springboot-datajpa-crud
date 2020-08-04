package ru.achernyavskiy0n.springboot.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.achernyavskiy0n.springboot.model.customer.Customer;


/**
 * 31.07.2020
 *
 * @author a.chernyavskiy0n
 */
@Repository
@Transactional(readOnly = true)
public interface CrudCustomerRepository extends JpaRepository<Customer, Integer> {

    @Modifying
    @Query("delete from Customer c where c.id=:id")
    int delete(@Param("id") Integer id);

}
