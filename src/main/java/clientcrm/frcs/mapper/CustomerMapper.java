package clientcrm.frcs.mapper;

import clientcrm.frcs.dto.CustomerDto;
import clientcrm.frcs.entities.Customer;


/**
 * @author
 * @Project
 */
public interface CustomerMapper {
    CustomerDto toCustomerDto(Customer customer);
    Customer toCustomer(CustomerDto customerDto);
}
