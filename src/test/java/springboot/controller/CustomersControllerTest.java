package springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import springboot.entity.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:springboot/test.properties")
public class CustomersControllerTest {
    private static final int STATUS_OK = 200;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        Resource initSchema = new ClassPathResource("springboot\\script\\schema.sql");
        Resource data = new ClassPathResource("springboot\\script\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testGetAllCustomers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/customer")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
        List<Customer> customers = getListCustomersFromResult(mvcResult);
        assertEquals(2, customers.size());
    }

    @Test
    public void testGetCustomerByIdExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/customer/{id}", "1111")).andDo(print()).andReturn();
        Customer customer = mapper.readValue(mvcResult.getResponse().getContentAsString(), Customer.class);
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertNotNull(customer);
    }

    @Test
    public void testGetCustomerByIdNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/customer/{id}", "-11")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().length() == 0);
    }

    @Test
    public void testAddCustomer() throws Exception {
        String json = mapper.writeValueAsString(new Customer());
        MvcResult mvcResult = mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/customer/{id}", "1111").param("company", "Wayne Entr.")).andDo(print())
                .andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/customer/{id}", "1111")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }


    private List<Customer> getListCustomersFromResult(MvcResult mvcResult)
            throws IOException, JsonParseException, JsonMappingException, UnsupportedEncodingException {
        return mapper.readValue(mvcResult.getResponse().getContentAsString(),
                mapper.getTypeFactory().constructCollectionType(List.class, Customer.class));
    }
}
