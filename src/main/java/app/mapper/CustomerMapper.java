
package app.mapper;

import app.domain.dto.CustomerDTO;
import app.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerDTO dto);

    CustomerDTO toDTO(Customer entity);

    List<CustomerDTO> toDTOList(List<Customer> entities);
}
