package app.service;

import app.domain.dto.OrderDTO;
import app.domain.model.Order;
import app.domain.model.OrderStatus;
import app.mapper.OrderMapper;
import app.repository.CustomerRepository;
import app.repository.OrderRepository;
import app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repository, CustomerRepository customerRepo,
                        ProductRepository productRepo, OrderMapper mapper) {
        this.repository = repository;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    public OrderDTO create(OrderDTO dto) {
        Order order = new Order();
        order.setCustomer(customerRepo.findById(dto.getCustomerId()).orElseThrow());
        order.setProducts(productRepo.findAllById(dto.getProductIds()));
        order.setStatus(OrderStatus.RECEBIDO);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(order.getProducts().stream()
                .map(p -> p.getPrice())
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        return mapper.toDTO(repository.save(order));
    }

    public List<OrderDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}
