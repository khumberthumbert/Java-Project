package readers;

import java.util.Scanner;

public class ReaderService {
	
	public static Reader readerInfo = null;
	Scanner sc = new Scanner(System.in);
	public void login() {
		System.out.println("아이디를 입력하세요>");
		String id = sc.nextLine();
		
		System.out.println("비밀번호를 입력하세요>");
		String pw = sc.nextLine();
		
		Reader reader = ReaderDAO.getInstance().login(id);
	}
}
