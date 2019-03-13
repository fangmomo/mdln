package mysb.mdln.respositroy;

import java.util.ArrayList;

import mysb.mdln.domain.Invoice;

public interface InvoiceService {
	
	
	//保存
	public void save(Invoice invoice);
	
	//获取
	public ArrayList<Invoice> getInvoiceAll();
	
	//通过一个pid获取一个交易
	public Invoice getInvoiceByPid(String pid);
	
}
