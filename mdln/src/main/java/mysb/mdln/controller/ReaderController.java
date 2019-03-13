package mysb.mdln.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mysb.mdln.domain.User;
import mysb.mdln.respositroy.ReaderService;
import mysb.mdln.respositroy.UserService;

@Controller
public class ReaderController {

	@Autowired
    private ReaderService readerService;
	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/all",method = RequestMethod.GET)
    public String  getAllCusBaseInfo(){
		System.out.println( readerService.getAllReaderInfo().toString());
        //return readerService.getAllReaderInfo();
		return "home";
    }
	
	@RequestMapping("/RegisteViem") 
	public String registe_view() {
        return "registe";
	}
	
	@RequestMapping("/Registe") 
	public String Registe(HttpServletRequest request,HttpServletResponse response) throws IOException {
		User user = new User();
		user.setName(request.getParameter("username"));
		user.setPassward(request.getParameter("userPassword"));
		user.setEmail(request.getParameter("userEmail"));
		
		String statue=userService.registe(user);
		if(!statue.equals("ok")) {
			request.getSession().setAttribute("errormsg", "注册失败。原因："+statue);
			return "error/404";
			
		}
		System.out.println("注册成功");
        return "home";
	}
	
	@RequestMapping("/SignInViem") 
	public String signin_view() {
        return "SignIn";
	}
	
	@RequestMapping("/SignIn") 
	public String signin(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("user");
		String passward = request.getParameter("passward");
		User user = userService.queryByNameAndPsd(username, passward);
		if(null==user) {
			request.getSession().setAttribute("errormsg", "登录失败。原因：密码或者用户名错误");
			return "error/404";
		}
		Cookie cookie = new Cookie("username",username);
		cookie.setMaxAge(10);
		response.addCookie(cookie);
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("id", user.getId());
		return "home";
	}
}
