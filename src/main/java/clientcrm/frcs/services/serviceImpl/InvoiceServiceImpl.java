package clientcrm.frcs.services.serviceImpl;

import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;
import clientcrm.frcs.mapper.InvoiceMapper;
import clientcrm.frcs.repositories.CustomerRepository;
import clientcrm.frcs.repositories.InvoiceRepository;
import clientcrm.frcs.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author
 * @Project
 */
@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoiceMapper::toInvoiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto getInvoiceById(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            return invoiceMapper.toInvoiceDto(invoice);
        } else {
            throw new IllegalArgumentException("La facture avec l'ID spécifié n'existe pas");
        }
    }

    @Override
    public InvoiceDto createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceMapper.toInvoice(invoiceDto);
        Customer customer = customerRepository.findById(invoiceDto.getIdCustomer()).orElse(null);
        if (customer != null) {
            invoice.setCustomer(customer);
            invoice = invoiceRepository.save(invoice);
            customer.getInvoices().add(invoice);
            customerRepository.save(customer);
            return invoiceMapper.toInvoiceDto(invoice);
        }
        return null;
    }

    @Override
    public InvoiceDto updateInvoiceById(Long invoiceId, InvoiceDto invoiceDto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setAmount(invoiceDto.getAmount());
            invoice.setSentAt(invoiceDto.getSentAt());
            invoice.setStatus(invoiceDto.getStatus());
            invoice = invoiceRepository.save(invoice);
            return invoiceMapper.toInvoiceDto(invoice);
        }
        return null;
    }


    @Override
    public void deleteInvoice(Long invoiceId) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalInvoice.isPresent()) {
            invoiceRepository.delete(optionalInvoice.get());
        } else {
            throw new IllegalArgumentException("La facture avec l'ID spécifié n'existe pas");
        }
    }
}
