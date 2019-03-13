package mysb.mdln.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import mysb.mdln.domain.Book;
import mysb.mdln.respositroy.BookService;

@Controller
public class AllController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/updaterecommend") 
	public String update() {
		
		bookService.updaterecommend();
		return "forward:/home";
	}
	
	
	@RequestMapping("/") 
	public String index(HttpServletRequest request) {
		ArrayList<Book> books = bookService.getRecomBooks();
		String rebooks = JSON.toJSONString(books);
		request.getSession().setAttribute("rebooks", rebooks);
        return "home";
	}
	
	@RequestMapping("/home") 
	public String home() {
        return "forward:/";
	}

	
	@RequestMapping("/logout") 
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("username", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
        return "forward:/";
	}
	
	@RequestMapping("/errormsg") 
	public String errormsg() {
		return "error/404";
	}
	
	
	
	
}
