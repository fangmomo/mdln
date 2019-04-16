package mysb.mdln.respositroy;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mysb.mdln.domain.Book;

@Repository
public interface BookRepositroy extends JpaRepository<Book,String>{

	@Query(value="select * from recommend", nativeQuery = true)
	ArrayList<Book> getRecomBooks();
	
	@Query(value="select * from novel where author=?1",nativeQuery=true)
	ArrayList<Book> findByAuthor(String author);
	
	@Query(value="select * from novel where bookname like '%?1%'",nativeQuery=true)
	ArrayList<Book> findByBookname(String bookname);
	
	@Query(value="select * from novel where bookname=?1 and author=?2",nativeQuery=true)
	Book findByBookname(String bookname,String author);
	
	@Query(value="truncate table recommend",nativeQuery=true)
	void clearRecommend();
	
	@Query(value="insert into recommend(novel_id,bookname,path,author,current_count,intro,stroyclass)"
			+ "select * from novel limit 14",nativeQuery=true)
	void getRecommendFromNovel();
}
