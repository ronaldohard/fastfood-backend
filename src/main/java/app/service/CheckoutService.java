package app.service;

import app.domain.dto.OrderDTO;
import app.domain.model.Order;
import app.domain.model.OrderStatus;
import app.domain.model.Product;
import app.mapper.OrderMapper;
import app.repository.CustomerRepository;
import app.repository.OrderRepository;
import app.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CheckoutService {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;
    private final OrderMapper mapper;

    public CheckoutService(OrderRepository orderRepo,
                           CustomerRepository customerRepo,
                           ProductRepository productRepo,
                           OrderMapper mapper) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    public OrderDTO checkout(OrderDTO dto) {
        Order order = new Order();
        order.setCustomer(customerRepo.findById(dto.getCustomerId()).orElseThrow());
        order.setProducts(productRepo.findAllById(dto.getProductIds()));
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.RECEBIDO);
        BigDecimal total = order.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal(total);
        Order saved = orderRepo.save(order);
        return mapper.toDTO(saved);
    }
}