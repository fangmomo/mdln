package mysb.mdln.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import mysb.mdln.domain.Book;
import mysb.mdln.domain.Chapter;
import mysb.mdln.domain.Invoice;
import mysb.mdln.respositroy.BookService;
import mysb.mdln.respositroy.InvoiceService;
import mysb.mdln.utils.WriteToLocal;

@Controller
public class NovelController {
	
	@Autowired
    private BookService bookService;
	//read page
	@RequestMapping(value = "/read", method = RequestMethod.POST) 
	public String readnevol(HttpServletRequest request) {

		String author = request.getParameter("author");
		String bookname = request.getParameter("bookname");
		String curr = request.getParameter("zjs");
		String message = WriteToLocal.read(author,bookname, Integer.valueOf(curr));
		
		request.getSession().setAttribute("curr_book", bookname);
		request.getSession().setAttribute("zuozhe", author);
		request.getSession().setAttribute("curr", Integer.valueOf(curr));
		request.getSession().setAttribute("message", message);
		return "readview";
	}
	
	//pre page
	@RequestMapping(value = "/pre")
	public String pre(HttpServletRequest request) {
		int curr = (int) request.getSession().getAttribute("curr");
		String author = (String)request.getSession().getAttribute("zuozhe");
		String bookname = (String)request.getSession().getAttribute("curr_book");
		String message = null;
		if(curr!=1)
			curr=curr-1;
		request.getSession().setAttribute("curr",curr+1);
		message =WriteToLocal.read(author,bookname, curr);
		request.getSession().setAttribute("message", message);
		return "readview";
	}
	
	// next page
	@RequestMapping(value = "/next")
	public String next(HttpServletRequest request) {
		int curr = (int) request.getSession().getAttribute("curr");
		String author = (String)request.getSession().getAttribute("zuozhe");
		String bookname = (String)request.getSession().getAttribute("curr_book");
		int MaxCount = (int) request.getSession().getAttribute("curr_bookCount");
		if(curr<MaxCount)
			curr=curr+1;
		String message = WriteToLocal.read(author,bookname, Integer.valueOf(curr));
		request.getSession().setAttribute("curr",curr);
		request.getSession().setAttribute("message", message);
 		return "readview";
	}
	
	//add chapter
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String save(HttpServletRequest request) { 
		if(request.getSession().getAttribute("username") == null) {
			return "forward:/home";
		}
		String username = (String)request.getSession().getAttribute("username");
		String bookname = (String)request.getSession().getAttribute("bookname");
		Chapter chapter = new Chapter();
		chapter.setTitle(request.getParameter("chaptertitle"));
		chapter.setContent(request.getParameter("content"));
		Book book = null;
		book = bookService.getBookByName(bookname, username);
		if(book==null)
			return "forward:/author";
		bookService.savebook(book);
		chapter.setCount(book.getCurrent_count()+1);
		WriteToLocal.save(username,bookname,chapter);
		return "forward:/author";
	}
	
	//author page
	@RequestMapping("/author")
	public String authorPage(HttpServletRequest request) {
		if(request.getSession().getAttribute("username") == null) {
			return "home";
		}
		String username = (String)request.getSession().getAttribute("username");
		String jsonbooks = bookService.getBooksByUsername(username);
		request.getSession().setAttribute("books", jsonbooks);
		return "author";
	}
	
	//upload chatper page
	@RequestMapping(value="/loadnovel",method = RequestMethod.POST) 
	public String index(HttpServletRequest request) {
		String bookname = request.getParameter("book-name");
		String curr_count = request.getParameter("ch-count");
		request.getSession().setAttribute("bookname", bookname);
		request.getSession().setAttribute("curr_count", curr_count);
		return "load";
	}

	//set over
	@RequestMapping(value = "/setover", method = RequestMethod.POST)
	public String setover(HttpServletRequest requset) {
		String bookname = requset.getParameter("bookname");
		String username = (String)requset.getSession().getAttribute("username");
		bookService.setover(username, bookname);
		return "forward:/author";
	}

	//add book 
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addbook(HttpServletRequest requset) {
		try {
			requset.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
		} 
		String bookname = requset.getParameter("title");
		String storyclass = requset.getParameter("storyclass");
		String intro = requset.getParameter("intro");
		String username = (String)requset.getSession().getAttribute("username");
		if(null==username) {
			return "home";
		}
		//这里需要判断一次啊书名是否重复
		String path = "books\\"+username+"\\"+bookname;
		Book book = new Book(username,bookname,path,0,intro,storyclass);
		bookService.createbook(book);
		return "forward:/author";
	}
	
	//create book page
	@RequestMapping(value = "/createbook")
	public String createbook() {
		return "createbook";
	}
	
	//show book info 
	@RequestMapping(value = "/detail")
	public String detali() {
		return "detailinfo";
	}

	//search book 
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String search(HttpServletRequest requset){
		String key = requset.getParameter("searchkey");
		ArrayList<Book> books = null;
		books = bookService.getBooksByName(key);
		if(books==null){
			return "nobook";
		}else {
			String jsonbook = JSON.toJSONString(books);
			requset.getSession().setAttribute("searchbooks", jsonbook);
			return "search_display";
		}
	}
	
