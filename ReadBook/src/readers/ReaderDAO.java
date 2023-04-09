package readers;


import java.util.ArrayList;

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
	public Reader login(String id) {
		Reader reader = null;
		
		try {
			conn();
			String sql = "SELECT * FROM reader where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reader = new Reader();
				reader.setId(rs.getString("id"));
				reader.setPw(rs.getString("pw"));
			}
		}catch(Exception e)	{
			e.printStackTrace();
		}finally {
			disconn();
		}
		return reader;
	}
	public List<Reader> getReaderList(){
		List<Reader> list = new ArrayList<>();
		Reader reader = null;
		try {
			conn();
			String sql = "SELECT * FROM reader";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs	= pstmt.executeQuery();
			
			if(rs.next()) {
				reader = new reader();
				reader.setId(rs.getString("id"));
				reader.setPw(rs.getString("pw"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return reader;
	}
	
	public List<Reader>	getReaderList() {
		List<Reader> list = new ArrayList<>();
		Reader reader = null;
		try {
			conn();
			String sql = "SELECT * FROM reader";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pstmt.setId(rs.getString("id"));
				pstmt.setPw(rs.getString("pw"));
				list.add(reader);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
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
