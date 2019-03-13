package mysb.mdln.respositroy;

import java.util.List;

import mysb.mdln.domain.Reader;

public interface ReaderService {
	/*
	 * 获取所有读者信息
	 */
	List<mysb.mdln.domain.Reader> getAllReaderInfo();
	
	/*
	 * 根据id获取信息
	 */
	Reader getReaderInfoById(String id);
	
	/*
	 * 保存客户信息
	 */
	Reader saveReaderInfo(Reader reader);
	
	/*
	 * 修改客户信息
	 */
	Reader updataReaderInfo(Reader reader);
	
	/*
	 * 删除客户信息
	 */
	int deleteReaderInfo(String id);
}
