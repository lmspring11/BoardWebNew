package com.ezen.board.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.board.domain.Member;
import com.ezen.board.persistence.MemberRepository;

/*
 * UserDetails를 구현한 SecurityUser 객체에 검색한 회원정보를 설정하는 클래스
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> optional = memberRepo.findById(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		} else {
			Member member = optional.get();
			
			return new SecurityUser(member);
		}
	}

}
