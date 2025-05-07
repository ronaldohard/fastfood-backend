
package app.controller;

import app.domain.dto.ProductDTO;
import app.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductDTO save(@RequestBody ProductDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<ProductDTO> list() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
