package clientcrm.frcs.mapper.mapperImpl;

import clientcrm.frcs.dto.CustomerDto;
import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;
import clientcrm.frcs.entities.User;
import clientcrm.frcs.mapper.CustomerMapper;
import clientcrm.frcs.mapper.InvoiceMapper;
import clientcrm.frcs.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author
 * @Project
 */
@Component
@RequiredArgsConstructor
public class CustomerMapperImpl implements CustomerMapper {


    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getIdCustomer())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setIdCustomer(customerDto.getCustomerId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }
}
