package readers;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

@Getter
@Setter
public class ReaderDTO {

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	private String id;
	private String pw;
}
