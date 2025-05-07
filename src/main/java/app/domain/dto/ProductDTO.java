
package app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class ProductDTO {
    private Long id;
    private String name;

    private String category; // LANCHE, BEBIDA, SOBREMESA
    private BigDecimal price;
    private String description;

    private List<String> defaultIngredients;
    private List<String> optionalIngredients;
    private List<String> customizationOptions;

}
