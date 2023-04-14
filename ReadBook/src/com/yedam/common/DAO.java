package com.yedam.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
	//DAO -> Data Access Object
	//JDBC
	//1) ojdbc를 추가
	//2) DAO
	
	//java-> DB 연결할 때 쓰는 객체
	protected Connection conn = null;
	
	//Select(조회) 결과 값을 반환 받는 객체
	//Select한 결과를 Java로 전달
	protected ResultSet rs = null;
	
	//Query문을(SQL)을 가지고 실행하는 객체
	//1) sql 문 실행해주는 객체
	protected PreparedStatement pstmt = null;
	
	//2) sql 문 실행해주는 객체
	protected Statement stmt = null;
	
	//DB 접속 정보
	Properties pro = new Properties();
	String driver = "oracle.jdbc.driver.OracleDriver"; //연결할 때 어떠한 드라이버를 활용해서 연결하겠는가?
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";//@뒤부터 중요한 내용.
	//localhost : 내 IP = 127.0.0.1. 접속할 IP
	//1521: 내가 접속할 PORT
	String id = "JavaProject";
	String pw = "1234";
	
	//DB 연결
	public void conn() {
		try {
			//1. 드라이버 로딩
			Class.forName(driver);
			//2. DB연결
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//DB 연결 해제
	public void disconn() {
		try {
			//결과 조회 하는 객체
			if(rs != null) {
				rs.close();
			}
			//sql 실행하는 객체
			if(stmt != null) {
				stmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			//db연결 하는 객체
			if(conn !=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//DB접속 정보 호출 메소드
//	private void getProperties() {
//		try {
//			FileReader resource = new FileReader("db.propreties");
//			pro.load(resource);
//			
//			driver = pro.getProperty("diver");
//			url = pro.getProperty("url");
//			id = pro.getProperty("id");
//			pw = pro.getProperty("pw");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	}

