package test.test_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.test_spring.repository.MemberRepository;
import test.test_spring.repository.MemoryMemberRepositiry;
import test.test_spring.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepositiry();
    }
}
