package clientcrm.frcs.Exceptions;

public class InvoiceCreationException extends RuntimeException {

    public InvoiceCreationException(Long id) {
        super("Impossible de trouver la facture avec l'identifiant : " + id);
    }
}
