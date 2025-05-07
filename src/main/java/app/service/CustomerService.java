
package app.service;

import app.domain.dto.CustomerDTO;
import app.domain.model.Customer;
import app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerDTO save(CustomerDTO dto) {
        Customer obj = new Customer();
        obj.setName(dto.getName());
        obj.setEmailOrDescription(dto.getEmailOrDescription());
        Customer saved = repository.save(obj);
        dto.setId(saved.getId());
        return dto;
    }

    public List<CustomerDTO> findAll() {
        return repository.findAll().stream().map(o -> {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(o.getId());
            dto.setName(o.getName());
            dto.setEmailOrDescription(o.getEmailOrDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
