package mysb.mdln.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mysb.mdln.domain.User;
import mysb.mdln.respositroy.UserRepositroy;

@Service
public class UserServiceImp implements UserService {

	
	@Autowired
	UserRepositroy userRepositroy;

	
	@Override
	public void registe(User user) {
		userRepositroy.save(user);
	}

	@Override
	public void signin() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean UserIsExit(String name) {
		return userRepositroy.findByUsername(name).size()>0;

	}
	@Override
	public void saveReaderInfo(User user) {	
		userRepositroy.save(user);
	}
	@Override
	public List<User> getAllReaderInfo() {	
		return userRepositroy.findAll();
	}
	
	public User queryByNameAndPsd(String username,String psd) {
		return userRepositroy.findByUsernameAndPsd(username, psd);
	}

}
