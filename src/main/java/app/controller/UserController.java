
package app.controller;

import app.domain.dto.UserDTO;
import app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDTO save(@RequestBody UserDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<UserDTO> list() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
