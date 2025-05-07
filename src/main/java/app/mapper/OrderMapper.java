package app.mapper;

import app.domain.dto.OrderDTO;
import app.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);

    Order toEntity(OrderDTO dto);
}
