package mysb.mdln.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mysb.mdln.domain.Book;
import mysb.mdln.service.BookService;

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
	public String index(Model model) {
		ArrayList<Book> books = bookService.getRecomBooks();
		model.addAttribute("rebooks", books);
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
