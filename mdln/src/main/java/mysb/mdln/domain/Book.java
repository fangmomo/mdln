package mysb.mdln.domain;

public class Book {

	public String author;
	public String bookname;
	public String path;
	public int current_count;
	public String info;
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
