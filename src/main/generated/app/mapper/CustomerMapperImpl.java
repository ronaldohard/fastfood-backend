package app.mapper;

import app.domain.dto.CustomerDTO;
import app.domain.model.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T18:47:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Microsoft)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( dto.getId() );
        customer.setName( dto.getName() );
        customer.setEmailOrDescription( dto.getEmailOrDescription() );

        return customer;
    }

    @Override
    public CustomerDTO toDTO(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( entity.getId() );
        customerDTO.setName( entity.getName() );
        customerDTO.setEmailOrDescription( entity.getEmailOrDescription() );

        return customerDTO;
    }

    @Override
    public List<CustomerDTO> toDTOList(List<Customer> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( entities.size() );
        for ( Customer customer : entities ) {
            list.add( toDTO( customer ) );
        }

        return list;
    }
}
