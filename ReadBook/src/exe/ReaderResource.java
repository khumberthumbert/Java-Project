package exe;

import java.util.Scanner;

import board.BoardService;
import comments.CommentsDAO;
import comments.CommentsService;
import readers.ReaderService;
 
public class ReaderResource {
	int menu = 0;
	ReaderService rs = new ReaderService();
	BoardService bs = new BoardService();
	CommentsService cs = new CommentsService();
	Scanner sc = new Scanner(System.in);
	
	public ReaderResource() {
		exe();
	}
	private void exe() {
		boolean run1 = true;
		while(run1) {
		boolean run = true;
		String menu = "";
		while(run) {
			System.out.println("1. 로그인 | 2. 회원가입 | 99. 종료");
			System.out.println("메뉴>");
			menu = sc.nextLine();
			if(menu.equals("1")) {
				rs.login();
				break;
			}else if(menu.equals("2")){
				rs.readerAdd();
				rs.login();
				break;
			}
			else if(menu.equals("99")) {
				System.out.println("종료");
				run =false;
				run1 = false;
				break;
			}else {
				System.out.println("정확한 번호를 입력하세요");
			}
		}
		if(ReaderService.readerInfo != null) {
			while(true) {
				if(ReaderService.readerInfo.getGrade().equals("관리자")) {
				System.out.println("1. 회원 조회 | 2. 정보 수정 | 3. 회원등록 | 4. 회원탈퇴 | 5. 내 정보보기 | 6. 게시판 보기 | 99. 로그 아웃");
				System.out.println("메뉴>");
				String menu1 = "";
				menu1 = sc.nextLine();
				if(menu1.equals("1")) {
						rs.getReaderList();
					}else if(menu1.equals("2")) {
						rs.updateReader();					
					}else if(menu1.equals("3")) {
						rs.readerAdd();					
					}else if(menu1.equals("4")) {
						rs.readerDelete();
					}else if(menu1.equals("5")) {
						rs.myInfo();
					}else if(menu1.equals("6")) {
						boardScreen();
					}else if(menu1.equals("99")) {
						rs.logout();
						break;
					}else {
						System.out.println("정확한 번호를 입력하세요");
					}
				} else {
					System.out.println("1. 내 정보 수정 | 2. 내 정보 보기 | 3. 게시판 보기 | 99.로그아웃");
					System.out.println("메뉴>"); 
					String menu2 = "";
					menu2 = sc.nextLine();
					if(menu2.equals("1")) {
						rs.updateReader2();
					}else if(menu2.equals("2")){
						rs.myInfo();
					}else if(menu2.equals("3")) {
						boardScreen();
					}else if(menu2.equals("99")) {
						rs.logout();
						break;
					}else {
						System.out.println("정확한 번호를 입력하세요");
				}
			}
		}
	}else {
		
	}
		}
	}
	private void boardScreen() {
		while(true) {
			bs.getBoardList();
			System.out.println("1. 글 조회  |  2. 글 작성  |  3. 글 삭제  |  99. 나가기 ");
			String num = sc.nextLine();
			if(num.equals("1")) {
				deepBoardScreen();
			}else if(num.equals("2")) {
				bs.boardInsert();
			}else if(num.equals("3")) {
				bs.boardDelete();
			}else if (num.equals("99")){
				break;
			}else {
				System.out.println("정확한1 번호를 입력하세요.");
			}
		}
	}
	private void deepBoardScreen() {
		
			while(true) {
				bs.getBoard();
				cs.getCommentsList();
				System.out.println(" 1. 댓글 작성  |  2. 댓글 삭제 | 3. 글 수정하기 |  99. 나가기");
				System.out.println("입력 >");
				String no = sc.nextLine();
				if(no.equals("1")) {
					cs.commentsList();
				}else if(no.equals("2")) {
					cs.commentsDelete();
				}else if(no.equals("3")) {
					bs.boardUpdate();
				}else if(no.equals("99")) {
					BoardService.currentBoard =null;
					break;
				}else {
					System.out.println("정확한 번호 입력하세요");
				}
			}
	}
}

 

	
