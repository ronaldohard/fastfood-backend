
package app.service;

import app.domain.dto.ProductDTO;
import app.domain.model.Product;
import app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO save(ProductDTO dto) {
        Product obj = new Product();
        obj.setName(dto.getName());
        Product saved = repository.save(obj);
        dto.setId(saved.getId());
        return dto;
    }

    public List<ProductDTO> findAll() {
        return repository.findAll().stream().map(o -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(o.getId());
            dto.setName(o.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
