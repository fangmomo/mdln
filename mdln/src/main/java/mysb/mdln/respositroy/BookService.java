package mysb.mdln.respositroy;

import java.util.ArrayList;

import mysb.mdln.domain.Book;

public interface BookService {

	//保存书籍
	public void savebook(Book book);
	
	public void createbook(Book book);
	//根据书名获取书
	public Book getBookByName(String bookname,String username);	
	//搜索
	public ArrayList<Book> getBooksByName(String bookname);
	
	public String getBooksByUsername(String username);
	
	//设置书本完结
	public void setover(String author,String bookname);
	
	public ArrayList<Book> getRecomBooks();
	
	//跟新推荐
	public void updaterecommend();
	
	//获取所有
	public ArrayList<Book> getAllbooks();
	
}
