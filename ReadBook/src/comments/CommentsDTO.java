package comments;

import java.sql.Date;

public class CommentsDTO {

	private int boardNumber;
	private int num;
	private String name;
	private String contents;
	private Date date;
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int board_number) {
		this.boardNumber = board_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
