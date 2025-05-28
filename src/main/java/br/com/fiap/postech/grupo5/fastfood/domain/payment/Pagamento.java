package br.com.fiap.postech.grupo5.fastfood.domain.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamento_id_seq")
    @SequenceGenerator(name = "pagamento_id_seq", sequenceName = "pagamento_id_seq", allocationSize = 1)
    private Long id;
    private Long tipoPagamentoId;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private String status;
    private String qrCodeUrl;

}
