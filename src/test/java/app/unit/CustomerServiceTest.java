
package app.unit;

import app.domain.dto.CustomerDTO;
import app.domain.model.Customer;
import app.repository.CustomerRepository;
import app.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(CustomerRepository.class);
        service = new CustomerService(repository);
    }

    @Test
    void testSave() {
        CustomerDTO dto = new CustomerDTO();
        dto.setName("Test");
        dto.setEmailOrDescription("test@test.com");

        when(repository.save(any())).thenAnswer(i -> {
            Customer e = i.getArgument(0);
            e.setId(1L);
            return e;
        });

        CustomerDTO saved = service.save(dto);
        assertNotNull(saved.getId());
        assertEquals("Test", saved.getName());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.findAll().isEmpty());
    }
}
