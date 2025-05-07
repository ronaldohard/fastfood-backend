
package app.service;

import app.domain.dto.UserDTO;
import app.domain.model.User;
import app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTO save(UserDTO dto) {
        User obj = new User();
        obj.setName(dto.getName());
        obj.setEmail(dto.getEmail());
        User saved = repository.save(obj);
        dto.setId(saved.getId());
        return dto;
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(o -> {
            UserDTO dto = new UserDTO();
            dto.setId(o.getId());
            dto.setName(o.getName());
            dto.setEmail(o.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
