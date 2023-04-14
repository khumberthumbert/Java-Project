package board;

import java.util.List;
import java.util.Scanner;

import readers.ReaderDAO;
import readers.ReaderService;

public class BoardService {
	public static BoardDTO currentBoard = null;
	Scanner sc = new Scanner(System.in);
	
	public void getBoardList() {
		List<BoardDTO> list = BoardDAO.getInstance().getBoardList();
		//리스트를 받으려고 list변수를 만들었고 BoardDAO안에 static 메서드 이기 떄문에 클래스이름.getInstance()만 하면 되는데 왜 이걸 해야하냐면
		//싱글톤은 private라서 들고 올 수 없기 때문에 한다. 그리고 뒤에 우리가 만든 메서드 호출한다.
		for(BoardDTO b : list) {
			System.out.println(b.getNumber()+ "\t" + b.getName() + "\t\t"+ b.getTitle()+ "\t\t" + b.getDate());
		}
	}
	public void getBoard() {
		if(BoardService.currentBoard == null) {
		System.out.println("조회하실 글의 번호를 입력해주세요");
		int num = Integer.parseInt(sc.next());
		BoardDTO list = BoardDAO.getInstance().getBoard(num);
		BoardService.currentBoard = list;
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("글 번호 [%d] %s %s\n",list.getNumber(),list.getTitle(), list.getDate());
		System.out.printf("작성자 : %s\n", list.getName());
		System.out.println("------------------------------------------------------------------------------------");
		System.out.printf("%s\n", list.getContent());
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
		}else {
			System.out.println("------------------------------------------------------------------------------------");
			System.out.printf("글 번호 %d %s %s\n",BoardService.currentBoard.getNumber(),BoardService.currentBoard.getTitle(),BoardService.currentBoard.getDate());
			System.out.printf("작성자 : %s\n", BoardService.currentBoard.getName());
			System.out.println("--------------------------------------");
			System.out.printf("%s\n", BoardService.currentBoard.getContent());
			System.out.println();
			System.out.println("------------------------------------------------------------------------------------");
		}
	}
	public void boardInsert() {
		System.out.println("제목을 입력하세요>");
		String title = sc.nextLine();
		System.out.println("내용을 입력하세요>");
		String content = sc.nextLine();
		
		BoardDTO list = new BoardDTO();
		list.setTitle(title);
		list.setContent(content);
		int result = BoardDAO.getInstance().boardInsert(list);
		if(result >0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}
	public void boardDelete() {
		//글 삭제 기능 (본인만 삭제 가능하게(관리자는 다 삭제 가능) , 삭제시 글 번호 삭제된 번호보다 큰 번호들은 1씩 내려주기 ex) 3번 삭제시 -> 12 456  -> 12 345 만들어주기)
		System.out.println("삭제할 글의 번호를 입력하세요.");
		System.out.println("입력 >");
		int no = Integer.parseInt(sc.nextLine());
		if(ReaderService.readerInfo.getGrade().equals("관리자") || BoardDAO.getInstance().getBoard(no).getName().equals(ReaderService.readerInfo.getName())) {
			int result = BoardDAO.getInstance().boardDelete(no);
			if(result > 0) {
				System.out.println("삭제 완료");
			}else {
				System.out.println("삭제 실패");
			}
		}else {
			System.out.println("본인의 글만 지울 수 있습니다.");
			
		}
	}
	public void boardUpdate() {
		String str = sc.nextLine();
		System.out.printf(str);
		if(ReaderService.readerInfo.getGrade().equals("관리자") || BoardDAO.getInstance().getBoard(BoardService.currentBoard.getNumber()).getName().equals(ReaderService.readerInfo.getName())) {
			System.out.println("수정할 내용을 입력하세요");
			System.out.println("입력 >");
			String content = sc.nextLine();
		int result = BoardDAO.getInstance().boardUpdate(content);
		
			if(result > 0) {
				BoardDTO b = BoardDAO.getInstance().getBoard(BoardService.currentBoard.getNumber());
				BoardService.currentBoard = b;
				System.out.println("수정 완료");
			}else {
				System.out.println("수정 실패");
			}
		}else {
			System.out.println("본인의 글만 수정할 수 있습니다.");
			
		}
	}
}
