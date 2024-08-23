package test.test_spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import test.test_spring.domain.Member;
import test.test_spring.repository.MemoryMemberRepositiry;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepositiry memberRepositiry = new MemoryMemberRepositiry();

    @BeforeEach
    public void BeforeEach(){ 
        //dependency injection (내부에서 설정하는 것이 아닌 외부에서 설정을 해주어야하는 것)
        memberRepositiry = new MemoryMemberRepositiry();
        memberService = new MemberService(memberRepositiry);
    }


    @AfterEach //테스트 함수 1개가 끝이 날때마다 지워져야함
    public void afterEach(){
        memberRepositiry.clear_store();
    }

    @Test
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
        //memberservice.join 로직을 돌렸을떄 IllegalStateException이 터지면 테스트 성공
        // try{
        //     memberService.join(member2);
        // }
        // catch(IllegalStateException e){
        //     Assertions.assertThat(e.getMessage()).isEqualTo("존재하는 회원");
        // }

    }

    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }
}
