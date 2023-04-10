package readers;

public class WantBook extends ReaderDTO {
	private String bookName;
	private String author;
	
	public WantBook() {}
	
	public WantBook(String bookName, String author) {
		this.bookName = bookName;
		this.author = author;
	}
	
	
	
	
}
