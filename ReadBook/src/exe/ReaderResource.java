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
		boolean run = true;
		String menu = "";
		while(run) {
			System.out.println("1. 로그인 | 2. 회원가입 | 99. 종료");
			System.out.println("메뉴>");
			menu = sc.nextLine();
			if(menu.equals("1")) {
				rs.login();
			}else if(menu.equals("2")){
				rs.readerAdd();
				rs.login();
			}
			else if(menu.equals("99")) {
				System.out.println("종료");
				break;
			}else {
				System.out.println("정확한 번호를 입력하세요");
			}run = false;
			}
//		}catch (Exception e){
//			System.out.println("지정된 숫자 \"1, 2, 3\" 중에서 입력해주세요.");
//			System.out.println("에러 메세지 : " + e.getMessage());
//			e.printStackTrace();
			//try{
				while(true) {
				if(ReaderService.readerInfo.getGrade().equals("관리자")) {
				System.out.println("1. 회원 조회 | 2. 정보 수정 | 3. 회원등록 | 4. 회원탈퇴 | 5. 내 정보보기 | 99. 로그 아웃");
				System.out.println("메뉴>");
				menu = sc.nextLine();
				if(menu.equals("1")) {
						rs.getReaderList();
					}else if(menu.equals("2")) {
						rs.updateReader();					
					}else if(menu.equals("3")) {
						rs.readerAdd();					
					}else if(menu.equals("4")) {
						rs.readerDelete();
					}else if(menu.equals("5")) {
						rs.myInfo();
					}else if(menu.equals("99")) {
						rs.logout();
						break;
					}else {
						System.out.println("정확한 번호를 입력하세요");}
				} else {
					System.out.println("1. 내 정보 수정 | 2. 내 정보 보기 | 3. 게시판 보기 | 99.로그아웃");
					System.out.println("메뉴>");
					menu = sc.nextLine();
					if(menu.equals("1")) {
						rs.updateReader();
					}else if(menu.equals("2")){
						rs.myInfo();
					}else if(menu.equals("3")) {
						boardScreen();
					}else if(menu.equals("99")) {
						rs.logout();
						break;
					}else {
						System.out.println("정확한 번호를 입력하세요");
					
				}
			
			}
//			}catch (Exception f) {
//				System.out.println("지정된 숫자 중에서 입력해주세요.");
//				System.out.println("에러 메세지 : " + f.getMessage());
//				f.printStackTrace();
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
				//글 삭제 기능 (본인만 삭제 가능하게(관리자는 다 삭제 가능) , 삭제시 글 번호 삭제된 번호보다 큰 번호들은 1씩 내려주기 ex) 3번 삭제시 -> 12 456  -> 12 345 만들어주기)
			}else if (num.equals("99")){
				break;
			}
		}
	}
	
	private void deepBoardScreen() {
		
			while(true) {
				bs.getBoard();
				cs.getCommentsList();
				System.out.println(" 1. 댓글 작성  |  2. 댓글 삭제 |  99. 나가기");
				System.out.println("입력 >");
				String no = sc.next();
				if(no.equals("1")) {
					//댓글작성기능
					cs.commentsList();
				}else if(no.equals("2")) {
					//댓글 삭제 기능 ( 본인것만 삭제가능 ( 관리자는 그냥 가능) , 번호 갱신)
					cs.commentsDelete();
				}else if(no.equals("99")) {
					BoardService.currentBoard =null;
					break;
				}
			}
	}
	}
 

	
