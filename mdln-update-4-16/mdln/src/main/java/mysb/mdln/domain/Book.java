package mysb.mdln.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="novel")
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="novel_id")
	public int novel_id;
	public String author;
	public String bookname;
	public String path;
	public int current_count;
	@Column(name="intro")
	public String info;
	@Column(name="storyclass")
	public String style;

	public Book() {
		
	}
	
	public Book(String author, String bookname, String path, int current_count,String info,String style) {
		super();
		this.author = author;
		this.bookname = bookname;
		this.path = path;
		this.current_count = current_count;
		this.info = info;
		this.style = style;
	}
	
	public int getNovel_id() {
		return novel_id;
	}

	public void setNovel_id(int novel_id) {
		this.novel_id = novel_id;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getCurrent_count() {
		return current_count;
	}
	public void setCurrent_count(int current_count) {
		this.current_count = current_count;
	}
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}
