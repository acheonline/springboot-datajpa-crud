package ru.achernyavskiy0n.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.achernyavskiy0n.springboot.model.customer.Customer;
import ru.achernyavskiy0n.springboot.service.customer.CustomerService;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.achernyavskiy0n.springboot.TestUtil.CustomerTestData.*;
import static ru.achernyavskiy0n.springboot.TestUtil.json.JsonUtils.convertJsonToObject;
import static ru.achernyavskiy0n.springboot.TestUtil.json.JsonUtils.convertObjectToJson;

/**
 * 02.08.2020
 *
 * @author a.chernyavskiy0n
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:db/populateDB.sql",
        config = @SqlConfig(encoding = "UTF-8"))
public class CustomerRestControllerTest {
    private static final String URL = CustomerRestController.REST_URL + '/';

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CustomerService service;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    @Test
    public void findByIdTest() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + CUSTOMER_ID1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CUSTOMER_MATCHER.contentJson(CUSTOMER_1));
    }

    @Test
    public void findAllTest() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CUSTOMER_MATCHER.contentJson(CUSTOMERS));
    }

    @Test
    public void saveNewTest() throws Exception {
        Customer newCustomer = NEW_CUSTOMER;
        ResultActions action = perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(newCustomer)))
                .andDo(print())
                .andExpect(status().isCreated());

        Customer created = convertJsonToObject(Customer.class, action);
        int newId = Objects.requireNonNull(created).getId();
        newCustomer.setId(newId);
        CUSTOMER_MATCHER.assertMatch(created, newCustomer);
        CUSTOMER_MATCHER.assertMatch(service.findById(newId), newCustomer);
    }


    @Test
    public void update() throws Exception {
        Customer updated = UPDATED_CUSTOMER;
        perform(MockMvcRequestBuilders.put(URL + CUSTOMER_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        CUSTOMER_MATCHER.assertMatch(service.findById(CUSTOMER_ID1), updated);
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + CUSTOMER_ID1))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(service.findAll().size(), 2);
    }
}
