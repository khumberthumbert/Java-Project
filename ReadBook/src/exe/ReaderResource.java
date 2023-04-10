package exe;

import java.util.Scanner;

import readers.ReaderService;

public class ReaderResource {
	int menu = 0;
	ReaderService rs = new ReaderService();
	Scanner sc = new Scanner(System.in);
	
	public ReaderResource() {
		run();
	}
	
	public void run() {
		exe();
	}
	private void exe() {
		boolean run = true;
		String menu = "";
		while(run) {
			if(ReaderService.readerInfo != null) {
				System.out.println("1. 회원 조회 | 2. 정보 수정 | 3. 회원등록 | 99. 로그 아웃");
				System.out.println("메뉴>");
				menu = sc.nextLine();
				switch(menu) {
				case "1" :
					rs.getReaderList();
					break;
				case "2" :
					rs.updateReader();
					break;
				case "3" :
					rs.readerAdd();
					break;
				case "99" :
					rs.logout();
					break;
				}
			}
			else if(ReaderService.readerInfo == null) {
				System.out.println("1. 로그인 | 2. 종료");
				System.out.println("메뉴");
				menu=sc.nextLine();
				if(menu.equals("1")) {
					rs.login();
				}else {
					System.out.println("종료");
					break;
				}
			}
		}
	}
	private void menu() {
		System.out.println("1. 로그인 | 2. 종료");
		System.out.println("메뉴>");
		menu = Integer.parseInt(sc.nextLine());
		if(menu ==1) {
			login();
		}else {
			System.out.println("종료");
		}
	}
	private void login() {
		rs.login();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
