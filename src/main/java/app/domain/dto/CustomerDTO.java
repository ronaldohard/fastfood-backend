
package app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String emailOrDescription;

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
