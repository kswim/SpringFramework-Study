package com.lec.lec21.member.dao;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lec.lec21.member.Member;
import com.mchange.v2.c3p0.DriverManagerDataSource;

@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:oraknu";
	private String userId;
	private String userPw;

	private DriverManagerDataSource dataSource;
	// org.springframework.jdbc.datasource DriverManagerDataSource dataSource; �� ����� ���� �ִ�.
	private JdbcTemplate template;
	
	private HashMap<String, Member> dbMap;
	
	private static Properties prop;
	
	public MemberDao() {
		prop = new Properties();
		
		String config = "config.properties";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = classLoader.getResource(config); 

		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}	

		userId  = prop.getProperty("userId");
		userPw  = prop.getProperty("userPw");
		
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(userId);
		dataSource.setPassword(userPw);
		
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
	@Override
	public int memberInsert(Member member) {
		int result = 0;
		String sql = "INSERT INTO member (memId, memPW, memMail) values (?, ?, ?)";
		
		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());
		
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		
		List<Member> members = null;
		
		String sql = "SELECT * FROM member WHERE memId = ? AND memPw = ?";
		
		//template.query()�� �Ķ������ sql�� �����ϰ� ����� ���� ResultSet�� RowMapper�� Member ��ü�� �������ش�!-> return���� List<Member>
		members = template.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
				
			}
		}, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				return mem;
			}
		
		});
	
		if(members.isEmpty()) return null;
		
		return members.get(0);
		
	}

	@Override
	public int memberUpdate(Member member) {
		int result = 0;
		String sql = "UPDATE member SET memPw = ?, memMail = ? WHERE memId = ?";
		
		result = template.update(sql, member.getMemPw(), member.getMemMail(), member.getMemId());
		
		return result;
		
	}

	@Override
	public int memberDelete(Member member) {
		int result = 0;
		String sql = "DELETE member WHERE memId = ? AND memPw = ?";
		
		result = template.update(sql, member.getMemId(), member.getMemPw());

		return result;
	}

}
