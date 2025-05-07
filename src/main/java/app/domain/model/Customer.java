
package app.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class Customer {
    @Id
    //@GeneratedValue
    private Long id;
    private String name;
    private String emailOrDescription; // email for User, description for Product

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailOrDescription() {
        return emailOrDescription;
    }

    public void setEmailOrDescription(String emailOrDescription) {
        this.emailOrDescription = emailOrDescription;
    }
}
