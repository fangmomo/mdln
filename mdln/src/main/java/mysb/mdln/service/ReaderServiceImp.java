package mysb.mdln.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mysb.mdln.domain.Reader;
import mysb.mdln.respositroy.ReaderService;
import mysb.mdln.utils.StringUtils;

/*
 * 读者信息服务实现类
 */
@Service
public class ReaderServiceImp implements ReaderService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Reader> getAllReaderInfo() {
		 List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from reader");
	     List<mysb.mdln.domain.Reader> readers=new ArrayList<>();
	     for (Map<String,Object> map:list ) {
	    	Reader reader = new Reader();
	    	reader.setId(map.get("id").toString());
	    	reader.setName(map.get("name").toString());
	    	reader.setTel(map.get("tel").toString());
	    	reader.setAddr(null==map.get("addr")?"":map.get("addr").toString());
	    	readers.add(reader);
	     }
		return readers;
	}

	@Override
	public Reader getReaderInfoById(String id) {
		Map<String,Object> map=jdbcTemplate.queryForMap("select * from reader where cus_id = ?",id);
		if(null!=map&&map.size()>0) {
			Reader reader = new Reader();
			reader.setId(map.get("id").toString());
	    	reader.setName(map.get("name").toString());
	    	reader.setTel(map.get("tel").toString());
	    	reader.setAddr(null==map.get("addr")?"":map.get("addr").toString());
	    	return reader;
		}
		return null;
	}

	@Override
	public Reader saveReaderInfo(Reader reader) {
		reader.setId(StringUtils.getUUID());
		String sql="insert into reader(name,id,tel,address) values(?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{reader.getName(),
                reader.getId(),
                reader.getTel(),
                reader.getAddr()},new int[] {Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR});
		return getReaderInfoById(reader.getId());
	}

	@Override
	public Reader updataReaderInfo(Reader reader) {
		String sql="update reader set name = ?,addr = ?,tel = ?,address = ? where id = ?";
		jdbcTemplate.update(sql,new Object[]{reader.getName(),reader.getTel(),reader.getAddr(),reader.getId()});
		return getReaderInfoById(reader.getId());
	}

	@Override
	public int deleteReaderInfo(String id) {
		String sql="delete from reader where cus_id = ? ";
        return jdbcTemplate.update(sql,new Object[]{id});
	}

}
