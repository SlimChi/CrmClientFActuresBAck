package clientcrm.frcs.controller;

import clientcrm.frcs.dto.CustomerDto;
import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.dto.UserDto;
import clientcrm.frcs.services.mapperServiceImpl.CustomerServiceImpl;
import clientcrm.frcs.services.mapperServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @Project
 */

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}/invoices")
    public List<InvoiceDto> getUserInvoices(@PathVariable Long customerId) {
        return customerService.getCustomerInvoices(customerId);
    }

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
    @PutMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto updatedCustomerDto) {
        return customerService.updateCustomer(customerId, updatedCustomerDto);
    }

}

