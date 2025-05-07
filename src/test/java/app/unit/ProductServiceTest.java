
package app.unit;

import app.domain.dto.ProductDTO;
import app.domain.model.Product;
import app.repository.ProductRepository;
import app.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService service;
    private ProductRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void testSave() {
        ProductDTO dto = new ProductDTO();
        dto.setName("Test");

        when(repository.save(any())).thenAnswer(i -> {
            Product e = i.getArgument(0);
            e.setId(1L);
            return e;
        });

        ProductDTO saved = service.save(dto);
        assertNotNull(saved.getId());
        assertEquals("Test", saved.getName());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.findAll().isEmpty());
    }
}
