package clientcrm.frcs.mapper.mapperImpl;

import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;
import clientcrm.frcs.mapper.InvoiceMapper;
import clientcrm.frcs.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author
 * @Project
 */
@Component
@RequiredArgsConstructor
public class InvoiceMapperImpl implements InvoiceMapper {

private final  CustomerRepository customerRepository;
    @Override
    public InvoiceDto toInvoiceDto(Invoice invoice) {
        return InvoiceDto.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .sentAt(invoice.getSentAt())
                .status(invoice.getStatus())
                .idCustomer(invoice.getCustomer().getIdCustomer())
                .build();
    }

    @Override
    public Invoice toInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setSentAt(invoiceDto.getSentAt());
        invoice.setStatus(invoiceDto.getStatus());

        Customer customer = customerRepository.findById(invoiceDto.getIdCustomer()).orElse(null);
        if (customer != null) {
            invoice.setCustomer(customer);
        }

        return invoice;
    }


}
