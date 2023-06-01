package clientcrm.frcs.mapper;

import clientcrm.frcs.dto.InvoiceDto;
import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;

/**
 * @author
 * @Project
 */
public interface InvoiceMapper {
    InvoiceDto toInvoiceDto(Invoice invoice);
    Invoice toInvoice(InvoiceDto invoiceDto);

}
