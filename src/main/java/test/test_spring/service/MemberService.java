package test.test_spring.service;


import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import test.test_spring.domain.Member;
import test.test_spring.repository.MemberRepository;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){ //외부에서 넣어주도록 제적
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){ //회원가입
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m ->{
            throw new IllegalStateException("존재하는 회원");
        });
    }

    public List<Member> findMembers(){ //전체회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){
        return memberRepository.findById(memberID);
    }
    
}
