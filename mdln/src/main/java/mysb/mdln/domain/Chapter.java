package mysb.mdln.domain;

import javax.validation.constraints.NotEmpty;

public class Chapter {
	
	@NotEmpty(message="标题: 不能为空")   
	String title;
	
	@NotEmpty(message="内容: 不能为空")  
	String content;
	
	@NotEmpty(message="章节号: 不能为空") 
	int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	 public Chapter() {
		 super();
	}
}
