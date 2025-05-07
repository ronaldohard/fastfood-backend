
package app.domain.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class Product {
    @Id
    // @GeneratedValue
    private Long id;
    private String name;
    private String category; // LANCHE, BEBIDA, SOBREMESA
    private BigDecimal price;
    private String description;

    @ElementCollection
    private List<String> defaultIngredients;

    @ElementCollection
    private List<String> optionalIngredients;

    @ElementCollection
    private List<String> customizationOptions;
}
