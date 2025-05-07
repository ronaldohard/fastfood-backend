
package app.unit;

import app.domain.dto.UserDTO;
import app.domain.model.User;
import app.repository.UserRepository;
import app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService service;
    private UserRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    void testSave() {
        UserDTO dto = new UserDTO();
        dto.setName("Test");
        dto.setEmail("test@test.com");

        when(repository.save(any())).thenAnswer(i -> {
            User e = i.getArgument(0);
            e.setId(1L);
            return e;
        });

        UserDTO saved = service.save(dto);
        assertNotNull(saved.getId());
        assertEquals("Test", saved.getName());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.findAll().isEmpty());
    }
}
