package mysb.mdln.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import mysb.mdln.domain.Book;
import mysb.mdln.respositroy.BookRepositroy;

@Service
public class BookServiceImp implements BookService{
	
	
	@Autowired
	BookRepositroy bookRepositroy;
	
	@Override
	public void savebook(Book book) {
		//直接save就行 ，jpa会通过primary来确定 入含有这个iD 则直接更新，否则就插入
		book.setCurrent_count(book.getCurrent_count()+1);
		bookRepositroy.save(book);
	}
	
	@Override
	public void createbook(Book book) {
		bookRepositroy.save(book);
	}

	@Override
	public Book getBookByName(String bookname, String username) {
		
		return bookRepositroy.findByBookname(bookname,username);
	}
	
	@Override
	public ArrayList<Book> getBooksByName(String bookname) {
		return bookRepositroy.findByBookname(bookname);
	}

	@Override
	public String getBooksByUsername(String username) {
		ArrayList<Book> books = new ArrayList<Book>();
		books = bookRepositroy.findByAuthor(username);
		String jsonString = JSON.toJSONString(books);
		return jsonString;
	}
	
	@Override
	public void setover(String author,String bookname) {
		Book book = this.getBookByName(bookname, author);
		book.setCurrent_count(9999);
		this.savebook(book);
	}
	@Override
	@Cacheable(value="recommwnd")
	public ArrayList<Book> getRecomBooks() {
		return bookRepositroy.getRecomBooks();
	}

	@Override
	public void updaterecommend() {
		bookRepositroy.clearRecommend();
		bookRepositroy.getRecommendFromNovel();
	}
	
	@Override
	public ArrayList<Book> getAllbooks(){
		return (ArrayList<Book>) bookRepositroy.findAll();
	}

}
