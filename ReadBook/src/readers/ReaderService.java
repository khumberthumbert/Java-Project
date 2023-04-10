package readers;

import java.util.List;
import java.util.Scanner;

public class ReaderService {
	
	public static ReaderDTO readerInfo = null;
	Scanner sc = new Scanner(System.in);
	public void login() {
		System.out.println("아이디를 입력하세요>");
		String id = sc.nextLine();
		
		System.out.println("비밀번호를 입력하세요>");
		String pw = sc.nextLine();
		
		ReaderDTO reader = ReaderDAO.getInstance().login(id);
		
		if(reader != null) {
			if(reader.getPw().equals(pw)) {
				System.out.println("로그인 되었습니다.");
				readerInfo = reader;
			}else{
				System.out.println("비밀번호가 틀렸습니다.");
			}
		}else {
			System.out.println("아이디가 존재하지 않습니다.");
		}
	}
	//로그아웃
	public void logout() {
		readerInfo = null;
		System.out.println("로그 아웃 성공");
	}
	//1. 전체 조회
	public void getReaderList() {
		List<ReaderDTO> list = ReaderDAO.getInstance().getReaderList();
		
		for(ReaderDTO reader : list) {
			System.out.println("ID : " + reader.getId());
			System.out.println("PW : " + reader.getPw());
			System.out.println("NM : " + reader.getName());
		}
	}
	//회원 등록
	public void readerAdd() {
		String id = "";
		while(true) {
			System.out.println("ID>");
			id = sc.nextLine();
			ReaderDTO reader = ReaderDAO.getInstance().login(id);
			if(reader == null) {
				System.out.println("회원 가입 가능");
				break;
			}else {
				System.out.println("ID 중복, 재입력");
			}
		}
		
		System.out.println("PW>");
		String pw = sc.nextLine();
		System.out.println("닉네임>");
		String name = sc.nextLine();
		
		ReaderDTO reader = new ReaderDTO();
		reader.setId(id);
		reader.setPw(pw);
		reader.setName(name);
		
		int result = ReaderDAO.getInstance().readerAdd(reader);
		if(result >0) {
			System.out.println("아이디 생성 성공");
		}else {
			System.out.println("아이디 생성 실패");
		}
	}
	//회원 수정
//	public void readerUpdate() {
//		System.out.println("변경할 내용>");
//		
//		System.out.println("ID>");
//		String id = sc.nextLine();
//		System.out.println("PW>");
//		String pw = sc.nextLine();
//		
//		ReaderDTO reader = new ReaderDTO();
//		reader.setId(id);
//		reader.setPw(pw);
//		
//		int result = ReaderDAO.getInstance().readerUpdate(reader);
//		
//		if(result>0) {
//			if(id.equals(readerInfo.getId())) {
//				readerInfo = ReaderDAO.getInstance().login(id);
//			}
//		}else {
//			System.out.println("PW 변경 실패");
//		}
//	}
//	//좋아하는 책 수정
//	public void loveUpdate() {
//		System.out.println("좋아하는 책>");
//		String love = sc.nextLine();
//		
//		ReaderDTO reader = new ReaderDTO();
//		reader.setLove(love);
//		
//		int result = ReaderDAO.getInstance().loveUpdate(reader);
//		if(result >0) {
////			if(love.equals(readerInfo.getId())) {
////				readerInfo = null;
////			}
//			System.out.println("변경 완료");
//		}else {
//			System.out.println("변경 실패");
//		}
//		//if절 실험
//	}
//	//좋아하는 문장 수정
//	public void sentenceUpdate() {
//		System.out.println("좋아하는 문장>");
//		String sentence = sc.nextLine();
//		
//		ReaderDTO reader = new ReaderDTO();
//		reader.setSentence(sentence);
//		
//		int result = ReaderDAO.getInstance().sentenceUpdate(reader);
//		if(result >0) {
//			if(sentence.equals(readerInfo.getId())) {
//				readerInfo = null;
//			}
//		}else {
//			System.out.println("삭제 완료");
//		}
//	}
//	//좋아하는 작가 수정
//	public void authorUpdate() {
//		System.out.println("좋아하는 작가>");
//		String author = sc.nextLine();
//		
//		ReaderDTO reader = new ReaderDTO();
//		reader.setAuthor(author);
//		int result = ReaderDAO.getInstance().authorUpdate(reader);
//		if(result >0) {
//			if(author.equals(readerInfo.getId())) {
//				readerInfo = null;
//			}
//		}else {
//			System.out.println("삭제 완료");
//		}
//	}
//	//좋아하는 출판사 수정
//	public void companyUpdate() {
//		System.out.println("좋아하는 출판사>");
//		String company = sc.nextLine();
//		
//		ReaderDTO reader = new ReaderDTO();
//		reader.setAuthor(company);
//		int result = ReaderDAO.getInstance().companyUpdate(reader);
//		if(result >0) {
//			if(company.equals(readerInfo.getId())) {
//				readerInfo = null;
//			}
//		}else {
//			System.out.println("삭제 완료");
//		}
//	}
	
	public void updateReader() {
		while(true) {
			System.out.println("변경할 곳의 번호를 입력해주세요>");
			System.out.println("1. 비밀번호 | 2. 좋아하는 책 | 3. 문장 | 4. 작가 | 5. 출판사 | 99. 나가기");
			int num = Integer.parseInt(sc.nextLine());
//			System.out.println("변경할 내용");
//			String shift = sc.nextLine();
			//int result = ReaderDAO.getInstance().updateReader(shift, num);
//			if(result > 0) {
//				System.out.println("변경 성공");
//			} else {
//				System.out.println("변경 실패");
			if(num == 1) {
				clean(num);
			} else if (num ==2) {
				clean(num);
			} else if (num ==3) {
				clean(num);
			}else if (num ==4) {
				clean(num);
			}else if (num ==5) {
				clean(num);
			}else if (num ==99) {
				break;
			}
		}
	}
	private void clean(int num) {
		System.out.println("변경할 내용");
		String shift = sc.nextLine();
		int result = ReaderDAO.getInstance().updateReader(shift, num);
		if(result > 0) {
			System.out.println("변경 성공");
		} else {
			System.out.println("변경 실패");
		}	
	}
	
	//정보 업데이트
	public void infoUpdate() {
		System.out.println("");
	}
	//읽고 싶은 책
	public void wantBook() {
		System.out.println("책 이름>");
		String book = sc.nextLine();
		System.out.println("저자 이름>");
		String author = sc.nextLine();
	}
	//회원 삭제
	public void readerDelete() {
		System.out.println("삭제 ID>");
		String id = sc.nextLine();
		int result = ReaderDAO.getInstance().readerDelete(id);
		if(result >0) {
			if(id.equals(readerInfo.getId())) {
				readerInfo = null;
			}
		}else {
			System.out.println("삭제 완료");
		}
	}
	
	
}
