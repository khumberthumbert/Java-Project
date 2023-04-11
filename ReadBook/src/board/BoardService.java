package board;

import java.util.List;
import java.util.Scanner;

public class BoardService {
	
	Scanner sc = new Scanner(System.in);
	
	public void getBoardList() {
		List<BoardDTO> list = BoardDAO.getInstance().getBoardList();
		//리스트를 받으려고 리스트를 만들었고 BoardDAO안에 static 메서드 이기 떄문에 클래스이름.getInstance()만 하면 되는데 왜 이걸 해야하냐면
		//싱글톤은 private라서 들고 올 수 없기 때문에 한다. 그리고 뒤에 우리가 만든 메서드 호출한다.
		for(BoardDTO b : list) {
			System.out.println(b.getNumber()+ "\t" + b.getName() + "\t"+ b.getTitle()+ "\t" + b.getDate());
			
		}
		
	}
	public void getBoard() {
		System.out.println("조회하실 글의 번호를 입력해주세요");
		int num = Integer.parseInt(sc.next());
		BoardDTO list = BoardDAO.getInstance().getBoard(num);
		System.out.println("---------------------------------------");
		System.out.printf("글 번호 %d %s %s\n",list.getNumber(),list.getTitle(), list.getDate());
		System.out.printf("작성자 : %s\n", list.getName());
		System.out.println("--------------------------------------");
		System.out.printf("%s\n", list.getContent());
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
	
}
