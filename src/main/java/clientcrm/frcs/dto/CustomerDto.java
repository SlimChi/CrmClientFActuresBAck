package clientcrm.frcs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @Project
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"idCustomer", "firstName", "lastName", "email", "phone"})
public class CustomerDto {

    private Long customerId;  // Correction : utiliser le nom 'customerId' au lieu de 'idCustomer'
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
