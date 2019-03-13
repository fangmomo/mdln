package mysb.mdln.respositroy;

import java.util.List;

import mysb.mdln.domain.User;

public interface UserService {
	//注册
	public String registe(User user);
	
	//登录
	public void signin();
	
	public boolean UserIsExit(String tel);
	
	//添加
	public void saveReaderInfo(User user);
	
	/*
	 * 获取所有读者信息
	 */
	public List<User> getAllReaderInfo();
	
	/*
	 * 通过用户名与密码查询
	 */
	public User queryByNameAndPsd(String user,String psd);
}
