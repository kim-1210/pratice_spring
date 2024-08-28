package test.test_spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.test_spring.repository.JDBCMemberRepository;
import test.test_spring.repository.JDBCTemplateMemberRepository;
import test.test_spring.repository.MemberRepository;
import test.test_spring.repository.MemoryMemberRepositiry;
import test.test_spring.service.MemberService;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource database){
        this.dataSource = database;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JDBCMemberRepository(dataSource);
        return new JDBCTemplateMemberRepository(dataSource);
    }
}
