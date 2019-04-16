package mysb.mdln.domain;

public class Message {
	int type;
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	int num;
	String msg;
	
	public Message(int type, int num, String msg) {
		super();
		this.type = type;
		this.num = num;
		this.msg = msg;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}
}
