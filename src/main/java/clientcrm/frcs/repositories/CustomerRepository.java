package clientcrm.frcs.repositories;

import clientcrm.frcs.entities.Customer;
import clientcrm.frcs.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
