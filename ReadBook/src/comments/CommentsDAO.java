package comments;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

import board.BoardDTO;
import board.BoardService;
import readers.ReaderService;

public class CommentsDAO extends DAO{
	private static CommentsDAO commentsDao = new CommentsDAO();
	
	private CommentsDAO() {}
	
	public static CommentsDAO getInstance() {
		return commentsDao;
	}
	public List<CommentsDTO> getCommentsList() {
		List<CommentsDTO> list = new ArrayList();
		
		CommentsDTO commentsDto = null;
		try {
			conn();
			
			String sql = "SELECT * FROM comments";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				commentsDto = new CommentsDTO();
				commentsDto.setBoardNumber(rs.getInt("board_number"));
				commentsDto.setNum(rs.getInt("num"));
				commentsDto.setContents(rs.getString("name"));
				commentsDto.setName(rs.getString("contents"));
				commentsDto.setDate(rs.getDate("comments_date"));
				
				list.add(commentsDto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}return list;
	}
	//댓글쓰기
	public int commentsInsert(String content) {
		int result = 0;
		try {
			conn();
			
			String sql = "Insert into comments values (?,NVL((SELECT max(num) FROM comments)+1,1),?,?,sysdate)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,BoardService.currentBoard.getNumber());
			pstmt.setString(2, ReaderService.readerInfo.getName());
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			disconn();
		}return result;
	}
	public CommentsDTO getComments(int no) {
		CommentsDTO commentsDto = null;
		try {
			conn();
			String sql = "SELECT * FROM comments where board_number = ? and num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardService.currentBoard.getNumber());
			pstmt.setInt(2, no);
			rs = pstmt.executeQuery(); //이거
			if(rs.next()) {
				commentsDto = new CommentsDTO();
				commentsDto.setBoardNumber(rs.getInt("board_number"));
				commentsDto.setNum(rs.getInt("num"));
				commentsDto.setName(rs.getString("name"));
				commentsDto.setContents(rs.getString("contents"));
				commentsDto.setDate(rs.getDate("board_date"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			disconn();
		} return commentsDto;
		}
	
	public int commentsDelete(int number) {
		int result = 0;
		try {
			conn();
			String sql = "delete from comments where num =? AND board_number =?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, BoardService.currentBoard.getNumber());
			result = pstmt.executeUpdate();
			if(result > 0) {
				String sql2 = "update comments set num = num -1 where num > ? AND board_number =?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, number);
				pstmt.setInt(2, BoardService.currentBoard.getNumber());
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
