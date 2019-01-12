package com.lec.lec21.member.dao;

import java.beans.PropertyVetoException;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lec.lec21.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;

@Repository
public class MemberDao implements IMemberDao {

	//private DriverManagerDataSource dataSource;
	//org.springframework.jdbc.datasource DriverManagerDataSource dataSource; 를 사용할 수도 있다.
	private ComboPooledDataSource dataSource;
	private JdbcTemplate template;
	
	@Autowired	
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
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
		
		//template.query()는 파라매터의 sql을 실행하고 결과로 나온 ResultSet을 RowMapper가 Member 객체로 매핑해준다!-> return값은 List<Member>
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
