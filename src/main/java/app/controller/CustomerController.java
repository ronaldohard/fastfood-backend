
package app.controller;

import app.domain.dto.CustomerDTO;
import app.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDTO save(@RequestBody CustomerDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<CustomerDTO> list() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
