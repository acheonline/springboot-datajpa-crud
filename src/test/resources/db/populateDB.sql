DELETE FROM orders;
DELETE FROM customers;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO customers (id, name)
VALUES (100000, 'User'),
       (100001, 'Admin'),
       (100002, 'God');

INSERT INTO orders (id, date_time, customer_id)
VALUES (100000, '2020-08-01 10:00:00', 100000),
       (100001, '2020-08-01 11:00:00', 100000),
       (100002, '2020-08-01 12:00:00', 100001),
       (100003, '2020-08-02 10:00:00', 100001),
       (100004, '2020-08-02 11:00:00', 100001),
       (100005, '2020-08-03 12:00:00', 100002);
