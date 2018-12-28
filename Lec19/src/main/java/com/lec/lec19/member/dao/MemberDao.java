package com.lec.lec19.member.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lec.lec19.member.Member;

//@Service, @Component, @Repository annotation을 사용해서 DAO객체를 생성할 수 있음

@Repository
public class MemberDao implements IMemberDao {

	private HashMap<String, Member> dbMap;
	
	public MemberDao() {
		dbMap = new HashMap<String, Member>();
	}
	
	@Override
	public Map<String, Member> memberInsert(Member member) {
		
		dbMap.put(member.getMemId(), member);
		return dbMap;
		
	}

	@Override
	public Member memberSelect(Member member) {
		
		Member mem = dbMap.get(member.getMemId());
		return mem;
		
	}

	@Override
	public Member memberUpdate(Member member) {
		
		dbMap.put(member.getMemId(), member);
		return dbMap.get(member.getMemId());
		
	}

	@Override
	public Map<String, Member> memberDelete(Member member) {
		
		dbMap.remove(member.getMemId());
		return dbMap;
		
	}

}