package exe;

import java.util.Scanner;

import board.BoardService;
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
				}else if(menu ==2) {
					rs.updateReader();					
				}else if(menu == 3) {
					rs.readerAdd();					
				}else if(menu == 4) {
					rs.readerDelete();					
				}else if(menu == 99) {
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
				rs.myInfo();
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
			System.out.println("1. 글 조회  |  2. 글 작성  |  3. 글 삭제  |  99. 나가기 ");
			int number = Integer.parseInt(sc.nextLine());
			if(number ==1) {
				deepBoardScreen();
			}else if(number ==2) {
				bs.boardInsert();
			}else if(number ==3) {
				bs.boardDelete();
				//글 삭제 기능 (본인만 삭제 가능하게(관리자는 다 삭제 가능) , 삭제시 글 번호 삭제된 번호보다 큰 번호들은 1씩 내려주기 ex) 3번 삭제시 -> 12 456  -> 12 345 만들어주기)
			}else if (number ==99){
				break;
			}
		}
	}
	
	private void deepBoardScreen() {
			while(true) {
			bs.getBoard();
			cs.getCommentsList();
			System.out.println(" 1. 댓글 작성  |  2. 댓글 삭제 |  3. 나가기");
			System.out.println("입력 >");
			int no = Integer.parseInt(sc.nextLine());
			if(no == 1) {
				//댓글작성기능
				cs.commentsList();
			}else if(no ==2) {
				//댓글 삭제 기능 ( 본인것만 삭제가능 ( 관리자는 그냥 가능) , 번호 갱신)
				cs.commentsDelete();
			}else if(no ==3) {
				BoardService.currentBoard =null;
				break;
			}
		}
	}
}
		
	
