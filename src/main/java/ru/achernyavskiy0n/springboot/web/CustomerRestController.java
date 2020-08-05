package ru.achernyavskiy0n.springboot.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.achernyavskiy0n.springboot.model.customer.Customer;
import ru.achernyavskiy0n.springboot.service.customer.CustomerService;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static ru.achernyavskiy0n.springboot.web.CustomerRestController.REST_URL;

/**
 * 02.08.2020
 *
 * @author a.chernyavskiy0n
 */
@RestController
@RequestMapping(REST_URL)
public class CustomerRestController {

    public static final String REST_URL = "/customers";

    private final CustomerService service;

    public CustomerRestController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/")
    List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{customerId}")
    Customer findById(@PathVariable @NotNull Integer customerId) {
        return service.findById(customerId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Customer> save(@RequestBody Customer customer) {
        Customer created = service.save(customer);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{customerId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    Customer save(@RequestBody Customer customer, @PathVariable Integer id) {
        return service.save(customer);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer customerId) {
        service.delete(customerId);
    }
}
