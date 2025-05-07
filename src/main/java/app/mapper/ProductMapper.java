
package app.mapper;

import app.domain.dto.ProductDTO;
import app.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product entity);

    List<ProductDTO> toDTOList(List<Product> entities);
}
