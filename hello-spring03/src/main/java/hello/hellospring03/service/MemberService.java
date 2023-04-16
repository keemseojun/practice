package hello.hellospring03.service;

import java.util.List;
import java.util.Optional;



import hello.hellospring03.domain.Member;
import hello.hellospring03.repository.MemberRepository;
import hello.hellospring03.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	/*
	 * 회원가입
	 */
	public Long join(Member member) {
		// 같은 이름이 있는 중복 회원이 있으면 안됨
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		Optional<Member> result = memberRepository.findByName(member.getName());
		result.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/*
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}
