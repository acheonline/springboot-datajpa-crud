package ru.achernyavskiy0n.springboot.model.customer;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.achernyavskiy0n.springboot.model.order.Order;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static java.util.Objects.hash;

@Entity
@Access(AccessType.FIELD)
@Table(name = "customers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"}, name = "customers_name_idx")})
public class Customer {

    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @NotNull
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
    @OrderBy("dateTime DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders;

    public Customer() {
    }

    public Customer(@NotBlank @Size(min = 2, max = 120) String name) {
        this.name = name;
    }

    public Customer(@NotNull Integer id, @NotBlank @Size(min = 2, max = 120) String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s']",
                id, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return (id.equals(customer.id)) && name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return hash(id, name);
    }
}
