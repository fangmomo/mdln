package mysb.mdln.respositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mysb.mdln.domain.User;

@Repository
public interface UserRepositroy extends JpaRepository<User, Integer>{

	@Query(value="select * from user where username=?1 and password=?2",nativeQuery=true)
	User findByUsernameAndPsd(String username,String password);
	
	@Query(value="select * from user where username=?1",nativeQuery=true)
	List<User> findByUsername(String username);
	
}
