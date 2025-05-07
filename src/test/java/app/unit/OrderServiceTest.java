package app.unit;

import app.mapper.OrderMapper;
import app.repository.CustomerRepository;
import app.repository.OrderRepository;
import app.repository.ProductRepository;
import app.service.OrderService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class OrderServiceTest {

    @Test
    void testCreate() {
        OrderRepository orderRepo = mock(OrderRepository.class);
        CustomerRepository customerRepo = mock(CustomerRepository.class);
        ProductRepository productRepo = mock(ProductRepository.class);
        OrderMapper mapper = mock(OrderMapper.class);

        OrderService service = new OrderService(orderRepo, customerRepo, productRepo, mapper);
        // Use DTO simulando dados e valide que a chamada ao save() foi feita.
    }
}
