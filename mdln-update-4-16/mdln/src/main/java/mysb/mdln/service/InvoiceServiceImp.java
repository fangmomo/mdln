package mysb.mdln.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mysb.mdln.domain.Invoice;
import mysb.mdln.respositroy.InvoiceRepositroy;

@Service
public class InvoiceServiceImp implements InvoiceService{


	@Autowired
	InvoiceRepositroy invoiceRepositroy;
	
	@Override
	public void save(Invoice invoice) {
		
		invoiceRepositroy.save(invoice);
	}
	/*
	 * 查找所有  目前没有什么限制 
	 * @see mysb.mdln.respositroy.InvoiceService#getInvoiceAll()
	 */
	@Override
	public ArrayList<Invoice> getInvoiceAll() {	
		return (ArrayList<Invoice>) invoiceRepositroy.findAll();		
	}

	@Override
	public Invoice getInvoiceByPid(int id) {	
		return invoiceRepositroy.findById(id).get();
	}
}
