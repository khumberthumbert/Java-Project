package readers;

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
				reader.setGrade(rs.getString("grade"));
			}
		}catch(Exception e)	{
			e.printStackTrace();
		}finally {
			disconn();
		}
		return reader;
	}
	//1. 전체조회
	public List<ReaderDTO> getReaderList() {
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
				reader.setGrade(rs.getString("grade"));
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
			String sql = "insert into reader (id, pw, name, grade) values(?,?,?,'독린이')";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reader.getId());
			pstmt.setString(2, reader.getPw());
			pstmt.setString(3, reader.getName());
			//pstmt.setString(4, reader.getGrade());
			
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
			System.out.println(e.getMessage());
		}finally {
			disconn();
		}
		return result;
	}
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
			} else if(num ==6) {
				String sql = "update reader set grade = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, shift);
			}
			pstmt.setString(2, ReaderService.readerInfo.getId());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			disconn();
		}
		return result;
	}//update, delete, insert 는 int 반환.
	//내 정보 보기
	public ReaderDTO getReader() {
		ReaderDTO reader = null;
		try {
			conn();
			String sql = "SELECT id, pw, name, grade, love, sentence, author, company\r\n"
					+ " FROM reader"
					+ " where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ReaderService.readerInfo.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reader = new ReaderDTO();
				reader.setId(rs.getString("id"));
				reader.setPw(rs.getString("pw"));
				reader.setName(rs.getString("name"));
				reader.setGrade(rs.getString("grade"));
				reader.setLove(rs.getString("love"));
				reader.setSentence(rs.getString("sentence"));
				reader.setAuthor(rs.getString("author"));
				reader.setCompany(rs.getString("company"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return reader;
	}
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
