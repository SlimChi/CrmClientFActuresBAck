package clientcrm.frcs.services;

import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.entities.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author
 * @Project
 */
public interface InvoiceService {
    List<InvoiceDto> getAllInvoices();

    InvoiceDto getInvoiceById(Long invoiceId);

    @Transactional
    InvoiceDto createInvoice(InvoiceDto invoiceDto);

    InvoiceDto updateInvoiceById(Long invoiceId, InvoiceDto invoiceDto);

    void deleteInvoice(Long invoiceId);
}
