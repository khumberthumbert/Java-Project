package board;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

import readers.ReaderService;

public class BoardDAO extends DAO {
	
	private static BoardDAO boardDao = new BoardDAO(); //객체 하나로만 디비 접근
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		return boardDao;
	}
	
	public List<BoardDTO> getBoardList(){
		List<BoardDTO> list = new ArrayList();
		
		BoardDTO boardDto = null;
		try {
			conn();
			String sql = "SELECT * FROM board order By board_number";
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardDto = new BoardDTO();
				boardDto.setNumber(rs.getInt("board_number"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setDate(rs.getDate("board_date"));
				boardDto.setView(rs.getInt("board_view"));
				boardDto.setName(rs.getString("name"));
				
				list.add(boardDto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			disconn();
		}return list;
	}
	public BoardDTO getBoard(int no) {
		BoardDTO boardDto = null;
		try {
			conn();
			String sql = "SELECT * FROM board where board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery(); //이거
			if(rs.next()) {
				boardDto = new BoardDTO();
				boardDto.setNumber(rs.getInt("board_number"));
				boardDto.setTitle(rs.getString("title"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setDate(rs.getDate("board_date"));
				boardDto.setView(rs.getInt("board_view"));
				boardDto.setName(rs.getString("name"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			disconn();
		} return boardDto;
		}
	public int boardInsert(BoardDTO d) {
		int result = 0;
		try {
			conn();
			
			String sql = "Insert into board values (NVL((SELECT max(board_number) FROM board)+1,1),?,?,sysdate,0,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, d.getTitle());
			pstmt.setString(2, d.getContent());
			pstmt.setString(3, ReaderService.readerInfo.getName());
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconn();
		}return result;
	}
	public int boardUpdate(String content) {
		int result = 0;
		try {
			conn();
			
			String sql = "Update board set content =? where board_number = ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, BoardService.currentBoard.getNumber());
			result = pstmt.executeUpdate();
		}catch(Exception e ) {
			e.printStackTrace();
		}finally {
			disconn();
		}return result;
	}
	public int boardDelete(int number) {
		int result = 0;
		try {
			conn();
			String sql = "delete from board where board_number =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			result = pstmt.executeUpdate();
			if(result > 0) {
				String sql2 = "update board set board_number = board_number -1 where board_number > ?";
				pstmt =conn.prepareStatement(sql2);
				pstmt.setInt(1, number);
				pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
}
