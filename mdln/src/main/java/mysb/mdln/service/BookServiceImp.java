package mysb.mdln.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import mysb.mdln.domain.Book;
import mysb.mdln.respositroy.BookService;

@Service
public class BookServiceImp implements BookService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void savebook(Book book) {
		String sql = "update novel SET current_count="+String.valueOf(book.getCurrent_count()+1)+" WHERE bookname='"+book.getBookname()+"'";
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public void createbook(Book book) {
		String sql="insert into novel(bookname,path,author,current_count,intro,storyclass) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{book.getBookname(),
                book.getPath(),
                book.getAuthor(),
                book.getCurrent_count(),
                book.getInfo(),
                book.getStyle()},new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR});
		
	}

	@Override
	public Book getBookByName(String bookname, String username) {
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from novel where bookname = '"+bookname+"' and author = '"+username+"'");
		if(null!=list&&list.size()>0) {
			Map<String,Object> map=list.get(0);
			Book book = new Book();
			book.setBookname(map.get("bookname").toString());
			book.setAuthor(map.get("author").toString());
			book.setPath(map.get("path").toString());
			book.setCurrent_count((int) map.get("current_count"));
			book.setInfo(map.get("intro").toString());
			book.setStyle(map.get("storyclass").toString());
	    	return book;
		}
		return null;
	}
	
	@Override
	public ArrayList<Book> getBooksByName(String bookname) {
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from novel where bookname like '%"+bookname+"%'");
		ArrayList<Book> books = new ArrayList<Book>();
		System.out.println("first"+list.size());
		if(null!=list&&list.size()>0) {
			for(Map<String,Object> map:list) {
				Book book = new Book();
				book.setBookname(map.get("bookname").toString());
				book.setAuthor(map.get("author").toString());
				book.setPath(map.get("path").toString());
				book.setCurrent_count((int) map.get("current_count"));
				book.setInfo(map.get("intro").toString());
				book.setStyle(map.get("storyclass").toString());
				books.add(book);
			}
			return books;
		}
		return null;
	}

	@Override
	public String getBooksByUsername(String username) {
		ArrayList<Book> books = new ArrayList<Book>();
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from novel where author = '"+username+"'");
		if(null!=list&&list.size()>0) {
			for(Map<String,Object> map:list) {
				Book book = new Book();
				book.setBookname(map.get("bookname").toString());
				book.setAuthor(map.get("author").toString());
				book.setPath(map.get("path").toString());
				book.setCurrent_count((int) map.get("current_count"));
				book.setInfo(map.get("intro").toString());
				book.setStyle(map.get("storyclass").toString());
				books.add(book);
			}
		}
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
	public ArrayList<Book> getRecomBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from recommend");
		if(null!=list&&list.size()>0) {
			for(Map<String,Object> map:list) {
				Book book = new Book();
				book.setBookname(map.get("bookname").toString());
				book.setAuthor(map.get("author").toString());
				book.setInfo(map.get("intro").toString());
				book.setStyle(map.get("style").toString());
				book.setPath(map.get("path").toString());
				books.add(book);
			}
		}
		return books;
	}

	@Override
	public void updaterecommend() {
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from novel");
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		int count=0;
		int len = list.size();
		Random random = new Random();
		while(len!=0&&count<14) {
			int pos = Math.abs(random.nextInt())%len;
			list1.add(list.get(pos));
			list.remove(pos);
			len-=1;
			count+=1;
		}
		
		jdbcTemplate.execute("truncate table recommend");
		count = 1;
		for(Map<String,Object> map:list1) {
			jdbcTemplate.update("INSERT INTO recommend VALUES(?, ?, ?, ?, ?)",
					new Object[] {
					count,map.get("bookname").toString(), 
					map.get("author").toString(), 
					map.get("intro").toString(), 
					map.get("storyclass").toString()});  
		}
	}
	
	@Override
	public ArrayList<Book> getAllbooks(){
		ArrayList<Book> books = new ArrayList<Book>();
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from novel");
		if(null!=list&&list.size()>0) {
			for(Map<String,Object> map:list) {
				Book book = new Book();
				book.setBookname(map.get("bookname").toString());
				book.setAuthor(map.get("author").toString());
				book.setPath(map.get("path").toString());
				book.setCurrent_count((int) map.get("current_count"));
				book.setInfo(map.get("intro").toString());
				book.setStyle(map.get("storyclass").toString());
				books.add(book);
			}
		}
		return books;
	}

}
