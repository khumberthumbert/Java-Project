package exe;

import java.util.Scanner;

import board.BoardService;
import readers.ReaderService;
 
public class ReaderResource {
	int menu = 0;
	ReaderService rs = new ReaderService();
	BoardService bs = new BoardService();
	Scanner sc = new Scanner(System.in);
	
	public ReaderResource() {
		exe();
	}
	
	
	private void exe() {
		int menu = 0;
		while(true) {
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 종료");
			System.out.println("메뉴>");
			menu = Integer.parseInt(sc.nextLine());
			if(menu ==1) {
				rs.login();
			}else if(menu ==2){
				rs.readerAdd();
				rs.login();
			}
			else if(menu ==3) {
				System.out.println("종료");
				break;
			}
		while(true) {
			 if(ReaderService.readerInfo.getGrade().equals("관리자")) {
				System.out.println("1. 회원 조회 | 2. 정보 수정 | 3. 회원등록 | 4. 회원탈퇴 | 99. 로그 아웃");
				System.out.println("메뉴>");
				menu = Integer.parseInt(sc.nextLine());
				if(menu == 1) {
					rs.getReaderList();
				}
				else if(menu ==2) {
					rs.updateReader();					
				}
				else if(menu == 3) {
					rs.readerAdd();					
				}
				else if(menu == 4) {
					rs.readerDelete();					
				}
				else if(menu == 99) {
					rs.logout();
					break;				
				}			
				} else {
			System.out.println("1. 내 정보 수정 | 2. 내 정보 보기 | 3. 게시판 보기 | 99.로그아웃");
			System.out.println("메뉴>");
			menu = Integer.parseInt(sc.nextLine());
			if(menu == 1) {
				rs.updateReader();
			}else if(menu == 2){
				rs.myInfo(); //내 정보보기 만들어야함.
			}else if(menu == 3) {
				boardScreen();
			}else if(menu == 99) {
				rs.logout();
				break;
			}
					
			}
		}
		}
	}
	private void boardScreen() {
		while(true) {
			bs.getBoardList();
			System.out.println("1. 글 조회 2. 글 작성| 99. 나가기 ");
			int number = Integer.parseInt(sc.nextLine());
			if(number ==1) {
				bs.getBoard();
			}else if(number ==2) {
				bs.boardInsert();
			}else if (number ==99){
				break;
			}
		}
	}
}
	
//	private void menu() {
//		System.out.println("1. 로그인 | 2. 회원가입 | 3. 종료");
//		System.out.println("메뉴>");
//		menu = Integer.parseInt(sc.nextLine());
//		if(menu ==1) {
//			login();
//		}else if(menu ==2){
//			//회원가입 칸
//		}
//		else if(menu ==3) {
//			System.out.println("종료");
//			boolean run = false;
//		}
//	}
		
	
