package comments;

import java.util.List;
import java.util.Scanner;

import board.BoardService;
import readers.ReaderService;


public class CommentsService {
	Scanner sc = new Scanner(System.in);
	//전체 조회 기능
	public void getCommentsList() {
		List<CommentsDTO> list = CommentsDAO.getInstance().getCommentsList();
		
		for(CommentsDTO c : list) {
			System.out.println(c.getNum() +"\t"+ c.getName() + " : " + c.getContents() + "\t" + c.getDate());
		}
	}
	//댓글 입력
	public void commentsList() {
		System.out.println("댓글을 입력하세요>");
		String name = sc.nextLine();
		int result = CommentsDAO.getInstance().commentsInsert(name);
		if(result >0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}
	//댓글 삭제
	public void commentsDelete() {
		System.out.println("삭제할 댓글의 번호를 입력하세요.");
		System.out.println("입력>");
		int no = Integer.parseInt(sc.nextLine());
		if(ReaderService.readerInfo.getGrade().equals("관리자") || CommentsDAO.getInstance().getComments(no).getName().equals(ReaderService.readerInfo.getName())) {
			int result = CommentsDAO.getInstance().commentsDelete(no);
			if(result >0) {
				System.out.println("삭제 완료");
			}else {
				System.out.println("삭제 실패");
			}
		}else {
			System.out.println("본인의 댓글만 지울 수 있습니다.");
		}
	}

}
