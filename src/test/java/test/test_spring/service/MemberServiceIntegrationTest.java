package test.test_spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import test.test_spring.domain.Member;
import test.test_spring.repository.MemberRepository;

@SpringBootTest //spring container를 실행해줌
@Transactional //tset시 DB에 넣고 끝나면 rollback해줌 
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepositiry;

    @Test
    //@commit 커밋해줌
    void 회원가입(){
        //given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("Test");

        //when : 뭔가가 실행해서
        Long saveid = memberService.join(member);

        //then : 뭔가가 나와야함
        Member findmember = memberService.findOne(saveid).get();
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
    }

    @Test
    void 중복회원예외(){
        Member member1 = new Member();
        member1.setName("Me");

        Member member2 = new Member();
        member2.setName("Me");

        memberService.join(member1);

        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("존재하는 회원");

    }
}