	@RequestMapping(value="/detafromhome",method = RequestMethod.POST)
	public String detali2(HttpServletRequest requset) {

		String bookname =  requset.getParameter("bookname");
		String author =  requset.getParameter("author");
		Book book = null;
		book = bookService.getBookByName(bookname, author);
		if(null==book)
			return "nobook";
		else {
			//在容器中讲当前书的总共章节数显示
			requset.getSession().setAttribute("curr_bookCount", book.getCurrent_count());
			String jsonbook = JSON.toJSONString(book);
			requset.getSession().setAttribute("curr_bookstring", jsonbook);
			return "detailinfo";
		}
	}
	@RequestMapping(value ="/book_base",method = RequestMethod.POST)
	public String bookbase(HttpServletRequest requset) {
		String curr_page = requset.getParameter("pagecount");
		if(null==requset.getSession().getAttribute("all_books")) {
			String allbooks =  JSON.toJSONString(bookService.getAllbooks());
			requset.getSession().setAttribute("all_books", allbooks);
		}
		requset.getSession().setAttribute("curr_page", curr_page);
		return "bookbase";
	}
	
	@RequestMapping(value ="/book_base")
	public String bookbase2(HttpServletRequest requset) {
		if(null==requset.getSession().getAttribute("all_books")) {
			String allbooks =  JSON.toJSONString(bookService.getAllbooks());
			requset.getSession().setAttribute("all_books", allbooks);
		}
		requset.getSession().setAttribute("curr_page", 1);
		return "bookbase";
	}
	
	
	@Autowired
    private InvoiceService invoiceService;
	
	/*
	 * 发布作品插画广告 等待画师来应征 
	 * 2019-1-17
	 */
	@RequestMapping(value="/paint",method = RequestMethod.POST)
	public String find_paint(HttpServletRequest requset){
		String paint_book = requset.getParameter("paint_bookname");
		requset.getSession().setAttribute("paint_bookname",paint_book);
		return "invoice/xieyi";
	}
	/*
	 * 接受是同意协议
	 */
	@RequestMapping(value="/xieyi_agree")
	public String agree_xieyi(HttpServletRequest requset){
		return "invoice/require";
	}
	/*
	 * 接受不同意协议
	 */
	@RequestMapping(value="/xieyi_disagree")
	public String disagree_xieyi(HttpServletRequest requset){
		return "forward:author";
	}
	/*
	 *  接受作者提的应征画师的要求
	 * 2019-1-17
	 */
	@RequestMapping(value="/upload_require",method = RequestMethod.POST)
	public String getXieyiRequire(HttpServletRequest requset){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//风格要求
		String style = requset.getParameter("style");
		//稿件颜色模式	
		String color_style = requset.getParameter("color_style");
		//画师可否公开发布稿件
		String is_public = requset.getParameter("is_public");
		//需求描述
		String desc = requset.getParameter("desc");
		//报价
		String price = requset.getParameter("price");
		//试稿要求 0=不需要试稿 -1=无偿 1=有偿
		String check_require = requset.getParameter("check_require");
		//反馈时间要求
		String reponse_time = requset.getParameter("reponse_time");
		//时间
		String public_time = df.format(new Date());
		
		String bookname =  (String) requset.getSession().getAttribute("paint_bookname");
		String publicer = (String) requset.getSession().getAttribute("username");
		String publicer_id = (String) requset.getSession().getAttribute("id");
		
		
		Invoice invoice = new Invoice(publicer, Integer.valueOf(publicer_id), Integer.valueOf(style), 
				color_style, is_public, desc, Integer.valueOf(price), Integer.valueOf(check_require), 
				reponse_time,public_time,bookname);
		invoiceService.save(invoice);
		
		//还有例图和文件没有解决
		return "forward:author";
	}
	
	/*
	 * 诸多应征页面
	 */
	@RequestMapping(value="/invoice_home")
	public String invoice_home(HttpServletRequest requset){
		if(requset.getSession().getAttribute("username") == null) {
			return "forward:/home";
		}
		ArrayList<Invoice> invoices = null;
		invoices = invoiceService.getInvoiceAll();
		String invoice_json =  JSON.toJSONString(invoices);
		requset.getSession().setAttribute("all_invoice", invoice_json);
		return "invoice/painter_page";
	}
	
	/*
	 * 接受用户点击联系作者 跳转到聊天页面 
	 * 2019-1-17
	 */
	@RequestMapping(value="/chat_author",method = RequestMethod.POST)
	public String chat(HttpServletRequest requset){
		String pid = requset.getParameter("publicer_id");
		Invoice invoice = invoiceService.getInvoiceByPid(pid);
		if(null==invoice) {
			return "error/404";
		}
		String string = JSON.toJSONString(invoice);
		requset.getSession().setAttribute("curr_chat",string);
		/*
		 * 
		 */
		
		return "invoice/chat";
	}
	/*
	 * 跳转至某个交易的详细界面
	 */
	@RequestMapping(value="/invo_detail",method = RequestMethod.POST)
	public String toinvo_detail(HttpServletRequest request) {
		int li_index = Integer.valueOf(request.getParameter("li_index"));
		String invos = (String) request.getSession().getAttribute("all_invoice");
		ArrayList<Invoice> invoices = (ArrayList<Invoice>) JSON.parseArray(invos, Invoice.class);
		String curr_invo = JSON.toJSONString(invoices.get(li_index));
		
		request.getSession().setAttribute("curr_invo", curr_invo);
		
		
		return "invoice/invo_detail";
	}
}
