package app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class OrderDTO {
    private Long id;
    private Long customerId;
    private List<Long> productIds;
    private String status;
    private LocalDateTime createdAt;
    private BigDecimal total;
}
