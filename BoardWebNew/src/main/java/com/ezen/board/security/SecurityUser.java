package com.ezen.board.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.ezen.board.domain.Member;

/*
 * JPA를 기반으로 스프링 시큐리티를 적용하기 위한 클래스
 * User클래스는 UserDetails 인터페이스를 구현
 */
public class SecurityUser extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Member member;
	
	public SecurityUser(Member member) {
		//super(member.getId(), "{noop}"+member.getPassword(),
		super(member.getId(),member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
	
}
