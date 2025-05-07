package app.mapper;

import app.domain.dto.ProductDTO;
import app.domain.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T21:25:25-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( dto.getId() );
        product.name( dto.getName() );
        product.category( dto.getCategory() );
        product.price( dto.getPrice() );
        product.description( dto.getDescription() );
        List<String> list = dto.getDefaultIngredients();
        if ( list != null ) {
            product.defaultIngredients( new ArrayList<String>( list ) );
        }
        List<String> list1 = dto.getOptionalIngredients();
        if ( list1 != null ) {
            product.optionalIngredients( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = dto.getCustomizationOptions();
        if ( list2 != null ) {
            product.customizationOptions( new ArrayList<String>( list2 ) );
        }

        return product.build();
    }

    @Override
    public ProductDTO toDTO(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.id( entity.getId() );
        productDTO.name( entity.getName() );
        productDTO.category( entity.getCategory() );
        productDTO.price( entity.getPrice() );
        productDTO.description( entity.getDescription() );
        List<String> list = entity.getDefaultIngredients();
        if ( list != null ) {
            productDTO.defaultIngredients( new ArrayList<String>( list ) );
        }
        List<String> list1 = entity.getOptionalIngredients();
        if ( list1 != null ) {
            productDTO.optionalIngredients( new ArrayList<String>( list1 ) );
        }
        List<String> list2 = entity.getCustomizationOptions();
        if ( list2 != null ) {
            productDTO.customizationOptions( new ArrayList<String>( list2 ) );
        }

        return productDTO.build();
    }

    @Override
    public List<ProductDTO> toDTOList(List<Product> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( entities.size() );
        for ( Product product : entities ) {
            list.add( toDTO( product ) );
        }

        return list;
    }
}
