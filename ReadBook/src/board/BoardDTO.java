package board;

import java.sql.Date;

import lombok.Data;

@Data

public class BoardDTO {
	public int number;
	public String title;
	public String content;
	public Date date;
	public int view;
	public String name;
}
