package mysb.mdln.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mysb.mdln.domain.User;
import mysb.mdln.respositroy.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String registe(User user) {
		if(user.getName().equals("")||user.getPassward().equals("")||user.getEmail().equals(""))
			return "信息不全";
		if(!UserIsExit(user.getName())) {
			saveReaderInfo(user);
			return "ok";
		}else {
			return "已注册";
		}
	}

	@Override
	public void signin() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean UserIsExit(String name) {
		boolean flag = false;
		List<Map<String,Object>> logs=jdbcTemplate.queryForList("select * from user where username = ?",name);
		//Map<String,Object> map=jdbcTemplate.queryForMap("select * from user where tel = ?",tel);
		if(null!=logs&&logs.size()>0) {
			flag=true;
		}
		return flag;
	}
	@Override
	public void saveReaderInfo(User user) {
		List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from user");
		int index=list.size()+100000;
		user.setId(String.valueOf(index));
		String sql="insert into user(id,username,passward,email) values(?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{Integer.valueOf(user.getId()),
				user.getName(),
                user.getPassward(),
               user.getEmail()},new int[] {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
	}
	@Override
	public List<User> getAllReaderInfo() {
		List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from user");
	     List<User> users=new ArrayList<>();
	     for (Map<String,Object> map:list ) {
	    	User user = new User();
	    	user.setId(map.get("id").toString());
	    	user.setName(map.get("username").toString());
	    	user.setPassward(map.get("passward").toString());
	    	user.setEmail(map.get("email").toString());
	    	
	    	users.add(user);
	     }
		return users;
	}
	
	public User queryByNameAndPsd(String username,String psd) {
		//String sql="select * from reader where username = '"+username+"' and passward= '"+psd+"'";
		List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from user where username = '"+username+"' and passward= '"+psd+"'");
		if(null!=list&&list.size()>0) {
			Map<String,Object> map=list.get(0);
			User user = new User();
	    	user.setId(map.get("id").toString());
	    	user.setName(map.get("username").toString());
	    	user.setPassward(map.get("passward").toString());
	    	user.setEmail(map.get("email").toString());
	    	return user;
		}
		return null;
	}

}
