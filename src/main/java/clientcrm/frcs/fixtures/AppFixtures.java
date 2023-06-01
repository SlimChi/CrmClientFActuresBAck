package clientcrm.frcs.fixtures;

import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;
import clientcrm.frcs.repositories.CustomerRepository;
import clientcrm.frcs.repositories.InvoiceRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class AppFixtures implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;

    public AppFixtures(CustomerRepository customerRepository, InvoiceRepository invoiceRepository) {
        this.customerRepository = customerRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) {
        // Génération des utilisateurs avec des factures
        for (int i = 0; i < 30; i++) {
            Customer customer = createRandomCustomer();
            customerRepository.save(customer);
        }
    }

    private Customer createRandomCustomer() {
        Customer customer = new Customer();
        String firstName = Faker.instance().name().firstName();
        String lastName = Faker.instance().name().lastName();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        // Générer l'adresse e-mail à partir du prénom et du nom de famille
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@outlook.com";
        customer.setEmail(email);

        customer.setPhone(generateFrenchPhoneNumber());

        // Génération des factures pour l'utilisateur
        List<Invoice> invoices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Invoice invoice = createRandomInvoice();
            invoice.setCustomer(customer); // Liaison de l'utilisateur avec la facture
            invoices.add(invoice);
        }
        customer.setInvoices(invoices);

        return customer;
    }


    private String generateFrenchPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();

        // Générer le premier chiffre (0)
        phoneNumber.append("0");

        // Générer le deuxième chiffre (7 ou 6)
        int secondDigit = random.nextInt(2) + 6;
        phoneNumber.append(secondDigit);

        // Générer les 8 chiffres restants du numéro de téléphone
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(random.nextInt(10));
        }

        // Insérer des espaces pour respecter le format français
        phoneNumber.insert(2, " ");
        phoneNumber.insert(5, " ");
        phoneNumber.insert(8, " ");
        phoneNumber.insert(11, " ");

        return phoneNumber.toString();
    }


    private Invoice createRandomInvoice() {
        Invoice invoice = new Invoice();
        invoice.setAmount((float) Faker.instance().number().randomDouble(2, 100, 3000));
        invoice.setSentAt(Faker.instance().date().past(365, TimeUnit.DAYS));
        invoice.setStatus(Faker.instance().random().nextBoolean() ? "PAID" : "UNPAID");

        return invoice;
    }
}
