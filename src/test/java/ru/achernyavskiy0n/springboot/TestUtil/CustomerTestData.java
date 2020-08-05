package ru.achernyavskiy0n.springboot.TestUtil;

import ru.achernyavskiy0n.springboot.model.customer.Customer;

import java.util.List;

import static ru.achernyavskiy0n.springboot.model.customer.Customer.START_SEQ;

/**
 * 04.08.2020
 *
 * @author a.chernyavskiy0n
 */
public class CustomerTestData {
    public static TestMatcher<Customer> CUSTOMER_MATCHER = TestMatcher.usingEquals(Customer.class);

    public static final int CUSTOMER_ID1 = START_SEQ;
    public static final int CUSTOMER_ID2 = START_SEQ + 1;
    public static final int CUSTOMER_ID3 = START_SEQ + 2;

    public static final Customer CUSTOMER_1 = new Customer(CUSTOMER_ID1, "User");
    public static final Customer CUSTOMER_2 = new Customer(CUSTOMER_ID2, "Admin");
    public static final Customer CUSTOMER_3 = new Customer(CUSTOMER_ID3, "God");

    public static final List<Customer> CUSTOMERS = List.of(CUSTOMER_1, CUSTOMER_2, CUSTOMER_3);

    public static final Customer NEW_CUSTOMER = new Customer(100003, "new_customer");
    public static final int NEW_CUSTOMER_ID = START_SEQ + 3;

    public static final Customer UPDATED_CUSTOMER = new Customer(CUSTOMER_ID1, "updated");

}
