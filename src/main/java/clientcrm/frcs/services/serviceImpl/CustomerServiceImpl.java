package clientcrm.frcs.services.serviceImpl;

import clientcrm.frcs.dto.CustomerDto;
import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;
import clientcrm.frcs.mapper.CustomerMapper;
import clientcrm.frcs.mapper.InvoiceMapper;
import clientcrm.frcs.repositories.CustomerRepository;
import clientcrm.frcs.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 * @Project
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final InvoiceMapper invoiceMapper;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getCustomerInvoices(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Client introuvable"));
        List<Invoice> invoices = customer.getInvoices();
        return invoices.stream()
                .map(invoiceMapper::toInvoiceDto)
                .collect(Collectors.toList());
    }
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto updatedCustomerDto) {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Client introuvable"));
        // Mettez à jour les propriétés du client existant avec les valeurs du DTO mis à jour
        existingCustomer.setFirstName(updatedCustomerDto.getFirstName());
        existingCustomer.setLastName(updatedCustomerDto.getLastName());
        existingCustomer.setEmail(updatedCustomerDto.getEmail());
        // Autres mises à jour...

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerDto(updatedCustomer);
    }
}
