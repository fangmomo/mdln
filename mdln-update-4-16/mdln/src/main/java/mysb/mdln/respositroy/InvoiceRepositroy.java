package mysb.mdln.respositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mysb.mdln.domain.Invoice;

@Repository
public interface InvoiceRepositroy extends JpaRepository<Invoice, Integer>{

}
