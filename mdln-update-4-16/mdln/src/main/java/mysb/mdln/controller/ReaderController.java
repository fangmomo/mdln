package mysb.mdln.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mysb.mdln.domain.User;
import mysb.mdln.service.UserService;

@Controller
public class ReaderController {


	@Autowired
    private UserService userService;
	
	
	@RequestMapping("/RegisteViem") 
	public String registe_view() {
        return "registe";
	}
	
	@RequestMapping("/Registe") 
	public String Registe(HttpServletRequest request,HttpServletResponse response) throws IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassward(request.getParameter("userPassword"));
		user.setEmail(request.getParameter("userEmail"));

		userService.registe(user);
		System.out.println("注册成功");
        return "home";
	}
	
	@RequestMapping("/SignInViem") 
	public String signin_view() {
        return "SignIn";
	}
	
	@RequestMapping("/SignIn") 
	public String signin(HttpServletRequest request,HttpServletResponse response,Model model) {
		String username = request.getParameter("user");
		String passward = request.getParameter("passward");
		User user = userService.queryByNameAndPsd(username, passward);
		if(null==user) {
			model.addAttribute("errormsg","登录失败。原因：密码或者用户名错误");
			return "error/404";
		}
		Cookie cookie = new Cookie("username",username);
		cookie.setMaxAge(10);
		response.addCookie(cookie);
		model.addAttribute("username", username);
		model.addAttribute("id", user.getId());
		return "forward:/home";
	}
}
