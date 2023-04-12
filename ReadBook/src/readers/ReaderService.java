package readers;

import java.util.List;
import java.util.Scanner;

public class ReaderService {
	
	public static ReaderDTO readerInfo = null;
	Scanner sc = new Scanner(System.in);

	//로그인
	public void login() {
		System.out.println("아이디를 입력하세요>");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요>");
		String pw = sc.nextLine();
		ReaderDTO reader = new ReaderDTO();
		reader = ReaderDAO.getInstance().login(id);
		
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

	//1. 전체 조회
	public void getReaderList() {
		List<ReaderDTO> list = ReaderDAO.getInstance().getReaderList();
		
		for(ReaderDTO reader : list) {
			System.out.println("=====================================================================================================");
			System.out.println("ID : " + reader.getId() + "\tPW : " + reader.getPw() + "\t닉네임 :" + reader.getName() + "\t등급 : " + reader.getGrade());
			System.out.println("=====================================================================================================");
			
		}
	}
	//회원 등록
	public void readerAdd() {
		String id = "";
		while(true) {
			System.out.println("아이디를 입력하세요>");
			id = sc.nextLine();
			ReaderDTO reader = ReaderDAO.getInstance().login(id);
			if(reader == null) {
				System.out.println("회원 가입 가능");
				break;
			}else {
				System.out.println("ID 중복, 재입력");
			}
		}
		
		System.out.println("비밀번호를 입력하세요>");
		String pw = sc.nextLine();
		System.out.println("닉네임을 입력하세요>");
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
	
	public void updateReader() {
		while(true) {
			System.out.println("변경할 곳의 번호를 입력해주세요>");
			System.out.println("1. 비밀번호 | 2. 좋아하는 책 | 3. 문장 | 4. 작가 | 5. 출판사 | 6. 등급 | 7. 읽고 싶은 책 | 99. 나가기");
			int num = Integer.parseInt(sc.nextLine());
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
			}else if (num ==6)	{
				clean(num);
			}else if (num == 7)	{
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
	//내 정보 보기
	public void myInfo() {
		ReaderDTO reader = ReaderDAO.getInstance().getReader();
		System.out.println("나의 아이디 : " + reader.getId());
		System.out.println("나의 닉네임 : " + reader.getName());
		System.out.println("나의 비밀번호 : " + reader.getPw());
		System.out.println("좋아하는 책 : " + reader.getLove());
		System.out.println("좋아하는 문장 : " + reader.getSentence());
		System.out.println("좋아하는 작가 : " + reader.getAuthor());
		System.out.println("좋아하는 출판사 : " + reader.getCompany());
		System.out.println("당신의 등급 : " + reader.getGrade());
		
		//null값이 안나오게 하고 싶음.
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
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
	}
	//로그아웃
	public void logout() {
		if(readerInfo != null) {
			readerInfo = null;
			System.out.println("로그아웃 되었습니다");
	}
	}
}
	
