package app.controller;

import app.domain.dto.OrderDTO;
import app.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<OrderDTO> listAll() {
        return service.findAll();
    }
}
