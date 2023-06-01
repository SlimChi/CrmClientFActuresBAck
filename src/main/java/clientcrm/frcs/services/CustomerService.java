package clientcrm.frcs.services;

import clientcrm.frcs.dto.CustomerDto;
import clientcrm.frcs.dto.InvoiceDto;

import java.util.List;

/**
 * @author
 * @Project
 */
public interface CustomerService {

    List<CustomerDto> getCustomers();

    List<InvoiceDto> getCustomerInvoices(Long userId);

    CustomerDto createCustomer(CustomerDto customerDto);

    void deleteCustomer(Long customerId);

    CustomerDto updateCustomer(Long customerId, CustomerDto updatedCustomerDto);
}
