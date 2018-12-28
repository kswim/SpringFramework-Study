package com.lec.lec19.member.service;

import com.lec.lec19.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	void memberSearch(Member member);
	Member[] memberModify(Member member);
	void memberRemove(Member member);
}
