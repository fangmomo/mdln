package mysb.mdln.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="invoice")
public class Invoice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="invoice_id")
	int invoice_id;
	String publicer;
	int publicer_id;
	//1 有风格要求 0无风格要求
	int style;
	String color_style;
	String is_public;
	String desc;
	int  price;
	int check_require;
	String reponse_time;
	String public_time;
	String bookname;
	
	public Invoice(String publicer, int publicwe_id, int style, String color_style, String is_public, String desc,
			int price, int check_require, String reponse_time,String public_time,String bookname) {
		super();
		this.publicer = publicer;
		this.publicer_id = publicwe_id;
		this.style = style;
		this.color_style = color_style;
		this.is_public = is_public;
		this.desc = desc;
		this.price = price;
		this.check_require = check_require;
		this.reponse_time = reponse_time;
		this.public_time = public_time;
		this.bookname = bookname;
	}
	
	public Invoice() {
		// TODO Auto-generated constructor stub
	}

	public String getPublicer() {
		return publicer;
	}

	public void setPublicer(String publicer) {
		this.publicer = publicer;
	}

	public int getPublicer_id() {
		return publicer_id;
	}

	public void setPublicer_id(int publicer_id) {
		this.publicer_id = publicer_id;
	}

	public String getPublic_time() {
		return public_time;
	}

	public void setPublic_time(String public_time) {
		this.public_time = public_time;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public String getColor_style() {
		return color_style;
	}

	public void setColor_style(String color_style) {
		this.color_style = color_style;
	}

	public String getIs_public() {
		return is_public;
	}

	public void setIs_public(String is_public) {
		this.is_public = is_public;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCheck_require() {
		return check_require;
	}

	public void setCheck_require(int check_require) {
		this.check_require = check_require;
	}

	public String getReponse_time() {
		return reponse_time;
	}

	public void setReponse_time(String reponse_time) {
		this.reponse_time = reponse_time;
	}

	
}
