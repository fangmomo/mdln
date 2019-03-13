package mysb.mdln.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mysb.mdln.domain.Invoice;
import mysb.mdln.respositroy.InvoiceService;

@Service
public class InvoiceServiceImp implements InvoiceService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Invoice invoice) {
		
		String sql = "insert into invoice values(?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				invoice.getPublicer(),invoice.getPublicer_id(),invoice.getStyle(),invoice.getColor_style(),invoice.getIs_public(),
				invoice.getDesc(),invoice.getPrice(),invoice.getCheck_require(),invoice.getReponse_time(),invoice.getPublic_time(),invoice.getBookname()
		}, 
		new int[] {
				Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,
				Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR
		});
	}

	/*
	 * 查找所有  目前没有什么限制 
	 * @see mysb.mdln.respositroy.InvoiceService#getInvoiceAll()
	 */
	@Override
	public ArrayList<Invoice> getInvoiceAll() {
		
		ArrayList<Invoice> invoice_list = new ArrayList<Invoice>();
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from invoice");
		Invoice invoice = new Invoice();
		if(null!=list&&list.size()>0) {
			for(Map<String,Object> map:list) {
				invoice.setPublicer(map.get("publicer").toString());
				invoice.setPublicer_id(Integer.valueOf(map.get("publicer_id").toString()));
				invoice.setStyle(Integer.valueOf(map.get("style").toString()));
				invoice.setColor_style(map.get("color_style").toString());
				invoice.setIs_public(map.get("is_public").toString());
				invoice.setDesc(map.get("desc").toString());
				invoice.setPrice(Integer.valueOf(map.get("price").toString()));
				invoice.setCheck_require(Integer.valueOf(map.get("check_require").toString()));
				invoice.setReponse_time(map.get("reponse_time").toString());
				invoice.setPublic_time(map.get("public_time").toString());
				invoice.setBookname(map.get("bookname").toString());
				
				invoice_list.add(invoice);
			}
		}		
		return invoice_list;
	}

	@Override
	public Invoice getInvoiceByPid(String pid ) {
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from invoice where publicer_id = '"+pid+"'");
		if(null!=list&&list.size()>0) {
			Map<String,Object> map=list.get(0);
			Invoice invoice = new Invoice();
			invoice.setPublicer(map.get("publicer").toString());
			invoice.setPublicer_id(Integer.valueOf(map.get("publicer_id").toString()));
			invoice.setStyle(Integer.valueOf(map.get("style").toString()));
			invoice.setColor_style(map.get("color_style").toString());
			invoice.setIs_public(map.get("is_public").toString());
			invoice.setDesc(map.get("desc").toString());
			invoice.setPrice(Integer.valueOf(map.get("price").toString()));
			invoice.setCheck_require(Integer.valueOf(map.get("check_require").toString()));
			invoice.setReponse_time(map.get("reponse_time").toString());
			invoice.setPublic_time(map.get("public_time").toString());
			invoice.setBookname(map.get("bookname").toString());
			
	    	return invoice;
		}
		return null;
	}
}
