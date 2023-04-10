package readers;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class ReaderDAO extends DAO{

	//SingleTon
	private static ReaderDAO readerDao = null;
	
	private ReaderDAO() {}
	
	public static ReaderDAO getInstance() {
		if(readerDao == null) {
			readerDao = new ReaderDAO();
		}
		return readerDao;
	}
	
	//1. 로그인
	public ReaderDTO login(String id) {
		ReaderDTO reader = null;
		
		try {
			conn();
			String sql = "SELECT * FROM reader where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reader = new ReaderDTO();
				reader.setId(rs.getString("id"));
				reader.setPw(rs.getString("pw"));
				reader.setName(rs.getString("name"));
			}
		}catch(Exception e)	{
			e.printStackTrace();
		}finally {
			disconn();
		}
		return reader;
	}
	/*
	 * // 1. 회원 조회 public List<ReaderDTO> getReaderList(){ List<ReaderDTO> list =
	 * new ArrayList<>(); ReaderDTO reader = null; try { conn(); String sql =
	 * "SELECT * FROM reader" + " WHERE id = ?"; pstmt = conn.prepareStatement(sql);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()) { reader = new ReaderDTO();
	 * reader.setId(rs.getString("id")); reader.setPw(rs.getString("pw"));
	 * reader.setName(rs.getString("name"));
	 * 
	 * list.add(reader); } }catch(Exception e) { e.printStackTrace(); }finally {
	 * disconn(); } return list; }
	 */
	
	//1. 전체조회
	public List<ReaderDTO>	getReaderList() {
		List<ReaderDTO> list = new ArrayList<>();
		ReaderDTO reader = null;
		try {
			conn();
			String sql = "SELECT * FROM reader";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reader = new ReaderDTO(); //객체생성
				reader.setId(rs.getString("id"));
				reader.setPw(rs.getString("pw"));
				reader.setName(rs.getString("name"));
				list.add(reader);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	
	//2. 회원 등록
	public int readerAdd(ReaderDTO reader) {
		int result = 0;
		try {
			conn();
			String sql = "insert into reader (id, pw, name) values(?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reader.getId());
			pstmt.setString(2, reader.getPw());
			pstmt.setString(3, reader.getName());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	//3. 회원 수정 - pw 변경
	public int readerUpdate(ReaderDTO reader) {
		int result = 0 ;
		try {
			conn();
			String sql = "update reader set pw = ? where id = ?";
			pstmt.setString(1, reader.getPw());
			pstmt.setString(2, reader.getId());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	//4. 정보입력
	//4.1 좋아하는 책
//	public int loveUpdate(ReaderDTO reader) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "update reader set love = ?";
//			pstmt.setString(1, reader.getLove());
//			result = pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//		return result;
//	}
//	//4.2 좋아하는 문장
//	public int sentenceUpdate(ReaderDTO reader) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "update reader set sentence = ?";
//			pstmt.setString(1, reader.getSentence());
//			result = pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//		return result;
//	}
//	//4.3 좋아하는 작가
//	public int authorUpdate(ReaderDTO reader) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "update reader set author = ?";
//			pstmt.setString(1, reader.getAuthor());
//			result = pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//		return result;
//	}
//	//4.4 좋아하는 출판사
//	public int companyUpdate(ReaderDTO reader) {
//		int result = 0;
//		try {
//			conn();
//			String sql = "update reader set company = ?";
//			pstmt.setString(1, reader.getCompany());
//			result = pstmt.executeUpdate();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			disconn();
//		}
//		return result;
//	}
	//수정
	public int updateReader(String shift, int num) {
		int result = 0;
		try {
			conn();
			if(num == 1) {
				String sql = "update reader set pw = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shift);
			} else if (num ==2) {
				String sql = "update reader set love = ? where id =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shift);
			} else if (num == 3) {
				String sql = "update reader set sentence = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shift);
			} else if(num ==4) {
				String sql = "update reader set author = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shift);
			} else if(num == 5) {
				String sql = "update reader set company = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shift);
			}
			pstmt.setString(2, ReaderService.readerInfo.getId());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}//update, delete, insert 는 int 반환.
	
	//99. 회원 삭제
	public int readerDelete(String id) {
		int result = 0;
		try {
			conn();
			String sql = "delete from reader where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
