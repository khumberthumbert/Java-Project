package board;

import lombok.Data;

@Data
public class BoardGeDTO {

//	CREATE TABLE board(
//			fiction VARCHAR2(30),
//			sf VARCHAR2(30),
//			science VARCHAR2(30),
//			essay VARCHAR2(30),
//			philosophy VARCHAR2(30),
//			poem VARCHAR2(30));
	
	private String fiction;
	private String sf;
	private String science;
	private String essay;
	private String philosophy;
	private String poem;
}
