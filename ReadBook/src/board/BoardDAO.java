package board;

import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;

public class BoardDAO extends DAO{
	private static BoardDAO boardDao = null;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAO();
		}
		return boardDao;
	}

	public List<BoardDTO> getBoardsList() {
		List<BoardDTO> list = new ArrayList<>();
		BoardDTO board = null;
		try {
			conn();
			String sql = "SELECT * FROM board";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board.setFiction(rs.getString("fiction"));
				board.setSf(rs.getString("sf"));
				board.setScience(rs.getString("science"));
				board.setEssay(rs.getString("essay"));
				board.setPhilosophy(rs.getString("philosophy"));
				board.setPoem(rs.getString("poem"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	//게시판 등록
	public int boardGenreAdd(BoardGeDTO boardGe) {
		int result = 0;
		try {
			conn();
			String sql = "insert into board values (?,?,?,?,?)";
					//어떻게 해야할까
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardGe.getFiction());
			pstmt.setString(2, boardGe.getSf());
			pstmt.setString(3, boardGe.getScience());
			pstmt.setString(4, boardGe.getEssay());
			pstmt.setString(5, boardGe.getPhilosophy());
			pstmt.setString(5, boardGe.getPoem());
			
			result = pstmt.executeUpdate();
			//하나씩 받아오려면 어떻게 해야할까
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	public void boardPost(Board board) {
		int result = 0;
		System.out.println("제목을 입력하세요");
		
		System.out.println("내용을 입력하세요");
		
		//String sql = "INSERT INTO board (title, content) VALUES (?, ?)";
		
		try {
			conn();
			String sql = "INSERT INTO board (title, content) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
		}
	}



}
