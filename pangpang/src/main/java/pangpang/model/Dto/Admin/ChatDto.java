package pangpang.model.Dto.Admin;

public class ChatDto {
	private int chat_no; 
	private int chat_fmno; //이게 로그인한 사람이면 오른쪽에 표시 아니면 왼쪽에 표시
    private int chat_tmno; //이게 로그인한 사람이면 왼쪽에 표시 아니면 오른쪽에 표시
	private String chat_msg;
	private String chat_date;
   
    
    private String chat_fmname;
    private String chat_fmid;
    
    public ChatDto() {
		// TODO Auto-generated constructor stub
	}

	public ChatDto(int chat_no, int chat_fmno, int chat_tmno, String chat_msg, String chat_date, String chat_fmname,
			String chat_fmid) {
		super();
		this.chat_no = chat_no;
		this.chat_fmno = chat_fmno;
		this.chat_tmno = chat_tmno;
		this.chat_msg = chat_msg;
		this.chat_date = chat_date;
		this.chat_fmname = chat_fmname;
		this.chat_fmid = chat_fmid;
	}

	public ChatDto(int chat_no, int chat_fmno, int chat_tmno, String chat_msg, String chat_date) {
		super();
		this.chat_no = chat_no;
		this.chat_fmno = chat_fmno;
		this.chat_tmno = chat_tmno;
		this.chat_msg = chat_msg;
		this.chat_date = chat_date;
	}

	public ChatDto(String chat_msg, int chat_fmno, int chat_tmno) {
		super();
		this.chat_msg = chat_msg;
		this.chat_fmno = chat_fmno;
		this.chat_tmno = chat_tmno;
	}

	

	@Override
	public String toString() {
		return "ChatDto [chat_no=" + chat_no + ", chat_fmno=" + chat_fmno + ", chat_tmno=" + chat_tmno + ", chat_msg="
				+ chat_msg + ", chat_date=" + chat_date + ", chat_fmname=" + chat_fmname + ", chat_fmid=" + chat_fmid
				+ "]";
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public String getChat_msg() {
		return chat_msg;
	}

	public void setChat_msg(String chat_msg) {
		this.chat_msg = chat_msg;
	}

	public String getChat_date() {
		return chat_date;
	}

	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}

	public int getChat_fmno() {
		return chat_fmno;
	}

	public void setChat_fmno(int chat_fmno) {
		this.chat_fmno = chat_fmno;
	}

	public int getChat_tmno() {
		return chat_tmno;
	}

	public void setChat_tmno(int chat_tmno) {
		this.chat_tmno = chat_tmno;
	}

	public String getChat_fmname() {
		return chat_fmname;
	}

	public void setChat_fmname(String chat_fmname) {
		this.chat_fmname = chat_fmname;
	}

	public String getChat_fmid() {
		return chat_fmid;
	}

	public void setChat_fmid(String chat_fmid) {
		this.chat_fmid = chat_fmid;
	}
    
}