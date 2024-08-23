package test.test_spring.Repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import test.test_spring.domain.Member;
import test.test_spring.repository.MemoryMemberRepositiry;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepositiry repository = new MemoryMemberRepositiry();

    @AfterEach //테스트 함수 1개가 끝이 날때마다 지워져야함
    public void afterEach(){
        repository.clear_store();
    }
    
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        
        Member result = repository.findById(member.getId()).get();
        System.out.println("result : " + (result == member));

        Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
        //실무에서는 여기서 통과가 안되면 다음 단계로 못가게 막음

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        org.assertj.core.api.Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
